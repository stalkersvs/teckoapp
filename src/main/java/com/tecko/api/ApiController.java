package com.tecko.api;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
        tags="ApiController",
        description = "Application init page."
)
@RestController
public class ApiController {
    @GetMapping("/")
    public ResponseEntity<String> getHome(){
        return ResponseEntity.ok("Page works");
    }
}
