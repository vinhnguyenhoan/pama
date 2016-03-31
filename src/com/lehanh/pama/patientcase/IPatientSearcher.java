package com.lehanh.pama.patientcase;

import java.util.Date;
import java.util.List;

public interface IPatientSearcher {

	Patient getPatientDetailById(Long id);
	
	List<Patient> searchPatient(Date appointment, Date lastUpdate, String name);

}