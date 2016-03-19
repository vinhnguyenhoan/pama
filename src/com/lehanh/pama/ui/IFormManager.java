package com.lehanh.pama.ui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IFormManager {

	IFormManager addAllControlFromComposite(Composite composite, Control... ignoreControls);

	IFormManager addAllControlFromComposite(Composite composite, boolean ignoreLabel, Control... ignoreControls);

	IFormManager enableAll(boolean enabled);

	IFormManager setEditableAll(boolean editable);

	IFormManager clearFormData();

	IFormManager addCreateButtons(Button... addNewBtn);

	IFormManager addEditButtons(Button... editBtn);

	IFormManager addSaveButtons(Button... saveBtn);

	IFormManager addCancelButtons(Button... cancelBtn);

	IFormManager cancel(boolean stillEdit);

	IFormManager saved(boolean stillEdit);

	IFormManager edit();

	IFormManager addNew();

}