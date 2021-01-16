package com.omernaci.mawaccess.dto.response;

import com.omernaci.mawaccess.dto.ProjectDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectListResponse extends BaseApiResponse {

    private List<ProjectDTO> projectDTOS;

    public ProjectListResponse() {
        projectDTOS = new ArrayList<>();
    }

    public ProjectListResponse(List<ProjectDTO> projectDTOS) {
        this.projectDTOS = projectDTOS;
    }

    public ProjectListResponse(boolean success, String message) {
        super(success, message);
    }

    public ProjectListResponse(boolean success, String message, List<ProjectDTO> projectDTOS) {
        super(success, message);
        this.projectDTOS = projectDTOS;
    }

}
