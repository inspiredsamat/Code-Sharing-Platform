package kz.inspiredsamat.codesharingplatform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kz.inspiredsamat.codesharingplatform.model.Code;
import kz.inspiredsamat.codesharingplatform.service.CodeSharingPlatformService;

@Controller
public class CodeSharingPlatformController {
    CodeSharingPlatformService service;

    public CodeSharingPlatformController(CodeSharingPlatformService service) {
        this.service = service;
    }

    @PostMapping(path = "/api/code/new", produces = "application/json")
    @ResponseBody
    public Map<String, String> postCodeAPI(@RequestBody Code newCode) {
        return service.postNewCode(newCode);
    }

    @GetMapping("/code/new")
    public String postCode() {
        return "post_code";
    }

    @GetMapping("/code/{id}")
    public String getCodeById(@PathVariable String id, Model model) {
        Code code = service.getCodeById(id);
        model.addAttribute("code", code);
        return "code";
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public Code getCodeById(@PathVariable String id) {
        return service.getCodeById(id);
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<Code> getLatestCodesAPI() {
        return service.getLatestCodes();
    }

    @GetMapping("/code/latest")
    public String getLatestCodes(Model model) {
        List<Code> latestCodes = service.getLatestCodes();
        model.addAttribute("latestCodes", latestCodes);
        return "latest_codes";
    }
}