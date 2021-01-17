package com.omernaci.mawaccess.common.response;

import com.omernaci.mawaccess.common.dto.TaskDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse extends BaseApiResponse {

    private TaskDTO taskDto;

}
