package com.lehanh.pama.patientcase;

import java.util.Calendar;
import java.util.Date;

import com.lehanh.pama.util.DateUtils;
import com.lehanh.pama.util.JsonMapper;

import static com.lehanh.pama.util.ValidateUtils.*;

public class Patient {

	private Long id;
	private String imageName;
	private String name;
	private String address;
	private String birthDayText;
	private Date birthDay;
	private boolean isFermale;
	private String cellPhone;
	private String phone;
	private String email;
	private String career;
	
	private Date lastVisit;
	private Date lastSurgery;
	private Date nextAppointment;
	
	// Note from doctor about this patient
	private int patientLevel; // from 1-4
	private String note;
	
	private MedicalPersonalInfo medicalPersonalInfo;
	
	public Patient() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public MedicalPersonalInfo getMedicalPersonalInfo() {
		if (this.medicalPersonalInfo == null) {
			this.medicalPersonalInfo = new MedicalPersonalInfo();
		}
		return this.medicalPersonalInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		validateIsAllEmpty("Phải nhập tên", name);
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
		validateIsAllEmpty("Phải nhập ngày sinh", birthDayText);
		this.birthDayText = birthDayText;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		validateIsAllEmpty("Phải nhập ngày sinh", birthDay);
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
		// TODO validateMobi(cellPhone);
		validateIsAllEmpty("Phải nhập số điện thoại", cellPhone, this.phone);
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		validateIsAllEmpty("Phải nhập số điện thoại", cellPhone, this.phone);
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		// TODO validateEmail(email);
		this.email = email;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imagePath) {
		this.imageName = imagePath;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Date getNextAppointment() {
		return nextAppointment;
	}

	public void setNextAppointment(Date nextAppointment) {
		this.nextAppointment = nextAppointment;
	}

	public Date getLastSurgery() {
		return lastSurgery;
	}

	public void setLastSurgery(Date lastSurgery) {
		this.lastSurgery = lastSurgery;
	}

	public int getPatientLevel() {
		return patientLevel;
	}

	public void setPatientLevel(int patientLevel) {
		this.patientLevel = patientLevel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getAge() {
		return DateUtils.calculateAge(this.getBirthDay());
	}

	public void setBirthDay(Calendar date) {
		validateIsAllEmpty("Phải nhập ngày sinh", date);
		setBirthDay(date.getTime());
	}
	
	public String getMedicalPersonalInfoText() {
		if (medicalPersonalInfo == null) {
			return null;
		}
		return JsonMapper.toJson(medicalPersonalInfo);
	}

	void reloadMedicalInfo() {
		String json = JsonMapper.toJson(getMedicalPersonalInfo());
		this.medicalPersonalInfo = JsonMapper.fromJson(json, MedicalPersonalInfo.class);
	}
}