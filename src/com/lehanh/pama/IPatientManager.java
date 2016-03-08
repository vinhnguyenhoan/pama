package com.lehanh.pama;

import java.util.Date;
import java.util.List;

import com.lehanh.pama.patientcase.Patient;

public interface IPatientManager extends IService {

	Patient getPatientDetailById(Long id);
	
	List<Patient> searchPatient(Date appointment, Date lastUpdate, String name);
	
	List<Patient> searchPatientAppointmentToday(boolean forceReload);
	
	int clearOldAppointment();
}
