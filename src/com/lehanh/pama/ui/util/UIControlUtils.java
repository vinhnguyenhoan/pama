package com.lehanh.pama.ui.util;

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
		int index = (Integer) combo.getData(ID_KEY + key);
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

	public static final boolean isDirty(Text text) {
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

}