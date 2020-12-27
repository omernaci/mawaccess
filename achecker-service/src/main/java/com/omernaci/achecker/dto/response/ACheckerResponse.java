package com.omernaci.achecker.dto.response;

import com.omernaci.achecker.dto.model.Resultset;
import lombok.Data;

@Data
public class ACheckerResponse {

    private Resultset resultset;

    private String returnCode;

}
