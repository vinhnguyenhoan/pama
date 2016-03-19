package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.db.dao.JsonMapper;

public class MedicalPersonalInfo implements Serializable, IJsonDataObject {
	
	/*
	 * Auto gen
	 */
	private static final long serialVersionUID = -912854231669513801L;
	
	@Expose
	private List<PatientCaseEntity> patientCase;
	
	@Expose
	private PatientCaseSummary patientCaseSummary;
	@Expose
	private String anamnesis;
	@Expose
	private String medicalHistory;
	// TODO xet nghiem...
	
	public List<PatientCaseEntity> getPatientCase() {
		return patientCase;
	}

	public void setPatientCase(List<PatientCaseEntity> patientCase) {
		this.patientCase = patientCase;
	}

	public PatientCaseSummary getPatientCaseSummary() {
		if (patientCaseSummary == null) {
			patientCaseSummary = new PatientCaseSummary();
		}
		patientCaseSummary.updatePatientCase(this.patientCase);
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
	
	public static void main(String[] args) {
		MedicalPersonalInfo mI = new MedicalPersonalInfo();
		List<PatientCaseEntity> patientCase = new LinkedList<PatientCaseEntity>();
		
		PatientCaseEntity p1 = new PatientCaseEntity();
		List<PatientCaseEntity> p1List = new LinkedList<PatientCaseEntity>();
		PatientCaseEntity p11 = new PatientCaseEntity();
		p1List.add(p11);
		p1.setReExamInfo(p1List);
		
		PatientCaseEntity p2 = new PatientCaseEntity();
		
		patientCase.add(p1);
		patientCase.add(p2);
		mI.patientCase = patientCase;
		
		PatientCaseSummary patientCaseSummary = new PatientCaseSummary();
		mI.patientCaseSummary = patientCaseSummary;
		
		mI.anamnesis = null;
		
		String medicalHistory = "khong benh";
		mI.medicalHistory = medicalHistory;
		
		String json = JsonMapper.toJson(mI);
		System.out.println(json);
		
		MedicalPersonalInfo mI2 = JsonMapper.fromJson(json, MedicalPersonalInfo.class);
		System.out.println(mI2);
	}

}
