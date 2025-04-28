package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.service.MLModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MLModelServiceImpl implements MLModelService {

    private final RestTemplate restTemplate;

    @Autowired
    public MLModelServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public List<String> predictSubcategories(List<String> descriptions) {
        String url = "http://localhost:5000/predict";

        Map<String, Object> request = new HashMap<>();
        request.put("descriptions", descriptions);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        List<String> subcategory = (List<String>) response.getBody().get("subcategory");

        return subcategory;
    }
}
