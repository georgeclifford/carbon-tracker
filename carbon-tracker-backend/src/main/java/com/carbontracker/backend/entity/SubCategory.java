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
import java.time.LocalDateTime;

@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;  // Many-to-One relationship with Category

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;  // Many-to-One relationship with User
    
    @Column(name = "sub_category_name", nullable = false)
    private String subCategoryName;

    @Column(name = "ef_value", nullable = false)
    private Double efValue;  // Emission factor value

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "status", nullable = false)
    private String status;  // Active/Inactive

    // Default constructor
    public SubCategory() {}

    // Constructor with parameters
    public SubCategory(Long subCategoryId, Category category, User user, String subCategoryName, 
                       Double efValue, String source, Integer year, LocalDateTime timestamp, String status) {
        this.subCategoryId = subCategoryId;
        this.category = category;
        this.user = user;
        this.subCategoryName = subCategoryName;
        this.efValue = efValue;
        this.source = source;
        this.year = year;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters and Setters
    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Double getEfValue() {
        return efValue;
    }

    public void setEfValue(Double efValue) {
        this.efValue = efValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
