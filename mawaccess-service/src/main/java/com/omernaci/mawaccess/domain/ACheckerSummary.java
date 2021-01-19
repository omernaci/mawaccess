package com.omernaci.mawaccess.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class ACheckerSummary extends BaseEntity {

    private String sessionId;

    private ACheckerStatus status;

    private int numOfPotentialProblems;

    private int numOfLikelyProblems;

    private int numOfErrors;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Project project;

}
