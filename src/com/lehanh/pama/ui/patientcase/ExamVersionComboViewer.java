package com.lehanh.pama.ui.patientcase;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lehanh.pama.patientcase.IPatientCaseList;
import com.lehanh.pama.patientcase.PatientCaseEntity;
import com.lehanh.pama.ui.util.ACommonComboViewer;

class ExamVersionComboViewer extends ACommonComboViewer {

	private final TableComboViewer tableComboViewer;
	
	private final Color backgroundSelected;

	private PatientCaseEntity selectedEntity;

	private IPatientCaseList patientCaseList;
	
	ExamVersionComboViewer(TableComboViewer examVersionTComboViewer, Color backgroundSelected) {
		this.tableComboViewer = examVersionTComboViewer;
		this.backgroundSelected = backgroundSelected;
		
		this.tableComboViewer.setContentProvider(this);
		// set the label providers
		this.tableComboViewer.setLabelProvider(this);
		// add listener
		this.tableComboViewer.addSelectionChangedListener(this);
		this.tableComboViewer.getTableCombo().getTextControl().addModifyListener(this);
		
		this.tableComboViewer.getTableCombo().setShowTableHeader(true);
		this.tableComboViewer.getTableCombo().defineColumns(new String[] {StringUtils.EMPTY, "Ngày đến", "Thực hiện" });
		this.tableComboViewer.getTableCombo().setClosePopupAfterSelection(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		PatientCaseEntity model = (PatientCaseEntity) element;
		switch (columnIndex) {
			case 0: return StringUtils.EMPTY;
			case 1: return model.getDateAsText();
			case 2: return model.getStatusEnum().desc;
		}
		return "";
	}
	
	/* (non-Javadoc)
	 * @see com.lehanh.pama.ui.util.ACommonComboViewer#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		PatientCaseEntity model = (PatientCaseEntity) ((IStructuredSelection) event.getSelection()).getFirstElement();
		if (model == null) {
			return;
		}
		selectionChanged(model);
	}
	
	void selectionChanged(PatientCaseEntity model) {
		if (model == null) {
			return;
		}
		this.selectedEntity = model;

		String selectionText = getSelectionText(selectedEntity);

		this.tableComboViewer.getTableCombo().setText(selectionText);
		this.tableComboViewer.update(model, null);
	}
	
	private String getSelectionText(PatientCaseEntity selectedEntity) {
		String lastExamText = StringUtils.EMPTY;
		if (this.patientCaseList.isLastExam(selectedEntity)) {
			lastExamText = " (lần gần nhất)";
		} else if (this.patientCaseList.isCreatingExam(selectedEntity)) {
			lastExamText = " (đang thực hiện)";
		}
		String selectionText = selectedEntity.getDateAsText() 
									+ " - " + selectedEntity.getStatusEnum().desc + lastExamText;
		return selectionText;
	}
	
	/* (non-Javadoc)
	 * @see com.lehanh.pama.ui.util.ACommonComboViewer#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		TableCombo tableCombo = ((TableComboViewer) viewer).getTableCombo();
		if (newInput instanceof IPatientCaseList) {
        	this.patientCaseList = (IPatientCaseList) newInput;
        	this.selectedEntity = null;
        	tableCombo.setText(StringUtils.EMPTY);
        }
	}

	/* (non-Javadoc)
	 * @see com.lehanh.pama.ui.util.ACommonComboViewer#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
        if (inputElement instanceof IPatientCaseList) {
        	Object[] allVers = ((IPatientCaseList) inputElement).getAllVersions();
        	return allVers;
		}
        
        return null;
	}
	
	/* (non-Javadoc) 
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang.Object, int)
	 */
	@Override
	public Color getBackground(Object element, int columnIndex) {
		if (isSelected(element)) {
			return backgroundSelected;
		}
		return null;
	}
	
	private boolean isSelected(Object element) {
		return selectedEntity != null && selectedEntity == element;
	}

	PatientCaseEntity getSelectedEntity() {
		return selectedEntity;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableFontProvider#getFont(java.lang.Object, int)
	 */
	@Override
	public Font getFont(Object element, int index) {
		PatientCaseEntity model = (PatientCaseEntity) element;
		if (model == null) {
			return null;
		}
		if (StringUtils.isBlank(model.getDateAsText())) {
			return SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC);
		}
		return null;
	}

}