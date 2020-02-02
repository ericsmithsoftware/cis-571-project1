package com.ericss.project1.invoker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.ericss.project1.constants.ServiceConstants.KEY;
import static com.ericss.project1.constants.ServiceConstants.TITLE;

@Component
public class GoodreadsInvoker {

    @Value("${goodreads.host}")
    private String host;

    @Value("${goodreads.context.path}")
    private String contextPath;

    @Value("${goodreads.key}")
    private String key;

    public ResponseEntity<String> getBookReview(String title) {
        RestTemplate restTemplate = new RestTemplate();

        final String url = host+contextPath;

        Map<String, String> params = new HashMap<>();
        params.put(TITLE, title);
        params.put(KEY, key);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, params);
        return result;
    }
}