package com.lehanh.pama.patientcase;

public enum PatientCaseStatus {

	CONSULT("Tư vấn"),
	SURGERY("Phẩu thuật"),
	RE_EXAM("Tái khám"), 
	NEW("Đang thực hiện");

	public final String desc;
	
	private PatientCaseStatus(String desc) {
		this.desc = desc;
	}
	
}
