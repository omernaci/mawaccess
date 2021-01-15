package com.omernaci.mawaccess.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Result extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
