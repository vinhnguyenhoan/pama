package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.List;

import com.lehanh.pama.IJsonDataObject;

public class PatientCase implements Serializable, IJsonDataObject {

	private List<PatientCaseEntity> entities;
}
