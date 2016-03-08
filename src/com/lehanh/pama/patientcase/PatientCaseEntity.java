package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;

public class PatientCaseEntity implements Serializable, IJsonDataObject {

	@Expose
	private int id;
	
	@Expose
	private String status;
	private PatientCaseStatus action;
	
	@Expose
	private Long drId;	
	private DrCatagory dr;
	
	@Expose
	private Long serviceId;	
	private ServiceCatagory service;
	
	@Expose
	private Long prognosticCatagoryId;
	private PrognosticCatagory prognosticCatagory;
	
	@Expose
	private Long diagnoseCatagoryId;
	private DiagnoseCatagory diagnoseCatagory;
	
	@Expose
	private Long surgeryCatagoryId;
	private SurgeryCatagory surgeryCatagory;
	
	@Expose
	private String noteFromClient;
	@Expose
	private String noteFromDr;
	
	@Expose
	private String tips;
	@Expose
	private String adviceFromDr;
	
	@Expose
	private List<String> picNames;
}
