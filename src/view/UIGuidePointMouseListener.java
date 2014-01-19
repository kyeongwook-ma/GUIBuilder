package view;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

import components.UIGuidePoint;
import controller.ComponentController;

public class UIGuidePointMouseListener implements MouseListener, MouseMotionListener{
	private int direction;
	private boolean show = false;
	private JComponent component;
	private int oldX;
	private int oldY;
	private int oldHeight;
	private int oldWidth;
	
	public UIGuidePointMouseListener(int direction) {
		this.direction = direction;
	}
	
	public void setShow(boolean b) {
		this.show = b;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if(this.show == true) {
			switch(this.direction) {
			case UIGuidePoint.TOPLEFT :
				label.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
				break;
			case UIGuidePoint.TOP :
				label.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
				break;
			case UIGuidePoint.TOPRIGHT :
				label.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
				break;
			case UIGuidePoint.LEFT :
				label.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
				break;
			case UIGuidePoint.RIGHT :
				label.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
				break;
			case UIGuidePoint.BOTTOMLEFT :
				label.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
				break;
			case UIGuidePoint.BOTTOM :
				label.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
				break;
			case UIGuidePoint.BOTTOMRIGHT :
				label.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		component = (JComponent) e.getSource();
		component = (JComponent) component.getParent().getParent();
		oldX = e.getX();
		oldY = e.getY();
		oldHeight = component.getHeight();
		oldWidth = component.getWidth();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = component.getX();
		int y = component.getY();
		switch(this.direction) {
		case UIGuidePoint.TOPLEFT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth+(x-component.getX()), oldHeight+(y-component.getY()));
			oldWidth = component.getWidth();
			oldHeight = component.getHeight();
			break;
		case UIGuidePoint.TOP :
			component.setLocation(component.getX(), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth, oldHeight+(y-component.getY()));
			oldHeight = component.getHeight();
			break;
		case UIGuidePoint.TOPRIGHT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth-(x-component.getX()), oldHeight+(y-component.getY()));
			component.setLocation(x, component.getY());
			oldWidth = component.getWidth();
			oldHeight = component.getHeight();
			break;
		case UIGuidePoint.LEFT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY());
			component.setSize(oldWidth+(x-component.getX()), oldHeight);
			oldWidth = component.getWidth();
			break;
		case UIGuidePoint.RIGHT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY());
			component.setSize(oldWidth-(x-component.getX()), oldHeight);
			component.setLocation(x, y);
			oldWidth = component.getWidth();
			break;
		case UIGuidePoint.BOTTOMLEFT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth+(x-component.getX()), oldHeight-(y-component.getY()));
			component.setLocation(component.getX(), y);
			oldWidth = component.getWidth();
			oldHeight = component.getHeight();
			break;
		case UIGuidePoint.BOTTOM :
			component.setLocation(component.getX(), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth, oldHeight-(y-component.getY()));
			component.setLocation(x, y);
			oldHeight = component.getHeight();
			break;
		case UIGuidePoint.BOTTOMRIGHT :
			component.setLocation(component.getX()+(e.getX()-oldX), component.getY()+(e.getY()-oldY));
			component.setSize(oldWidth-(x-component.getX()), oldHeight-(y-component.getY()));
			component.setLocation(x, y);
			oldWidth = component.getWidth();
			oldHeight = component.getHeight();
			break;
		}
		JComponent temp = (JComponent) e.getSource();
		temp = (JComponent) temp.getParent();
		temp.setSize(oldWidth, oldHeight);
		ComponentController.getInstance().updateAttr();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
}
