package com.lehanh.pama.ui.patientcase;

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class SurgeryImageListSelectionDialog extends ElementListSelectionDialog {

	private Object[] fElements;
	protected String dir;

	public SurgeryImageListSelectionDialog(Shell parent, ILabelProvider renderer) {
		super(parent, renderer);
		super.setSize(50, 7);
	}

	/**
	 * Sets the elements of the list.
	 * 
	 * @param elements
	 *            the elements of the list.
	 */
	public void setElements(Object[] elements) {
		fElements = elements;
	}

	protected Control createDialogArea(final Composite parent) {
		Composite contents = (Composite) this.createDialogAreaParent(parent);

		createMessageArea(contents);
		createFilterText(contents);
		createFilteredList(contents);

		setListElements(fElements);

		setSelection(getInitialElementSelections().toArray());

		final Composite fileCom = new Composite(parent, SWT.NONE);
		fileCom.setLayout(new GridLayout(6, true));
		new Label(fileCom, SWT.NONE).setText("File: ");

		// Create the text box extra wide to show long paths
		final Text imagePathText = new Text(fileCom, SWT.BORDER);
		imagePathText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dir = imagePathText.getText();
				validateCurrentSelection();
			}
		});
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 4;
		imagePathText.setLayoutData(data);

		// Clicking the button will allow the user
		// to select a directory
		Button button = new Button(fileCom, SWT.PUSH);
		button.setText("Chọn...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				FileDialog dlg = new FileDialog(parent.getShell());

				// Set the initial filter path according
				// to anything they've selected or typed in
				dlg.setFilterPath(imagePathText.getText());

				// Change the title bar text
				dlg.setText("Ảnh phẩu thuật");

				String[] filterExt = { "*.*", "*.png", "*.jpeg", "*.jpg", "*.PNG", "*.JPEG", "*.JPG" };
				dlg.setFilterExtensions(filterExt);

				// Calling open() will open and run the dialog.
				// It will return the selected directory, or
				// null if user cancels
				SurgeryImageListSelectionDialog.this.dir = dlg.open();
				if (dir != null) {
					// Set the text box to the new selection
					imagePathText.setText(dir);
				}
			}
		});

		return contents;
	}

	private Control createDialogAreaParent(Composite parent) {
		// create a composite with standard margins and spacing
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(composite);
		return composite;
	}

	protected boolean validateCurrentSelection() {
		boolean result = super.validateCurrentSelection();
		if (!result) {
			return false;
		}
		
		IStatus status;
		boolean isExistFile = dir != null ? new File(dir).exists() : false;
		if (isExistFile) {
	        status = new Status(IStatus.OK, PlatformUI.PLUGIN_ID,
	                IStatus.OK, "", null);
		} else {
			status = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID,
					IStatus.ERROR, dir != null ? "File không tồn tại" : "Chưa chọn file", null);
		}

        updateStatus(status);

        return result;
    }
	
	String filePath() {
    	return this.dir;
    }
}
