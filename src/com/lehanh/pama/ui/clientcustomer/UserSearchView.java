package com.lehanh.pama.ui.clientcustomer;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class UserSearchView extends ViewPart {

	public UserSearchView() {
	}
	
	public static final String ID = "com.lehanh.pama.userSearchView";
	private Text name_text;
	private Text phone_text;
	private Table table;

	@Override
	public void createPartControl(Composite parent) {
		
		Composite content_com = new Composite(parent, SWT.NONE);
		content_com.setLayout(new GridLayout(1, false));
		
		Composite filter_composite = new Composite(content_com, SWT.BORDER);
		filter_composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		filter_composite.setLayout(new GridLayout(6, false));
		
		Label lblHTn = new Label(filter_composite, SWT.NONE);
		lblHTn.setText("H\u1ECD T\u00EAn:");
		
		name_text = new Text(filter_composite, SWT.BORDER);
		name_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblSinThoi = new Label(filter_composite, SWT.NONE);
		lblSinThoi.setText("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		
		phone_text = new Text(filter_composite, SWT.BORDER);
		phone_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		CCombo date_filter_combo = new CCombo(filter_composite, SWT.BORDER);
		date_filter_combo.setListVisible(true);
		date_filter_combo.setItems(new String[] {"Ng\u00E0y t\u01B0 v\u1EA5n", "Ng\u00E0y ph\u1EA9u thu\u1EADt", "Ng\u00E0y t\u00E1i kh\u00E1m"});
		date_filter_combo.setEditable(false);
		date_filter_combo.select(0);
		
		CalendarCombo calendarCombo = new CalendarCombo(filter_composite, SWT.NONE);
		
		TableViewer tableViewer = new TableViewer(content_com, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// TODO Auto-generated method stub
		
		filter_composite.setFocus();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
