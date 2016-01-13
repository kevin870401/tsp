package com.tsp.data.config;


import org.testng.annotations.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = JiveRestClientConfig.class)
public class JiveRestClientConfigTest extends AbstractTestNGSpringContextTests {
    @Test
    public void testJiveResourceDetails() throws Exception {
        JiveRestClientConfig jiveRestClientConfig= new JiveRestClientConfig();
System.out.println(jiveRestClientConfig.accessTokenUri);
    }
}