package com.lehanh.pama.patientcase;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;
import com.lehanh.pama.util.DateUtils;
import com.lehanh.pama.util.PamaException;

public class PatientCaseList implements IPatientCaseList {

	private final List<PatientCaseEntity> patientCases;

	private final SurgeryImageList surgeryImageList;
	
	private String summary;
	
	private final boolean isListRootCase;

	// Manage sub patient case list
	private PatientCaseEntity creatingDetailExamCase; // create instance for creating data so when clear data this info will delete
	private final Map<Integer, PatientCaseList> detailPAList;

	private final Long patientId;
	
	private static final Comparator<? super PatientCaseEntity> idDescComparator = new Comparator<PatientCaseEntity>() {

		@Override
		public int compare(PatientCaseEntity p1, PatientCaseEntity p2) {
			Integer id1 = p1.getId();
			Integer id2 = p1.getId();
			return id2.compareTo(id1);
		}
	};
	
	PatientCaseList(Long patientId, List<PatientCaseEntity> patientCases) {
		this(patientId, patientCases, true);
		if (patientCases.isEmpty()) {
			createRootCase();
		}
	}
	
	private PatientCaseList(Long patientId, List<PatientCaseEntity> patientCases, boolean isRoot) {
		this.patientId = patientId;
		this.isListRootCase = isRoot;
		this.patientCases = patientCases;
		// order list
		Collections.sort(this.patientCases, idDescComparator);
		detailPAList = new TreeMap<Integer, PatientCaseList>();
		for (PatientCaseEntity pCase : this.patientCases) {
			detailPAList.put(pCase.getId(), new PatientCaseList(this.patientId, pCase.getReExamInfo(), false));
		}
		
		if (isRoot) {
			surgeryImageList = new SurgeryImageList(this.patientId, this.patientCases);
		} else {
			surgeryImageList = null;
		}
	}

	@Override
	public boolean isListRootCase() {
		return this.isListRootCase;
	}
	
	@Override
	public IPatientCaseList createRootCase() {
		if (!isListRootCase) {
			throw new PamaException("Cannot create root case from this list");
		}
		if (!detailPAList.isEmpty() && ((PatientCaseList) detailPAList.values().toArray()[0]).patientCases.isEmpty()) {
			throw new InvalidParameterException("Bệnh án gần nhất đang trống, sử dụng bệnh án này để thăm khám hay tư vấn !");
		}
		for (PatientCaseList subList : this.detailPAList.values()) {
			if (subList.creatingDetailExamCase != null) {
				throw new PamaException("Cannot create new root case while creating detail exam");
			}
		}
		
		PatientCaseEntity newRootCase = new PatientCaseEntity(DateUtils.convertDateDataType(GregorianCalendar.getInstance().getTime()));
		this.patientCases.add(0, newRootCase);
		// set id after add to list
		newRootCase.setId(patientCases.size());
		
		PatientCaseList result = new PatientCaseList(this.patientId, newRootCase.getReExamInfo(), false);
		this.detailPAList.put(newRootCase.getId(), result);
		return result;
	}
	
	@Override
	public PatientCaseEntity createDetailCase(PatientCaseStatus status) {
		if (isListRootCase) {
			throw new PamaException("Cannot create new detail exam without a parent case");
		}
		if (status == null) {
			throw new PamaException("Cannot create new detail exam without status");
		}
		if (creatingDetailExamCase != null) {
			throw new PamaException("Cannot create new exam while editing and not sumit yet");
		}
		
		// create new instance and update + add to list when call update method
		this.creatingDetailExamCase = new PatientCaseEntity(DateUtils.convertDateDataType(GregorianCalendar.getInstance().getTime()), status);

		// set default value from previous version for re exam
		if (PatientCaseStatus.EXAM == status) {
			for (PatientCaseEntity pe : patientCases) {
				if (PatientCaseStatus.EXAM == pe.getStatusEnum() && pe.getSurgeryCatagoryNames() != null && !pe.getSurgeryCatagoryNames().isEmpty()) {
					PatientCaseEntity previousVersion = getLastExamHaveStatus(PatientCaseStatus.EXAM);
					creatingDetailExamCase.setServiceNames(previousVersion.getServiceNames());
					creatingDetailExamCase.setPrognosticCatagoryNames(previousVersion.getPrognosticCatagoryNames());
					creatingDetailExamCase.setDiagnoseCatagoryNames(previousVersion.getDiagnoseCatagoryNames());
					creatingDetailExamCase.setSurgeryCatagoryNames(previousVersion.getSurgeryCatagoryNames() );
				}
			}
		}
		
		return creatingDetailExamCase;
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
		
		// validate input data
		if (isListRootCase) {
			throw new PamaException("Cannot update new detail exam without a parent case");
		}
		// validate date before update
		if (PatientCaseStatus.EXAM == paCaseEntity.getStatusEnum()) {
			if (surList == null || surList.isEmpty()) {
				PatientCaseEntity lastExam = getLastExamHaveStatus(PatientCaseStatus.EXAM);
				if (lastExam == null) {
					throw new InvalidParameterException("Phải chọn phẩu thuật");
				}
			}
		}
		PatientCaseEntity sampleEmtity = new PatientCaseEntity(paCaseEntity.getDateAsText(), paCaseEntity.getStatusEnum());
		sampleEmtity.updateData(drCat == null ? null : drCat.getId(), Catagory.getListName(serviceList), Catagory.getListName(progCatList), prognosticOtherText,
				Catagory.getListName(diagCatList), diagOtherText, noteFromPa, noteFromDr, Catagory.getListName(surList), surgeryNote,
				surgeryDate, complication, beauty, smallSurgery, drAdvice, nextApp,
				appPurpose, appNote);
		
		// validate ok then update data
		if (creatingDetailExamCase != null && creatingDetailExamCase == paCaseEntity) {
			patientCases.add(0, paCaseEntity);
			// set id after add to list
			paCaseEntity.setId(patientCases.size());
			
		}
		paCaseEntity.updateData(drCat == null ? null : drCat.getId(), Catagory.getListName(serviceList), Catagory.getListName(progCatList), prognosticOtherText,
				Catagory.getListName(diagCatList), diagOtherText, noteFromPa, noteFromDr, Catagory.getListName(surList), surgeryNote,
				surgeryDate, complication, beauty, smallSurgery, drAdvice, nextApp,
				appPurpose, appNote);
	}
	
