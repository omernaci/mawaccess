package com.omernaci.achecker.dto.model;

import lombok.Data;

@Data
public class Result {

    private String columnNum;

    private String errorSourceCode;

    private String decisionPass;

    private String lineNum;

    private String decisionFail;

    private String resultType;

    private String sequenceID;

    private String errorMsg;

}
