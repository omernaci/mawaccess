package com.omernaci.achecker.controller;

import com.omernaci.achecker.dto.response.ACheckerResponse;
import com.omernaci.achecker.service.ACheckerExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/achecker")
public class ACheckerController {

    private ACheckerExternalService aCheckerExternalService;

    @Autowired
    ACheckerController(ACheckerExternalService aCheckerExternalService) {
        this.aCheckerExternalService = aCheckerExternalService;
    }

    @GetMapping
    public ACheckerResponse getResult(@RequestParam String url) {
        return aCheckerExternalService.getResult(url);
    }

}
