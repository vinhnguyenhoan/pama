package com.lehanh.pama.catagory;

import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.util.PamaHome;

public class ServiceCatagory extends SimpleCatagory {

	private static final long serialVersionUID = 1164478787375164065L;

	private List<DiagnoseCatagory> diagnoseList;
	
	public ServiceCatagory() {
		super(CatagoryType.SERVICE);
	}

	public void addDiagnose(DiagnoseCatagory diagnose) {
		super.addRef(diagnose.getId());
	}
	
	public List<DiagnoseCatagory> getDiagnoseList() {
		if (getRefIds().isEmpty()) {
			return new LinkedList<DiagnoseCatagory>();
		}
//		PamaHome.getService(ICatagoryManager).
		// TODO
		return null;
	}
}