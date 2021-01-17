package com.omernaci.mawaccess.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateProjectRequest {

    @NotEmpty
    private String name;

    private String url;

}
