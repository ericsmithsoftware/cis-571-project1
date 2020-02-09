package com.ericss.project1.validator;

import com.ericss.project1.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public final class InputValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputValidator.class);

    public static void validateInput(Integer numBooks, List<String> bestSellerLists) {

        if(null == numBooks || numBooks == 0){
            throw new BadRequestException("Number of books must be greater than 0!");
        }
        if(null == bestSellerLists || bestSellerLists.size() == 0){
            throw new BadRequestException("You must choose at least one category!");
        }
        printInput(numBooks, bestSellerLists);
        if(bestSellerLists.size() > numBooks){
            throw new BadRequestException("Number of books must be greater than number of categories!");
        }
    }

    private static void printInput(Integer numBooks, List<String> bestSellerLists) {
        LOGGER.info("NumBooks: {}", numBooks);
        LOGGER.info("Best seller lists:");
        for(String list : bestSellerLists){
            LOGGER.info(list);
        }
    }
}