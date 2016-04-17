package com.lehanh.pama.ui.patientcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.ListItemRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.patientcase.ICaseDetailHandler;
import com.lehanh.pama.patientcase.IImageInfo;
import com.lehanh.pama.patientcase.IPatientManager;
import com.lehanh.pama.patientcase.ISurgeryImageList;
import com.lehanh.pama.ui.patientcase.patientimage.ImageCanvasPainter;
import com.lehanh.pama.ui.util.PamaResourceManager;
import com.lehanh.pama.util.PamaException;
import com.lehanh.pama.util.PamaHome;

class SurgeryGallary {
	
	private final Gallery gallery;

	private static final int MAX_H = 53 + 20;
	private static final int MAX_W = 80 + 120;

	private static final String IS_ADD_ITEM = "IS_ADD_ITEM";
	private static final String GROUP_ID = "GROUP_ID";
	private static final String DETAIL_ID = "DETAIL_ID";
	private static final String ALL_SURGERY = "ALL_SURGERY";
	private static final String IMAGE_NAME = "IMAGE_NAME";
	private static final String SUR_SYM = "SUR_SYM";
	private static final String FOLDER_PATH = "FOLDER_PATH";
	private static final String DATE = "DATE";

	private Image imageAdd;
	
	private final String surgeryPath;
	private ISurgeryImageList imageList;
	
	private IPatientManager paManager;

	private final String uid;

	private boolean galleryKeyPressed;

	private ImageCanvasPainter canvasPainter;
	
	SurgeryGallary(String uid, Composite galleryCom, int style, GridData gridData) {
		this.uid = uid;
		this.paManager = (IPatientManager) PamaHome.getService(IPatientManager.class);
		this.surgeryPath = PamaHome.application.getProperty(PamaHome.SURGERY_IMAGE_PATH, PamaHome.DEFAULT_SURGERY_IMAGE_PATH);
		this.imageAdd = PamaHome.application.loadImage("icons/addFolder.png");
		
		this.gallery = new Gallery(galleryCom, style);
		this.gallery.setLayoutData(gridData);
		
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(MAX_H);
		gr.setItemWidth(MAX_W);
		gr.setAutoMargin(false);
		gallery.setGroupRenderer(gr);
		
		gallery.setItemRenderer(new ListItemRenderer());
		
		Listener[] typedUpListeners = gallery.getListeners(SWT.KeyUp);
//		Listener[] typedDownListeners = gallery.getListeners(SWT.KeyDown);
		removeTypedListener(gallery, typedUpListeners);
//		removeTypedListener(gallery, typedDownListeners);
		gallery.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {

				switch (e.keyCode) {
				case SWT.ARROW_LEFT:
				case SWT.ARROW_RIGHT:
				case SWT.ARROW_UP:
				case SWT.ARROW_DOWN:
				case SWT.PAGE_UP:
				case SWT.PAGE_DOWN:
				case SWT.HOME:
				case SWT.END:
					galleryKeyPressed = true;
					break;
				}
			}
			public void keyReleased(KeyEvent e) {
				// Nothing yet.
			}
		});
		addTypedListener(gallery, typedUpListeners);
//		addTypedListener(gallery, typedDownListeners);
		
		gallery.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				GalleryItem item = (GalleryItem)e.item;
				selected(item);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				GalleryItem item = (GalleryItem)e.item;
				selected(item);
			}
			
			private void selected(GalleryItem item ) {
				if (item == null) {
					return;
				}
				if (isAddItem(item)) {
					gallery.deselectAll();
					if (galleryKeyPressed) {
						galleryKeyPressed = false;
					} else {
						addItemBaseOn(item);
					}
				} else {
					viewItem(item);
				}
			}
		});
		
		// initial popup menu
		Menu popupMenu = new Menu(gallery);
	    
