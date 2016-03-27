package com.lehanh.pama.patientcase;

public interface IPatientCaseList {

	boolean isLastExam(PatientCaseEntity selectedEntity);

	Object[] getAllVersions();

}
