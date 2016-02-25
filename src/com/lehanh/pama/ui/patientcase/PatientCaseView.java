package com.lehanh.pama.ui.patientcase;

import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;

public class PatientCaseView extends ViewPart {
	
	public static final String ID = "com.lehanh.pama.patientCaseView";
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Table table;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
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
		GridLayout gl_composite_2 = new GridLayout(3, false);
		gl_composite_2.verticalSpacing = 3;
		gl_composite_2.marginHeight = 3;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		CLabel lblNewLabel_1 = new CLabel(composite_2, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(128, 0, 0));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		lblNewLabel_1.setText("Th\u00F4ng tin t\u01B0 v\u1EA5n");
		
		Label label = new Label(composite_2, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		CLabel lblDchVThm = new CLabel(composite_2, SWT.NONE);
		lblDchVThm.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDchVThm.setBounds(0, 0, 61, 21);
		lblDchVThm.setText("D\u1ECBch v\u1EE5 Th\u1EA9m m\u1EF9:");
		
		CLabel lblGhiChT = new CLabel(composite_2, SWT.NONE);
		lblGhiChT.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblGhiChT.setText("Ghi ch\u00FA t\u1EEB kh\u00E1ch:");
		
		CLabel lblGhiChT_1 = new CLabel(composite_2, SWT.NONE);
		lblGhiChT_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblGhiChT_1.setText("Ghi ch\u00FA t\u1EEB b\u00E1c s\u1EF9:");
		
		TableComboViewer tableComboViewer_2 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_2 = tableComboViewer_2.getTableCombo();
		tableCombo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3);
		gd_text.heightHint = 80;
		text.setLayoutData(gd_text);
		
		text_1 = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		
		CLabel lblChnon = new CLabel(composite_2, SWT.NONE);
		lblChnon.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblChnon.setText("Ch\u1EA9n \u0111o\u00E1n:");
		
