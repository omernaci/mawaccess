package com.omernaci.pa11y.controller;

import com.omernaci.pa11y.dto.model.Resultset;
import com.omernaci.pa11y.dto.request.ResultRequest;
import com.omernaci.pa11y.dto.request.TaskRequest;
import com.omernaci.pa11y.dto.response.ResultResponse;
import com.omernaci.pa11y.dto.response.TaskResponse;
import com.omernaci.pa11y.service.Pa11yExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/pa11y")
public class Pa11yController {

    private Pa11yExternalService pa11yExternalService;

    @Autowired
    public Pa11yController(Pa11yExternalService pa11yExternalService) {
        this.pa11yExternalService = pa11yExternalService;
    }

    /*
    *  @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTask(id));
    }*/

    @GetMapping(value = "/tasks")
    public List<TaskResponse> getTaskList() {
        return pa11yExternalService.getTaskList();
    }

    @GetMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(pa11yExternalService.getTask(id));
    }

    @PostMapping(value = "/tasks")
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        return pa11yExternalService.createTask(request);
    }

    @GetMapping(value = "/tasks/{id}/results")
    public ResultResponse getResultList(@PathVariable String id,
                                        @RequestParam(required = false) String full) {
        ResultRequest request = new ResultRequest(id, full);
        return pa11yExternalService.getResultList(request);
    }

    @PostMapping(value = "/tasks/{id}/run")
    public ResponseEntity<?> runTask(@PathVariable String id) {
        return pa11yExternalService.runTask(id);
    }

}
