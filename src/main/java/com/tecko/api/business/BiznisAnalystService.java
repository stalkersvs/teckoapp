
package com.tecko.api.business;

import com.tecko.api.data.DataPostRequest;
import com.tecko.api.shared.TeckoRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BiznisAnalystService {

    private static final Logger LOG = LoggerFactory.getLogger(BiznisAnalystService.class);

    @Autowired
    TeckoRestTemplate TeckoRestTemplate;

    public Matcher getMatcher(String str, String regex){
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(str);
    }

    public String getDataValue(){
        String str = TeckoRestTemplate.getCall(
                "http://localhost:8080/data/",
                String.class
        );

        LOG.info(String.format("Get data value from dataService: %s", str));

        return str;
    }

    public Long getAlphabetCount(String str){
        Matcher matcher = getMatcher(str, "[aeiouAEIOU]");
        return matcher.results().count();
    }


    private void convertLowerCaseToUpper(String str){
        StringBuilder newStr = new StringBuilder(str);
        Matcher matcher = getMatcher(str, "[aeiou]");

        while (matcher.find()){
            char character = str.charAt(matcher.start());
            LOG.info(String.format("Converting: %s", character ));
            newStr.setCharAt(
                    matcher.start(),
                    Character.toUpperCase(character)
            );
        }

        if(!str.contentEquals(newStr)){
            DataPostRequest dataPostRequest = new DataPostRequest();
            dataPostRequest.setNewString(newStr.toString());

            TeckoRestTemplate.putCall(
                    "http://localhost:8080/data/update",
                    dataPostRequest
            );
        } else {
            LOG.info("Skipping update.");
        }
    }

    public Long getData(){

        String str = getDataValue();

        this.convertLowerCaseToUpper(str);

        return this.getAlphabetCount(str);
    }
}
