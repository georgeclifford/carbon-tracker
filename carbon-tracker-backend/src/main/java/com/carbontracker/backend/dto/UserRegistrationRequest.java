package com.carbontracker.backend.dto;

import java.time.LocalDate;

public class UserRegistrationRequest {
    // Login-related fields
    private String username;
    private String password;
    private String role;

    // User-related fields
    private String firstName;
    private String middleName;
    private String lastName;
    private String department;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String address;
    private String district;
    private String state;
    private String pinCode;
    
	public UserRegistrationRequest() {
		super();
	}

	public UserRegistrationRequest(String username, String password, String role, String firstName, String middleName,
			String lastName, String department, LocalDate dob, String gender, String phone, String address,
			String district, String state, String pinCode) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.department = department;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
