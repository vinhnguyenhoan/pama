package com.lehanh.pama.ui;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;

import com.lehanh.pama.ui.clientcustomer.ClientCustomerPerspective;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private PamaWorkbenchErrorHandler workbenchErrorHandler;

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return ClientCustomerPerspective.ID;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#getWorkbenchErrorHandler()
	 */
	@Override
	public synchronized AbstractStatusHandler getWorkbenchErrorHandler() {
		if (workbenchErrorHandler == null) {
			workbenchErrorHandler = new PamaWorkbenchErrorHandler();
		}
		return workbenchErrorHandler;
	}

}
