package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.text.ParseException;
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
import com.lehanh.pama.util.DateUtils;

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
	@SerializedName("sIds")
	private List<String> serviceIds;	
	private List<ServiceCatagory> serviceList;
	
	@Expose
	@SerializedName("progIds")
	private List<String> prognosticCatagoryIds;
	private List<PrognosticCatagory> prognosticCatagoryList;
	
	@Expose
	@SerializedName("diagIds")
	private List<String> diagnoseCatagoryIds;
	private List<DiagnoseCatagory> diagnoseCatagoryList;
	
	@Expose
	@SerializedName("sgIds")
	private List<String> surgeryCatagoryIds;
	private List<SurgeryCatagory> surgeryCatagoryList;
	
	@Expose
	@SerializedName("noteCl")
	private String noteFromClient;
	@Expose
	@SerializedName("noteDr")
	private String noteFromDr;
	
	@Expose
	@SerializedName("sSurgery")
	private String smallSurgery;
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
	private Date date;
	
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

	public List<String> getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(List<String> serviceIds) {
		this.serviceIds = serviceIds;
	}

	public List<ServiceCatagory> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceCatagory> serviceList) {
		this.serviceList = serviceList;
	}

	public List<String> getPrognosticCatagoryIds() {
		return prognosticCatagoryIds;
	}

	public void setPrognosticCatagoryIds(List<String> prognosticCatagoryIds) {
		this.prognosticCatagoryIds = prognosticCatagoryIds;
	}

	public List<PrognosticCatagory> getPrognosticCatagoryList() {
		return prognosticCatagoryList;
	}

	public void setPrognosticCatagoryList(
			List<PrognosticCatagory> prognosticCatagoryList) {
		this.prognosticCatagoryList = prognosticCatagoryList;
	}

	public List<String> getDiagnoseCatagoryIds() {
		return diagnoseCatagoryIds;
	}

	public void setDiagnoseCatagoryIds(List<String> diagnoseCatagoryIds) {
		this.diagnoseCatagoryIds = diagnoseCatagoryIds;
	}

	public List<DiagnoseCatagory> getDiagnoseCatagoryList() {
		return diagnoseCatagoryList;
	}

	public void setDiagnoseCatagoryList(List<DiagnoseCatagory> diagnoseCatagoryList) {
		this.diagnoseCatagoryList = diagnoseCatagoryList;
	}

	public List<String> getSurgeryCatagoryIds() {
		return surgeryCatagoryIds;
	}

	public void setSurgeryCatagoryIds(List<String> surgeryCatagoryIds) {
		this.surgeryCatagoryIds = surgeryCatagoryIds;
	}

	public List<SurgeryCatagory> getSurgeryCatagoryList() {
		return surgeryCatagoryList;
	}

	public void setSurgeryCatagoryList(List<SurgeryCatagory> surgeryCatagoryList) {
		this.surgeryCatagoryList = surgeryCatagoryList;
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

	public String getSmallSurgery() {
		return smallSurgery;
	}

	public void setSmallSurgery(String smallSurgery) {
		this.smallSurgery = smallSurgery;
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
		this.date = null;
	}
	
	public String getDateAsText() {
		return dateAsText;
	}

	public Date getDate() throws ParseException {
		if (date == null) {
			date = DateUtils.convertDateDataType(dateAsText);
		}
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		this.dateAsText = DateUtils.convertDateDataType(date);
	}
	
}