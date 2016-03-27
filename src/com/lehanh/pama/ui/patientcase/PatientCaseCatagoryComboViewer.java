package com.lehanh.pama.ui.patientcase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Color;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.ui.util.ACommonComboViewer;

class PatientCaseCatagoryComboViewer extends ACommonComboViewer {

	private final CatagoryType type;
	private TableComboViewer tableComboViewer;
	private ICatagoryManager catManager;
	
	private List<Catagory> multiSelectionCatList = new LinkedList<Catagory>();
	private List<PatientCaseCatagoryComboViewer> dependViewers;
	private Color backgroundSelected;
	
	public PatientCaseCatagoryComboViewer(ICatagoryManager catManager, Color backgroundSelected, final TableComboViewer tableComboViewer, CatagoryType type, 
			PatientCaseCatagoryComboViewer... dependViewers) {
		this(catManager, false, backgroundSelected, tableComboViewer, type, dependViewers);
	}
	
	public PatientCaseCatagoryComboViewer(ICatagoryManager catManager, boolean showDataAtFirst, Color backgroundSelected, final TableComboViewer tableComboViewer, CatagoryType type, 
			PatientCaseCatagoryComboViewer... dependViewers) {
		this.catManager = catManager;
		this.backgroundSelected = backgroundSelected;
		this.tableComboViewer = tableComboViewer;
		this.type = type;
		this.dependViewers = Arrays.asList(dependViewers);

		this.tableComboViewer.setContentProvider(this);
		// set the label providers
		this.tableComboViewer.setLabelProvider(this);
		if (showDataAtFirst) {
			// load the data
			this.tableComboViewer.setInput(catManager.getCatagoryByType(type).values());
		}
		// add listener
		this.tableComboViewer.addSelectionChangedListener(this);
		
		this.tableComboViewer.getTableCombo().setShowTableHeader(false);
		this.tableComboViewer.getTableCombo().defineColumns(new String[] { "Description" });
		this.tableComboViewer.getTableCombo().setClosePopupAfterSelection(false);
		this.tableComboViewer.getTableCombo().getTextControl().addModifyListener(this);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		Catagory model = (Catagory) ((IStructuredSelection) event.getSelection()).getFirstElement();
		if (model == null) {
			return;
		}
		if (multiSelectionCatList.contains(model)) {
			multiSelectionCatList.remove(model);
		} else {
			multiSelectionCatList.add(model);
			//Collections.sort(multiSelectionCatList);
		}

		String selectionText = getSelectionText();

		TableComboViewer viewer = ((TableComboViewer) event.getSource());
		viewer.getTableCombo().setText(selectionText);
		viewer.update(model, null);
		
		notifyDependViewers(dependViewers);
	}
	
	private String getSelectionText() {
		Iterator<Catagory> it = multiSelectionCatList.iterator();
		String selectionText = "";
		if (it.hasNext()) {
			selectionText += it.next().getDesc();
		}
		while (it.hasNext()) {
			selectionText += ", " + it.next().getDesc();
		}
		return selectionText;
	}
	
	private void notifyDependViewers(List<PatientCaseCatagoryComboViewer> dependViewers) {
		if (dependViewers == null) {
			return;
		}
		for (PatientCaseCatagoryComboViewer dependViewer : dependViewers) {
			dependViewer.changedByParent(type, multiSelectionCatList);
		}
	}

	private void changedByParent(CatagoryType typeChanged, List<Catagory> multiSelectionCatList) {
		if (multiSelectionCatList == null || multiSelectionCatList.isEmpty() || typeChanged != this.type.getParentCatagoryType()) {
			tableComboViewer.setInput(null);
			return;
		}
		
		TreeMap<Long, Catagory> result = new TreeMap<Long, Catagory>();
		for (Catagory catSelected : multiSelectionCatList) {
			List<Catagory> filterdCats = catManager.getSubCatagorysByParent(catSelected.getId(), this.type);
			for (Catagory filterdCat : filterdCats) {
				result.put(filterdCat.getId(), filterdCat);
			}
		}
		tableComboViewer.setInput(result.values());
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//if (newInput != null) {
		((TableComboViewer) viewer).getTableCombo().setText(StringUtils.EMPTY);
		//((TableComboViewer) viewer).setInput(((TableComboViewer) viewer).getInput());
		multiSelectionCatList.clear();
		notifyDependViewers(dependViewers);
		//}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO handle first element
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
        if (inputElement instanceof Collection) {
			return ((Collection<?>) inputElement).toArray();
		}
        return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		Catagory model = (Catagory) element;
		switch (columnIndex) {
			case 0: return model.getDesc();
		}
		return "";
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
		return multiSelectionCatList.contains(element);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		String text = tableComboViewer.getTableCombo().getText();
		if (StringUtils.isBlank(text)) {
			tableComboViewer.refresh();
			multiSelectionCatList.clear();
		}
	}
}
