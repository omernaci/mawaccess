package com.omernaci.achecker.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class RestUtil {

    private RestUtil() { }

    private static String buildLineOfParameters(Map<String, String> parameters) {
        return buildLineOfParameters(parameters, true);
    }

    private static String buildLineOfParameters(Map<String, String> parameters, boolean useUrlTemplateFormat) {
        StringBuilder parametersLine = new StringBuilder();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (parametersLine.length() > 0) {
                parametersLine.append("&");
            }
            parametersLine.append(entry.getKey()).append("=").append(getParameterValue(useUrlTemplateFormat, entry));
        }

        return parametersLine.length() > 0 ? ("?" + parametersLine.toString()) : "";
    }

    public static String httpGET(RestTemplate client, Map<String, String> parameters, String endpoint) {
        long startTime = System.currentTimeMillis();

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        log.info("GET endpoint {} with parameters {}", endpoint, parameters);

        String response = client.exchange(endpoint + buildLineOfParameters(parameters), HttpMethod.GET, entity,
                String.class, parameters).getBody();

        logOperation(HttpMethod.GET.name(), startTime, response, endpoint);

        return response;
    }

    public static String httpPOST(RestTemplate client, Object requestBody, Map<String, String> parameters,
                                  String endpoint, String contentType) {
        return sendBody(client, HttpMethod.POST, requestBody, parameters, endpoint, contentType);
    }

    public static String httpPUT(RestTemplate client, Object requestBody, Map<String, String> parameters,
                                 String endpoint, String contentType) {
        return sendBody(client, HttpMethod.PUT, requestBody, parameters, endpoint, contentType);
    }

    private static String sendBody(RestTemplate client, HttpMethod method, Object requestBody,
                                   Map<String, String> parameters, String endpoint, String contentType) {

        long startTime = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, new HttpHeaders());

        if (log.isDebugEnabled()) {
            log.debug("{} endpoint {} with parameters {} and body {}", method.name(),
                    endpoint, parameters, requestBody);
        } else {
            log.info("{} endpoint {} with parameters {}", method.name(), endpoint,
                    parameters);
        }

        String response = client
                .exchange(endpoint + buildLineOfParameters(parameters), method, entity, String.class, parameters)
                .getBody();

        logOperation(method.name(), startTime, response, endpoint);

        return response;
    }

    private static void logOperation(String operation, long startTime, String response,
                                     String objectName) {
        if (log.isDebugEnabled()) {
            log.debug("{} endpoint {} returned response with data {}", operation, response);
        } else {
            log.info("{} endpoint {} returned response", operation);
        }
        log.info("Elapsed time {} milliseconds for objectName: {}", (System.currentTimeMillis() - startTime),
                objectName);
    }

    private static String getParameterValue(boolean useUrlTemplateFormat, Map.Entry<String, String> entry) {
        return useUrlTemplateFormat ? ("{" + entry.getKey() + "}") : entry.getValue();
    }


}
