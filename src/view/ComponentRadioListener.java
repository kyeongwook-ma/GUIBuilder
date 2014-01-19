package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

import controller.ComponentController;

import util.EditMode;

public class ComponentRadioListener implements ItemListener{
	@Override
	public void itemStateChanged(ItemEvent e) {
		JRadioButton temp = (JRadioButton) e.getItem();
		String type = temp.getText();
		ComponentController.STATE = EditMode.CREATE_UI;
		
		if(type.equals("JButton")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JBUTTON);
		}
		else if(type.equals("JLabel")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JLABEL);
		}
		else if(type.equals("JTextField")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JTEXTFIELD);
		}
		else if(type.equals("JTextArea")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JTEXTAREA);
		}
		else if(type.equals("JCheckBox")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JCHECKBOX);
		}
		else if(type.equals("JRadioButton")) {
			EditMode.setCurrentComponentType(EditMode.TYPE_JRADIOBUTTON);
		}
		else {
			EditMode.setCurrentComponentType(EditMode.NOTHING);
		}
	}
}
