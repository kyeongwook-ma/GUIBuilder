package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import util.XMLIOHelper;

import controller.ComponentController;

public class FileSaveAsListener implements ActionListener{
	private JTabbedPane tab;
	
	public FileSaveAsListener(JTabbedPane tab) {
		this.tab = tab;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
			chooser.setFileFilter(filter);
			
			int ret = chooser.showSaveDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				System.out.println("No File");
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			filePath += ".xml";
			
			XMLIOHelper xmlio = new XMLIOHelper(filePath);
			xmlio.generateCode(filePath);
			
			tab.setTitleAt(tab.getSelectedIndex(), chooser.getSelectedFile().getName());
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}