package view;

import javax.swing.JComponent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import controller.ComponentController;

public class TreePaneSelectionListener implements TreeSelectionListener {
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		try {
			String name = e.getPath().getPathComponent(1).toString();
			JComponent comp = ComponentController.getInstance().searchComponent(name);
			ComponentController.getInstance().setSelectedComponent(comp);
		} catch(Exception ex) {
			
		}
	}
}
