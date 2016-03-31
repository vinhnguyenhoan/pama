package com.lehanh.pama.ui.util;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

public class UIControlUtils {

	private static final String FIRST_TEXT = "FIRST_TEXT";
	private static final String ID_KEY = "ID_KEY";
	private static final String VALUE_KEY = "VALUE_KEY";
	private static final String DIRTY = "DIRTY";
	private static final String START_INDEX = "START_INDEX";

	public static final void initialCombo(CCombo combo, Iterable<?> listData, String firstText, int startIndex, ObjectToUIText objectToUIText) {
		int comboItemIndex = 0;
		
		if (firstText != null) {
			combo.add(firstText);
			combo.setData(FIRST_TEXT, firstText);
			comboItemIndex = 1;
		}
		
		for (Object value : listData) {
			if (objectToUIText != null) {
				combo.add(objectToUIText.showUI(value));
				combo.setData(ID_KEY + objectToUIText.getIdForUI(value), comboItemIndex);
			} else {
				combo.add(value.toString());
				combo.setData(ID_KEY + comboItemIndex, comboItemIndex);
			}
			combo.setData(VALUE_KEY + comboItemIndex, value);
			comboItemIndex++;
		}
		
		combo.setData(START_INDEX, startIndex);
		combo.select(startIndex);
	}
	
	public static final Object getValueFromCombo(CCombo combo) {
		int index = combo.getSelectionIndex();
		if (index < 0) {
			return null;
		}
		return combo.getData(VALUE_KEY + index);
	}

	public static void selectComboById(CCombo combo, Object key) {
		Integer index = (Integer) combo.getData(ID_KEY + key);
		if (index == null) {
			index = (Integer) combo.getData(START_INDEX);
		}
		if (index == null) {
			index = -1;
		}
		combo.select(index);
	}

	public static final void listenModifiedByClient(final Text text) {
		text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				text.setData(DIRTY, text.isFocusControl());
			}
		});
	}

	public static final boolean isChanged(Text text) {
		Boolean isDirty = (Boolean) text.getData(DIRTY);
		return isDirty != null && isDirty;
	}

	public static final void revert(CCombo combo) {
		Integer startIndex = (Integer) combo.getData(START_INDEX);
		if (startIndex == null) {
			return;
		}
		combo.select(startIndex);
	}

	public static void setText(Text textControl, String textContent) {
		if (textContent == null) {
			textControl.setText(StringUtils.EMPTY);
		} else {
			textControl.setText(textContent);
		}
	}

//	public static void initialCombo(TableComboViewer combo,
//			Collection<Catagory> values, String firstText, int startIndex,
//			CatagoryToUIText objectToUIText) {
//		
//		// set the content provider
//		combo.setContentProvider(ArrayContentProvider.getInstance());
//		
//		// set the label provider
//		combo.setLabelProvider(new SingleItemLabelProvider());
//
//		// load the data
//		combo.setInput(modelList);
//		
//		// add listener
//		combo.addSelectionChangedListener(new ItemSelected("Sample1"));

		
//		int comboItemIndex = 0;
//		
//		if (firstText != null) {
//			combo.add(firstText);
//			combo.setData(FIRST_TEXT, firstText);
//			comboItemIndex = 1;
//		}
//		
//		for (Object value : listData) {
//			if (objectToUIText != null) {
//				combo.add(objectToUIText.showUI(value));
//				combo.setData(ID_KEY + objectToUIText.getIdForUI(value), comboItemIndex);
//			} else {
//				combo.add(value.toString());
//				combo.setData(ID_KEY + comboItemIndex, comboItemIndex);
//			}
//			combo.setData(VALUE_KEY + comboItemIndex, value);
//			comboItemIndex++;
//		}
//		
//		combo.setData(START_INDEX, startIndex);
//		combo.select(startIndex);
//	}

}