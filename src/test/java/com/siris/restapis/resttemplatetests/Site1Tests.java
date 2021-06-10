package com.siris.restapis.resttemplatetests;

import com.siris.restapis.resttemplate.RestTemplateExecutor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import org.testng.annotations.*;

public class Site1Tests {

    //Logger
    private final Logger LOG = LoggerFactory.getLogger(Site1Tests.class);

    //Member Variables
    private final String TARGET_URL = "https://restful-booker.herokuapp.com";
    private final String AUTH_ENDPOINT = "/auth";
    private RestTemplate restTemplate;
    private String token;

    @BeforeTest
    public void beforeTest() {
        LOG.info("IN BEFORE TEST");
    }

    @BeforeClass(alwaysRun = true)
    public void fetchToken() {
        LOG.info("IN BEFORE CLASS");
        LOG.info("GETTING THE REST TEMPLATE OBJECT");
        this.restTemplate = new RestTemplateExecutor().getRestTemplate();

        LOG.info("GETTING THE AUTHENTICATION TOKEN");
        String uri = TARGET_URL.concat(AUTH_ENDPOINT);

        String data = "{\"username\": \"admin\", \"password\": \"password123\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<String>(data, headers);

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, entity,String.class);
        LOG.info("TOKEN RESPONSE BODY: {}", responseEntity.getBody());
        this.token = curateTokenResponseAndGiveToken(responseEntity.getBody());
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        LOG.info("IN BEFORE METHOD");
    }

    @Test(alwaysRun = true)
    public void postMethod() {
        LOG.info("TESTING POST METHOD");
    }

    @Test
    public void getAllBookingIDs() {
        LOG.info("TESTING GET METHOD");

        String uri = TARGET_URL.concat("/booking");

        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(uri, String.class);

        LOG.info("TOKEN RESPONSE BODY: {}", responseEntity.getBody());

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(responseEntity.getBody());
            JSONArray jsonArray = (JSONArray)obj;
            for(Object obj1 : jsonArray) {
                LOG.info(obj1.toString());
                JSONObject obj2 = (JSONObject)obj1;
                String id = obj2.get("bookingid").toString();
                LOG.info("Details for bookingid: {}", id);
                getBookingIDDetails(id);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Test(dependsOnMethods = "postMethod")
    public void putMethod() {
        LOG.info("TESTING THE PUT METHOD");
    }

    @Test
    public void deleteMethod() {
        LOG.info("TESTING THE DELETE METHOD");
    }

    private void getBookingIDDetails(String id) {
        String uri = TARGET_URL.concat("/booking/{bookingid}");
        //uri = uri.concat("/").concat(bookingid);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity(headers);

        String bookingid = id;
        ResponseEntity<String> responseEntity = this.restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, bookingid);
        LOG.info("TOKEN RESPONSE BODY: {}", responseEntity.getBody());
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

    @AfterClass()
    public void afterClass() {
        LOG.info("IN AFTER CLASS");
    }
}
