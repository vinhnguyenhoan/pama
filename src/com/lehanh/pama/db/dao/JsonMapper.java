package com.lehanh.pama.db.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lehanh.pama.IJsonDataObject;

public class JsonMapper {
	
	private static final GsonBuilder builder = new GsonBuilder();
	private static Gson gson;
	static {
		builder.excludeFieldsWithoutExposeAnnotation();
		gson = builder.create();
	}
	
	public static final String toJson(IJsonDataObject obj) {
		return gson.toJson(obj);
	}
	
	public static final <T> T fromJson(String json, Class<T> classT) {
		return gson.fromJson(json, classT);
	}
}