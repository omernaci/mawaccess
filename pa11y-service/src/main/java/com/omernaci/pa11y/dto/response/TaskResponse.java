package com.omernaci.pa11y.dto.response;

import com.omernaci.pa11y.dto.TaskDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse extends BaseApiResponse {

    private TaskDto taskDto;

}
