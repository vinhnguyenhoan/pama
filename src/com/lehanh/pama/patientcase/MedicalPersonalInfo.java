package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lehanh.pama.IJsonDataObject;

public class MedicalPersonalInfo implements Serializable, IJsonDataObject {
	
	/*
	 * Auto gen
	 */
	private static final long serialVersionUID = -912854231669513801L;
	
	@Expose
	@SerializedName("allPa")
	private List<PatientCaseEntity> patientCases;
	private PatientCaseList patientCaseList;
	
	@Expose
	@SerializedName("paS")
	private PatientCaseSummary patientCaseSummary;
	@Expose
	@SerializedName("ana")
	private String anamnesis;
	@Expose
	@SerializedName("mH")
	private String medicalHistory;
	
	public void setPatientCases(List<PatientCaseEntity> patientCases) {
		this.patientCases = patientCases;
		this.patientCaseList = new PatientCaseList(patientCases);
	}
	
	public IPatientCaseList getPatientCaseList() {
		return this.patientCaseList;
	}

	public PatientCaseSummary getPatientCaseSummary() {
		if (patientCaseSummary == null) {
			patientCaseSummary = new PatientCaseSummary();
		}
		patientCaseSummary.updatePatientCase(this.patientCases);
		return patientCaseSummary;
	}

	public void setPatientCaseSummary(PatientCaseSummary patientCaseSummary) {
		this.patientCaseSummary = patientCaseSummary;
	}

	public void setPatientCaseSummary(String text) {
		setPatientCaseSummary(new PatientCaseSummary(text));
	}
	
	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	
	public boolean isEmptyVersions() {
		return this.patientCases == null || this.patientCases.isEmpty();
	}

}
//public static void main(String[] args) {
//	MedicalPersonalInfo mI = new MedicalPersonalInfo();
//	List<PatientCaseEntity> patientCase = new LinkedList<PatientCaseEntity>();
//	
//	PatientCaseEntity p1 = new PatientCaseEntity();
//	List<PatientCaseEntity> p1List = new LinkedList<PatientCaseEntity>();
//	PatientCaseEntity p11 = new PatientCaseEntity();
//	p1List.add(p11);
//	p1.setReExamInfo(p1List);
//	
//	PatientCaseEntity p2 = new PatientCaseEntity();
//	
//	patientCase.add(p1);
//	patientCase.add(p2);
//	mI.patientCases = patientCase;
//	
//	PatientCaseSummary patientCaseSummary = new PatientCaseSummary();
//	mI.patientCaseSummary = patientCaseSummary;
//	
//	mI.anamnesis = null;
//	
//	String medicalHistory = "khong benh";
//	mI.medicalHistory = medicalHistory;
//	
//	String json = JsonMapper.toJson(mI);
//	System.out.println(json);
//	
//	MedicalPersonalInfo mI2 = JsonMapper.fromJson(json, MedicalPersonalInfo.class);
//	System.out.println(mI2);
//}