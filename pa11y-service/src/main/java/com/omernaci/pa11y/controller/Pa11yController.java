package com.omernaci.pa11y.controller;

import com.omernaci.mawaccess.common.request.ResultRequest;
import com.omernaci.mawaccess.common.request.TaskRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.common.response.ResultResponse;
import com.omernaci.mawaccess.common.response.TaskListResponse;
import com.omernaci.mawaccess.common.response.TaskResponse;
import com.omernaci.pa11y.service.Pa11yExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "api/pa11y")
public class Pa11yController {

    private Pa11yExternalService pa11yExternalService;

    @Autowired
    public Pa11yController(Pa11yExternalService pa11yExternalService) {
        this.pa11yExternalService = pa11yExternalService;
    }

    @GetMapping(value = "/tasks")
    public ResponseEntity<TaskListResponse> getTaskList() {
        return ResponseEntity.status(HttpStatus.OK).body(pa11yExternalService.getTaskList());
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(pa11yExternalService.getTask(id));
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<BaseApiResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pa11yExternalService.createTask(request));
    }

    @GetMapping(value = "/tasks/{id}/results")
    public ResponseEntity<ResultResponse> getResultList(@PathVariable String id,
                                                        @RequestParam(required = false) String full) {
        return ResponseEntity.status(HttpStatus.OK).body(pa11yExternalService
                .getResultList(new ResultRequest(id, full)));
    }

    @PostMapping(value = "/tasks/{id}/run")
    public ResponseEntity<BaseApiResponse> runTask(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(pa11yExternalService.runTask(id));
    }

}
