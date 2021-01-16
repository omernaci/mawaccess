package com.omernaci.mawaccess.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApiResponse {

    private boolean success;

    private String message;

    public BaseApiResponse() {
    }

    public BaseApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}