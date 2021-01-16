package com.omernaci.mawaccess.service;

import com.omernaci.mawaccess.dto.request.CreateProjectRequest;
import com.omernaci.mawaccess.dto.response.BaseApiResponse;
import com.omernaci.mawaccess.dto.response.ProjectListResponse;
import com.omernaci.mawaccess.dto.response.ProjectResponse;

public interface ProjectService {

    BaseApiResponse createProject(CreateProjectRequest request);

    BaseApiResponse deleteProject(Long id);

    ProjectResponse getProject(Long id);

    ProjectListResponse getProjectList();
}
