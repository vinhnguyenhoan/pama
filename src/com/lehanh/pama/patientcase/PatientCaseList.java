package com.lehanh.pama.patientcase;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;
import com.lehanh.pama.util.DateUtils;
import com.lehanh.pama.util.PamaException;

public class PatientCaseList implements IPatientCaseList {

	private final List<PatientCaseEntity> patientCases;
	
	private PatientCaseEntity creatingExamCase;
	
	private Comparator<? super PatientCaseEntity> descDateComparator = new Comparator<PatientCaseEntity>() {

		@Override
		public int compare(PatientCaseEntity p1, PatientCaseEntity p2) {
			Integer id1 = p1.getId();
			Integer id2 = p1.getId();
			return id2.compareTo(id1);
		}
	};

	PatientCaseList(List<PatientCaseEntity> patientCases) {
		this.patientCases = patientCases;
		// order list
		Collections.sort(patientCases, this.descDateComparator);
	}

	@Override
	public boolean isLastExam(PatientCaseEntity selectedEntity) {
		if (patientCases == null || patientCases.isEmpty()) {
			return false;
		}
		return patientCases.get(0) == selectedEntity;
	}

	@Override
	public Object[] getAllVersions() {
		Object[] allVers = patientCases.toArray();
    	if (creatingExamCase != null) {
    		LinkedList<Object> allVersList = new LinkedList<Object>(Arrays.asList(allVers));
    		allVersList.addFirst(creatingExamCase);
    		allVers = allVersList.toArray();
    	}
    	return allVers;
	}

	@Override
	public void updateCase(PatientCaseEntity paCaseEntity, DrCatagory drCat,
			List<ServiceCatagory> serviceList,
			List<PrognosticCatagory> progCatList, String prognosticOtherText,
			List<DiagnoseCatagory> diagCatList, String diagOtherText,
			String noteFromPa, String noteFromDr,
			List<SurgeryCatagory> surList, String surgeryNote,
			Date surgeryDate, boolean complication, boolean beauty,
			String smallSurgery, String drAdvice, Date nextApp,
			AppointmentCatagory appPurpose, String appNote) {
		
		if (creatingExamCase != null && creatingExamCase == paCaseEntity) {
			paCaseEntity.setId(patientCases.size());
			patientCases.add(0, paCaseEntity);
		}
		paCaseEntity.updateData(drCat, serviceList, progCatList, prognosticOtherText,
				diagCatList, diagOtherText, noteFromPa, noteFromDr, surList, surgeryNote,
				surgeryDate, complication, beauty, smallSurgery, drAdvice, nextApp,
				appPurpose, appNote);
	}

	PatientCaseEntity createEmptyCase(PatientCaseStatus status) {
		if (creatingExamCase != null) {
			throw new PamaException("Cannot create new exam while editing and not sumit yet");
		}
		this.creatingExamCase = new PatientCaseEntity(DateUtils.convertDateDataType(GregorianCalendar.getInstance().getTime()), status);
		if (PatientCaseStatus.RE_EXAM == status) {
			// set default value from previous version for re exam
			PatientCaseEntity previousVersion = getLastExamByStatus(PatientCaseStatus.RE_EXAM, PatientCaseStatus.SURGERY);
			creatingExamCase.setServiceList(previousVersion.getServiceList());
			creatingExamCase.setPrognosticCatagoryList(previousVersion.getPrognosticCatagoryList());
			creatingExamCase.setDiagnoseCatagoryList(previousVersion.getDiagnoseCatagoryList());
			creatingExamCase.setSurgeryCatagoryList(previousVersion.getSurgeryCatagoryList());
		}
		return creatingExamCase;
	}

	@Override
	public PatientCaseEntity getLastExamByStatus(PatientCaseStatus... caseStatus) {
		if (Arrays.binarySearch(caseStatus, PatientCaseStatus.NEW) > -1) {
			return this.creatingExamCase;
		}
		
		if (patientCases == null || patientCases.isEmpty()) {
			return null;
		}
		
		if (caseStatus == null || caseStatus.length == 0) {
			return patientCases.get(0);
		}
		
		
		for (PatientCaseEntity pe : patientCases) {
			for (PatientCaseStatus cs : caseStatus) {
				if (cs == pe.getStatusEnum()) {
					return pe;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public boolean isEmptyVersions() {
		return this.creatingExamCase == null && (this.patientCases == null || this.patientCases.isEmpty());
	}

	@Override
	public boolean isCreatingExam(PatientCaseEntity entity) {
		return this.creatingExamCase != null && this.creatingExamCase == entity;
	}
}
