package com.omernaci.achecker.dto.model;

import lombok.Data;

@Data
public class Summary {

    private String NumOfPotentialProblems;

    private String NumOfLikelyProblems;

    private String NumOfErrors;

    private Guidelines guidelines;

    private String sessionID;

    private String status;

}
