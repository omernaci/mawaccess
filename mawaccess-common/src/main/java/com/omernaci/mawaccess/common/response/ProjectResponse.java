package com.omernaci.mawaccess.common.response;

import com.omernaci.mawaccess.common.dto.ProjectDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponse extends BaseApiResponse {

    private ProjectDTO projectDTO;

    public ProjectResponse() {
        super();
    }

    public ProjectResponse(boolean success, String message) {
        super(success, message);
    }

    public ProjectResponse(boolean success, String message, ProjectDTO projectDTO) {
        super(success, message);
        this.projectDTO = projectDTO;
    }
}
