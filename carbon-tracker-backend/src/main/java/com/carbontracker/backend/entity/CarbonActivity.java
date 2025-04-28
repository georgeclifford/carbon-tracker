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
@Table(name = "carbon_activity")
public class CarbonActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long activityId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "month_id", referencedColumnName = "month_id", nullable = false)
//    private MonthlyEmissions monthlyEmissions;  // Many-to-One relationship with MonthlyEmissions

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id", nullable = false)
    private ExternalEntity externalEntity;  // Many-to-One relationship with ExternalEntity
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "sub_category_id", nullable = false)
    private SubCategory subCategory;  // Many-to-One relationship with SubCategory

    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;  // Unique transaction ID for tracking

    @Column(name = "raw_value", nullable = false)
    private double rawValue;  // Raw value of the activity (e.g., amount of energy consumed)

    @Column(name = "emission_value", nullable = false)
    private double emissionValue;  // Calculated emission value for the activity

    @Column(name = "date", nullable = false)
    private String date;  // Date of the activity

    // Default constructor
    public CarbonActivity() {}

	public CarbonActivity(Long activityId, ExternalEntity externalEntity, SubCategory subCategory, String transactionId,
			double rawValue, double emissionValue, String date) {
		super();
		this.activityId = activityId;
		this.externalEntity = externalEntity;
		this.subCategory = subCategory;
		this.transactionId = transactionId;
		this.rawValue = rawValue;
		this.emissionValue = emissionValue;
		this.date = date;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public ExternalEntity getExternalEntity() {
		return externalEntity;
	}

	public void setExternalEntity(ExternalEntity externalEntity) {
		this.externalEntity = externalEntity;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getRawValue() {
		return rawValue;
	}

	public void setRawValue(double rawValue) {
		this.rawValue = rawValue;
	}

	public double getEmissionValue() {
		return emissionValue;
	}

	public void setEmissionValue(double emissionValue) {
		this.emissionValue = emissionValue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
