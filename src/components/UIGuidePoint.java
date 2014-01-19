package components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;

import util.EditMode;
import view.UIGuidePointMouseListener;

public class UIGuidePoint extends JComponent{
	private JLabel[] point = new JLabel[8];
	private UIGuidePointMouseListener[] listener = new  UIGuidePointMouseListener[8];
	public static final int TOPLEFT = 0 ;
	public static final int TOP = 1;
	public static final int TOPRIGHT = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public static final int BOTTOMLEFT = 5;
	public static final int BOTTOM = 6;
	public static final int BOTTOMRIGHT = 7;
	
	private boolean show = false;
	
	public UIGuidePoint(int width, int height) {
		this.setSize(width, height);
		for(int i = 0; i < 8; i++) {
			listener[i] = new UIGuidePointMouseListener(i);
			point[i] = new JLabel();
			point[i].setBackground(Color.BLACK);
			point[i].setSize(6, 6);
			point[i].setOpaque(show);
			point[i].addMouseListener(listener[i]);
			point[i].addMouseMotionListener(listener[i]);
			add(point[i]);
		}
	}
	
	public void setShow(boolean b) {
		this.show = b;
		for(int i = 0; i < 8; i++) {
			point[i].setOpaque(this.show);
			listener[i].setShow(this.show);
		}
	}
	
	public void paintComponent(Graphics g) {	
		if(EditMode.getCurrentComponentType() != EditMode.TYPE_JBUTTON) {
			JComponent comp = (JComponent) this.getParent();
			this.setSize(comp.getWidth(), comp.getHeight());
		}
		point[UIGuidePoint.TOPLEFT].setLocation(0, 0);
		point[UIGuidePoint.TOP].setLocation(this.getWidth()/2-4, 0);
		point[UIGuidePoint.TOPRIGHT].setLocation(this.getWidth()-7, 0);
		point[UIGuidePoint.LEFT].setLocation(0, this.getHeight()/2-3);
		point[UIGuidePoint.RIGHT].setLocation(this.getWidth()-7, this.getHeight()/2-3);
		point[UIGuidePoint.BOTTOMLEFT].setLocation(0, this.getHeight()-7);
		point[UIGuidePoint.BOTTOM].setLocation(this.getWidth()/2-4, this.getHeight()-7);
		point[UIGuidePoint.BOTTOMRIGHT].setLocation(this.getWidth()-7, this.getHeight()-7);
	}
}
