package com.lehanh.pama.ui.patientcase;

import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.PamaResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.nebula.jface.galleryviewer.GalleryTreeViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.custom.SashForm;

public class DetailImageView extends ViewPart {
	
	public static final String ID = "com.lehanh.pama.detailImageView";


	public DetailImageView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Image itemImage = PamaResourceManager.getImage("C:\\Users\\vinhhnguyen\\Desktop\\mui_3 - Copy.jpg"/*, 120, 150*/); // 17176 - Copy
		Gallery gallery = new Gallery(sashForm, SWT.V_SCROLL | SWT.MULTI);
		
//		gallery.setGroupRenderer(new DefaultGalleryGroupRenderer());
		DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer();
		gr.setMinMargin(2);
		gr.setItemHeight(itemImage.getImageData().height);
		gr.setItemWidth(itemImage.getImageData().width);
		gr.setAutoMargin(false);
//		gr.setAlwaysExpanded(true);
		gallery.setGroupRenderer(gr);
		
		gallery.setItemRenderer(new DefaultGalleryItemRenderer());

		GalleryItem group = new GalleryItem(gallery, SWT.NONE);
		group.setText("Group " + 1); //$NON-NLS-1$
		group.setExpanded(true);

		for (int i = 0; i < 2; i++) {
			GalleryItem item = new GalleryItem(group, SWT.NONE);
			if (itemImage != null) {
				item.setImage(itemImage);
			}
//			item.setText("Item " + i); //$NON-NLS-1$
		}
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		sashForm.setWeights(new int[] {1, 1});
		// TODO Auto-generated method stub
		

	}

	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
