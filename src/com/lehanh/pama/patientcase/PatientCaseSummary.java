package com.lehanh.pama.patientcase;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.annotations.Expose;
import com.lehanh.pama.IJsonDataObject;

public class PatientCaseSummary implements Serializable, IJsonDataObject {

	/**
	 * Auto gen
	 */
	private static final long serialVersionUID = 7265743622218960408L;

	@Expose
	private String summary;

	private List<PatientCaseEntity> patientCase;

	public PatientCaseSummary() {
	}
	
	public PatientCaseSummary(String text) {
		this.summary = text;
	}

	/**
	 * FieldBindingStrategy is field so we can customize getter
	 */
	public String getSummary(boolean includeConsult) {
		String result = summary;
		if (StringUtils.isBlank(result)) {
			result = generateSummary(this.patientCase, includeConsult);
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

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void updatePatientCase(List<PatientCaseEntity> patientCase) {
		this.patientCase = patientCase;
	}
	
}
