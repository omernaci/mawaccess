package com.omernaci.pa11y.service;

import com.omernaci.pa11y.dto.request.ResultRequest;
import com.omernaci.pa11y.dto.request.TaskRequest;
import com.omernaci.pa11y.dto.response.ResultResponse;
import com.omernaci.pa11y.dto.response.TaskResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Pa11yExternalService {

    TaskResponse createTask(TaskRequest request);

    ResultResponse getResultList(ResultRequest request);

    TaskResponse getTask(String id);

    List<TaskResponse> getTaskList();

    ResponseEntity<?> runTask(String id);

}
