package com.omernaci.mawaccess.common.response;

import com.omernaci.mawaccess.common.dto.TaskDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskListResponse extends BaseApiResponse {

    private List<TaskDTO> taskDTOS;

    public TaskListResponse() {
        this.taskDTOS = new ArrayList<>();
    }
}
