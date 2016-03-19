package com.lehanh.pama.catagory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.db.dao.CatagoryDao;

public class CatagoryManager implements ICatagoryManager {

	private final Map<CatagoryType, TreeMap<Long, Catagory>> allCatagory = new HashMap<CatagoryType, TreeMap<Long, Catagory>>();
	
	@Override
	public void initialize() throws SQLException {
		List<Catagory> allCat = new CatagoryDao().loadAllCatagory();
		loadCatList(allCat);
		
		// Load internal catagory
		InternalCatagory internalCat;
		for (CatagoryType catType : CatagoryType.values()) {
			Catagory createdCat = catType.createCatalog(null);
			if ((createdCat instanceof InternalCatagory)) {
				internalCat = (InternalCatagory) createdCat;
				List<Catagory> catDatas = internalCat.createCatagoryList();
				loadCatList(catDatas);
			}
		}
	}

	private void loadCatList(List<Catagory> allCat) {
		TreeMap<Long, Catagory> catByType;
		for (Catagory cat : allCat) {
			catByType = allCatagory.get(cat.getType());
			if (catByType == null) {
				catByType = new TreeMap<Long, Catagory>();
				allCatagory.put(cat.getType(), catByType);
			}
			catByType.put(cat.getId(), cat);
			if (cat instanceof IContainJsonDataCatagory) {
				((IContainJsonDataCatagory) cat).updateFromText();
			}
		}
	}

	@Override
	public TreeMap<Long, Catagory> getCatagoryByType(CatagoryType catType) {
		TreeMap<Long, Catagory> catByType = this.allCatagory.get(catType);
		if (catByType == null) {
			return new TreeMap<Long, Catagory>();
		}
		return new TreeMap<Long, Catagory>(catByType);
	}

	@Override
	public List<Catagory> getSubCatagorysByParent(Long parentId, CatagoryType type) {
		final List<Catagory> result = new LinkedList<Catagory>();
		
		CatagoryType parentType = type.getParentCatagoryType();
		if (parentType == null) {
			return result;
		}
		TreeMap<Long, Catagory> catByType = this.allCatagory.get(parentType);
		if (catByType == null) {
			return result;
		}
		
		Catagory parentCat = catByType.get(parentId);
		if (parentCat == null) {
			return result;
		}
		
		TreeMap<Long, Catagory> subList = allCatagory.get(type);
		if (subList == null) {
			return result;
		}
		Catagory subCatagory;
		for (Long subId : parentCat.getRefIds()) {
			subCatagory = subList.get(subId);
			result.add(subCatagory);
		}
		
		return result;
	}
	
	@Override
	public void saveCatagory(Catagory cat) throws SQLException {
		if (cat instanceof IContainJsonDataCatagory) {
			cat.setOtherDataText(((IContainJsonDataCatagory) cat).toOtherDataAsText());
		}
		if (cat.getId() <= 0) {
			new CatagoryDao().insert(cat);
		} else {
			new CatagoryDao().update(cat);
		}
		allCatagory.get(cat.getType()).put(cat.getId(), cat);
	}
	
	public static void main(String[] args) {
		final GsonBuilder builder = new GsonBuilder();
	    final Gson gson = builder.create();
	    DrugCatagory d = new DrugCatagory();
	    d.setTimePerDay(1);
	    String json = gson.toJson(d);
	    System.out.println(json); 
	    
	    HashMap<String, Object> jsonObj = gson.fromJson(json, HashMap.class);
	    System.out.println(jsonObj);
	    
	    json = gson.toJson(jsonObj);
	    System.out.println(json); 
	    
	    json = null;
	    System.out.println(gson.fromJson(json, HashMap.class));
	}
}