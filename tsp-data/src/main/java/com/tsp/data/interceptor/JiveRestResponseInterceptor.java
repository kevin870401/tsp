package com.tsp.data.interceptor;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
//@Slf4j
public class JiveRestResponseInterceptor implements ClientHttpRequestInterceptor {


	
	@Override	
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		
		ClientHttpResponse response = execution.execute(request, body); 
		
		//log.info("JiveRestResponseInterceptor : "+response.getBody());
		return response;
	}

}
