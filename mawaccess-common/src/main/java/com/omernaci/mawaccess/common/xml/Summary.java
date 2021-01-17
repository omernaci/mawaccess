package com.omernaci.mawaccess.common.xml;

import lombok.Data;

@Data
public class Summary {

    private int numOfPotentialProblems;

    private int numOfLikelyProblems;

    private int numOfErrors;

    private Guidelines guidelines;

    private String sessionID;

    private String status;

}
