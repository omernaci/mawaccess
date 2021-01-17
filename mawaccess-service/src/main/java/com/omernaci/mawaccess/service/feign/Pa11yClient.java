package com.omernaci.mawaccess.service.feign;

import com.omernaci.mawaccess.common.request.TaskRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "pa11y-service")
public interface Pa11yClient {

    @PostMapping(value = "/api/pa11y/tasks")
    ResponseEntity<BaseApiResponse> createTask(@RequestBody TaskRequest request);

    @GetMapping(value = "/api/pa11y/tasks/{id}")
    ResponseEntity<?> getTask(@PathVariable String id);

    @GetMapping(value = "/api/pa11y/tasks/{id}/results")
    ResponseEntity<?> getResultList(@PathVariable String id,
                                    @RequestParam(required = false) String full);

    @PostMapping(value = "/api/pa11y/tasks/{id}/run")
    ResponseEntity<BaseApiResponse> runTask(@PathVariable String id);
}
