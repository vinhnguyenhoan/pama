package com.lehanh.pama.catagory;

public class DrugCatagory extends Catagory {

	private static final long serialVersionUID = -5352501831971610470L;

	
	public DrugCatagory() {
		super(CatagoryType.DRUG);
	}

	protected void synOtherDataObjAndText(boolean synOtherDataObjFromText) {
		// for subclass
	}
}
