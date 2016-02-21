package com.lehanh.pama.ui.clientcustomer;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ClientCustomerPerspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "com.lehanh.pama.clientCustomerPerspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.addStandaloneView(UserSearchView.ID,  false, IPageLayout.LEFT, 0.75f, editorArea);
		layout.addStandaloneView(TodayUserQueueView.ID, false, IPageLayout.RIGHT, 0.25f, editorArea);
	}
}
