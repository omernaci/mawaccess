package com.omernaci.pa11y.service;

import com.omernaci.pa11y.dto.request.ResultRequest;
import com.omernaci.pa11y.dto.request.TaskRequest;
import com.omernaci.pa11y.dto.response.BaseApiResponse;
import com.omernaci.pa11y.dto.response.ResultResponse;
import com.omernaci.pa11y.dto.response.TaskListResponse;
import com.omernaci.pa11y.dto.response.TaskResponse;

public interface Pa11yExternalService {

    BaseApiResponse createTask(TaskRequest request);

    ResultResponse getResultList(ResultRequest request);

    TaskResponse getTask(String id);

    TaskListResponse getTaskList();

    BaseApiResponse runTask(String id);

}
