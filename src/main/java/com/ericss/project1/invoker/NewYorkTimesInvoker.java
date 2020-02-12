package com.ericss.project1.invoker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.ericss.project1.constants.ServiceConstants.API_KEY;

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

    public ResponseEntity<String> getBestSellerList(String listName) {
        final String url = host + basePath + contextPath + listName;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam(API_KEY, apiKey);

        return new RestTemplate().getForEntity(builder.build().toUri(), String.class);
    }
}