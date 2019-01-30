package com.maltem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.maltem.model.RequestDetailMessage;


public class UpdationTask implements Callable<RequestDetailMessage>{
	
	@Value("${api.url}")
	private String allNotificationsApiURL;    

	RestTemplate restTemplate;
	
	RequestDetailMessage requestDetailMessage;
	
	
	
	

	public UpdationTask(RestTemplate restTemplate, RequestDetailMessage requestDetailMessage) {
		super();
		this.restTemplate = restTemplate;
		this.requestDetailMessage = requestDetailMessage;
	}





	@Override
	public RequestDetailMessage call() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		List<MediaType> mediaType = new ArrayList<MediaType>();
		mediaType.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(mediaType);
		HttpEntity<?> requestEntity = new HttpEntity<>(requestDetailMessage, httpHeaders);
		
		ResponseEntity<RequestDetailMessage> responseEntity = restTemplate.exchange("http://localhost:8085/maltemapp/service", HttpMethod.POST, 
				requestEntity, RequestDetailMessage.class);
		
		
		if (responseEntity.getStatusCode()==HttpStatus.OK){
			return requestDetailMessage;
		}
		return new RequestDetailMessage();
	
	}

}
