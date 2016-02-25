package com.lehanh.pama.ui.patientcase;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;


public class PatientInfoView extends ViewPart {
	
	public static final String ID = "com.lehanh.pama.patientInfoView";
	
	private Text text;
	private Text text_3;
	private Text text_4;
	private Text text_6;
	private Text text_1;
	private Text text_2;
	private Text text_5;
	private Text text_7;
	public PatientInfoView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		composite_1.setLayout(new GridLayout(3, false));
		
		CLabel label = new CLabel(composite_1, SWT.BORDER);
		label.setImage(SWTResourceManager.getImage("D:\\LeHanh\\soft\\Backup LHS\\20151025\\Run\\LHS\\Images\\Local\\PatientPic\\17176.jpg"/*, 120, 150*/));
		
		Composite composite_2 = new Composite(composite_1, SWT.BORDER);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_2.setLayout(new GridLayout(4, false));
		
		CLabel lblId = new CLabel(composite_2, SWT.NONE);
		lblId.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblId.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblId.setText("ID:");
		
		text = new Text(composite_2, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1);
		gd_text.widthHint = 100;
		text.setLayoutData(gd_text);
		
		CLabel lblHTn = new CLabel(composite_2, SWT.NONE);
		lblHTn.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblHTn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblHTn.setText("H\u1ECD & t\u00EAn:");
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNewLabel = new CLabel(composite_2, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setText("\u0110\u1ECBa ch\u1EC9:");
		
		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		
		CalendarCombo calendarCombo = new CalendarCombo(composite_4, SWT.NONE);
		CLabel lblTui = new CLabel(composite_4, SWT.NONE);
		lblTui.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblTui.setText("26 tu\u1ED5i");
		
		Button btnN = new Button(composite_4, SWT.RADIO);
		btnN.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		btnN.setText("N\u1EEF");
		
		Button btnNam = new Button(composite_4, SWT.RADIO);
		btnNam.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		btnNam.setText("Nam");
		
		CLabel lblD = new CLabel(composite_2, SWT.NONE);
		lblD.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblD.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblD.setText("D\u0110:");
		
		text_3 = new Text(composite_2, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblinThoi = new CLabel(composite_2, SWT.NONE);
		lblinThoi.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblinThoi.setText("\u0110i\u1EC7n tho\u1EA1i:");
		
		text_4 = new Text(composite_2, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNghNghip = new CLabel(composite_2, SWT.NONE);
		lblNghNghip.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNghNghip.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		lblNghNghip.setText("Email:");
		
		text_5 = new Text(composite_2, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNghNghip_1 = new CLabel(composite_2, SWT.NONE);
		lblNghNghip_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNghNghip_1.setText("Ngh\u1EC1 nghi\u1EC7p");
		
		text_7 = new Text(composite_2, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_1, SWT.BORDER);
		composite_3.setLayout(new GridLayout(2, false));
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		CLabel lblMcTinh = new CLabel(composite_3, SWT.NONE);
		lblMcTinh.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMcTinh.setText("M\u1EE9c \u0111\u1ED9 tinh th\u1EA7n:");
		
		CCombo combo = new CCombo(composite_3, SWT.BORDER);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 90;
		combo.setLayoutData(gd_combo);
		
		CLabel lblGhiChV = new CLabel(composite_3, SWT.NONE);
		lblGhiChV.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblGhiChV.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblGhiChV.setText("Ghi ch\u00FA v\u1EC1 b\u1EC7nh nh\u00E2n:");
		
		text_6 = new Text(composite_3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		
		Composite composite_5 = new Composite(composite, SWT.NONE);
		composite_5.setLayout(new GridLayout(3, true));
		composite_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite_5, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setText("Ch\u1EC9nh s\u1EEDa");
		
		Button btnNewButton_1 = new Button(composite_5, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnNewButton_1.setText("H\u1EE7y");
		
		Button btnNewButton_2 = new Button(composite_5, SWT.NONE);
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnNewButton_2.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnNewButton_2.setText("L\u01B0u");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
