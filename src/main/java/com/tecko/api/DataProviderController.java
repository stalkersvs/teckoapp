package com.tecko.api;

import com.tecko.api.data.DataPostRequest;
import com.tecko.api.data.DataProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/data")
public class DataProviderController {

    private static final Logger LOG = LoggerFactory.getLogger(DataProviderController.class);

    @Autowired
    private DataProviderService dataProviderService;

    @GetMapping("/")
    public ResponseEntity<String> getHome(){
        return ResponseEntity.ok(dataProviderService.getValue());
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateString(@RequestBody DataPostRequest dataPostRequest){

        String newValue = dataPostRequest.getNewString();

        LOG.info(String.format("Updating data from: %s / %s",  dataProviderService.getValue(), newValue));

        dataProviderService.setValue(newValue);

        return ResponseEntity.noContent().build();
    }
}
