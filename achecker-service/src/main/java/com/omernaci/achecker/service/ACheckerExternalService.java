package com.omernaci.achecker.service;

import com.omernaci.mawaccess.common.response.ACheckerResponse;

public interface ACheckerExternalService {

    ACheckerResponse getResult(String url);

}
