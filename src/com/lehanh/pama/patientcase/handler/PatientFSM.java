package com.lehanh.pama.patientcase.handler;

import java.util.List;

import com.lehanh.pama.db.dao.PatientDao;
import com.lehanh.pama.patientcase.Patient;

public class PatientFSM {

	private Patient patient;
	
	private PatientDao dao;
	
	private List<IPatientListener> patientListeners;
	//private I
	
	public void startNewConsulting() {
		
	}
	
	public void surgery() {
		
	}
	
	public void reExam() {
		
	}
	
	public void captureImage() {
		
	}
}