package com.lehanh.pama.ui;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private ApplicationActionBarAdvisor actionBar;

	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    	this.actionBar = new ApplicationActionBarAdvisor(configurer);
        return this.actionBar;
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setTitle("LeHanh Cosmetic Patient Manager");
    }

    @Override
    public void postWindowCreate() {
    	IStatusLineManager statusLine = this.actionBar.getStatusLine();
    	//((Composite) ((StatusLineManager) statusLine).getControl()).setLayout(new StatusLineLayout());

    	
    	Shell shell = getWindowConfigurer().getWindow().getShell();
        shell.setMaximized(true);
        super.postWindowCreate();
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