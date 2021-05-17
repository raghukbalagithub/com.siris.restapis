package com.siris.restapis.resttemplatetests;

import com.siris.restapis.resttemplate.GetRestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.RestTemplate;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Site1Tests {

    //Logger
    private final Logger LOG = LoggerFactory.getLogger(Site1Tests.class);
    private final String TARGET_URL = "http://<website-to-test>";

    private RestTemplate restTemplate;

    @BeforeClass
    public void beforeClass() {
        LOG.info("GETTING THE REST TEMPLATE OBJECT");
        this.restTemplate = new GetRestTemplate().getRestTempalte();
    }

    @AfterClass
    public void afterClass() {

    }

    @Test
    public void postMethod() {
        LOG.info("TESTING POST METHOD");
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
}
