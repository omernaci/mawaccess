package com.omernaci.pa11y.service.impl;

import com.omernaci.pa11y.dto.TaskDto;
import com.omernaci.pa11y.dto.model.Resultset;
import com.omernaci.pa11y.dto.request.ResultRequest;
import com.omernaci.pa11y.dto.request.TaskRequest;
import com.omernaci.pa11y.dto.response.ResultResponse;
import com.omernaci.pa11y.dto.response.TaskResponse;
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

import java.util.ArrayList;
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
    public TaskResponse createTask(TaskRequest request) {

        ResponseEntity<TaskResponse> response = restTemplate
                .postForEntity(pa11yServiceEndpoint, request, TaskResponse.class);


        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("Response : {}", response);
        } else {
            log.error("ERRRRR");
        }

        return response.getBody();
    }

    @Override
    public ResultResponse getResultList(ResultRequest request) {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", request.getId());

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(pa11yServiceEndpoint.concat("/{id}/results"))
                .queryParam("full", request.getFull());

        ResponseEntity<Resultset> response = restTemplate
                .getForEntity(builder.buildAndExpand(urlParams).toUri(), Resultset.class);

        return null;
    }

    @Override
    public TaskResponse getTask(String id) {
        TaskResponse taskResponse = new TaskResponse();

        ResponseEntity<TaskDto> response = restTemplate
                .getForEntity(pa11yServiceEndpoint.concat("/{id}"), TaskDto.class, id);

        if (response != null && response.hasBody()) {
            taskResponse.setTaskDto(new ModelMapper().map(response.getBody(), TaskDto.class));
            taskResponse.setSuccess(true);
        } else {
            taskResponse.setSuccess(false);
        }

        return taskResponse;
    }

    @Override
    public List<TaskResponse> getTaskList() {
        List<TaskResponse> tasks = new ArrayList<>();
        ResponseEntity<List<TaskResponse>> response = restTemplate.exchange(
                pa11yServiceEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TaskResponse>>() {});

        if(response != null && response.hasBody()){
            tasks = response.getBody();
        }

        return tasks;
    }

    @Override
    public ResponseEntity<?> runTask(String id) {

        final Map<String, String> variables = new HashMap<>();
        variables.put("id", id);

        ResponseEntity<Void> response = restTemplate
                .postForEntity(pa11yServiceEndpoint.concat("/{id}/run"), null, Void.class, variables);

        if (response != null && response.getStatusCode().is2xxSuccessful())
            return new ResponseEntity<>(null, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
