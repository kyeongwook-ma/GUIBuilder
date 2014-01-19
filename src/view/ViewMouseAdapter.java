package view; 

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import util.EditMode;
import controller.ComponentController;


/**
 * view 
 * ViewMouseAdapter.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 9.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class ViewMouseAdapter extends MouseAdapter{

	private JComponent comp;
	
	ViewMouseAdapter(JComponent comp){
		this.comp = comp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ComponentController.STATE = EditMode.CREATE_UI;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		comp.setForeground(Color.CYAN);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		comp.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ComponentController.STATE = EditMode.SELECT_UI;
		ComponentController.getInstance().setSelectedComponent(comp);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		ComponentController.STATE = EditMode.SELECT_UI;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		ComponentController.getInstance().setSelectedComponent(null);
	}
	
}
