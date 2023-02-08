package com.tecko.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/")
    public ResponseEntity<String> getHome(){
        return ResponseEntity.ok("Page works");
    }
}