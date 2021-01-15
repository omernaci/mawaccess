package com.omernaci.mawaccess.controller;

import com.omernaci.mawaccess.service.feign.ACheckerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/mawaccess")
public class MawAccessController {

    private final ACheckerClient aCheckerClient;

    @Autowired
    public MawAccessController(ACheckerClient aCheckerClient) {
        this.aCheckerClient = aCheckerClient;
    }

    @GetMapping
    public ResponseEntity<?> getAchecker(@RequestParam String url) {
        return aCheckerClient.getResult(url);
    }

}
