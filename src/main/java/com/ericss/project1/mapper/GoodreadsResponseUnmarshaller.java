package com.ericss.project1.mapper;

import com.ericss.project1.model.goodreads.GoodreadsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class GoodreadsResponseUnmarshaller {

    private static Logger LOGGER = LoggerFactory.getLogger(GoodreadsResponseUnmarshaller.class);
    private static XmlMapper xmlMapper = new XmlMapper();;

    public static GoodreadsResponse unmarshal(ResponseEntity<String> responseEntity) {
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.setDefaultUseWrapper(false);
        if(null != responseEntity && null != responseEntity.getBody()){
            return mapBookInfoList(responseEntity);
        }
        return null;
    }

    private static GoodreadsResponse mapBookInfoList(ResponseEntity<String> responseEntity) {
        GoodreadsResponse goodreadsResponse = null;
        try {
            goodreadsResponse = xmlMapper.readValue(responseEntity.getBody(), GoodreadsResponse.class);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return goodreadsResponse;
    }
}