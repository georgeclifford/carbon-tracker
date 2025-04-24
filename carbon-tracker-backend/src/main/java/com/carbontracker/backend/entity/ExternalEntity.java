package com.carbontracker.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "external_entity")
public class ExternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    // Default constructor
    public ExternalEntity() {}

    // Constructor with parameters
    public ExternalEntity(Long entityId, String entityType, String entityName, String phone, String address, 
                          String district, String state, String pinCode) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.entityName = entityName;
        this.phone = phone;
        this.address = address;
        this.district = district;
        this.state = state;
        this.pinCode = pinCode;
    }

    // Getters and Setters
    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
