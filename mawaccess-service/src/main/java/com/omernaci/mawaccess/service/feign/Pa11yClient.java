package com.omernaci.mawaccess.service.feign;

import com.omernaci.mawaccess.dto.response.BaseApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pa11y-service")
public interface Pa11yClient {

    @GetMapping(value = "/tasks/{id}")
    ResponseEntity<?> getTask(@PathVariable String id);

    @GetMapping(value = "/tasks/{id}/results")
    ResponseEntity<?> getResultList(@PathVariable String id,
                                                 @RequestParam(required = false) String full);

    @PostMapping(value = "/tasks/{id}/run")
    ResponseEntity<BaseApiResponse> runTask(@PathVariable String id);
}
