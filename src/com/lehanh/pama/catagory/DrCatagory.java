package com.lehanh.pama.catagory;

public class DrCatagory extends Catagory {

	private static final long serialVersionUID = 1164478787375164065L;

	public DrCatagory() {
		super(CatagoryType.DR);
	}

	DrCatagory(Long id, CatagoryType catagoryType) {
		super(id, catagoryType);
	}

}