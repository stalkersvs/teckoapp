package com.tecko.api.data;

import com.mifmif.common.regex.Generex;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataProviderService {

    private static final Logger LOG = LoggerFactory.getLogger(DataProviderService.class);

    @Getter
    @Setter
    private String value;

    private final String pattern = "([a-z]{20})";

    public DataProviderService() {
        this.setValue(generateString());
    }

    /**
     * Create scheduler for generating new string every 5 seconds
     */
    @Scheduled(initialDelay=5000,fixedRate = 5000)
    private void createGenerateSchedule(){
        String oldVal = this.getValue();
        this.setValue(generateString());

        LOG.info(String.format("Scheduled event run: %s/%s", oldVal, this.getValue()));
    }

    /**
     * Generate string via dependency Generex which use regex as generate pattern
     *
     * @return {String}
     */
    private String generateString() {
        return new Generex(pattern).random();
    }
}
