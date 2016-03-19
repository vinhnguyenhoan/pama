package com.lehanh.pama.ui.patientcase;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PatientPerspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "com.lehanh.pama.patientPerspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
	    IFolderLayout folder = layout.createFolder("view", IPageLayout.TOP , 1, editorArea);
	    folder.addView(PatientInfoView.ID);
	    folder.addView(PatientCaseView.ID);
	    folder.addView(DetailImageView.ID);
	    folder.addView(PrescriptionView.ID);
	    
	    layout.getViewLayout(PatientInfoView.ID).setCloseable(false);
	    layout.getViewLayout(PatientCaseView.ID).setCloseable(false);
	    layout.getViewLayout(DetailImageView.ID).setCloseable(false);
	    layout.getViewLayout(PrescriptionView.ID).setCloseable(false);
	}
}
