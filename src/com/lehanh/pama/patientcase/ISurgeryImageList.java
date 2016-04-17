package com.lehanh.pama.patientcase;

import java.util.List;

public interface ISurgeryImageList {

	Long getPatientId();
	
	void iteratorCaseDetail(ICaseDetailHandler caseDetailHandler);

	void deletedImages(List<String> imageNameToDelete);

	IImageInfo addImage(int groupId, int detailId, String sugeryName, String extension);

}
