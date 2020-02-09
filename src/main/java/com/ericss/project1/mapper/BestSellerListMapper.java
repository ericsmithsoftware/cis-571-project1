package com.ericss.project1.mapper;

import com.ericss.project1.model.nytimes.BestSellerList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

public final class BestSellerListMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static BestSellerList map(ResponseEntity<String> responseEntity) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(null != responseEntity && null != responseEntity.getBody()){
            return mapBestSellerList(responseEntity);
        }
        return null;
    }

    private static BestSellerList mapBestSellerList(ResponseEntity<String> responseEntity) {
        BestSellerList bestSellerList = null;
        try {
            bestSellerList = objectMapper.readValue(responseEntity.getBody(), BestSellerList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bestSellerList;
    }
}