package com.omernaci.mawaccess.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    @JsonIgnore
    private Integer version;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date lastModifiedDate;

    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;

}
