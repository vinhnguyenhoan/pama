package com.lehanh.pama.catagory;

import java.util.LinkedList;
import java.util.List;

public class SpiritLevel extends Catagory implements InternalCatagory {
	
	public SpiritLevel() {
		super(CatagoryType.SPIRIT_LEVEL);
	}

	SpiritLevel(Long id, CatagoryType catagoryType) {
		super(id, catagoryType);
	}

	@Override
	public List<Catagory> createCatagoryList() {
		List<Catagory> result = new LinkedList<Catagory>();
		long index = 1;
		result.add(this.getType().createCatalog(index++, "1", "Mức 1"));
		result.add(this.getType().createCatalog(index++, "2", "Mức 2"));
		result.add(this.getType().createCatalog(index++, "3", "Mức 3"));
		result.add(this.getType().createCatalog(index++, "4", "Mức 4"));
		
		return result;
	}
}
