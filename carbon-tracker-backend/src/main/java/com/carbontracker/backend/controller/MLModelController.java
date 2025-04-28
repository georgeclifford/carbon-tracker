package com.carbontracker.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbontracker.backend.service.MLModelService;

@RestController
@RequestMapping("/ml")
public class MLModelController {

    private final MLModelService mlModelService;

    @Autowired
    public MLModelController(MLModelService mlModelService) {
        this.mlModelService = mlModelService;
    }

    @PostMapping("/predict")
    public List<String> predict(@RequestBody List<String> descriptions) {
        return mlModelService.predictSubcategories(descriptions);
    }
}
