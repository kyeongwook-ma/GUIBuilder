package view; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import controller.ComponentController;

import util.EditMode;


/**
 * view 
 * EditorPane.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 5.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class EditorPane extends JPanel {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int guideRectX;
	private int guideRectY;
	private int guideWidth;
	private int guideHeight;
	private boolean guide = false;
	private int index;

	public EditorPane(int index) {
		this.index = index;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		//Background 출력.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < this.getWidth(); i += 10) {
			for(int j = 0; j < this.getHeight(); j += 10) {
				g.setColor(Color.BLACK);
				g.fillRect(i, j, 1, 1);
			}	
		}
		//GuideLine 출력.
		g.setColor(Color.DARK_GRAY);
		if(guide == true) {
			if(x1 > guideRectX) {
				if(y1 > guideRectY) g.drawRect(guideRectX, guideRectY, guideWidth, guideHeight);
				else g.drawRect(guideRectX, y1, guideWidth, guideHeight);
			}
			else {
				if(y1 > guideRectY) g.drawRect(x1, guideRectY, guideWidth, guideHeight);
				else g.drawRect(x1, y1, guideWidth, guideHeight);
			}
		}
	}

	public void addComponent(int type) {
		String typestr;
		switch(type) {
		case EditMode.TYPE_JBUTTON :
			typestr = new String("JButton");
			break;
		case EditMode.TYPE_JLABEL :
			typestr = new String("JLabel");
			break;
		case EditMode.TYPE_JTEXTFIELD :
			typestr = new String("JTextField");
			break;
		case EditMode.TYPE_JTEXTAREA :
			typestr = new String("JTextArea");
			break;
		case EditMode.TYPE_JCHECKBOX :
			typestr = new String("JCheckBox");
			break;
		case EditMode.TYPE_JRADIOBUTTON :
			typestr = new String("JRadioButton");
			break;
		default :
			typestr = new String("NOTHNIG");				
		}
		ComponentController.getInstance().setSelectedEditorPane(this);
		if(type != 0) ComponentController.getInstance().setSelectedPaneIndex(index);
		ComponentController.getInstance().createComponent(typestr, x1, y1, x2, y2);
		ComponentController.getInstance().setOnIViewAdder(new ComponentViewAdder());
	}

	void setComponentLocation(Point p1, Point p2) {
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		if(x1 > p2.x) {
			x2 = x1;
			x1 = p2.x;
		}
		if(y1 > p2.y) {
			y2 = y1;
			y1 = p2.y;
		}
	}

	void setGuideOption(boolean b) {
		guide = b;
	}

	void setGuideRectLocation(Point p1, Point p2) {
		x1 = p1.x;
		y1 = p1.y;
		guideRectX = p2.x;
		guideRectY = p2.y;
		guideWidth = x1 - guideRectX;
		guideHeight = y1 - guideRectY;
		if(guideWidth < 0) guideWidth = -guideWidth;
		if(guideHeight < 0) guideHeight = -guideHeight;
	}
}
