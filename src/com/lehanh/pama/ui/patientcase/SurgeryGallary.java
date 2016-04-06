package com.lehanh.pama.ui.patientcase;

import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.lehanh.pama.patientcase.ImageInfo;
import com.lehanh.pama.ui.util.PamaResourceManager;

class SurgeryGallary {
	
	private final Gallery gallery;

	SurgeryGallary(Composite galleryCom, int style, GridData gridData) {
		this.gallery = new Gallery(galleryCom, style);
		this.gallery.setLayoutData(gridData);
		
		Image itemImage = PamaResourceManager.getImage(
				"C:\\Users\\vinhhnguyen\\Desktop\\mui_3 - Copy.jpg"/*, 120, 150*/
				//""
				); // 17176 - Copy
		
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
		
		gallery.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(e.widget);
				System.out.println(e.getSource());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.widget);
				System.out.println(e.getSource());
			}
		});
	}

	void updateGallery(Map<String, Map<String, Map<String, Object>>> allImages) {
		// TODO Auto-generated method stub
		
	}

}
