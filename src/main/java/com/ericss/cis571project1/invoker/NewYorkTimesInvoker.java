package com.ericss.cis571project1.invoker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericss.cis571project1.constants.ServiceConstants.API_KEY;

@Component
public class NewYorkTimesInvoker {

    @Value("${ny.times.host}")
    private String host;

    @Value("${ny.times.base.path}")
    private String basePath;

    @Value("${ny.times.context.path}")
    private String contextPath;

    @Value("${ny.times.api.key}")
    private String apiKey;

    public ResponseEntity<String> getBestSellerList(String bestSellerList) {
        RestTemplate restTemplate = new RestTemplate();

        final String url = host+basePath+contextPath+bestSellerList;
        System.out.println("URL: " + url);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
            .queryParam(API_KEY, apiKey);

        System.out.println("Full url: " + builder.build().toUri().toString());

        ResponseEntity<String> response = restTemplate.getForEntity(builder.build().toUri(), String.class);
        return response;
    }
}