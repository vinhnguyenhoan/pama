package com.lehanh.pama.patientcase;

import java.util.List;

public interface IAppointmentManager {

	List<Patient> searchPatientAppointmentToday(boolean forceReload);
	
	int clearOldAppointment();

}
