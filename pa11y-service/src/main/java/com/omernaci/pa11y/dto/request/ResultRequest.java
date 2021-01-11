package com.omernaci.pa11y.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequest {

    private String id;

    private String full;

    public ResultRequest() {
    }

    public ResultRequest(String id, String full) {
        this.id = id;
        this.full = full;
    }
}
