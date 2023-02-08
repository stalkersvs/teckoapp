package com.tecko.api;

import com.tecko.api.business.BiznisAnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/biznis")
public class BiznisAnalystController {

    @Autowired
    private BiznisAnalystService biznisAnalystService;


    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Long> getHome(){
        return ResponseEntity.ok(biznisAnalystService.getData());
    }
}
