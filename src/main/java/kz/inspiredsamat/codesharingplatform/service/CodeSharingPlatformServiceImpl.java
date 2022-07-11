package kz.inspiredsamat.codesharingplatform.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kz.inspiredsamat.codesharingplatform.exception.CodeNotFoundException;
import kz.inspiredsamat.codesharingplatform.model.Code;
import kz.inspiredsamat.codesharingplatform.repository.CodeSharingPlatformRepository;

@Service
public class CodeSharingPlatformServiceImpl implements CodeSharingPlatformService {
    private final int SIZE_OF_LATEST_CODES = 10;

    private final CodeSharingPlatformRepository repository;

    public CodeSharingPlatformServiceImpl(CodeSharingPlatformRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Code> getLatestCodes() {
        List<Code> latestCodes = new ArrayList<>();
        List<Code> database = repository.findAll();
        int sizeOfDatabase = database.size();
        System.out.println(sizeOfDatabase);
        int size = Math.min(sizeOfDatabase, SIZE_OF_LATEST_CODES);

        for (int i = sizeOfDatabase - 1; sizeOfDatabase >= size && i >= sizeOfDatabase - size; i--) {
            Code currentCode = database.get(i);
            if (currentCode.getViews() > 0 || currentCode.getTime() > 0) {
                size++;
            } else {
                latestCodes.add(currentCode);
            }
        }

        return latestCodes;
    }

    @Override
    public Map<String, String> postNewCode(Code newCode) {
        Code code = new Code(newCode.getCode(), newCode.getTime(),
                newCode.getViews());
        repository.save(code);
        System.out.println(code);
        return Map.of("id", code.getId());
    }

    @Override
    public Code getCodeById(String id) {
        Code code = repository.findById(id).orElseThrow(() -> new CodeNotFoundException());
        if (code.isRestrictedByTime()) {
            long remainingTime = Duration.between(LocalTime.now(), code.getExpirationTime()).getSeconds();
            if (remainingTime <= 0) {
                repository.deleteById(id);
                throw new CodeNotFoundException();
            }
            code.setTime(remainingTime);
        }

        if (code.isRestrictedByViews()) {
            if (code.getViews() == 0) {
                repository.deleteById(id);
                throw new CodeNotFoundException();
            }
            code.setViews(code.getViews() - 1);
        }
        code = updateCode(code);
        return code;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Code updateCode(Code code) {
        return repository.save(code);
    }
}