package com.tecko.api;

import com.tecko.api.data.DataPostRequest;
import com.tecko.api.data.DataProviderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(
        tags="DataProviderController",
        description = "Controller for word retrieve or update."
)
@RestController
@RequestMapping(path = "/data")
public class DataProviderController {

    private static final Logger LOG = LoggerFactory.getLogger(DataProviderController.class);

    private final DataProviderService dataProviderService;

    @Autowired
    public DataProviderController(DataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
    }

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
