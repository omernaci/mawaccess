package com.omernaci.mawaccess.controller;

import com.omernaci.mawaccess.common.dto.ProjectDTO;
import com.omernaci.mawaccess.common.request.CreateProjectRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.service.ProjectService;
import com.omernaci.mawaccess.service.feign.ACheckerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/mawaccess", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class MawAccessController {

    private final ProjectService projectService;

    private final ACheckerClient aCheckerClient;

    @Autowired
    public MawAccessController(ProjectService projectService, ACheckerClient aCheckerClient) {
        this.projectService = projectService;
        this.aCheckerClient = aCheckerClient;
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse> createProject(@Valid @RequestBody CreateProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BaseApiResponse> deleteProject(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.deleteProject(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProject(id).getProjectDTO());
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getProjectList() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectList().getProjectDTOS());
    }

    @GetMapping("/{id}/run")
    public ResponseEntity<BaseApiResponse> runProject(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.runProject(id));
    }

}
