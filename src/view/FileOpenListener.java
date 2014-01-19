package view;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import util.XMLIOHelper;

import components.UIButton;
import components.UICheckBox;
import components.UIGuidePoint;
import components.UILabel;
import components.UIRadioButton;
import components.UITextArea;
import components.UITextField;

import controller.ComponentController;

public class FileOpenListener implements ActionListener{
	private JTabbedPane tab;
	private ArrayList<EditorPane> editorPane;
	private JTree tree;
	private JScrollPane scroll;
	
	public FileOpenListener(JTabbedPane tpane, ArrayList<EditorPane> editorPane, JTree tree, JScrollPane scroll) {
		this.tab = tpane;
		this.editorPane = editorPane;
		this.tree = tree;
		this.scroll = scroll;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			System.out.println("No File");
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		XMLIOHelper xmlio = new XMLIOHelper(filePath, tab, editorPane, tree, scroll);
		try {
			xmlio.readCode();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
