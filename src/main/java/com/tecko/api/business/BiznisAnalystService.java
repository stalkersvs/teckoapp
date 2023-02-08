
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

    private final TeckoRestTemplate teckoRestTemplate;

    @Autowired
    public BiznisAnalystService(TeckoRestTemplate teckoRestTemplate) {
        this.teckoRestTemplate = teckoRestTemplate;
    }

    /**
     * Create regex matcher for work
     *
     * @param str - generated string
     * @param regex - regex for match
     * @return {Matcher}
     */
    public Matcher getMatcher(String str, String regex){
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(str);
    }

    /**
     * Make call to other service and retrieve generated string
     *
     * @return {String}
     */
    public String getDataValue(){
        String str = teckoRestTemplate.getCall(
                "http://localhost:8080/data/",
                String.class
        );

        LOG.info(String.format("Get data value from dataService: %s", str));

        return str;
    }

    /**
     * Calculate vowels in word, match Capital and Lowercase character as well
     *
     * @param str - generated string
     * @return {Long}
     */
    public Long getAlphabetCount(String str){
        Matcher matcher = getMatcher(str, "[aeiouAEIOU]");
        return matcher.results().count();
    }


    /**
     * Convert lowercase vowels to uppercase and update word via put call
     *
     * @param str - generated string
     */
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

            teckoRestTemplate.putCall(
                    "http://localhost:8080/data/update",
                    dataPostRequest
            );
        } else {
            LOG.info("Skipping update.");
        }
    }

    /**
     * Retrieve word, convert to uppercase vowels and return them count
     *
     * @return {Long}
     */
    public Long getData(){

        String str = getDataValue();

        this.convertLowerCaseToUpper(str);

        return this.getAlphabetCount(str);
    }
}
