package com.carbontracker.backend.dto;

import java.util.List;

public class ExternalEntityDTO {

    private List<EntityDataDTO> entities;

    public List<EntityDataDTO> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityDataDTO> entities) {
        this.entities = entities;
    }
}
