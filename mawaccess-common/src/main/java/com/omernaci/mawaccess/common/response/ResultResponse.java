package com.omernaci.mawaccess.common.response;

import com.omernaci.mawaccess.common.dto.ResultDTO;
import com.omernaci.mawaccess.common.response.BaseApiResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultResponse extends BaseApiResponse {

    private List<ResultDTO> resultDTOS;

    public ResultResponse() {
        this.resultDTOS = new ArrayList<>();
    }
}
