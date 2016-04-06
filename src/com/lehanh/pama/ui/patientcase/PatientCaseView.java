package com.lehanh.pama.ui.patientcase;

import java.security.InvalidParameterException;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;
import com.lehanh.pama.patientcase.AppointmentSchedule;
import com.lehanh.pama.patientcase.IPatientCaseList;
import com.lehanh.pama.patientcase.IPatientManager;
import com.lehanh.pama.patientcase.IPatientViewPartListener;
import com.lehanh.pama.patientcase.MedicalPersonalInfo;
import com.lehanh.pama.patientcase.Patient;
import com.lehanh.pama.patientcase.PatientCaseEntity;
import com.lehanh.pama.patientcase.PatientCaseStatus;
import com.lehanh.pama.ui.PamaFormUI;
import com.lehanh.pama.ui.util.CatagoryToUIText;
import com.lehanh.pama.util.PamaHome;

import static com.lehanh.pama.ui.util.UIControlUtils.*;

public class PatientCaseView extends PamaFormUI implements IPatientViewPartListener, IPatientView {
	
	public static final String ID = "com.lehanh.pama.patientCaseView";
	private Text smallSurgeryText;
	private Text drAdviceText;
	private Table surgerySelectedTable;
	private Text prognosticOtherText;
	private Text diagnoseOtherText;
	private Text noteFromDrText;
	private Text surgeryNoteText;
	private Text noteFromPaText;
	private Text appNoteText;
	private CCombo drCombo;
	private TableComboViewer examVersionTComboViewer;

	private TableComboViewer serviceTComboViewer;
	private TableComboViewer prognosticTComboViewer;
	private TableComboViewer diagnoseTComboViewer;
	private TableComboViewer surgeryTComboViewer;

	private CDateTime surgeryDateCDate;
	private Button complicationCheckBtn;
	private Button beautyBut;
	
	private Button adviceBtn;
	private Button newCaseBtn;
	private Button reExamBtn;
	private Button updateBtn;
	private Button saveBtn;
	private Button cancelBtn;
	private CDateTime nextAppCDate;
	private CCombo appPurposrCombo;
	
	private Composite composite;

	private static Color grey;
	
	private ICatagoryManager catManager;
	
	private IPatientManager paManager;
	
	private ExamVersionComboViewer examCombo;
	private PatientCaseCatagoryComboViewer serviceCatComboViewer;
	private PatientCaseCatagoryComboViewer surgeryCatComboViewer;
	private PatientCaseCatagoryComboViewer diagnoseCatComboViewer;
	private PatientCaseCatagoryComboViewer prognosticCatComboViewer;
	
	public PatientCaseView() {
		catManager = (ICatagoryManager) PamaHome.getService(ICatagoryManager.class);
		paManager = (IPatientManager) PamaHome.getService(IPatientManager.class);
		paManager.addPaListener(this);
	}

