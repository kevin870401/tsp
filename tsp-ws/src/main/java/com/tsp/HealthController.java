package com.tsp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ResourceBundle;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HealthController {

    @Inject
    private RestTemplate restTemplate;

    @Inject
    private HttpEntity<String> jsonRequestEntity;

    private final static String RESOURCE_BUNDLE_NAME = "mvnbuild";
    private final static String VERSION = ResourceBundle
            .getBundle(RESOURCE_BUNDLE_NAME).getString("project.version") + " [" +
            ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME).getString("build.timestamp") + "]";

    @RequestMapping(value = "/version", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public String getVersion() {
        return VERSION;
    }

    private String getRestVersion(String url) {
        return restTemplate.exchange(url + "/version", HttpMethod.GET, jsonRequestEntity, String.class).getBody();
    }

 
}