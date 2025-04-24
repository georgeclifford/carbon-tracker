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
@Table(name = "monthly_emissions")
public class MonthlyEmissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "month_id")
    private Long monthId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id", referencedColumnName = "year_id", nullable = false)
    private YearlyEmissions yearlyEmissions;  // Many-to-One relationship with YearlyEmissions

    @Column(name = "month_year", nullable = false)
    private String monthYear;  // e.g., "Jan-2025"

    @Column(name = "monthly_emission", nullable = false)
    private double monthlyEmission;

    // Default constructor
    public MonthlyEmissions() {}

    // Constructor with parameters
    public MonthlyEmissions(Long monthId, YearlyEmissions yearlyEmissions, String monthYear, double monthlyEmission) {
        this.monthId = monthId;
        this.yearlyEmissions = yearlyEmissions;
        this.monthYear = monthYear;
        this.monthlyEmission = monthlyEmission;
    }

    // Getters and Setters
    public Long getMonthId() {
        return monthId;
    }

    public void setMonthId(Long monthId) {
        this.monthId = monthId;
    }

    public YearlyEmissions getYearlyEmissions() {
        return yearlyEmissions;
    }

    public void setYearlyEmissions(YearlyEmissions yearlyEmissions) {
        this.yearlyEmissions = yearlyEmissions;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public double getMonthlyEmission() {
        return monthlyEmission;
    }

    public void setMonthlyEmission(double monthlyEmission) {
        this.monthlyEmission = monthlyEmission;
    }
}
