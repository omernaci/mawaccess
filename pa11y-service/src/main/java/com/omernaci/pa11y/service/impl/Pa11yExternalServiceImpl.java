package com.omernaci.pa11y.service.impl;

import com.omernaci.mawaccess.common.dto.ResultDTO;
import com.omernaci.mawaccess.common.dto.TaskDTO;
import com.omernaci.mawaccess.common.request.ResultRequest;
import com.omernaci.mawaccess.common.request.TaskRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.common.response.ResultResponse;
import com.omernaci.mawaccess.common.response.TaskListResponse;
import com.omernaci.mawaccess.common.response.TaskResponse;
import com.omernaci.pa11y.service.Pa11yExternalService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Pa11yExternalServiceImpl implements Pa11yExternalService {

    private RestTemplate restTemplate;

    @Value("${pa11y.service.endpoint}")
    private String pa11yServiceEndpoint;

    @Autowired
    public Pa11yExternalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BaseApiResponse createTask(TaskRequest request) {
        BaseApiResponse response = new BaseApiResponse();

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(pa11yServiceEndpoint, request, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            log.info("Response : {}", response);
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public ResultResponse getResultList(ResultRequest request) {
        ResultResponse response = new ResultResponse();

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", request.getId());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(pa11yServiceEndpoint.concat("/{id}/results"))
                .queryParam("full", request.getFull());

        ResponseEntity<List<ResultDTO>> responseEntity = restTemplate.exchange(
                builder.buildAndExpand(urlParams).toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            response.setResultDTOS(responseEntity.getBody());
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public TaskResponse getTask(String id) {
        TaskResponse taskResponse = new TaskResponse();

        ResponseEntity<TaskDTO> response = restTemplate
                .getForEntity(pa11yServiceEndpoint.concat("/{id}"), TaskDTO.class, id);

        if (response != null && response.hasBody()) {
            taskResponse.setTaskDto(new ModelMapper().map(response.getBody(), TaskDTO.class));
            taskResponse.setSuccess(true);
        } else {
            taskResponse.setSuccess(false);
        }

        return taskResponse;
    }

    @Override
    public TaskListResponse getTaskList() {
        TaskListResponse response = new TaskListResponse();

        ResponseEntity<List<TaskDTO>> responseEntity = restTemplate.exchange(
                pa11yServiceEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        if(responseEntity != null && responseEntity.hasBody()){
            response.setTaskDTOS(responseEntity.getBody());
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public BaseApiResponse runTask(String id) {
        BaseApiResponse response = new BaseApiResponse();

        final Map<String, String> variables = new HashMap<>();
        variables.put("id", id);

        ResponseEntity<Void> responseEntity = restTemplate
                .postForEntity(pa11yServiceEndpoint.concat("/{id}/run"), null, Void.class, variables);

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }

        return response;
    }
}
