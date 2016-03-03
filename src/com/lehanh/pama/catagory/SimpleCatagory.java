package com.lehanh.pama.catagory;

import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.util.PamaException;

public class SimpleCatagory extends Catagory {

	private static final long serialVersionUID = 3821010386088343186L;

	public SimpleCatagory(CatagoryType type) {
		super(type);
	}
	
	public SimpleCatagory(Long id, CatagoryType type) {
		super(id, type);
	}

	@Override
	public IJsonDataObject getOtherData() {
		throw new PamaException("Service not have any other data");
	}

	@Override
	public void setOtherData(IJsonDataObject otherData) {
		throw new PamaException("Service not have any other data");
	}
}
