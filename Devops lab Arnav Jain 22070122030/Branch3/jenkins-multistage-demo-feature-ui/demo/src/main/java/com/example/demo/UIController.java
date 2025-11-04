package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {
    
   @GetMapping("/ui")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("UI is up and running");
    }
}
