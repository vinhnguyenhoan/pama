package com.lehanh.pama.patientcase;

import java.util.Date;

import com.lehanh.pama.catagory.AppointmentCatagory;

public class AppointmentSchedule {

	private Long id;
	private Long patientId;
	
	private Date appointmentDate;
	
	private boolean resolved;
	
	private Long appointmentType;
	private AppointmentCatagory appointmentCatagory;
	
	private String note;
	
	public AppointmentSchedule(Long id, Long patientId, Date appointmentDate,
			Long appointmentType) {
		this.id = id;
		this.patientId = patientId;
		this.appointmentDate = appointmentDate;
		this.appointmentType = appointmentType;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	public Long getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(Long appointmentType) {
		this.appointmentType = appointmentType;
	}
	public AppointmentCatagory getAppointmentCatagory() {
		return appointmentCatagory;
	}
	public void setAppointmentCatagory(AppointmentCatagory appointmentCatagory) {
		this.appointmentCatagory = appointmentCatagory;
	}
	
}
