package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import util.EditMode;

import controller.ComponentController;

public class ComponentAddDeleteActionListener implements ActionListener {
	private JTabbedPane tab;
	private ArrayList<EditorPane> edpane;
	private JTree tree;
	private JScrollPane scroll;
	private final int DEFAULT_HEIGHT = 15;
	private final int DEFAULT_WIDTH = 40;
	
	public ComponentAddDeleteActionListener(JTabbedPane tabbedPane, ArrayList<EditorPane> edpane
											, JTree tree, JScrollPane scroll) {
		this.tab = tabbedPane;
		this.edpane = edpane;
		this.tree = tree;
		this.scroll = scroll;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton comp = (AbstractButton)e.getSource();
		String name = comp.getText();
		int edIndex = tab.getSelectedIndex();
		
		switch(name) {
		case "Add" :
			Point p1 = new Point(edpane.get(edIndex).getWidth()/2-DEFAULT_WIDTH, edpane.get(edIndex).getHeight()/2-DEFAULT_HEIGHT);
			Point p2 = new Point(edpane.get(edIndex).getWidth()/2+DEFAULT_WIDTH, edpane.get(edIndex).getHeight()/2+DEFAULT_HEIGHT);
			edpane.get(edIndex).setComponentLocation(p1, p2);
			edpane.get(edIndex).addComponent(EditMode.getCurrentComponentType());
			tree = new JTree(ComponentController.getInstance().getTreeRoot());
			tree.addTreeSelectionListener(new TreePaneSelectionListener());
			scroll.setViewportView(tree);
			scroll.repaint();
			break;
		case "Delete" :
			JComponent removecomp = ComponentController.getInstance().getSelectedComponent();
			edpane.get(edIndex).remove(removecomp);
			ComponentController.getInstance().setSelectedPaneIndex(edIndex);
			ComponentController.getInstance().removeComponent(removecomp);
			edpane.get(edIndex).getParent().repaint();
			ComponentController.getInstance().removeTreeNode(removecomp.getName());
			tree = new JTree(ComponentController.getInstance().getTreeRoot());
			scroll.setViewportView(tree);
			scroll.repaint();
			break;
		default :
			System.out.println("ComponentAddDeleteActionListener : NOTHING");
		}
	}
}
