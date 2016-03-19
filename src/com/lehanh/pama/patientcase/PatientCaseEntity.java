package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;

public class PatientCaseEntity implements Serializable, IJsonDataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7533407014313088176L;

	@Expose
	private int id;
	
	@Expose
	@SerializedName("ss")
	private String status;
	private PatientCaseStatus statusEnum;
	
	@Expose
	@SerializedName("sDate")
	private String surgeryDateAsText;
	private Date surgeryDate;
	
	@Expose
	@SerializedName("nDate")
	private String nextDateAsText;
	private Date nextDate;
	
	@Expose
	private Long drId;	
	private DrCatagory dr;
	
	@Expose
	@SerializedName("sId")
	private Long serviceId;	
	private ServiceCatagory service;
	
	@Expose
	@SerializedName("progId")
	private Long prognosticCatagoryId;
	private PrognosticCatagory prognosticCatagory;
	
	@Expose
	@SerializedName("diagId")
	private Long diagnoseCatagoryId;
	private DiagnoseCatagory diagnoseCatagory;
	
	@Expose
	@SerializedName("sgId")
	private Long surgeryCatagoryId;
	private SurgeryCatagory surgeryCatagory;
	
	@Expose
	@SerializedName("noteCl")
	private String noteFromClient;
	@Expose
	@SerializedName("noteDr")
	private String noteFromDr;
	
	@Expose
	private String tips;
	@Expose
	@SerializedName("advice")
	private String adviceFromDr;
	
	@Expose
	@SerializedName("comp")
	private boolean complication;
	@Expose
	@SerializedName("save")
	private boolean beautiful;
	
	@Expose
	private List<String> picNames;
	
	@Expose
	private List<PatientCaseEntity> reExamInfo;

	@Expose
	@SerializedName("date")
	private String dateAsText;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PatientCaseStatus getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(PatientCaseStatus statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Long getDrId() {
		return drId;
	}

	public void setDrId(Long drId) {
		this.drId = drId;
	}

	public DrCatagory getDr() {
		return dr;
	}

	public void setDr(DrCatagory dr) {
		this.dr = dr;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceCatagory getService() {
		return service;
	}

	public void setService(ServiceCatagory service) {
		this.service = service;
	}

	public Long getPrognosticCatagoryId() {
		return prognosticCatagoryId;
	}

	public void setPrognosticCatagoryId(Long prognosticCatagoryId) {
		this.prognosticCatagoryId = prognosticCatagoryId;
	}

	public PrognosticCatagory getPrognosticCatagory() {
		return prognosticCatagory;
	}

	public void setPrognosticCatagory(PrognosticCatagory prognosticCatagory) {
		this.prognosticCatagory = prognosticCatagory;
	}

	public Long getDiagnoseCatagoryId() {
		return diagnoseCatagoryId;
	}

	public void setDiagnoseCatagoryId(Long diagnoseCatagoryId) {
		this.diagnoseCatagoryId = diagnoseCatagoryId;
	}

	public DiagnoseCatagory getDiagnoseCatagory() {
		return diagnoseCatagory;
	}

	public void setDiagnoseCatagory(DiagnoseCatagory diagnoseCatagory) {
		this.diagnoseCatagory = diagnoseCatagory;
	}

	public Long getSurgeryCatagoryId() {
		return surgeryCatagoryId;
	}

	public void setSurgeryCatagoryId(Long surgeryCatagoryId) {
		this.surgeryCatagoryId = surgeryCatagoryId;
	}

	public SurgeryCatagory getSurgeryCatagory() {
		return surgeryCatagory;
	}

	public void setSurgeryCatagory(SurgeryCatagory surgeryCatagory) {
		this.surgeryCatagory = surgeryCatagory;
	}

	public String getNoteFromClient() {
		return noteFromClient;
	}

	public void setNoteFromClient(String noteFromClient) {
		this.noteFromClient = noteFromClient;
	}

	public String getNoteFromDr() {
		return noteFromDr;
	}

	public void setNoteFromDr(String noteFromDr) {
		this.noteFromDr = noteFromDr;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getAdviceFromDr() {
		return adviceFromDr;
	}

	public void setAdviceFromDr(String adviceFromDr) {
		this.adviceFromDr = adviceFromDr;
	}

	public List<String> getPicNames() {
		return picNames;
	}

	public void setPicNames(List<String> picNames) {
		this.picNames = picNames;
	}

	public List<PatientCaseEntity> getReExamInfo() {
		return reExamInfo;
	}

	public void setReExamInfo(List<PatientCaseEntity> reExamInfo) {
		this.reExamInfo = reExamInfo;
	}

	public String getSurgeryDateAsText() {
		return surgeryDateAsText;
	}

	public void setSurgeryDateAsText(String surgeryDateAsText) {
		this.surgeryDateAsText = surgeryDateAsText;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public String getNextDateAsText() {
		return nextDateAsText;
	}

	public void setNextDateAsText(String nextDateAsText) {
		this.nextDateAsText = nextDateAsText;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public boolean isComplication() {
		return complication;
	}

	public void setComplication(boolean complication) {
		this.complication = complication;
	}

	public boolean isBeautiful() {
		return beautiful;
	}

	public void setBeautiful(boolean beautiful) {
		this.beautiful = beautiful;
	}

	public void setDateAsText(String date) {
		this.dateAsText = date;
	}
	
	public String getDateAsText() {
		return dateAsText;
	}
	
}