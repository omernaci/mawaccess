package com.omernaci.pa11y.dto.response;

import com.omernaci.pa11y.dto.model.Resultset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse extends BaseApiResponse {

    private List<Resultset> resultsets = new ArrayList<>();

}
