package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    
    @GetMapping("/api")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is up and running");
    }
}
