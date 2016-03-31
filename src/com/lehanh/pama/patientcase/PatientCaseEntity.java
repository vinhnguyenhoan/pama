package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.catagory.AppointmentCatagory;
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
	private int id = -1;
	
	@Expose
	@SerializedName("ss")
	private String status;
	private PatientCaseStatus statusEnum;
	
	@Expose
	@SerializedName("sDate")
	private String surgeryDateAsText;
	private Date surgeryDate;
	
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
	@SerializedName("progOth")
	private String prognosticOther;
	
	@Expose
	@SerializedName("diagIds")
	private List<String> diagnoseCatagoryIds;
	private List<DiagnoseCatagory> diagnoseCatagoryList;
	@Expose
	@SerializedName("diagOth")
	private String diagnoseOther;
	
	@Expose
	@SerializedName("sgIds")
	private List<String> surgeryCatagoryIds;
	private List<SurgeryCatagory> surgeryCatagoryList;
	
	@Expose
	@SerializedName("sgNote")
	private String surgeryNote;
	
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

//	@Expose
//	@SerializedName("nDate")
//	private String nextDateAsText;
//	private Date nextDate;
	
	@Expose
	@SerializedName("appoS")
	private AppointmentSchedule appoSchedule;

	PatientCaseEntity(String dateText, PatientCaseStatus status) {
		this.setDateAsText(dateText);
		this.setStatusEnum(status);
	}

	public int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}
	
	public boolean isNew() {
		return this.id == -1;
	}

	public String getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = status;
	}

	public PatientCaseStatus getStatusEnum() {
		return statusEnum;
	}

	void setStatusEnum(PatientCaseStatus statusEnum) {
		this.statusEnum = statusEnum;
		this.status = statusEnum.name();
	}

	public Long getDrId() {
		return drId;
	}

	void setDrId(Long drId) {
		this.drId = drId;
	}

	public DrCatagory getDr() {
		return dr;
	}

	void setDr(DrCatagory dr) {
		this.dr = dr;
	}

	public List<String> getServiceIds() {
		return serviceIds;
	}

	void setServiceIds(List<String> serviceIds) {
		this.serviceIds = serviceIds;
	}

	public List<ServiceCatagory> getServiceList() {
		return serviceList;
	}

	void setServiceList(List<ServiceCatagory> serviceList) {
		this.serviceList = serviceList;
	}

	public List<String> getPrognosticCatagoryIds() {
		return prognosticCatagoryIds;
	}

	void setPrognosticCatagoryIds(List<String> prognosticCatagoryIds) {
		this.prognosticCatagoryIds = prognosticCatagoryIds;
	}

	public List<PrognosticCatagory> getPrognosticCatagoryList() {
		return prognosticCatagoryList;
	}

	void setPrognosticCatagoryList(
			List<PrognosticCatagory> prognosticCatagoryList) {
		this.prognosticCatagoryList = prognosticCatagoryList;
	}

	public List<String> getDiagnoseCatagoryIds() {
		return diagnoseCatagoryIds;
	}

	void setDiagnoseCatagoryIds(List<String> diagnoseCatagoryIds) {
		this.diagnoseCatagoryIds = diagnoseCatagoryIds;
	}

	public List<DiagnoseCatagory> getDiagnoseCatagoryList() {
		return diagnoseCatagoryList;
	}

	void setDiagnoseCatagoryList(List<DiagnoseCatagory> diagnoseCatagoryList) {
		this.diagnoseCatagoryList = diagnoseCatagoryList;
	}

	public List<String> getSurgeryCatagoryIds() {
		return surgeryCatagoryIds;
	}

	void setSurgeryCatagoryIds(List<String> surgeryCatagoryIds) {
		this.surgeryCatagoryIds = surgeryCatagoryIds;
	}

	public List<SurgeryCatagory> getSurgeryCatagoryList() {
		return surgeryCatagoryList;
	}

	void setSurgeryCatagoryList(List<SurgeryCatagory> surgeryCatagoryList) {
		this.surgeryCatagoryList = surgeryCatagoryList;
	}

	public String getSurgeryNote() {
		return surgeryNote;
	}

	void setSurgeryNote(String surgeryNote) {
		this.surgeryNote = surgeryNote;
	}

	public String getNoteFromClient() {
		return noteFromClient;
	}

	void setNoteFromClient(String noteFromClient) {
		this.noteFromClient = noteFromClient;
	}

	public String getNoteFromDr() {
		return noteFromDr;
	}

	void setNoteFromDr(String noteFromDr) {
		this.noteFromDr = noteFromDr;
	}

	public String getSmallSurgery() {
		return smallSurgery;
	}

	void setSmallSurgery(String smallSurgery) {
		this.smallSurgery = smallSurgery;
	}

	public String getAdviceFromDr() {
		return adviceFromDr;
	}

	void setAdviceFromDr(String adviceFromDr) {
		this.adviceFromDr = adviceFromDr;
	}

	public List<String> getPicNames() {
		return picNames;
	}

	void setPicNames(List<String> picNames) {
		this.picNames = picNames;
	}

	public List<PatientCaseEntity> getReExamInfo() {
		return reExamInfo;
	}

	void setReExamInfo(List<PatientCaseEntity> reExamInfo) {
		this.reExamInfo = reExamInfo;
	}

	public String getSurgeryDateAsText() {
		return surgeryDateAsText;
	}

	void setSurgeryDateAsText(String surgeryDateAsText) {
		this.surgeryDateAsText = surgeryDateAsText;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public AppointmentSchedule getAppoSchedule() {
		return appoSchedule;
	}

	void setAppoSchedule(AppointmentSchedule appoSchedule) {
		this.appoSchedule = appoSchedule;
	}

	public boolean isComplication() {
		return complication;
	}

	void setComplication(boolean complication) {
		this.complication = complication;
	}

	public boolean isBeautiful() {
		return beautiful;
	}

	void setBeautiful(boolean beautiful) {
		this.beautiful = beautiful;
	}

	void setDateAsText(String date) {
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

	void setDate(Date date) {
		this.date = date;
		this.dateAsText = DateUtils.convertDateDataType(date);
	}

	public String getPrognosticOther() {
		return this.prognosticOther;
	}

	public String getDiagnoseOther() {
		return this.diagnoseOther;
	}

	void setPrognosticOther(String prognosticOther) {
		this.prognosticOther = prognosticOther;
	}

	void setDiagnoseOther(String diagnoseOther) {
		this.diagnoseOther = diagnoseOther;
	}

	void updateData(DrCatagory drCat,
			List<ServiceCatagory> serviceList,
			List<PrognosticCatagory> progCatList, String prognosticOtherText,
			List<DiagnoseCatagory> diagCatList, String diagOtherText,
			String noteFromPa, String noteFromDr,
			List<SurgeryCatagory> surList, String surgeryNote,
			Date surgeryDate, boolean complication, boolean beauty,
			String smallSurgery, String drAdvice, 
			Date nextApp, AppointmentCatagory appPurpose, String appNote) {
		this.setDr(drCat);
		this.setServiceList(serviceList);
		this.setPrognosticCatagoryList(progCatList);
		this.setPrognosticOther(prognosticOtherText);
		this.setDiagnoseCatagoryList(diagCatList);
		this.setDiagnoseOther(diagOtherText);
		this.setNoteFromClient(noteFromPa);
		this.setNoteFromDr(noteFromDr);
		this.setSurgeryCatagoryList(surList);
		this.setSurgeryNote(surgeryNote);
		this.setSurgeryDate(surgeryDate);
		this.setComplication(complication);
		this.setBeautiful(beauty);
		this.setSmallSurgery(smallSurgery);
		this.setAdviceFromDr(drAdvice);
		
		if (this.appoSchedule != null && appoSchedule.getId() != null) {
			this.appoSchedule.setAppointmentCatagory(appPurpose);
			this.appoSchedule.setAppointmentDate(nextApp);
			this.appoSchedule.setNote(appNote);
		} else {
			this.setAppoSchedule(new AppointmentSchedule(nextApp, appPurpose, appNote));
		}
	}

}