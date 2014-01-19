package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import util.EditMode;
import controller.ComponentController;
import controller.ComponentController.IUpdateAttrPane;

public class EditorPaneMouseListener implements MouseListener, MouseMotionListener{
	private Point p1;
	private Point p2;
	private Point tempPoint;
	private EditorPane edPane;
	private JTree tree;
	private JScrollPane scroll;

	public EditorPaneMouseListener(EditorPane pane, JTree tree, JScrollPane scroll) {
		this.edPane = pane;
		this.tree = tree;
		this.scroll = scroll;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		tempPoint = e.getPoint();
		edPane.setGuideRectLocation(p1, tempPoint);
		edPane.getParent().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		p1 = e.getPoint();
		edPane.setGuideOption(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		p2 = e.getPoint();
		if((p1.x != p2.x) || (p1.y != p2.y)){
			edPane.setComponentLocation(p1, p2);
			edPane.addComponent(EditMode.getCurrentComponentType());
			tree = new JTree(ComponentController.getInstance().getTreeRoot());
			tree.addTreeSelectionListener(new TreePaneSelectionListener());
			scroll.setViewportView(tree);
			scroll.repaint();
		}
		edPane.setGuideOption(false);
		edPane.getParent().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
