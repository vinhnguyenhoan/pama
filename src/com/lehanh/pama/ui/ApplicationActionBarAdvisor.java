package com.lehanh.pama.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
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
		Application.patientTextContribution = new TextStatusContribution("PA_INFO"); //$NON-NLS-1$
		Application.patientTextContribution.initialText = "---";
		statusLine.appendToGroup(StatusLineManager.BEGIN_GROUP, Application.patientTextContribution);
	}
    
    IStatusLineManager getStatusLine() {
    	return this.statusLine;
    }
    
    private class StatusLineLayout extends Layout {
		
    	private static final int GAP = 3;
		
		private final StatusLineLayoutData DEFAULT_DATA = new StatusLineLayoutData();

		public Point computeSize(Composite composite, int wHint, int hHint,
				boolean changed) {

			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}

			int totalWidth = 0;
			int maxHeight = 0;
			int totalCnt = 0;
			
			// TODO loop all pama status controls
			Point e = Application.patientTextContribution.computeSize(DEFAULT_DATA.widthHint, DEFAULT_DATA.heightHint,
						changed);
			totalWidth += e.x;
			totalCnt++;
			maxHeight = Math.max(maxHeight, e.y);
				
			if (totalCnt > 0) {
				totalWidth += (totalCnt - 1) * GAP;
			}
			if (totalWidth <= 0) {
				totalWidth = maxHeight * 4;
			}
			return new Point(totalWidth, maxHeight);
		}

		public void layout(Composite composite, boolean flushCache) {

			if (composite == null) {
				return;
			}

			// StatusLineManager skips over the standard status line widgets
			// in its update method. There is thus a dependency
			// between the layout of the standard widgets and the update method.

			// Make sure cancel button and progress bar are before
			// contributions.

			Rectangle rect = composite.getClientArea();
			Control[] children = composite.getChildren();
			int count = children.length;

			int ws[] = new int[count];

			int h = rect.height;
			int totalWidth = -GAP;
			
			// TODO loop all
			int width = Application.patientTextContribution.computeSize(DEFAULT_DATA.widthHint, h, flushCache).x;
			ws[0] = width;
			totalWidth += width + GAP;

			int diff = rect.width - totalWidth;
			ws[0] += diff; // make the first StatusLabel wider

			// Check against minimum recommended width
			final int msgMinWidth = rect.width / 3;
			if (ws[0] < msgMinWidth) {
				diff = ws[0] - msgMinWidth;
				ws[0] = msgMinWidth;
			} else {
				diff = 0;
			}

			// Take space away from the contributions first.
			for (int i = count - 1; i >= 0 && diff < 0; --i) {
				int min = Math.min(ws[i], -diff);
				ws[i] -= min;
				diff += min + GAP;
			}

			int x = rect.x;
			int y = rect.y;

			/*
			 * Workaround for Linux Motif: Even if the progress bar and
			 * cancel button are not set to be visible ad of width 0, they
			 * still draw over the first pixel of the editor contributions.
			 * 
			 * The fix here is to draw the progress bar and cancel button
			 * off screen if they are not visible.
			 */
			Application.patientTextContribution.setBounds(GAP, y, ws[0], h);
			if (ws[0] > 0) {
				x += ws[0] + GAP;
			}

		}
	}
}
