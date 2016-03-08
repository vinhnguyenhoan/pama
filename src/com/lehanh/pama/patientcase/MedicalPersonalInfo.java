package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.TreeMap;

import com.lehanh.pama.IJsonDataObject;

public class MedicalPersonalInfo implements Serializable, IJsonDataObject {
	
	/*
	 * Auto gen
	 */
	private static final long serialVersionUID = -912854231669513801L;
	
	private TreeMap<String, PatientCaseEntity> patientCase;
	
	private PatientCaseSummary patientCaseSummary;
	private TreeMap<String, Object> anamnesis;
	private String medicalHistory;
	// TODO xet nghiem...
	
}
