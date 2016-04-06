package com.lehanh.pama.patientcase;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;
import com.lehanh.pama.ui.util.ObjectToUIText;

public interface IPatientCaseList {

	boolean isLastExam(PatientCaseEntity selectedEntity);

	Object[] getAllVersions();

	void updateCase(PatientCaseEntity paCaseEntity, DrCatagory drCat,
			List<ServiceCatagory> serviceList,
			List<PrognosticCatagory> progCatList, String prognosticOtherText,
			List<DiagnoseCatagory> diagCatList, String diagOtherText,
			String noteFromPa, String noteFromDr,
			List<SurgeryCatagory> surList, String surgeryNote,
			Date surgeryDate, boolean complication, boolean beauty,
			String smallSurgery, String drAdvice, Date nextApp,
			AppointmentCatagory appPurpose, String appNote);

	PatientCaseEntity getLastExamByStatus(PatientCaseStatus... caseStatus);

	boolean isEmptyVersions();

	boolean isCreatingExam(PatientCaseEntity entity);

	Map<String, Map<String, Map<String, Object>>> getAllImageGroup(ObjectToUIText<PatientCaseEntity, Integer> objToText);

}