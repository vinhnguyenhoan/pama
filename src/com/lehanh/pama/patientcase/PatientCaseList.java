package com.lehanh.pama.patientcase;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.lehanh.pama.util.PamaException;

public class PatientCaseList implements IPatientCaseList {

	private final List<PatientCaseEntity> patientCases;
	
	private Comparator<? super PatientCaseEntity> descDateComparator = new Comparator<PatientCaseEntity>() {

		@Override
		public int compare(PatientCaseEntity p1, PatientCaseEntity p2) {
			try {
				Date d1 = p1.getDate();
				Date d2 = p1.getDate();
				return d2.compareTo(d1);
			} catch (ParseException e) {
				throw new PamaException(e);
			}
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
		return patientCases.toArray();
	}

}
