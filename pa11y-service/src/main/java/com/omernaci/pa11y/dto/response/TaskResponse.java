package com.omernaci.pa11y.dto.response;

import com.omernaci.pa11y.dto.TaskDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse extends BaseApiResponse {

    private TaskDTO taskDto;

}
