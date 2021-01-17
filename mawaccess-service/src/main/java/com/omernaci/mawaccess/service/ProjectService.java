package com.omernaci.mawaccess.service;

import com.omernaci.mawaccess.common.request.CreateProjectRequest;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import com.omernaci.mawaccess.common.response.ProjectListResponse;
import com.omernaci.mawaccess.common.response.ProjectResponse;

public interface ProjectService {

    BaseApiResponse createProject(CreateProjectRequest request);

    BaseApiResponse deleteProject(Long id);

    ProjectResponse getProject(Long id);

    ProjectListResponse getProjectList();

    BaseApiResponse runProject(Long id);
}
