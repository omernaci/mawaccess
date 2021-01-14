package com.omernaci.achecker.dto.xml;

import lombok.Data;

@Data
public class Summary {

    private int NumOfPotentialProblems;

    private int NumOfLikelyProblems;

    private int NumOfErrors;

    private Guidelines guidelines;

    private String sessionID;

    private String status;

}
