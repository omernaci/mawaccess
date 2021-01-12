package com.omernaci.pa11y.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Slf4j
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            String responseAsString = toString(response.getBody());
            log.error("ResponseBody: {}", responseAsString);

            throw new RestTemplateException(response.getStatusCode(), responseAsString);
        }

    }

    String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
