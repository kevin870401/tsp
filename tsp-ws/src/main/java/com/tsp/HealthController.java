package com.tsp;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.Map;
import java.util.ResourceBundle;

import static com.tsp.ControllerUtils.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HealthController {

    @Inject
    private RestTemplate restTemplate;

    @Value("${ws.factfinder.url}")
    private String factfinderUrl;

//    @Value("${ws.crypto.url}")
//    private String cryptoUrl;
//
//    @Value("${ws.org.url}")
//    private String orgUrl;

    @Inject
    private HttpEntity<String> jsonRequestEntity;

//    @Inject
//    private CryptoServiceManager cryptoManager;
//
//    @Inject
//    private OrganizationManager orgManager;

    private final static String RESOURCE_BUNDLE_NAME = "mvnbuild";
    private final static String VERSION = ResourceBundle
            .getBundle(RESOURCE_BUNDLE_NAME).getString("project.version") + " [" +
            ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME).getString("build.timestamp") + "]";

    @RequestMapping(value = "/version", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public String getVersion() {
        return VERSION;
    }

    @RequestMapping(value = "/dependencies", method = GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getDependencies() {
        Map<String, String> ff = getDependency("Fact Finder", factfinderUrl, getRestVersion(factfinderUrl));
//        Map<String, String> crypto = getDependency("Cryto WS", cryptoUrl, cryptoManager.getVersion());
//        Map<String, String> org = getDependency("Org WS", orgUrl, orgManager.getVersion());
        return ok("dependencies", new Map[] {ff});
    }

    private String getRestVersion(String url) {
        return restTemplate.exchange(url + "/version", HttpMethod.GET, jsonRequestEntity, String.class).getBody();
    }

    private Map<String, String> getDependency(String name, String url, String version) {
        return new ImmutableMap.Builder<String, String>()
                .put("serviceName", name)
                .put("version", version)
                .put("endpoint", url)
                .build();
    }
}