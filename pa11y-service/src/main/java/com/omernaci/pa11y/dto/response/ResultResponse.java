package com.omernaci.pa11y.dto.response;

import com.omernaci.pa11y.dto.ResultDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultResponse extends BaseApiResponse {

    private List<ResultDto> resultDtos;

    public ResultResponse() {
        this.resultDtos = new ArrayList<>();
    }
}
