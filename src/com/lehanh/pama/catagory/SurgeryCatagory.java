package com.lehanh.pama.catagory;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.util.JsonMapper;

public class SurgeryCatagory extends Catagory implements IContainJsonDataCatagory, IJsonDataObject {

	@Expose
	@SerializedName("advice")
	private String advice;
	
	public SurgeryCatagory() {
		super(CatagoryType.SURGERY);
	}

	SurgeryCatagory(Long id, CatagoryType catagoryType) {
		super(id, CatagoryType.SURGERY);
	}

	@Override
	public void updateFromText() {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> otherData = JsonMapper.fromJson(getOtherDataText(), HashMap.class);
		if (otherData == null) {
			return;
		}
		advice = (String) otherData.get("advice");
	}

	@Override
	public String toOtherDataAsText() {
		return JsonMapper.toJson(this);
	}

	public String getAdvice() {
		return advice;
	}
}