package com.lehanh.pama;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;


public interface ICatagoryManager extends IService {
	
	TreeMap<Long, Catagory> getCatagoryByType(CatagoryType catType);

	List<Catagory> getSubCatagorysByParent(Long parentId, CatagoryType type);
	
	void saveCatagory(Catagory cat) throws SQLException;
}
