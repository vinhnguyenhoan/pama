package com.lehanh.pama;

import java.util.Date;
import java.util.List;

import com.lehanh.pama.patientcase.Patient;

public interface IPatientManager extends IService {

	Patient getPatientDetailById(Long id);
	
	List<Patient> searchPatient(Date appointment, Date lastUpdate, String name);
	
	List<Patient> searchPatientAppointmentToday(boolean forceReload);
	
	int clearOldAppointment();

	Patient createNewPatient(String imagePath, String name, String address,
			Date birthDay, boolean isFermale, String cellPhone, String phone,
			String email, String career, int patientLevel, String note);

	Patient updatePatient(Long id, String imagePath, String name,
			String address, Date birthDay, boolean isFermale, String cellPhone,
			String phone, String email, String career, int patientLevel,
			String note, String medicalHistory, String anamnesis);

	Patient updateImage(String image);

	Patient getCurrentPatient();
}
