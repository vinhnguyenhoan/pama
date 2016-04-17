package com.lehanh.pama.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction exitAction;
	private Action newPatientCase;
	private Action serviceCat;
	private Action drugTemplateCat;
	private Action reportByService;
	private IStatusLineManager statusLine;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = ActionFactory.QUIT.create(window);
		exitAction.setText("Thoát"); 
		register(exitAction);
    	
    	newPatientCase = new Action("Bệnh nhân mới") {
    		
    		/* (non-Javadoc)
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				// TODO
			}
    	};
    	
    	serviceCat = new Action("Dịch vụ") {
    		
    		/* (non-Javadoc)
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				// TODO
			}
    	};
    	
    	drugTemplateCat = new Action("Toa thuốc mẩu") {
    		
    		/* (non-Javadoc)
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				// TODO
			}
    	};
    	
    	reportByService = new Action("Báo cáo theo dịch vụ") {
    		
    		/* (non-Javadoc)
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {
				// TODO
			}
    	};
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMM = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
    	menuBar.add(fileMM);
    	fileMM.add(exitAction);
		
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

    	MenuManager patientCase = new MenuManager("Bệnh án", null);
    	menuBar.add(patientCase);
    	patientCase.add(newPatientCase);
    	
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	MenuManager reportM = new MenuManager("Báo cáo", null);
    	menuBar.add(reportM);
    	reportM.add(reportByService);    	
    	
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

    	MenuManager catagoryM = new MenuManager("Chỉnh danh mục", null);
    	menuBar.add(catagoryM);
    	catagoryM.add(serviceCat);
    }
 
    @Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
    	this.statusLine = statusLine;
//    	((Composite) ((StatusLineManager) statusLine).getControl()).setLayout(new StatusLineLayout());
    	
    	final SeparatorContribution contributionSaparator = new SeparatorContribution();
    	
		statusLine.appendToGroup(StatusLineManager.BEGIN_GROUP, contributionSaparator);
//		Application.patientTextContribution = new TextStatusContribution("PA_INFO"); //$NON-NLS-1$
//		Application.patientTextContribution.initialText = "---";
//		Application.statusLine = statusLine;
//		statusLine.appendToGroup(StatusLineManager.BEGIN_GROUP, Application.patientTextContribution);
//		Application.statusLineItem = new ContributionItem() {
//
//			public void fill(Composite parent) {
//				Label label = new Label(parent, SWT.NONE);
//				label.setText("LABEL");
//				
//				Application.statusLineLabel = label;
//				Text text = new Text(parent, SWT.BORDER);
//				text.setText("TEXT");
//				Application.statusLineText = text;
//				Combo combo = new Combo(parent, SWT.NONE);
//				combo.setText("COMBO");
//			}
//		};
		Application.statusItem = new StatusLineContributionItem("LoggedInStatus");
		Application.statusItem.setText("Logged in");
		statusLine.add(Application.statusItem);
		statusLine.update(true);
	}
    
    IStatusLineManager getStatusLine() {
    	return this.statusLine;
    }
}
