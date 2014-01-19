package view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import controller.ComponentController.IViewAdder;

public class ComponentViewAdder implements IViewAdder{
		
	@Override
	public void addView(JComponent comp, EditorPane selectedPane) {
			
		selectedPane.add(comp);
		selectedPane.getParent().repaint();
	}
}
