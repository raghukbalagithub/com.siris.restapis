package com.siris.restapis.resttemplatetests;

import com.siris.restapis.resttemplate.GetRestTemplate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Site1Tests {

    //Logger
    private final Logger LOG = LoggerFactory.getLogger(Site1Tests.class);

    //Member Variables
    private final String TARGET_URL = "https://restful-booker.herokuapp.com";
    private final String AUTH_ENDPOINT = "/auth";
    private RestTemplate restTemplate;
    private String token;

    @BeforeClass
    public void beforeClass() {
        LOG.info("GETTING THE REST TEMPLATE OBJECT");
        this.restTemplate = new GetRestTemplate().getRestTemplate();
    }

    @AfterClass
    public void afterClass() {

    }

    @BeforeMethod
    public void fetchToken() {
        String uri = TARGET_URL.concat(AUTH_ENDPOINT);

        String data = "{\"username\": \"admin\", \"password\": \"password123\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<String>(data, headers);

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, entity,String.class);
        LOG.info("TOKEN RESPONSE BODY: {}", responseEntity.getBody());
        this.token = curateTokenResponseAndGiveToken(responseEntity.getBody());
    }

    @Test
    public void postMethod() {
        LOG.info("TESTING POST METHOD");
        LOG.info("Token: {}",this.token);
    }

    @Test
    public void getMethod() {

    }

    @Test
    public void putMethod() {

    }

    @Test
    public void deleteMethod() {

    }

    private String curateTokenResponseAndGiveToken(String tokenResponseEntity) {
        String fetchedToken = null;

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(tokenResponseEntity);
            JSONObject jsonObject = (JSONObject)obj;
            fetchedToken = jsonObject.get("token").toString();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return fetchedToken;
    }
}
