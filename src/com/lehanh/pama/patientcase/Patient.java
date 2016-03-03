package com.lehanh.pama.patientcase;

import java.util.Date;

public class Patient {

	private PatientCase patientCase;
	private Anamnesis anamnesis;
	private MedicalHistory medicalHistory;
	
	private long id;
	private String imagePath;
	private String name;
	private String address;
	private String birthDayText;
	private Date birthDay;
	private boolean isFermale;
	private String cellPhone;
	private String phone;
	private String email;
	private String career;
	
	public Patient() {
		
	}

	public PatientCase getPatientCase() {
		return patientCase;
	}

	public void setPatientCase(PatientCase patientCase) {
		this.patientCase = patientCase;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDayText() {
		return birthDayText;
	}

	public void setBirthDayText(String birthDayText) {
		this.birthDayText = birthDayText;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public boolean isFermale() {
		return isFermale;
	}

	public void setFermale(boolean isFermale) {
		this.isFermale = isFermale;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
