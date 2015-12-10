package com.tsp.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsp.data.JiveRestClient;
import com.tsp.service.JiveRestService;
import com.tsp.service.JiveRestServiceImpl;

@Configuration
public class JiveRestServiceConfig {

	@Autowired
	JiveRestClient jiveRestClient;

	// TODO discuss if we want to directly expose this low level external
	// service as a bean which can be accessed by class in controller level

	@Bean
	public JiveRestService jiveRestService() {
		JiveRestServiceImpl jiveRestService = new JiveRestServiceImpl();
		jiveRestService.setJiveRestClient(jiveRestClient);
		return jiveRestService;
	}
}