//		MenuItem newItem = new MenuItem(popupMenu, SWT.CASCADE);
//	    newItem.setImage(PamaHome.application.loadImage("icons/plus_orange.png"));
//	    newItem.setText("Thêm ảnh");
//	    newItem.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//			}
//		});
	    
	    MenuItem deleteItem = new MenuItem(popupMenu, SWT.NONE);
	    deleteItem.setText("Xóa ảnh");
	    deleteItem.setImage(PamaHome.application.loadImage("icons/symbol_delete.png"));
	    deleteItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteSelectedItems();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				deleteSelectedItems();
			}
		});
	    
	    MenuItem linkItem = new MenuItem(popupMenu, SWT.NONE);
	    linkItem.setText("Thêm ảnh before - after");
	    linkItem.setImage(PamaHome.application.loadImage("icons/insert_link.png"));
	    linkItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				linkItems();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				linkItems();
			}
		});

	    gallery.setMenu(popupMenu);
	}

	private static final void addTypedListener(Gallery gallery, Listener[] typedListeners) {
		for (Listener typedListener : typedListeners) {
			gallery.addListener(SWT.KeyUp, typedListener);
			gallery.addListener(SWT.KeyDown, typedListener);
		}
	}
	
	private static final void removeTypedListener(Gallery gallery, Listener[] typedListeners) {
		for (Listener typedListener : typedListeners) {
			gallery.removeListener(SWT.KeyUp, typedListener);
			gallery.removeListener(SWT.KeyDown, typedListener);
		}
	}
	
	void setCanvasPainter(ImageCanvasPainter canvasPainter) {
		this.canvasPainter = canvasPainter;
	}
	
	private void viewItem(GalleryItem item) {
		viewItem(new GalleryItem[]{item});
	}

	private void linkItems() {
		viewItem(gallery.getSelection());
	}

	private void viewItem(GalleryItem[] items) {
		if (items == null || items.length == 0) {
			return;
		}
		TreeMap<String, String> imageList = new TreeMap<String, String>();
		for (GalleryItem item : items) {
			if (item == null || item.isDisposed() || isAddItem(item)) {
				continue;
			}
			String imageName = (String) item.getData(IMAGE_NAME);
			String folder = (String) item.getData(FOLDER_PATH);
			imageList.put(folder + imageName, (String) item.getData(DATE));
		}
		canvasPainter.viewImage(imageList);
	}
	
	private void deleteSelectedItems() {
		GalleryItem[] selected = gallery.getSelection();
		if (selected == null || selected.length == 0) {
			return;
		}
		
		//List<Object[]> imageNamesToDelete = new LinkedList<Object[]>();
		List<String> imageNamesToDelete = new LinkedList<String>();
		for (GalleryItem item : selected) {
			if (isAddItem(item)) {
				continue;
			}
			imageNamesToDelete.add(
					//new Object[]{
					//(Integer) item.getData(GROUP_ID),
					//(Integer) item.getData(DETAIL_ID),
					//(String) item.getData(SUR_SYM),
					(String) item.getData(IMAGE_NAME)
					//}
			);
		}
		this.imageList.deletedImages(imageNamesToDelete);
		
		for (GalleryItem item : selected) {
			if (isAddItem(item)) {
				continue;
			}
			String imageName = (String) item.getData(IMAGE_NAME);
			String folder = (String) item.getData(FOLDER_PATH);
			
			item.dispose();
			
			File fileToDelete = new File(folder + imageName);
			fileToDelete.delete();
		}
		paManager.updatePatient(uid);
		// TODO notify viewing those images
	}

	private class CatToUI {

		private Catagory cat;

		private CatToUI(Catagory cat) {
			this.cat = cat;
		}

		@Override
		public String toString() {
			return cat.getName() + " (" + cat.getSymbol() + ")";
		}
	}
	
	private void addItemBaseOn(GalleryItem item) {
	    SurgeryImageListSelectionDialog surgeryImageListSelectionDialog = new SurgeryImageListSelectionDialog(Display.getCurrent().getActiveShell(), new LabelProvider());
		surgeryImageListSelectionDialog.setTitle("Chọn file ảnh cho phẩu thuật");
		
		@SuppressWarnings("unchecked")
		List<Catagory> allSurgeryCat = (List<Catagory>) item.getData(ALL_SURGERY);
		CatToUI[] allSurgery = new CatToUI[allSurgeryCat.size()];
		int index = 0;
		for (Catagory cat : allSurgeryCat) {
			allSurgery[index] = new CatToUI(cat);
			index++;
		}
		surgeryImageListSelectionDialog.setElements(allSurgery);
		
		// user pressed cancel
		if (surgeryImageListSelectionDialog.open() != Window.OK) {
		    return;
		}
		
		Object[] surgery = surgeryImageListSelectionDialog.getResult();
		if (surgery == null || surgery.length != 1) {
			return;
		}
		String filePath = surgeryImageListSelectionDialog.filePath();
		if (StringUtils.isEmpty(filePath)) {
			return;
		}
		
		String extension = FilenameUtils.getExtension(filePath);
		String name = ((CatToUI) surgery[0]).cat.getName();
		String symbol = ((CatToUI) surgery[0]).cat.getSymbol();
		IImageInfo iI = this.imageList.addImage((Integer) item.getData(GROUP_ID), (Integer) item.getData(DETAIL_ID),
				name, extension);
		
		final long patientId = imageList.getPatientId();
		String toFolder = folderNameFromSurgeryAndPatientId(patientId, symbol);
		try {
			File toFolderFile = new File(toFolder);
			if (!toFolderFile.exists()) {
				if (!toFolderFile.mkdirs()) {
					throw new PamaException("Can not create folder " + toFolder);
				}
			}
			Files.copy(new File(filePath).toPath(), new File(toFolder + iI.getImageName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new PamaException(e);
		}
		this.paManager.updatePatient(uid);
		
		// show new item
		index = 0;
		int orderAtSur = 0;
		boolean foundedSur = false;
		for (GalleryItem peerItem : item.getParentItem().getItems()) {
			String surSymCurr = (String) peerItem.getData(SUR_SYM);
			if (symbol.equals(surSymCurr)) {
				foundedSur = true;
				orderAtSur++;
			} else if (foundedSur) {
				break;
			}
			index++;
		}
		if (index == item.getParentItem().getItemCount()) {
			index -= 1; 
		}
		String folderName = folderNameFromSurgeryAndPatientId(imageList.getPatientId(), symbol);
		try {
			Image itemImage = PamaResourceManager.getImage(folderName, iI.getImageName(), MAX_W, MAX_H);
			createItem(index, item.getParentItem(), toFolder, itemImage, orderAtSur + 1, symbol, iI.getImageName(), allSurgeryCat, 
					(Integer) item.getData(GROUP_ID), (Integer) item.getData(DETAIL_ID), iI.getDate());
		} catch (FileNotFoundException e) {
			throw new PamaException(e);
		} catch (IOException e) {
			throw new PamaException(e);
		}
	}
	
	private int oldIndexDetail = -1;
	private ICaseDetailHandler caseDetailHandler = new ICaseDetailHandler() {
		
		private GalleryItem group;
		@Override
		public void handleCaseDetail(ISurgeryImageList imageList, int indexRoot, int indexDetail, int groupId, int caseDetailId,
				List<Catagory> allSurCat, Map<String, Map<String, IImageInfo>> imageNamesPerSurgery) {
			if (indexDetail != oldIndexDetail) {
				oldIndexDetail = indexDetail;
				group = new GalleryItem(gallery, SWT.NONE);
				String groupName = "Bệnh án thứ " + groupId + " -  lần khám " + caseDetailId;
				group.setText(groupName);
				group.setExpanded(indexRoot == 0);
				// TODO show group description -> date range
			}
			
			if (imageNamesPerSurgery != null) {
				for (Entry<String, Map<String, IImageInfo>> imagePerSur : imageNamesPerSurgery.entrySet()) {
					final String surSym = imagePerSur.getKey();
					int indexImagePerSymbol = 1;
					for (Entry<String, IImageInfo> aImage : imagePerSur.getValue().entrySet()) {
						String fileName = aImage.getKey();
						Image itemImage;
						try {
							String folderName = folderNameFromSurgeryAndPatientId(imageList.getPatientId(), surSym);
							itemImage = PamaResourceManager.getImage(folderName, fileName, MAX_W, MAX_H);
							createItem(-1, group, folderName, itemImage, indexImagePerSymbol++, surSym, fileName, allSurCat, groupId, caseDetailId, aImage.getValue().getDate());
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			createItem(-1, group, null, imageAdd, -1, null/*"Thêm ảnh"*/, null, allSurCat, groupId, caseDetailId, null)
					.setData(IS_ADD_ITEM, true);
		}
	};

	private static final GalleryItem createItem(int orderIndex, GalleryItem group, String folder, Image itemImage, int indexImagePerSymbol, String surSym, String imageName, 
			List<Catagory> allSurCat, int groupId, int caseDetailId, String date) {
		GalleryItem item = null;
		if (orderIndex < 0) {
			item = new GalleryItem(group, SWT.NONE);
		} else {
			item = new GalleryItem(group, SWT.NONE, orderIndex);
		}
		if (itemImage != null) {
			item.setImage(itemImage);
		}
		if (surSym != null) {
			item.setText(0, surSym);
			item.setText(1, "STT: " + indexImagePerSymbol);
		}
		item.setData(FOLDER_PATH, folder);
		item.setData(SUR_SYM, surSym);
		item.setData(DATE, date);
		item.setData(IMAGE_NAME, imageName);
		item.setData(GROUP_ID, groupId);
		item.setData(DETAIL_ID, caseDetailId);
		item.setData(ALL_SURGERY, allSurCat);
		item.addDisposeListener(SurgeryGallary.itemDispose);
		return item;
	}
	private static DisposeListener itemDispose = new DisposeListener() {
		
		@Override
		public void widgetDisposed(DisposeEvent e) {
			((GalleryItem) e.widget).getImage().dispose();
		}
	};
	
	private String folderNameFromSurgeryAndPatientId(long pId, String surgery) {
		String pIdPrefix = String.valueOf(pId);
		if (pIdPrefix.length() > 1) {
			pIdPrefix = pIdPrefix.substring(0, pIdPrefix.length() - 1);
		} else {
			pIdPrefix = StringUtils.EMPTY;
		}
		String result = pIdPrefix + "0-" + pIdPrefix + "9"; // example 16945 -> 16940-16949
		return surgeryPath + "/" + surgery + "/" + result + "/";
	}
	
	void updateGallery(ISurgeryImageList imageList, String[] callIds) {
		// TODO handle redraw
		if (callIds != null && Arrays.binarySearch(callIds, uid) > -1) {
			return;
		}
		if (imageList == null) {
			return;
		}
		
		gallery.removeAll();
		this.imageAdd = PamaHome.application.loadImage("icons/addFolder.png");
		this.imageList = imageList;
		this.oldIndexDetail = -1;
		this.imageList.iteratorCaseDetail(caseDetailHandler);
		gallery.redraw();
		
//		group = new GalleryItem(gallery, SWT.NONE);
//		group.setText("Nhóm 1");
//		group.setExpanded(true);
//		for (int i = 0; i < 50; i++) {
//			itemImage = PamaResourceManager.getImage(
//					"D:\\DatabaseLHS\\20150901\\LHS\\Images\\Local\\PhauThuat\\Resize\\10563-Mai xuong go_5__A12d.JPG"
//					/*, 120, 150*/
//					//""
//			); // 17176 - Copy
//			item = new GalleryItem(group, SWT.NONE);
//			if (itemImage != null) {
//				item.setImage(itemImage);
//			}
//			item.setData(GROUP, group);
//			item.setText("Item " + i); //$NON-NLS-1$
//		}
//		item = new GalleryItem(group, SWT.NONE);
//		item.setImage(imageAdd);
//		item.setData(IS_ADD_ITEM, true);
	}

	/*private void disposeAllImages() {
		for (GalleryItem itemG : gallery.getItems()) {
			if (itemG == null || itemG.getItems() == null) {
				continue;
			}
			for (GalleryItem item : itemG.getItems()) {
				if (isAddItem(item)) {
					continue;
				}
				gallery.remove(item);
			}
		}
	}*/

	private boolean isAddItem(GalleryItem item) {
		return item.getData(IS_ADD_ITEM) != null;
	}

}
