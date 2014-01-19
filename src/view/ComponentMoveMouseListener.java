package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import components.UIButton;
import components.UILabel;
import components.UITextField;

import util.EditMode;

import controller.ComponentController;
import controller.ComponentController.IUpdateAttrPane;

public class ComponentMoveMouseListener implements MouseListener, MouseMotionListener{
	int oldX;
	int oldY;
	JComponent lab;	

	@Override
	public void mouseDragged(MouseEvent e) {
		lab.setLocation(lab.getX()+(e.getX()-oldX), lab.getY()+(e.getY()-oldY));
		ComponentController.getInstance().updateAttr();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lab = (JComponent) e.getSource();
		ComponentController.getInstance().setSelectedComponent(lab);
		oldX = e.getX();
		oldY = e.getY();
		String str = lab.getClass().toString();
		switch(str) {
		case "class components.UIButton" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JBUTTON);
			break;
		case "class components.UILabel" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JLABEL);
			break;
		case "class components.UITextField" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JTEXTFIELD);
			break;
		case "class components.UITextArea" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JTEXTAREA);
			break;
		case "class components.UICheckBox" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JCHECKBOX);
			break;
		case "class components.UIRadioButton" :
			EditMode.setCurrentComponentType(EditMode.TYPE_JRADIOBUTTON);
			break;
		default :
			EditMode.setCurrentComponentType(EditMode.NOTHING);
		}
		
		ComponentController.getInstance().updateAttr();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
