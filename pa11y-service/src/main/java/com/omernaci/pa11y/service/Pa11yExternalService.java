package com.omernaci.pa11y.service;


import com.omernaci.mawaccess.common.request.ResultRequest;
import com.omernaci.mawaccess.common.request.TaskRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.common.response.ResultResponse;
import com.omernaci.mawaccess.common.response.TaskListResponse;
import com.omernaci.mawaccess.common.response.TaskResponse;

public interface Pa11yExternalService {

    BaseApiResponse createTask(TaskRequest request);

    ResultResponse getResultList(ResultRequest request);

    TaskResponse getTask(String id);

    TaskListResponse getTaskList();

    BaseApiResponse runTask(String id);

}
