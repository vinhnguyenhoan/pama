package com.lehanh.pama.ui.patientcase;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lehanh.pama.patientcase.IPatientManager;
import com.lehanh.pama.patientcase.IPatientViewPartListener;
import com.lehanh.pama.patientcase.Patient;
import com.lehanh.pama.patientcase.PatientCaseEntity;
import com.lehanh.pama.ui.PamaFormUI;
import com.lehanh.pama.ui.patientcase.patientimage.ImageCanvasPainter;
import com.lehanh.pama.ui.util.ObjectToUIText;
import com.lehanh.pama.util.PamaHome;

public class DetailImageView extends PamaFormUI implements IPatientViewPartListener, IPatientView {
	
	public static final String ID = "com.lehanh.pama.detailImageView";
	
	private SurgeryGallary gallery;

	private ImageCanvasPainter canvasPainter;

	private Button linkButton;

	private Button deleteButton;

	private Button addImageButton;

	private IPatientManager paManager;

	public DetailImageView() {
		paManager = (IPatientManager) PamaHome.getService(IPatientManager.class);
		paManager.addPaListener(this);
	}

	@Override
	public void createFormUI(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite galleryCom = new Composite(sashForm, SWT.NONE);
		GridLayout gl_galleryCom = new GridLayout(1, false);
		gl_galleryCom.verticalSpacing = 0;
		gl_galleryCom.marginWidth = 0;
		gl_galleryCom.marginHeight = 0;
		gl_galleryCom.horizontalSpacing = 0;
		galleryCom.setLayout(gl_galleryCom);
		galleryCom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		this.gallery = new SurgeryGallary(galleryCom, SWT.V_SCROLL | SWT.MULTI, new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite gaButtonsCom = new Composite(galleryCom, SWT.NONE);
		GridLayout gl_gaButtonsCom = new GridLayout(3, false);
		gl_gaButtonsCom.marginWidth = 0;
		gl_gaButtonsCom.marginHeight = 0;
		gl_gaButtonsCom.horizontalSpacing = 0;
		gaButtonsCom.setLayout(gl_gaButtonsCom);
		gaButtonsCom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		this.linkButton = new Button(gaButtonsCom, SWT.NONE);
		linkButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		linkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				link();
			}
		});
		linkButton.setImage(SWTResourceManager.getImage("C:\\Users\\vinhhnguyen\\Desktop\\insert_link.png"));

		this.deleteButton = new Button(gaButtonsCom, SWT.NONE);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		deleteButton.setImage(SWTResourceManager.getImage("C:\\Users\\vinhhnguyen\\Desktop\\symbol_delete.png"));
		
		this.addImageButton = new Button(gaButtonsCom, SWT.NONE);
		addImageButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		addImageButton.setImage(SWTResourceManager.getImage("C:\\Users\\vinhhnguyen\\Desktop\\plus_orange.png"));
		addImageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				add();
			}
		});

		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		
		Canvas canvas = new Canvas(composite_1, SWT.NONE);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		this.canvasPainter = new ImageCanvasPainter(canvas);
		
		sashForm.setWeights(new int[] {1, 3});
		// TODO Auto-generated method stub
	}
	
	@Override
	public void organizeUIComponent() {
		// initial actions
		
		
		// TODO Auto-generated method stub
	}
	
	private void add() {
		// TODO Auto-generated method stub
		
	}

	private void delete() {
		// TODO Auto-generated method stub
		
	}

	private void link() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientViewPartListener#patientChanged(com.lehanh.pama.patientcase.Patient, com.lehanh.pama.patientcase.Patient)
	 * View all surgery images from new selected patient
	 */
	@Override
	public void patientChanged(Patient oldPa, Patient newPa) {
		Map<String, Map<String, Map<String, Object>>> allImages = newPa.getMedicalPersonalInfo().getPatientCaseList().getAllImageGroup(this.showGroupFromPA);
		gallery.updateGallery(allImages);
		canvasPainter.clearImages();
	}
	private ObjectToUIText<PatientCaseEntity, Integer> showGroupFromPA = new ObjectToUIText<PatientCaseEntity, Integer>() {
		
		@Override
		public String showUI(PatientCaseEntity object) {
			return "Lần khám thứ " + String.valueOf(object.getId()) + " - " + object.getDateAsText();
		}
		
		@Override
		public Integer getIdForUI(PatientCaseEntity object) {
			return object.getId();
		}
	};
	
	
}