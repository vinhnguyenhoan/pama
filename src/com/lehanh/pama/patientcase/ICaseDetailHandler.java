package com.lehanh.pama.patientcase;

import java.util.List;
import java.util.Map;

import com.lehanh.pama.catagory.Catagory;

public interface ICaseDetailHandler {

	void handleCaseDetail(ISurgeryImageList imageList, int indexRoot, int indexDetail, int groupId, int caseDetailId,
			List<Catagory> allSurCat, Map<String, Map<String, IImageInfo>> allPicInfos);

}
