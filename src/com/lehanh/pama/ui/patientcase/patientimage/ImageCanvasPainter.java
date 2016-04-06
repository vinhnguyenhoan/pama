package com.lehanh.pama.ui.patientcase.patientimage;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

public class ImageCanvasPainter implements PaintListener {

	private static final int START_Y = 5;
	private static final int START_X = 5;
	
	private final Canvas canvas;
	private final List<String> imagePaths = new LinkedList<String>();
	private Image image;

	public ImageCanvasPainter(Canvas canvas) {
		this.canvas = canvas;
		this.canvas.addPaintListener(this);
	}

	public void addImage(String imagePath) {
		this.imagePaths.add(imagePath);
		this.image = reloadImage(this.imagePaths);
		canvas.redraw();
	}

	private static Image reloadImage(List<String> imagePaths) {
		//this.image = new Image(canvas.getDisplay(), imagePath);
		
		//Image img = SWTResourceManager.getImage(path);
		
//		final Image scaled050 = new Image(display,
//		        image.getImageData().scaledTo((int)(width*0.5),(int)(height*0.5)));
//		    final Image scaled200 = new Image(display,
//		        image.getImageData().scaledTo((int)(width*2),(int)(height*2)));
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (image == null) {
			return;
		}
		GC gc = e.gc;
		
		gc.drawImage(image, START_X, START_Y);
		Rectangle rect = image.getBounds();
		Rectangle client = canvas.getClientArea();
		int marginWidth = client.width - rect.width;
		if (marginWidth > 0) {
			gc.fillRectangle(rect.width, 0, marginWidth, client.height);
		}
		int marginHeight = client.height - rect.height;
		if (marginHeight > 0) {
			gc.fillRectangle(0, rect.height, client.width, marginHeight);
		}
	}
	
	
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		Image originalImage = null;
		FileDialog dialog = new FileDialog (shell, SWT.OPEN);
		dialog.setText ("Open an image file or cancel");
		String string = dialog.open ();
		if (string != null) {
			originalImage = new Image (display, string);
		}
		if (originalImage == null) {
			int width = 150, height = 200;
			originalImage = new Image (display, width, height);
			GC gc = new GC (originalImage);
			gc.fillRectangle (0, 0, width, height);
			gc.drawLine (0, 0, width, height);
			gc.drawLine (0, height, width, 0);
			gc.drawText ("Default Image", 10, 10);
			gc.dispose ();
		}
		final Image image = originalImage;
		final Point origin = new Point (0, 0);
		final Canvas canvas = new Canvas (shell, SWT.NO_BACKGROUND |
				SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
		final ScrollBar hBar = canvas.getHorizontalBar ();
		hBar.addListener (SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event e) {
				int hSelection = hBar.getSelection ();
				int destX = -hSelection - origin.x;
				Rectangle rect = image.getBounds ();
				canvas.scroll (destX, 0, 0, 0, rect.width, rect.height, false);
				origin.x = -hSelection;
			}
		});
				
		final ScrollBar vBar = canvas.getVerticalBar ();
		vBar.addListener (SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				int vSelection = vBar.getSelection ();
				int destY = -vSelection - origin.y;
				Rectangle rect = image.getBounds ();
				canvas.scroll (0, destY, 0, 0, rect.width, rect.height, false);
				origin.y = -vSelection;
			}
		});
				
		canvas.addListener (SWT.Resize, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				Rectangle rect = image.getBounds ();
				Rectangle client = canvas.getClientArea ();
				hBar.setMaximum (rect.width);
				vBar.setMaximum (rect.height);
				hBar.setThumb (Math.min (rect.width, client.width));
				vBar.setThumb (Math.min (rect.height, client.height));
				int hPage = rect.width - client.width;
				int vPage = rect.height - client.height;
				int hSelection = hBar.getSelection ();
				int vSelection = vBar.getSelection ();
				if (hSelection >= hPage) {
					if (hPage <= 0) hSelection = 0;
					origin.x = -hSelection;
				}
				if (vSelection >= vPage) {
					if (vPage <= 0) vSelection = 0;
					origin.y = -vSelection;
				}
				canvas.redraw ();
			}
		});
		canvas.addListener (SWT.Paint, new Listener() {
			
			@Override
			public void handleEvent(Event e) {
				GC gc = e.gc;
				gc.drawImage (image, origin.x, origin.y);
				Rectangle rect = image.getBounds ();
				Rectangle client = canvas.getClientArea ();
				int marginWidth = client.width - rect.width;
				if (marginWidth > 0) {
					gc.fillRectangle (rect.width, 0, marginWidth, client.height);
				}
				int marginHeight = client.height - rect.height;
				if (marginHeight > 0) {
					gc.fillRectangle (0, rect.height, client.width, marginHeight);
				}
			}
		});
		Rectangle rect = image.getBounds ();
		shell.setSize (Math.max(200, rect.width - 100), Math.max(150, rect.height - 100));
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		originalImage.dispose();
		display.dispose ();
	}

	public void clearImages() {
		this.image = null;
		this.imagePaths.clear();
		this.canvas.redraw();
	}
}
