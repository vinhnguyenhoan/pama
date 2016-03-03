package com.lehanh.pama.catagory;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lehanh.pama.IJsonDataObject;
import com.lehanh.pama.util.PamaException;

public class Catagory implements Serializable, IJsonDataObject {

	private static final long serialVersionUID = 513034063043717183L;
	private Long id;
	private String name;
	private String desc;
	private final CatagoryType type;
	
	public static final String SEPARATE = "|";
	private String refIdsText; // Ex: 1|2|3
	private List<Long> refIds;
	
	private IJsonDataObject otherData;
	private String otherDataText;
	
	public Catagory(CatagoryType type) {
		this.type = type;
	}
	
	public Catagory(Long id, CatagoryType type) {
		this.id = id;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}

	public CatagoryType getType() {
		return this.type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRefIdsText() {
		return refIdsText;
	}

	public void setRefIdsText(String refIdsText) {
		this.refIdsText = refIdsText;
		this.refIdsText = StringUtils.remove(refIdsText, StringUtils.SPACE);
	}

	public void addRef(Long id) {
		if (id == null) {
			throw new PamaException("Cannot add null Ref id");
		}
		if (StringUtils.isBlank(refIdsText)) {
			refIdsText = String.valueOf(id);
		} else {
			refIdsText += SEPARATE + String.valueOf(id);
		}
		refIds = null;
	}
	
	public List<Long> getRefIds() {
		if (refIds == null) {
			refIds = new LinkedList<Long>();
			if (!StringUtils.isBlank(refIdsText)) {
				String[] refIdsA = refIdsText.split(SEPARATE);
				try {
					for (String refIDText : refIdsA) {
						refIds.add(Long.parseLong(refIDText));
					}
				} catch (NumberFormatException e) {
					throw new PamaException("Cannot generate long as text from Catalog getRefIds " + refIdsText, e);
				}
			}
		}
		return new LinkedList<Long>(refIds);
	}

	public IJsonDataObject getOtherData() {
		return otherData;
	}

	public void setOtherData(IJsonDataObject otherData) {
		this.otherData = otherData;
		this.synOtherDataObjAndText(false);
	}

	public String getOtherDataText() {
		return otherDataText;
	}

	public void setOtherDataText(String otherDataText) {
		this.otherDataText = otherDataText;
		this.synOtherDataObjAndText(true);
	}

	protected void synOtherDataObjAndText(boolean synOtherDataObjFromText) {
		// for subclass
	}
	
}