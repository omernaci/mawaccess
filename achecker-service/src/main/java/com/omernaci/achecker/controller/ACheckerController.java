package com.omernaci.achecker.controller;

import com.omernaci.achecker.dto.response.ACheckerResponse;
import com.omernaci.achecker.service.ACheckerExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "api/achecker")
public class ACheckerController {

    private ACheckerExternalService aCheckerExternalService;

    @Autowired
    ACheckerController(ACheckerExternalService aCheckerExternalService) {
        this.aCheckerExternalService = aCheckerExternalService;
    }

    @GetMapping
    public ResponseEntity<ACheckerResponse> getResult(@RequestParam @NotNull String url) {
        return ResponseEntity.status(HttpStatus.OK).body(aCheckerExternalService.getResult(url));
    }

}
