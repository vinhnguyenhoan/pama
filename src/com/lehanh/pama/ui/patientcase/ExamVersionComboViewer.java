package com.lehanh.pama.ui.patientcase;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.graphics.Color;

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
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		PatientCaseEntity model = (PatientCaseEntity) ((IStructuredSelection) event.getSelection()).getFirstElement();
		if (model == null) {
			return;
		}
		this.selectedEntity = model;

		String selectionText = getSelectionText(selectedEntity);

		TableComboViewer viewer = ((TableComboViewer) event.getSource());
		viewer.getTableCombo().setText(selectionText);
		viewer.update(model, null);
	}
	
	private String getSelectionText(PatientCaseEntity selectedEntity) {
		if (this.patientCaseList == null) {
			return StringUtils.EMPTY;
		}
		String selectionText = selectedEntity.getDateAsText() + " - " + selectedEntity.getStatusEnum().desc
								+ (this.patientCaseList.isLastExam(selectedEntity) ? " (lần gần nhất)" : "");
		return selectionText;
	}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.patientCaseList = (IPatientCaseList) newInput;
		((TableComboViewer) viewer).getTableCombo().setText(StringUtils.EMPTY);
		selectedEntity = null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
        if (inputElement instanceof IPatientCaseList) {
			return ((IPatientCaseList) inputElement).getAllVersions();
		}
        return null;
	}
	
	/* (non-Javadoc) 
	 * @see org.eclipse.jface.viewers.ITableColorProvider#getBackground(java.lang.Object, int)
	 */
	public Color getBackground(Object element, int columnIndex) {
		if (isSelected(element)) {
			return backgroundSelected;
		}
		return null;
	}
	
	private boolean isSelected(Object element) {
		return selectedEntity != null && selectedEntity == element;
	}

}