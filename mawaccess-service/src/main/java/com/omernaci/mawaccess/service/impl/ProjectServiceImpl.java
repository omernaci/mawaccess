package com.omernaci.mawaccess.service.impl;

import com.omernaci.mawaccess.common.dto.ProjectDTO;
import com.omernaci.mawaccess.common.request.CreateProjectRequest;
import com.omernaci.mawaccess.common.request.TaskRequest;
import com.omernaci.mawaccess.common.response.ACheckerResponse;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.common.response.ProjectListResponse;
import com.omernaci.mawaccess.common.response.ProjectResponse;
import com.omernaci.mawaccess.common.xml.Resultset;
import com.omernaci.mawaccess.constant.MawAccessConstant;
import com.omernaci.mawaccess.domain.ACheckerStatus;
import com.omernaci.mawaccess.domain.ACheckerSummary;
import com.omernaci.mawaccess.domain.Project;
import com.omernaci.mawaccess.repository.ACheckerRepository;
import com.omernaci.mawaccess.repository.ProjectRepository;
import com.omernaci.mawaccess.service.ProjectService;
import com.omernaci.mawaccess.service.feign.ACheckerClient;
import com.omernaci.mawaccess.service.feign.Pa11yClient;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ACheckerClient aCheckerClient;

    private final Pa11yClient pa11yClient;

    private final ProjectRepository projectRepository;

    private final ACheckerRepository aCheckerRepository;

    @Autowired
    public ProjectServiceImpl(ACheckerClient aCheckerClient,
                              Pa11yClient pa11yClient,
                              ProjectRepository projectRepository,
                              ACheckerRepository aCheckerRepository) {
        this.aCheckerClient = aCheckerClient;
        this.pa11yClient = pa11yClient;
        this.projectRepository = projectRepository;
        this.aCheckerRepository = aCheckerRepository;
    }

    @Override
    public BaseApiResponse createProject(CreateProjectRequest request) {

        if (projectRepository.existsProjectByUrl(request.getUrl())) {
            return new BaseApiResponse(false, "");
        }

        //aCheckerClient.getResult(request.getUrl());
        createPa11yTask(request);

        Project project = new Project();
        project.setUrl(request.getUrl());
        project.setName(request.getName());

        projectRepository.save(project);

        return new BaseApiResponse(true, "success");
    }

    private void createPa11yTask(CreateProjectRequest request) {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName(request.getName());
        taskRequest.setUrl(request.getUrl());
        taskRequest.setStandard(MawAccessConstant.STANDART);
        taskRequest.setTimeout(MawAccessConstant.TIMEOUT);

        pa11yClient.createTask(taskRequest);
    }

    @Override
    public BaseApiResponse deleteProject(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            projectRepository.delete(projectOptional.get());
            return successResponse();
        }

        return new BaseApiResponse(false, "");
    }

    @Override
    public ProjectResponse getProject(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            return successProjectResponse(projectOptional.get());
        }

        return new ProjectResponse(false, "");
    }

    @Override
    public ProjectListResponse getProjectList() {
        List<Project> projects = projectRepository.findAllByOrderByIdDesc();

        if (CollectionUtils.isNotEmpty(projects)) {
            return successProjectListResponse(projects);
        }

        return new ProjectListResponse(false, "");
    }

    @Override
    public BaseApiResponse runProject(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {

           ResponseEntity<ACheckerResponse> aCheckerResponse = aCheckerClient.getResult(projectOptional.get().getUrl());

           if (aCheckerResponse.hasBody()) {
               ACheckerResponse response = aCheckerResponse.getBody();
               ACheckerSummary aCheckerSummary = new ACheckerSummary();
               Resultset resultset = response.getResultset();
               aCheckerSummary.setProject(projectOptional.get());
               aCheckerSummary.setNumOfErrors(resultset.getSummary().getNumOfErrors());
               aCheckerSummary.setNumOfPotentialProblems(resultset.getSummary().getNumOfPotentialProblems());
               aCheckerSummary.setNumOfLikelyProblems(resultset.getSummary().getNumOfLikelyProblems());
               aCheckerSummary.setSessionId(resultset.getSummary().getSessionID());
               aCheckerSummary.setStatus(ACheckerStatus.valueOf(resultset.getSummary().getStatus()));

               ACheckerSummary summary = aCheckerRepository.save(aCheckerSummary);

           }

        }


        return null;
    }

    private ProjectListResponse successProjectListResponse(List<Project> projects) {
        ProjectListResponse response = new ProjectListResponse();
        Type listType = new TypeToken<List<ProjectDTO>>() {}.getType();
        response.setProjectDTOS(new ModelMapper().map(projects, listType));
        response.setSuccess(true);
        return response;
    }

    private BaseApiResponse successResponse() {
        return new BaseApiResponse(true, "");
    }

    private ProjectResponse successProjectResponse(Project project) {
        return new ProjectResponse(true, "", new ModelMapper().map(project, ProjectDTO.class));
    }

}
