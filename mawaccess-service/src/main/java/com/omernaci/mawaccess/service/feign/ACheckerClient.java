package com.omernaci.mawaccess.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@FeignClient(name = "achecker-service")
public interface ACheckerClient {

    @GetMapping("/api/achecker")
    ResponseEntity<?> getResult(@RequestParam @NotNull String url);

}
