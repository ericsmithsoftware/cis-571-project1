package com.ericss.project1.invoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.ericss.project1.constants.ServiceConstants.KEY;

@Component
public class GoodreadsInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodreadsInvoker.class);

    @Value("${goodreads.host}")
    private String host;

    @Value("${goodreads.context.path}")
    private String contextPath;

    @Value("${goodreads.key}")
    private String key;

    public ResponseEntity<String> getBookReview(String title) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(host+contextPath)
                .queryParam(KEY, key)
                .queryParam("title", title);
        LOGGER.debug("Getting book info from Goodreads by title: {}", title);
        return new RestTemplate().getForEntity(builder.build().toUri(), String.class);

    }
}