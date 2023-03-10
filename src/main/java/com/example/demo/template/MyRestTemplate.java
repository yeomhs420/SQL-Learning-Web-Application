package com.example.demo.template;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class MyRestTemplate {
    public String getOpenData(MediaType mediaType, UriComponentsBuilder uriComponentsBuilder, HttpMethod httpMethod) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> responseEntity;

        try {
            responseEntity=restTemplate.exchange(
                    uriComponentsBuilder.build().encode(StandardCharsets.UTF_8).toUri(),
                    httpMethod,
                    request,
                    String.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        if(responseEntity.getStatusCode()==HttpStatus.OK) return responseEntity.getBody();
        else return "";
    }
}
