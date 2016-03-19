package com.lehanh.pama.ui.patientcase;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

public class PatientCaseView extends ViewPart {
	
	public static final String ID = "com.lehanh.pama.patientCaseView";
	private Text text_2;
	private Text text_3;
	private Table table;
	private Text text_4;
	private Text text_5;
	private Text text;
	private Text text_1;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	public PatientCaseView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		
		Composite composite = new Composite(sc, SWT.NONE);
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
		
		TableComboViewer tableComboViewer = new TableComboViewer(composite_1, SWT.BORDER);
		TableCombo tableCombo = tableComboViewer.getTableCombo();
		tableCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tableCombo.setSize(147, 21);
		
		CCombo combo = new CCombo(composite_1, SWT.BORDER);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.setSize(174, 21);
		
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
		
//		CTabFolder tabFolder = new CTabFolder(composite_2, SWT.BORDER);
//		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 8));
//		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
//		
//		CTabItem tbtmGhiChT = new CTabItem(tabFolder, SWT.NONE);
//		tbtmGhiChT.setText("Ghi ch\u00FA t\u1EEB kh\u00E1ch:");
//		
//		text = new Text(tabFolder, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
//		tbtmGhiChT.setControl(text);
//		
//		CTabItem tbtmGhiChT_1 = new CTabItem(tabFolder, SWT.NONE);
//		tbtmGhiChT_1.setText("Ghi ch\u00FA t\u1EEB B\u00E1c s\u1EF9:");
//		
//		text_1 = new Text(tabFolder, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
//		tbtmGhiChT_1.setControl(text_1);
		
		TableComboViewer tableComboViewer_2 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_2 = tableComboViewer_2.getTableCombo();
		tableCombo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		text_7 = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 7));
		
		text = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 7));
		
		CLabel lblChnon = new CLabel(composite_2, SWT.NONE);
		lblChnon.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblChnon.setText("Tri\u1EC7u ch\u1EE9ng:");
		
		CLabel lblKhc = new CLabel(composite_2, SWT.NONE);
		lblKhc.setText("Ngo\u00E0i Danh s\u00E1ch:");
		lblKhc.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		TableComboViewer tableComboViewer_1 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_1 = tableComboViewer_1.getTableCombo();
		tableCombo_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		text_4 = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_text_4.widthHint = 60;
		text_4.setLayoutData(gd_text_4);
		new Label(composite_2, SWT.NONE);
		
		CLabel label_3 = new CLabel(composite_2, SWT.NONE);
		label_3.setText("Ch\u1EA9n \u0111o\u00E1n:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		CLabel label_4 = new CLabel(composite_2, SWT.NONE);
		label_4.setText("Ngo\u00E0i Danh s\u00E1ch:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		
		TableComboViewer tableComboViewer_4 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_4 = tableComboViewer_4.getTableCombo();
		tableCombo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_5 = new Text(composite_2, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_5 = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_text_5.widthHint = 60;
		text_5.setLayoutData(gd_text_5);
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
		
		TableComboViewer tableComboViewer_3 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_3 = tableComboViewer_3.getTableCombo();
		tableCombo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_6 = new Text(composite_2, SWT.BORDER);
		GridData gd_text_6 = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 2);
		gd_text_6.widthHint = 60;
		text_6.setLayoutData(gd_text_6);
		
		text_2 = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
		gd_text_2.heightHint = 80;
		text_2.setLayoutData(gd_text_2);
		
		text_3 = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.horizontalSpacing = 0;
		gl_composite_3.marginWidth = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
		
		TableViewer tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_table.heightHint = 60;
		table.setLayoutData(gd_table);
		
		Label lblNgyPt = new Label(composite_3, SWT.NONE);
		lblNgyPt.setText("Ng\u00E0y PT:");
		
		CalendarCombo calendarCombo = new CalendarCombo(composite_3, SWT.NONE);
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.verticalSpacing = 0;
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		
		Button btnCheckButton = new Button(composite_4, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheckButton.setText("Bi\u1EBFn ch\u1EE9ng");
		
		Button button = new Button(composite_4, SWT.CHECK);
		button.setText("H\u00E0i l\u00F2ng");
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
		
		CalendarCombo calendarCombo_1 = new CalendarCombo(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);
		
		Label label_5 = new Label(composite_6, SWT.NONE);
		label_5.setText("\u0110\u1EC3:");
		
		ComboViewer comboViewer = new ComboViewer(composite_6, SWT.NONE);
		Combo combo_1 = comboViewer.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblGhiCh_1 = new Label(composite_6, SWT.NONE);
		lblGhiCh_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGhiCh_1.setText("Ghi ch\u00FA:");
		
		text_8 = new Text(composite_6, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCap = new Button(composite_6, SWT.NONE);
		btnCap.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnCap.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1));
		btnCap.setText("T\u01B0 v\u1EA5n");
		
		Button btnBnhnMi = new Button(composite_6, SWT.NONE);
		btnBnhnMi.setText("B\u1EC7nh \u00E1n m\u1EDBi");
		btnBnhnMi.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		Button btnA = new Button(composite_6, SWT.NONE);
		btnA.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		btnA.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnA.setText("T\u00E1i kh\u00E1m");
		
		Button btnChnhSa = new Button(composite_6, SWT.NONE);
		btnChnhSa.setText("Ch\u1EC9nh s\u1EEDa");
		btnChnhSa.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		Button btnLu = new Button(composite_6, SWT.NONE);
		btnLu.setText("L\u01B0u");
		btnLu.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		
		Button btnB = new Button(composite_6, SWT.NONE);
		btnB.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		btnB.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnB.setText("H\u1EE7y");

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
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
