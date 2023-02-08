package com.tecko.api.shared;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeckoRestTemplate {

    private final RestTemplate restTemplate;

    public TeckoRestTemplate() {
        restTemplate= new RestTemplate();
    }


    private HttpEntity getEntity(){
        return new HttpEntity<String>(new HttpHeaders());
    }


    public <T> T getCall(String url, Class<T> className){
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, getEntity(), className);

        return responseEntity.getBody();
    }

    public void putCall(String url, Object data){
         restTemplate.put(url, data);
    }
}