	@Override
	public boolean isLastCreatedExam(PatientCaseEntity selectedEntity) {
		if (patientCases == null || patientCases.isEmpty()) {
			return false;
		}
		return patientCases.get(0) == selectedEntity;
	}
	
	@Override
	public boolean isCreatingExam(PatientCaseEntity entity) {
		return this.creatingDetailExamCase != null && this.creatingDetailExamCase == entity;
	}

	@Override
	public Object[] getAllVersions() {
		Object[] allVers = patientCases.toArray();
    	if (creatingDetailExamCase != null) {
    		LinkedList<Object> allVersList = new LinkedList<Object>(Arrays.asList(allVers));
    		allVersList.addFirst(creatingDetailExamCase);
    		allVers = allVersList.toArray();
    	}
    	return allVers;
	}
	
	@Override
	public IPatientCaseList getSubList(int parentId) {
		return detailPAList.get(parentId);
	}
	
	@Override
	public PatientCaseEntity getLastExamHaveStatus(PatientCaseStatus... caseStatus) {
		if (caseStatus == null || caseStatus.length == 0) {
			if (this.creatingDetailExamCase != null) {
				return this.creatingDetailExamCase;
			}
			return patientCases.isEmpty() ? null : patientCases.get(0);
		}
		
		if (patientCases == null || patientCases.isEmpty()) {
			return null;
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
	public boolean isNotHaveAnyEntity() {
		return this.creatingDetailExamCase == null && (this.patientCases == null || this.patientCases.isEmpty());
	}

	/**
	 * Summary manage
	 */
	@Override
	public String getSummary(boolean includeConsult) {
		String result = summary;
		if (StringUtils.isBlank(result)) {
			result = generateSummary(this.patientCases, includeConsult);
		}
		return result;
	}

	/**
	 * 1) Lần khám (xx/xx/xxxx):
	 * 
	 * 1.1) Lần tái khám (xx/xx/xxxx): 
	 * 
	 * 1.2) Lần tái khám (xx/xx/xxxx): 
	 * 
	 * 2) Tư vấn (xx/xx/xxxx)
	 * 
	 * 3) Lần khám (xx/xx/xxxx)
	 * 
	 * 3.1) Lần tái khám (xx/xx/xxxx)
	 *  
	 * @param patientCases
	 * @param includeConsult 
	 * @return
	 */
	// TODO update logic
	private static final String generateSummary(List<PatientCaseEntity> patientCases, boolean includeConsult) {
		if (patientCases == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		int index = 0, indexReExam = 0;
		String dateEvent;
		for (PatientCaseEntity pE : patientCases) {
			dateEvent = pE.getDateAsText();
			if (PatientCaseStatus.CONSULT == pE.getStatusEnum()) {
				if (!includeConsult) {
					continue;
				}
				++index;
				result.append(String.valueOf(index)).append(") Tư vấn (").append(dateEvent).append(")")
					  .append(System.lineSeparator());
			} else if (pE.getReExamInfo() != null) {
				++index;
				result.append(String.valueOf(index)).append(") Khám (").append(dateEvent).append(")")
				      .append(System.lineSeparator());
				indexReExam = 0;
				for (PatientCaseEntity reE : pE.getReExamInfo()) {
					++indexReExam;
					result.append(String.valueOf(index)).append(".").append(String.valueOf(indexReExam))
						       .append(") Lần tái khám (").append(reE.getDateAsText()).append(")")
				          .append(System.lineSeparator());
				}
			}
		}
		return result.toString();
	}

	String setSummary(String summary) {
		this.summary = summary;
		return this.summary;
	}

	@Override
	public String getSurgerySummary() {
		final String postText = ", ";
		StringBuilder result = new StringBuilder();
		TreeSet<String> surgeryCurr = new TreeSet<String>();
		for (PatientCaseEntity entity : this.patientCases) {
			if (entity.getSurgeryCatagoryNames() == null) {
				continue;
			}
			
			for (String surgery : entity.getSurgeryCatagoryNames()) {
				if (surgeryCurr.contains(surgery)) {
					continue;
				}
				surgeryCurr.add(surgery);
				result.append(surgery).append(postText);
			}
		}
		
		if (result.length() > postText.length()) {
			return result.substring(0, result.length() - postText.length());
		}
		
		return result.toString();
	}
	
	/**
	 * Manage images
	 */
	@Override
	public ISurgeryImageList getSurgeryImageList() {
		return this.surgeryImageList;
	}

}