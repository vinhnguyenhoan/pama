package com.lehanh.pama.catagory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.db.dao.CatagoryDao;

public class CatagoryManager implements ICatagoryManager {

	private final Map<CatagoryType, List<Catagory>> allCatagory = new HashMap<CatagoryType, List<Catagory>>();
	
	@Override
	public void initialize() throws SQLException {
		new CatagoryDao().loadAllCatagory();
		// TODO automatic load catagory tree 
		// Load 
	}
}
