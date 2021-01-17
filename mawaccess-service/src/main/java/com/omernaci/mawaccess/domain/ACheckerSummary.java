package com.omernaci.mawaccess.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table
public class ACheckerSummary extends BaseEntity {

    private String sessionId;

    private ACheckerStatus status;

    private int numOfPotentialProblems;

    private int numOfLikelyProblems;

    private int numOfErrors;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