	@Override
	public void createFormUI(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		this.composite = new Composite(sc, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginHeight = 3;
		gl_composite.verticalSpacing = 3;
		composite.setLayout(gl_composite);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginHeight = 3;
		gl_composite_1.verticalSpacing = 3;
		composite_1.setLayout(gl_composite_1);
		
		CLabel lblCho = new CLabel(composite_1, SWT.NONE);
		lblCho.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblCho.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		lblCho.setSize(147, 21);
		lblCho.setText("Ch\u1ECDn l\u1EA7n kh\u00E1m - t\u00E1i kh\u00E1m:");
		
		CLabel lblBcST = new CLabel(composite_1, SWT.NONE);
		lblBcST.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBcST.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		lblBcST.setSize(78, 21);
		lblBcST.setText("B\u00E1c s\u1EF9 t\u01B0 v\u1EA5n:");
		
		this.examVersionTComboViewer = new TableComboViewer(composite_1, SWT.BORDER | SWT.READ_ONLY);
		TableCombo examVersionTCombo = examVersionTComboViewer.getTableCombo();
		examVersionTCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		examVersionTCombo.setSize(147, 21);
		
		this.drCombo = new CCombo(composite_1, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		drCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		drCombo.setSize(174, 21);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(4, false);
		gl_composite_2.verticalSpacing = 3;
		gl_composite_2.marginHeight = 3;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		CLabel lblNewLabel_1 = new CLabel(composite_2, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(128, 0, 0));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 4, 1));
		lblNewLabel_1.setText("Th\u00F4ng tin t\u01B0 v\u1EA5n - th\u0103m kh\u00E1m");
		
		Label label = new Label(composite_2, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		
		CLabel lblDchVThm = new CLabel(composite_2, SWT.NONE);
		lblDchVThm.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDchVThm.setBounds(0, 0, 61, 21);
		lblDchVThm.setText("D\u1ECBch v\u1EE5:");
		new Label(composite_2, SWT.NONE);
		
		Label lblGhiChu = new Label(composite_2, SWT.NONE);
		lblGhiChu.setText("Ghi ch\u00FA t\u1EEB kh\u00E1ch:");
		
		Label lblGhiChT = new Label(composite_2, SWT.NONE);
		lblGhiChT.setText("Ghi ch\u00FA t\u1EEB b\u00E1c s\u1EF9:");
		
		this.serviceTComboViewer = new TableComboViewer(composite_2, SWT.BORDER | SWT.READ_ONLY);
		TableCombo serviceTCombo = serviceTComboViewer.getTableCombo();
		serviceTCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		noteFromPaText = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		noteFromPaText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 7));
		
