package com.carbontracker.backend.controller;

import com.carbontracker.backend.service.ExternalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/externalentity")
public class ExternalEntityController {

    @Autowired
    private ExternalEntityService externalEntityService;

    @PostMapping("/import")
    public ResponseEntity<String> uploadExternalEntityData(@RequestParam("file") MultipartFile file) {
        try {
            externalEntityService.processExternalEntityFile(file);
            return new ResponseEntity<>("File processed and data stored successfully!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing file: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
