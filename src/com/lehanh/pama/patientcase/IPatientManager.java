package com.lehanh.pama.patientcase;

import java.util.Date;
import java.util.List;

import com.lehanh.pama.IService;
import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;

public interface IPatientManager extends IService {

	Patient getCurrentPatient();
	
	IPatientSearcher getPatientSearcher();
	
	IAppointmentManager getAppointmentManager();

	void updatePatient(String imagePath, String name, String address,
			Date birthDay, boolean isFermale, String cellPhone, String phone,
			String email, String career, int patientLevel, String note, String detailExam,
			String medicalHistory, String anamnesis);

	void addPaListener(IPatientViewPartListener paL);

	void updatePatientCase(PatientCaseEntity paCaseEntity,
			DrCatagory drCat,
			List<ServiceCatagory> serviceList,
			List<PrognosticCatagory> progCatList, String prognosticOtherText,
			List<DiagnoseCatagory> diagCatList, String diagOtherText,
			String noteFromPa, String noteFromDr,
			List<SurgeryCatagory> surList, String surgeryNote,
			Date surgeryDate, boolean complication, boolean beauty,
			String smallSurgery, String drAdvice, Date nextApp,
			AppointmentCatagory appPurpose, String appNot);

	void createEmptyCase(PatientCaseStatus status);

	void cancelEditingPatientCase();
}
