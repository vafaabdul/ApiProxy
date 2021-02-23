package com.sofi.apiproxy.service;

import com.sofi.apiproxy.exception.ServiceErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ServiceInvoker {
    private RestTemplate restTemplate;

    public  ServiceInvoker(){
        restTemplate= new RestTemplate();
        restTemplate.setErrorHandler(new ServiceErrorHandler());
    }

    public <T> ResponseEntity<T> executeGet(final String url, final HttpHeaders httpHeaders, final Class<T> type){
            return restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<>(null,httpHeaders),type);
    }
}
