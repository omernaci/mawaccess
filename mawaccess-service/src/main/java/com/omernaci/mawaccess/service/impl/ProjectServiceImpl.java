package com.omernaci.mawaccess.service.impl;

import com.omernaci.mawaccess.domain.Project;
import com.omernaci.mawaccess.dto.ProjectDTO;
import com.omernaci.mawaccess.dto.request.CreateProjectRequest;
import com.omernaci.mawaccess.dto.response.BaseApiResponse;
import com.omernaci.mawaccess.dto.response.ProjectListResponse;
import com.omernaci.mawaccess.dto.response.ProjectResponse;
import com.omernaci.mawaccess.repository.ProjectRepository;
import com.omernaci.mawaccess.service.ProjectService;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public BaseApiResponse createProject(CreateProjectRequest request) {

        if (projectRepository.existsProjectByUrl(request.getUrl())) {
            return new BaseApiResponse(false, "");
        }

        Project project = new Project();
        project.setUrl(request.getUrl());
        project.setName(request.getName());

        projectRepository.save(project);

        return new BaseApiResponse(true, "success");
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
