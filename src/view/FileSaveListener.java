package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ComponentController;

import util.XMLIOHelper;

public class FileSaveListener implements ActionListener{
	private JTabbedPane tab;
	
	public FileSaveListener(JTabbedPane tab) {
		this.tab = tab;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(ComponentController.getInstance().getSelectedPaneHasPath()) {
			XMLIOHelper xmlio = new XMLIOHelper("");
			xmlio.generateCode(ComponentController.getInstance().getFilePath());
		}
		else {
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
			
			XMLIOHelper xmlio = new XMLIOHelper("");
			xmlio.generateCode(filePath);
			
			tab.setTitleAt(tab.getSelectedIndex(), chooser.getSelectedFile().getName());
			ComponentController.getInstance().setFilePath(filePath);
			ComponentController.getInstance().setSelectedPaneHasPath(true);
		}
	}
}