		noteFromDrText = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		noteFromDrText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 7));
		
		CLabel lblChnon = new CLabel(composite_2, SWT.NONE);
		lblChnon.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblChnon.setText("Tri\u1EC7u ch\u1EE9ng:");
		
		CLabel lblKhc = new CLabel(composite_2, SWT.NONE);
		lblKhc.setText("Ngo\u00E0i Danh s\u00E1ch:");
		lblKhc.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		this.prognosticTComboViewer = new TableComboViewer(composite_2, SWT.BORDER | SWT.READ_ONLY);
		TableCombo prognosticTCombo = prognosticTComboViewer.getTableCombo();
		prognosticTCombo.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		prognosticOtherText = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_prognosticOtherText = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_prognosticOtherText.widthHint = 60;
		prognosticOtherText.setLayoutData(gd_prognosticOtherText);
		new Label(composite_2, SWT.NONE);
		
		CLabel label_3 = new CLabel(composite_2, SWT.NONE);
		label_3.setText("Ch\u1EA9n \u0111o\u00E1n:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		CLabel label_4 = new CLabel(composite_2, SWT.NONE);
		label_4.setText("Ngo\u00E0i Danh s\u00E1ch:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		this.diagnoseTComboViewer = new TableComboViewer(composite_2, SWT.BORDER | SWT.READ_ONLY);
		TableCombo diagnoseTCombo = diagnoseTComboViewer.getTableCombo();
		diagnoseTCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		diagnoseOtherText = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_diagnoseOtherText = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_diagnoseOtherText.widthHint = 60;
		diagnoseOtherText.setLayoutData(gd_diagnoseOtherText);
		new Label(composite_2, SWT.NONE);
		
		CLabel lblNewLabel_2 = new CLabel(composite_2, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(128, 0, 0));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 4, 1));
		lblNewLabel_2.setText("Th\u00F4ng tin ph\u1EA9u thu\u1EADt ");
		
		Label label_1 = new Label(composite_2, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		
		CLabel lblPhuThut = new CLabel(composite_2, SWT.NONE);
		lblPhuThut.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		GridData gd_lblPhuThut = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblPhuThut.widthHint = 78;
		lblPhuThut.setLayoutData(gd_lblPhuThut);
		lblPhuThut.setBounds(0, 0, 61, 21);
		lblPhuThut.setText("Ph\u1EA9u thu\u1EADt:");
		
		Label lblGhiCh = new Label(composite_2, SWT.NONE);
		lblGhiCh.setText("Ghi ch\u00FA:");
		
		CLabel lblThThut = new CLabel(composite_2, SWT.NONE);
		lblThThut.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblThThut.setText("Th\u1EE7 thu\u1EADt:");
		
		CLabel lblLiKhuynCa = new CLabel(composite_2, SWT.NONE);
		lblLiKhuynCa.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLiKhuynCa.setText("L\u1EDDi khuy\u00EAn c\u1EE7a b\u00E1c s\u1EF9:");
		
		this.surgeryTComboViewer = new TableComboViewer(composite_2, SWT.BORDER | SWT.READ_ONLY);
		TableCombo surgeryTCombo = surgeryTComboViewer.getTableCombo();
		surgeryTCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		surgeryNoteText = new Text(composite_2, SWT.BORDER);
		GridData gd_surgeryNoteText = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_surgeryNoteText.widthHint = 60;
		surgeryNoteText.setLayoutData(gd_surgeryNoteText);
		
		smallSurgeryText = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_smallSurgeryText = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
		gd_smallSurgeryText.heightHint = 80;
		smallSurgeryText.setLayoutData(gd_smallSurgeryText);
		
		drAdviceText = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		drAdviceText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.horizontalSpacing = 0;
		gl_composite_3.marginWidth = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
		
		TableViewer tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		surgerySelectedTable = tableViewer.getTable();
		GridData gd_surgerySelectedTable = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_surgerySelectedTable.heightHint = 60;
		surgerySelectedTable.setLayoutData(gd_surgerySelectedTable);
		
		Label lblNgyPt = new Label(composite_3, SWT.NONE);
		lblNgyPt.setText("Ng\u00E0y PT:");
		
		this.surgeryDateCDate = new CDateTime(composite_3, CDT.BORDER | CDT.SPINNER);
		surgeryDateCDate.setNullText("Ngày mổ");
		surgeryDateCDate.setPattern("dd/MM/yyyy");
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.verticalSpacing = 0;
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		
		this.complicationCheckBtn = new Button(composite_4, SWT.CHECK);
		complicationCheckBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		complicationCheckBtn.setText("Bi\u1EBFn ch\u1EE9ng");
		
		this.beautyBut = new Button(composite_4, SWT.CHECK);
		beautyBut.setText("H\u00E0i l\u00F2ng");
		new Label(composite_4, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		
		Label label_2 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayout(new GridLayout(10, false));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		
		Label lblNgyHn = new Label(composite_6, SWT.NONE);
		lblNgyHn.setText("Ng\u00E0y h\u1EB9n:");
		
		this.nextAppCDate = new CDateTime(composite_6, CDT.BORDER | CDT.SPINNER);
		nextAppCDate.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 9, 1));
		nextAppCDate.setNullText("Ngày hẹn");
		nextAppCDate.setPattern("dd/MM/yyyy");
		
		Label label_5 = new Label(composite_6, SWT.NONE);
		label_5.setText("\u0110\u1EC3:");
		
		this.appPurposrCombo = new CCombo(composite_6, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		appPurposrCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblGhiCh_1 = new Label(composite_6, SWT.NONE);
		lblGhiCh_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGhiCh_1.setText("Ghi ch\u00FA:");
		
		appNoteText = new Text(composite_6, SWT.BORDER);
		appNoteText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		this.adviceBtn = new Button(composite_6, SWT.NONE);
		adviceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				advice();
			}
		});
		adviceBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		adviceBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1));
		adviceBtn.setText("T\u01B0 v\u1EA5n");
		
		this.newCaseBtn = new Button(composite_6, SWT.NONE);
		newCaseBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newCase();
			}
		});
		newCaseBtn.setText("B\u1EC7nh \u00E1n m\u1EDBi");
		newCaseBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		this.reExamBtn = new Button(composite_6, SWT.NONE);
		reExamBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reExam();
			}
		});
		reExamBtn.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		reExamBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		reExamBtn.setText("T\u00E1i kh\u00E1m");
		
		this.updateBtn = new Button(composite_6, SWT.NONE);
		updateBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update();
			}
		});
		updateBtn.setText("Ch\u1EC9nh s\u1EEDa");
		updateBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		this.saveBtn = new Button(composite_6, SWT.NONE);
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
		saveBtn.setText("L\u01B0u");
		saveBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		this.cancelBtn = new Button(composite_6, SWT.NONE);
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancel();
			}
		});
		cancelBtn.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		cancelBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		cancelBtn.setText("H\u1EE7y");

		/*
	     * Set the absolute size of the child child.setSize(400, 400);
	     */
	    // Set the child as the scrolled content of the ScrolledComposite
	    sc.setContent(composite);

	    // Set the minimum size
	    sc.setMinSize(composite.getSize().x, composite.getSize().y);

	    // Expand both horizontally and vertically
	    sc.setExpandHorizontal(true);
	    sc.setExpandVertical(true);
	    sc.layout();
	}

	@Override
	public void organizeUIComponent() {
		getFormManager().addAllControlFromComposite(composite, true)
						.addCreateButtons(newCaseBtn, adviceBtn, reExamBtn)
							.addEditButtons(updateBtn).addSaveButtons(saveBtn).addCancelButtons(cancelBtn)
						.setEditableAll(false)
						.cancel(paManager.getCurrentPatient() != null)
						// disable all button at first
						.setEnableAllButtons(false)
						;
		grey = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		
		// initial versions
		this.examCombo = new ExamVersionComboViewer(this.examVersionTComboViewer, grey);
		examVersionTComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				PatientCaseEntity model = (PatientCaseEntity) ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (model == null || examCombo.getSelectedEntity() == model) {
					return;
				}
				viewData(model);
			}
		});

		// initial Combo values
		initialCombo(drCombo, catManager.getCatagoryByType(CatagoryType.DR).values(), "Chọn BS", 0,
				new CatagoryToUIText());
		initialCombo(appPurposrCombo, catManager.getCatagoryByType(CatagoryType.APPOINTMENT).values(), "Chọn", 0,
				new CatagoryToUIText());
		
		this.surgeryCatComboViewer = new PatientCaseCatagoryComboViewer(catManager, grey, surgeryTComboViewer, CatagoryType.SURGERY);
		this.diagnoseCatComboViewer = new PatientCaseCatagoryComboViewer(catManager, grey, diagnoseTComboViewer, CatagoryType.DIAGNOSE, surgeryCatComboViewer);
		this.prognosticCatComboViewer = new PatientCaseCatagoryComboViewer(catManager, grey, prognosticTComboViewer, CatagoryType.PROGNOSTIC, diagnoseCatComboViewer, surgeryCatComboViewer);
		this.serviceCatComboViewer = new PatientCaseCatagoryComboViewer(catManager, true, grey, serviceTComboViewer, CatagoryType.SERVICE, prognosticCatComboViewer, diagnoseCatComboViewer, surgeryCatComboViewer);
	}

	private void viewData(Patient patient) {
		if (patient == null) {
			return;
		}
		
		MedicalPersonalInfo mInfo = patient.getMedicalPersonalInfo();
		// initial versions combo
		examVersionTComboViewer.setInput(mInfo.getPatientCaseList());
		IPatientCaseList paList = mInfo.getPatientCaseList();
		if (paList.isEmptyVersions()) {
			getFormManager().cancel(false);
			return;
		}

		final PatientCaseEntity lastExam = paList.getLastExamByStatus(PatientCaseStatus.values());
		// clear form and allow edit form
		getFormManager().cancel(true)
						// after cancel then enable again to selectable versions list
						.setEditable(true, examVersionTComboViewer.getTableCombo());
		// show exam
		viewData(lastExam);
	}
	
	private void viewData(PatientCaseEntity model) {
		if (model == null) {
			return;
		}
		
		this.examCombo.selectionChanged(model);
		selectServiceCatagory(model);
		
		selectComboById(this.drCombo, model.getDrId());
		setText(this.prognosticOtherText, model.getPrognosticOther());
		setText(this.diagnoseOtherText, model.getDiagnoseOther());
		setText(this.noteFromPaText, model.getNoteFromClient());
		setText(this.noteFromDrText, model.getNoteFromDr());
		setText(this.surgeryNoteText, model.getSurgeryNote());
		this.surgeryDateCDate.setSelection(model.getSurgeryDate());
		this.complicationCheckBtn.setSelection(model.isComplication());
		this.beautyBut.setSelection(model.isBeautiful());
		setText(this.smallSurgeryText, model.getSmallSurgery());
		setText(this.drAdviceText, model.getAdviceFromDr());
		
		this.viewData(model.getAppoSchedule());
		
		//getFormManager().edit();
	}

	private void selectServiceCatagory(PatientCaseEntity model) {
		// Remain order of selection
		this.serviceCatComboViewer.selectionChanged(model.getServiceNames());
		this.prognosticCatComboViewer.selectionChanged(model.getPrognosticCatagoryNames());
		this.diagnoseCatComboViewer.selectionChanged(model.getDiagnoseCatagoryNames());
		this.surgeryCatComboViewer.selectionChanged(model.getSurgeryCatagoryNames());
	}

	private void viewData(AppointmentSchedule appoSchedule) {
		if (appoSchedule == null) {
			return; // sure that appointment controls cleared date before
		}
		this.nextAppCDate.setSelection(appoSchedule.getAppointmentDate());
		selectComboById(this.appPurposrCombo, appoSchedule.getAppointmentCatagory() == null ? null : appoSchedule.getAppointmentCatagory().getId());
		setText(this.appNoteText, appoSchedule.getNote());
	}
	
	private void cancel() {
		paManager.cancelEditingPatientCase();
		// show again patient info as a viewing status
		viewData(paManager.getCurrentPatient());
	}

	@SuppressWarnings("unchecked")
	private void save() {
		try {
			paManager.updatePatientCase(this.examCombo.getSelectedEntity(),
										(DrCatagory) getValueFromCombo(this.drCombo),
										(List<ServiceCatagory>) this.serviceCatComboViewer.getMultiSelectionCatList(),
										(List<PrognosticCatagory>) this.prognosticCatComboViewer.getMultiSelectionCatList(),
										this.prognosticOtherText.getText(),
										(List<DiagnoseCatagory>) this.diagnoseCatComboViewer.getMultiSelectionCatList(),
										this.diagnoseOtherText.getText(),
										this.noteFromPaText.getText(),
										this.noteFromDrText.getText(),
										(List<SurgeryCatagory>) this.surgeryCatComboViewer.getMultiSelectionCatList(),
										this.surgeryNoteText.getText(),
										this.surgeryDateCDate.getSelection(),
										this.complicationCheckBtn.getSelection(),
										this.beautyBut.getSelection(),
										this.smallSurgeryText.getText(),
										this.drAdviceText.getText(),
										this.nextAppCDate.getSelection(),
										(AppointmentCatagory) getValueFromCombo(this.appPurposrCombo),
										this.appNoteText.getText());
			
		} catch (InvalidParameterException e) {
			MessageBox dialog = 
				  new MessageBox(composite.getShell(), SWT.ICON_ERROR | SWT.OK);
			dialog.setText("Lổi nhập liệu");
			dialog.setMessage(e.getMessage());
			dialog.open();
			return;
		}
		
		getFormManager().saved(paManager.getCurrentPatient() != null);
	}

	private void update() {
		// do nothing for data
		getFormManager().edit();
	}

	private void reExam() {
		// do nothing for data
		newAction(PatientCaseStatus.RE_EXAM);
	}

	private void newCase() {
		// do nothing for data
		newAction(PatientCaseStatus.SURGERY);
	}

	private void advice() {
		// do nothing for data
		newAction(PatientCaseStatus.CONSULT);
	}

	private void newAction(PatientCaseStatus status) {
		this.paManager.createEmptyCase(status);
		
		// view patient and view lasest exam is creating exam case
		viewData(this.paManager.getCurrentPatient());
		// switch form to editing -> enable all controls
		update();
		// make sure disable exam combo
		examVersionTComboViewer.getTableCombo().setEnabled(false);
	}

	@Override
	public void setFocus() {
		viewData(paManager.getCurrentPatient());
		// TODO handle viewpart focus out while editing form
		// TODO handle viewpart focus in while it is not visible
	}

	@Override
	public void patientChanged(Patient oldPa, Patient newPa) {
		viewData(newPa);
	}

	@Override
	public boolean isEditing() {
		return this.saveBtn.getEnabled();
	}

}