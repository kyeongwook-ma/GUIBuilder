package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import controller.ComponentController;

public class NewFileActionListener implements ActionListener{
	private JTabbedPane tab;
	private AttributePane attriPane;
	private ArrayList<EditorPane> editorPane;
	private JTree tree;
	private JScrollPane scroll;
	
	public NewFileActionListener(JTabbedPane tpane, ArrayList<EditorPane> editorPane, AttributePane attPane
								, JTree tree, JScrollPane scroll) {
		this.tab = tpane;
		this.editorPane = editorPane;
		this.attriPane = attPane;
		this.tree = tree;
		this.scroll = scroll;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		NewFileDialog nfDialog = new NewFileDialog();
		nfDialog.setModal(true);
		nfDialog.setVisible(true);
		if(nfDialog.getOk() == true) {
			EditorPane edPane = new EditorPane(GUIView.tabNumber++);
			edPane.setLayout(null);
			edPane.addComponent(0); //객체 생성 
			EditorPaneMouseListener edpMouseListener = new EditorPaneMouseListener(edPane, this.tree, this.scroll);
			edPane.addMouseListener(edpMouseListener);
			edPane.addMouseMotionListener(edpMouseListener);

			JDesktopPane pane = new JDesktopPane();
			//Frame 생성시 옵션 구현.
			JInternalFrame frame = new JInternalFrame(nfDialog.getDataTitle(), true, false, true, true);
			frame.setBounds(0, 0, nfDialog.getDataSize().width, nfDialog.getDataSize().height);
			frame.setVisible(true);
			frame.add(edPane);
			pane.add(frame);
			ComponentController.getInstance().addFrame(frame);
			
			tab.addTab(nfDialog.getDataFileName()+"*", pane);
			editorPane.add(edPane);
			ComponentController.getInstance().createComponentListRow();
			ComponentController.getInstance().addHasPath(false);
			ComponentController.getInstance().addFilePath(null);
			
			ComponentController.getInstance().createTreeRoot(nfDialog.getDataTitle());
			this.tree = new JTree(ComponentController.getInstance().getTreeRoot());
			this.scroll.setViewportView(this.tree);
		}
	}
}
