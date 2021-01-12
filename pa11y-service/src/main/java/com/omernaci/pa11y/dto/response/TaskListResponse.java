package com.omernaci.pa11y.dto.response;

import com.omernaci.pa11y.dto.TaskDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskListResponse extends BaseApiResponse {

    private List<TaskDto> taskDtos;

    public TaskListResponse() {
        this.taskDtos = new ArrayList<>();
    }
}
