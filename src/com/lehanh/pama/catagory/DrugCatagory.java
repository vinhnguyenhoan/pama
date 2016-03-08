package com.lehanh.pama.catagory;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.db.dao.JsonMapper;

public class DrugCatagory extends Catagory implements IContainJsonDataCatagory, IJsonDataObject {

	@Expose
	@SerializedName("timePerDay")
	private Integer timePerDay;
	
	public DrugCatagory() {
		super(CatagoryType.DRUG);
	}

	DrugCatagory(Long id, CatagoryType catagoryType) {
		super(id, catagoryType);
	}

	public int getTimePerDay() {
		return timePerDay;
	}

	public void setTimePerDay(int timePerDay) {
		this.timePerDay = timePerDay;
	}

	@Override
	public void updateFromText() {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> otherData = JsonMapper.fromJson(getOtherDataText(), HashMap.class);
		if (otherData == null) {
			return;
		}
		timePerDay = (Integer) otherData.get("timePerDay");
		// TODO other fields
	}

	@Override
	public String toOtherDataAsText() {
		return JsonMapper.toJson(this);
	}

}
