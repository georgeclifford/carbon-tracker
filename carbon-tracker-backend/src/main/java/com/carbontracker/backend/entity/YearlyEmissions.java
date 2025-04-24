package com.carbontracker.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "yearly_emissions")
public class YearlyEmissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private Long yearId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id", nullable = false)
    private ExternalEntity externalEntity;  // Many-to-One relationship with ExternalEntity
    
    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "prediction")
    private String prediction;

    @Column(name = "yearly_emission", nullable = false)
    private double yearlyEmission;

    // Default constructor
    public YearlyEmissions() {}

    // Constructor with parameters
    public YearlyEmissions(Long yearId, ExternalEntity externalEntity, int year, String prediction, double yearlyEmission) {
        this.yearId = yearId;
        this.externalEntity = externalEntity;
        this.year = year;
        this.prediction = prediction;
        this.yearlyEmission = yearlyEmission;
    }

    // Getters and Setters
    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public ExternalEntity getExternalEntity() {
        return externalEntity;
    }

    public void setExternalEntity(ExternalEntity externalEntity) {
        this.externalEntity = externalEntity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public double getYearlyEmission() {
        return yearlyEmission;
    }

    public void setYearlyEmission(double yearlyEmission) {
        this.yearlyEmission = yearlyEmission;
    }
}
