package com.lehanh.pama.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.lehanh.pama.PamaApplication;
import com.lehanh.pama.patientcase.IPatientManager;
import com.lehanh.pama.patientcase.IPatientViewPartListener;
import com.lehanh.pama.patientcase.Patient;
import com.lehanh.pama.util.PamaHome;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication, PamaApplication, IPatientViewPartListener {

	private Properties appProperties;
	public static TextStatusContribution patientTextContribution;
	public static IStatusLineManager statusLine;
	public static ContributionItem statusLineItem;
	public static Label statusLineLabel;
	public static Text statusLineText;
	public static StatusLineContributionItem statusItem;

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		PamaHome.application = this;
		PamaHome.initialize();
		((IPatientManager) PamaHome.getService(IPatientManager.class)).addPaListener(this, Application.class.toString());
		Display display = PlatformUI.createDisplay();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}

	@Override
	public String getUserId() {
		// TODO getUserId
		return null;
	}

	@Override
	public String getPassword() {
		// TODO getPassword
		return null;
	}

	@Override
	public void setPassword(String pw) {
		// TODO setPassword
		
	}

	@Override
	public String getProperty(String name, String defaultValues) {
		if (appProperties == null) {
			initAppProperties();
		}
		String result = appProperties.getProperty(name);
		if (defaultValues != null && result == null) {
			result = defaultValues;
		}
		return result;
	}

	private void initAppProperties() {
		if (appProperties == null) {
			appProperties = new Properties();
			try {
				IPath path = Activator.getDefault().getStateLocation();
				if (!path.hasTrailingSeparator()) {
					path = path.addTrailingSeparator();
				}
				path = path.append("appliation.properties");
				File appPropertiesFile = path.toFile();
				if (!appPropertiesFile.exists()) {
					appPropertiesFile.createNewFile();
				}
				FileInputStream fis = new FileInputStream(appPropertiesFile);
				appProperties.load(fis);
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logError("Failed to load application.properties file", e);
			} catch (IOException e) {
				e.printStackTrace();
				logError("Failed to load application.properties file", e);
			}
		}
	}

	@Override
	public void logInfo(String message) {
		log(0, message, null, IStatus.INFO);
	}

	@Override
	public void logWarning(String message, Throwable t) {
		log(0, message, t, IStatus.WARNING);
	}

	@Override
	public void logError(String message, Throwable t) {
		log(0, message, t, IStatus.ERROR);
	}

	@Override
	public void logError(String message) {
		log(0, message, null, IStatus.ERROR);
	}

	public void log(int code, String message, Throwable t, int severity) {
		OperationStatus status = new OperationStatus(severity, Activator.PLUGIN_ID, code, message, t);
		Activator.getDefault().getLog().log(status);
		if (message != null) {
			System.out.println(message);
		}
		if (t != null) {
			t.printStackTrace();
		}
	}

	@Override
	public void patientChanged(Patient oldPa, Patient newPa, String[] callIds) {
		if (newPa == null) {
//			patientTextContribution.setText(StringUtils.EMPTY);
			statusItem.setText(StringUtils.EMPTY);
		} else {
//			patientTextContribution.setText(newPa.getName());
			statusItem.setText(newPa.getName());
		}
		//statusLine.update(true);
	}

	@Override
	public Image loadImage(String path) {
		return Activator.getImageDescriptor(path).createImage(); //$NON-NLS-1$
	}
}
