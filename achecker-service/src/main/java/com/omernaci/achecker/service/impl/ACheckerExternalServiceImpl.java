package com.omernaci.achecker.service.impl;

import com.omernaci.achecker.dto.model.Resultset;
import com.omernaci.achecker.dto.response.ACheckerResponse;
import com.omernaci.achecker.service.ACheckerExternalService;
import com.omernaci.achecker.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ACheckerExternalServiceImpl implements ACheckerExternalService {

    private RestTemplate restTemplate;

    @Value("${achecker.service.endpoint}")
    private String acheckerServiceEndpoint;

    @Value("${achecker.service.id}")
    private String achekerServiceId;

    private static final String OUTPUT = "REST";

    private static final String GUIDE = "WCAG2-AA";

    private static final String OFFSET = "10";

    @Autowired
    ACheckerExternalServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ACheckerResponse getResult(String url) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", achekerServiceId);
        parameters.put("output", OUTPUT);
        parameters.put("guide", GUIDE);
        parameters.put("offset", OFFSET);
        parameters.put("uri", url);

        String response = RestUtil.httpGET(restTemplate, parameters, acheckerServiceEndpoint);

        log.info("Response : {}", response);

        ACheckerResponse aCheckerResponse = new ACheckerResponse();
        if (StringUtils.isNotEmpty(response)) {
            try {
                Resultset resultset = getResultsetFromResponse(response);
                if (resultset != null) {
                    aCheckerResponse.setResultset(resultset);
                    aCheckerResponse.setReturnCode("0");
                }
            } catch (JAXBException e) {
                log.error("Unmarshall Error : {}", e.getLocalizedMessage());
                aCheckerResponse.setResultset(null);
                aCheckerResponse.setReturnCode("-1");
            }
        }

        return aCheckerResponse;
    }

    private Resultset getResultsetFromResponse(String response) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Resultset.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //unmarshaller.setProperty(UnmarshallerProperties.UNMARSHALLING_CASE_INSENSITIVE, true);
        return (Resultset) unmarshaller.unmarshal(new StringReader(response));
    }

}
