package com.lehanh.pama.ui.patientcase;

import java.security.InvalidParameterException;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.patientcase.IPatientManager;
import com.lehanh.pama.patientcase.IPatientViewPartListener;
import com.lehanh.pama.patientcase.MedicalPersonalInfo;
import com.lehanh.pama.patientcase.Patient;
import com.lehanh.pama.ui.PamaFormUI;
import com.lehanh.pama.ui.util.CatagoryToUIText;
import com.lehanh.pama.util.DateUtils;
import com.lehanh.pama.util.PamaHome;

import static com.lehanh.pama.ui.util.UIControlUtils.*;

public class PatientInfoView extends PamaFormUI implements IPatientViewPartListener, IPatientView {
	
	public static final String ID = "com.lehanh.pama.patientInfoView";
	
	private Text idText;
	private Text mobiText;
	private Text phoneText;
	private Text paNoteText;
	private Text nameText;
	private Text addText;
	private Text emailText;
	private Text careerText;
	private Text anamnesisText;
	private Text medicalHistoryText;
	private Text detailExamText;
	
	private CLabel ageLbl;
	private static final String AGE = "tu\u1ED5i";

	//private CalendarCombo birthDayCalendar;
	//private DateTime birthDayCalendar;
	private CDateTime birthDayCalendar;
	
	private CLabel paImageLabel;
	
	private CCombo paLevelCombo;

	private Composite composite;

	private Button addNewBtn;

	private Button editBtn;

	private Button saveBtn;

	private Button cancelBtn;
	
	private Composite buttonsComposite;
	
	private ICatagoryManager catManager;
	
	private IPatientManager paManager;

	private Button femaleRadio;
	private Button maleRadio;
	private static final String SEX_GROUP = "SEX_GROUP";
	
	public PatientInfoView() {
		catManager = (ICatagoryManager) PamaHome.getService(ICatagoryManager.class);
		paManager = (IPatientManager) PamaHome.getService(IPatientManager.class);
		paManager.addPaListener(this);
	}

