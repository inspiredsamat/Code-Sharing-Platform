package kz.inspiredsamat.codesharingplatform.service;

import java.util.List;
import java.util.Map;

import kz.inspiredsamat.codesharingplatform.model.Code;

public interface CodeSharingPlatformService {
    List<Code> getLatestCodes();

    Map<String, String> postNewCode(Code newCode);

    Code getCodeById(String id);

    void deleteById(String id);

    Code updateCode(Code code);
}