		TableComboViewer tableComboViewer_1 = new TableComboViewer(composite_2, SWT.BORDER);
		TableCombo tableCombo_1 = tableComboViewer_1.getTableCombo();
		tableCombo_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		
		CLabel lblNewLabel_2 = new CLabel(composite_2, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(128, 0, 0));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 3, 1));
		lblNewLabel_2.setText("Th\u00F4ng tin ph\u1EA9u thu\u1EADt ");
		
		Label label_1 = new Label(composite_2, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		CLabel lblPhuThut = new CLabel(composite_2, SWT.NONE);
		lblPhuThut.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		GridData gd_lblPhuThut = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblPhuThut.widthHint = 78;
		lblPhuThut.setLayoutData(gd_lblPhuThut);
		lblPhuThut.setBounds(0, 0, 61, 21);
		lblPhuThut.setText("Ph\u1EA9u thu\u1EADt:");
		
		CLabel lblThThut = new CLabel(composite_2, SWT.NONE);
		lblThThut.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblThThut.setText("Th\u1EE7 thu\u1EADt:");
		
		CLabel lblLiKhuynCa = new CLabel(composite_2, SWT.NONE);
		lblLiKhuynCa.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLiKhuynCa.setText("L\u1EDDi khuy\u00EAn c\u1EE7a b\u00E1c s\u1EF9:");
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(1, false);
		gl_composite_3.horizontalSpacing = 0;
		gl_composite_3.marginWidth = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		
		TableComboViewer tableComboViewer_3 = new TableComboViewer(composite_3, SWT.BORDER);
		TableCombo tableCombo_3 = tableComboViewer_3.getTableCombo();
		tableCombo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TableViewer tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.heightHint = 60;
		table.setLayoutData(gd_table);
		
		text_2 = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_text_2.heightHint = 80;
		text_2.setLayoutData(gd_text_2);
		
		text_3 = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		CLabel lblNewLabel_3 = new CLabel(composite, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(128, 0, 0));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel_3.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_3.setText("B\u1EC7nh \u00E1n");
		
		Label label_2 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_4 = new Composite(composite, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(4, false);
		gl_composite_4.marginWidth = 3;
		gl_composite_4.marginHeight = 3;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		composite_5.setBounds(0, 0, 64, 64);
		composite_5.setLayout(new GridLayout(2, false));
		
		CLabel lblNewLabel = new CLabel(composite_5, SWT.NONE);
		lblNewLabel.setText("M\u1EA1ch");
		
		text_4 = new Text(composite_5, SWT.BORDER);
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_text_4.widthHint = 50;
		text_4.setLayoutData(gd_text_4);
		
		CLabel lblNhit_1 = new CLabel(composite_5, SWT.NONE);
		lblNhit_1.setText("Nhi\u1EC7t \u0111\u1ED9:");
		
		text_7 = new Text(composite_5, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblNhit = new CLabel(composite_5, SWT.NONE);
		lblNhit.setText("Huy\u1EBFt \u00E1p:");
		
		text_5 = new Text(composite_5, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		CLabel lblCnNng = new CLabel(composite_5, SWT.NONE);
		lblCnNng.setText("C\u00E2n n\u1EB7ng:");
		
		text_6 = new Text(composite_5, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpTinCn = new Group(composite_4, SWT.NONE);
		grpTinCn.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		grpTinCn.setLayout(new GridLayout(2, false));
		grpTinCn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpTinCn.setText("Ti\u1EC1n c\u0103n");
		
		text_8 = new Text(grpTinCn, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		
		Button btnCheckButton = new Button(grpTinCn, SWT.CHECK);
		btnCheckButton.setText("Cao huy\u1EBFt \u00E1p");
		
		Button btnCheckButton_2 = new Button(grpTinCn, SWT.CHECK);
		btnCheckButton_2.setText("T\u0103ng \u0111\u01B0\u1EDDng huy\u1EBFt");
		
		Button btnCheckButton_1 = new Button(grpTinCn, SWT.CHECK);
		btnCheckButton_1.setText("Vi\u00EAm gan");
		
		Group grpBnhS = new Group(composite_4, SWT.NONE);
		grpBnhS.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		grpBnhS.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_grpBnhS = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpBnhS.widthHint = 190;
		grpBnhS.setLayoutData(gd_grpBnhS);
		grpBnhS.setText("B\u1EC7nh s\u1EED");
		
		text_9 = new Text(grpBnhS, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		
		Group grpDinBinKhm = new Group(composite_4, SWT.NONE);
		grpDinBinKhm.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		grpDinBinKhm.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_grpDinBinKhm = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpDinBinKhm.widthHint = 190;
		grpDinBinKhm.setLayoutData(gd_grpDinBinKhm);
		grpDinBinKhm.setText("Di\u1EC3n bi\u1EBFn b\u1EC7nh");
		
		text_10 = new Text(grpDinBinKhm, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		
		Composite composite_6 = new Composite(composite, SWT.NONE);
		composite_6.setLayout(new GridLayout(5, false));
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		CLabel lblBnhNhn = new CLabel(composite_6, SWT.NONE);
		lblBnhNhn.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblBnhNhn.setBottomMargin(0);
		lblBnhNhn.setLeftMargin(0);
		lblBnhNhn.setRightMargin(0);
		lblBnhNhn.setTopMargin(0);
		lblBnhNhn.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		lblBnhNhn.setText("B\u1EC7nh nh\u00E2n:");
		
		CLabel lblNguynThL = new CLabel(composite_6, SWT.NONE);
		lblNguynThL.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		lblNguynThL.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		lblNguynThL.setBottomMargin(0);
		lblNguynThL.setTopMargin(0);
		lblNguynThL.setRightMargin(0);
		lblNguynThL.setLeftMargin(0);
		lblNguynThL.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNguynThL.setText("Nguy\u1EC5n th\u1ECB l\u00EA la (N\u1EEF 26t )");
		
		Button btnCap = new Button(composite_6, SWT.NONE);
		btnCap.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnCap.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1));
		btnCap.setText("B\u1EC7nh \u00E1n m\u1EDBi");
		
		Button btnA = new Button(composite_6, SWT.NONE);
		btnA.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		btnA.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnA.setText("T\u00E1i kh\u00E1m");
		
		Button btnB = new Button(composite_6, SWT.NONE);
		btnB.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		btnB.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		btnB.setText("C\u1EADp nh\u1EADt");

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
