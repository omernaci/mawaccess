package com.omernaci.achecker.service;

import com.omernaci.achecker.dto.response.ACheckerResponse;

public interface ACheckerExternalService {

    ACheckerResponse getResult(String url);

}