	@Override
	public void createFormUI(Composite parent) {
		this.composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
		composite_1.setLayout(new GridLayout(3, false));
		
		this.paImageLabel = new CLabel(composite_1, SWT.BORDER);
		GridData gd_paImageLabel = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_paImageLabel.widthHint = 144;
		gd_paImageLabel.heightHint = 192;
		paImageLabel.setLayoutData(gd_paImageLabel);
//		paImageLabel.setImage(SWTResourceManager.getImage("D:\\LeHanh\\soft\\Backup LHS\\20151025\\Run\\LHS\\Images\\Local\\PatientPic\\17176.jpg"/*, 120, 150*/));
		
		Composite composite_2 = new Composite(composite_1, SWT.BORDER);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setLayout(new GridLayout(4, false));
		
		CLabel lblId = new CLabel(composite_2, SWT.NONE);
		lblId.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblId.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblId.setText("ID:");
		
		idText = new Text(composite_2, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_idText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_idText.widthHint = 100;
		idText.setLayoutData(gd_idText);
		
		CLabel lblHTn = new CLabel(composite_2, SWT.NONE);
		lblHTn.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblHTn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblHTn.setText("H\u1ECD & t\u00EAn:");
		
		nameText = new Text(composite_2, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNewLabel = new CLabel(composite_2, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setText("\u0110\u1ECBa ch\u1EC9:");
		
		addText = new Text(composite_2, SWT.BORDER);
		addText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNgySinh = new CLabel(composite_2, SWT.NONE);
		lblNgySinh.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNgySinh.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNgySinh.setText("Ng\u00E0y sinh:");
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		GridLayout gl_composite_4 = new GridLayout(4, false);
		gl_composite_4.verticalSpacing = 0;
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 3;
		composite_4.setLayout(gl_composite_4);
		
		//this.birthDayCalendar = new CalendarCombo(composite_4, SWT.CALENDAR);
		//this.birthDayCalendar = new DateTime (composite_4, SWT.CALENDAR);
		this.birthDayCalendar = new CDateTime(composite_4, CDT.BORDER | CDT.SPINNER);
		birthDayCalendar.setNullText("Nhập ngày");
		birthDayCalendar.setPattern("dd/MM/yyyy");
		
		this.ageLbl = new CLabel(composite_4, SWT.NONE);
		ageLbl.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		this.femaleRadio = new Button(composite_4, SWT.RADIO);
		femaleRadio.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		femaleRadio.setText("N\u1EEF");
		
		this.maleRadio = new Button(composite_4, SWT.RADIO);
		maleRadio.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		maleRadio.setText("Nam");
		
		CLabel lblD = new CLabel(composite_2, SWT.NONE);
		lblD.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblD.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblD.setText("D\u0110:");
		
		mobiText = new Text(composite_2, SWT.BORDER);
		mobiText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblinThoi = new CLabel(composite_2, SWT.NONE);
		lblinThoi.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblinThoi.setText("\u0110i\u1EC7n tho\u1EA1i:");
		
		phoneText = new Text(composite_2, SWT.BORDER);
		phoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNghNghip = new CLabel(composite_2, SWT.NONE);
		lblNghNghip.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNghNghip.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNghNghip.setText("Email:");
		
		emailText = new Text(composite_2, SWT.BORDER);
		emailText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNghNghip_1 = new CLabel(composite_2, SWT.NONE);
		lblNghNghip_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNghNghip_1.setText("Ngh\u1EC1 nghi\u1EC7p");
		
		careerText = new Text(composite_2, SWT.BORDER);
		careerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_1, SWT.BORDER);
		composite_3.setLayout(new GridLayout(2, false));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		CLabel lblMcTinh = new CLabel(composite_3, SWT.NONE);
		lblMcTinh.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMcTinh.setText("M\u0111 tinh th\u1EA7n:");
		
		this.paLevelCombo = new CCombo(composite_3, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
		GridData gd_paLevelCombo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_paLevelCombo.widthHint = 90;
		paLevelCombo.setLayoutData(gd_paLevelCombo);
		
		CLabel lblGhiChV = new CLabel(composite_3, SWT.NONE);
		lblGhiChV.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblGhiChV.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblGhiChV.setText("Ghi ch\u00FA v\u1EC1 b\u1EC7nh nh\u00E2n:");
		
		paNoteText = new Text(composite_3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		paNoteText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		Composite composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayout(new GridLayout(1, false));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		Label lblChiTitT = new Label(composite_6, SWT.NONE);
		lblChiTitT.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblChiTitT.setText("Chi ti\u1EBFt t\u01B0 v\u1EA5n - th\u0103m kh\u00E1m");
		
		detailExamText = new Text(composite_6, SWT.BORDER /*| SWT.READ_ONLY*/ | SWT.V_SCROLL | SWT.MULTI);
		detailExamText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_8 = new Composite(composite, SWT.NONE);
		composite_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_8.setLayout(new GridLayout(1, false));
		
		Label label_5 = new Label(composite_8, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		label_5.setText("Ti\u1EC1n c\u0103n:");
		
		anamnesisText = new Text(composite_8, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		anamnesisText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label label_6 = new Label(composite_8, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		label_6.setText("B\u1EC7nh s\u1EED:");
		
		medicalHistoryText = new Text(composite_8, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		medicalHistoryText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		
		this.buttonsComposite = new Composite(composite, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout(4, true));
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1));
		
		this.addNewBtn = new Button(buttonsComposite, SWT.NONE);
		addNewBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNew();
			}
		});
		addNewBtn.setText("Th\u00EAm m\u1EDBi");
		addNewBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		this.editBtn = new Button(buttonsComposite, SWT.NONE);
		editBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		editBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				edit();
			}
		});
		editBtn.setText("Ch\u1EC9nh s\u1EEDa");
		
		this.saveBtn = new Button(buttonsComposite, SWT.NONE);
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
		saveBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		saveBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		saveBtn.setText("L\u01B0u");
		
		this.cancelBtn = new Button(buttonsComposite, SWT.NONE);
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancel();
			}
		});
		cancelBtn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelBtn.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		cancelBtn.setText("H\u1EE7y");
	}
	
	@Override
	public void organizeUIComponent() {
		getFormManager().addAllControlFromComposite(composite, true, buttonsComposite)
						.addCreateButtons(addNewBtn).addEditButtons(editBtn).addSaveButtons(saveBtn).addCancelButtons(cancelBtn)
						.addRadioGroup(SEX_GROUP, this.maleRadio, this.femaleRadio).defaultRadios(this.femaleRadio)
						.setEditableAll(false)
						.cancel(paManager.getCurrentPatient() != null);
		
		// init paLevelCombo
		initialCombo(paLevelCombo, catManager.getCatagoryByType(CatagoryType.SPIRIT_LEVEL).values(), "Chưa xác định", 0,
				new CatagoryToUIText());
		
		// listen modified detailExamText
		listenModifiedByClient(detailExamText);
		
		birthDayCalendar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (birthDayCalendar.getSelection() == null) {
					return;
				}
				ageLbl.setText(DateUtils.calculateAge(birthDayCalendar.getSelection()) + " " + AGE);
				ageLbl.getParent().layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
	}

	private void handleData(Patient patient) {
		if (patient == null) {
			return;
		}
		setText(idText, String.valueOf(patient.getId()));
		setText(mobiText, patient.getCellPhone());
		setText(phoneText, patient.getPhone());
		setText(nameText, patient.getName());
		setText(addText, patient.getAddress());
		setText(emailText, patient.getEmail());
		setText(careerText, patient.getCareer());

		//birthDayCalendar.setDate(patient.getBirthDay());
		if (patient.getBirthDay() != null) {
//			int[] date = DateUtils.getDate(patient.getBirthDay());
//			birthDayCalendar.setDate(date[0], date[1], date[2]);
			birthDayCalendar.setSelection(patient.getBirthDay());
		}
		
		ageLbl.setText(patient.getAge() + " " + AGE);
		
		setText(paNoteText, patient.getNote());
		selectComboById(paLevelCombo, patient.getPatientLevel());
		
		MedicalPersonalInfo medicalPersonalInfo = patient.getMedicalPersonalInfo();
		setText(anamnesisText, medicalPersonalInfo.getAnamnesis());
		setText(medicalHistoryText, medicalPersonalInfo.getMedicalHistory());
		setText(detailExamText, medicalPersonalInfo.getPatientCaseSummary().getSummary(true));
		
		// TODO paImageLabel;
	}
	
	private void cancel() {
		// cancel form and clear form
		getFormManager().cancel(paManager.getCurrentPatient() != null);
		// revert if exist patient
		handleData(paManager.getCurrentPatient());
	}

	private void save() {
		int paLevel = -1;
		Catagory selectedPaLevel = (Catagory) getValueFromCombo(paLevelCombo);
		if (selectedPaLevel != null) {
			paLevel = selectedPaLevel.getId().intValue();
		}
		
		String detailExam = null;
		if (isChanged(detailExamText)) {
			detailExam = detailExamText.getText();
		}
		try {
			/*
			id, imagePath, name, address, birthDay, isFermale,
			cellPhone, phone, email, career, patientLevel, note, medicalHistory, anamnesis
			 */
			paManager.updatePatient(
					null, // TODO ImageName
					// personal info
					nameText.getText(), addText.getText(), birthDayCalendar.getSelection(),
					femaleRadio == getFormManager().getSelected(SEX_GROUP),
					mobiText.getText(), phoneText.getText(), emailText.getText(), careerText.getText(), paLevel, paNoteText.getText(),
					// medical info
					detailExam, medicalHistoryText.getText(), anamnesisText.getText());
			getFormManager().saved(paManager.getCurrentPatient() != null);
		} catch (InvalidParameterException e) {
			MessageBox dialog = 
				  new MessageBox(composite.getShell(), SWT.ICON_ERROR | SWT.OK);
			dialog.setText("Lổi nhập liệu");
			dialog.setMessage(e.getMessage());
			dialog.open();
		}
	}

	private void edit() {
		// do nothing for data
		getFormManager().edit();
	}

	private void addNew() {
		// do nothing for data
		getFormManager().addNew();
	}

	@Override
	public void setFocus() {
		// TODO
		handleData(paManager.getCurrentPatient());
	}

	@Override
	public void patientChanged(Patient oldPa, Patient newPa) {
		// TODO Auto-generated method stub
		handleData(newPa);
	}

	@Override
	public boolean isEditing() {
		return this.saveBtn.getEnabled();
	}

}
