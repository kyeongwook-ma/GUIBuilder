package view; 

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import util.XMLIOHelper;

import components.UIButton;
import components.UICheckBox;
import components.UILabel;
import components.UIRadioButton;
import components.UITextArea;
import components.UITextField;

import controller.ComponentController.IUpdateAttrPane;


/**
 * view 
 * AttrPaneListener.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 15.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class AttrPaneListener implements IUpdateAttrPane {

	private final String[] attrTab = {"Attribute", "Value"};
	private final String[] attrValue = {"NAME", "WIDTH", "HEIGHT", "X", "Y", "TEXT", "isOpaque", 
										"Background(R)", "Background(G)", "Background(B)"};
	private DefaultTableModel model;
	private JTable table;
	
	public AttrPaneListener(JTable table) {
		this.table = table;
		model = new DefaultTableModel(attrTab, 0);		
	}
	
	@Override
	public void updatePane(JComponent comp) {
		model = new DefaultTableModel(attrTab, 0);
		
		String[] namePair = new String[2];
		String[] widthPair = new String[2];
		String[] heightPair = new String[2];
		String[] xPair = new String[2];
		String[] yPair = new String[2];
		String[] textPair = new String[2];
		String[] opaquePair = new String[2];
		String[] backRPair = new String[2];
		String[] backGPair = new String[2];
		String[] backBPair = new String[2];
				
		namePair[0] = attrValue[0];
		widthPair[0] = attrValue[1];
		heightPair[0] = attrValue[2];
		xPair[0] = attrValue[3];
		yPair[0] = attrValue[4];
		textPair[0] = attrValue[5];
		opaquePair[0] = attrValue[6];
		backRPair[0] = attrValue[7];
		backGPair[0] = attrValue[8];
		backBPair[0] = attrValue[9];
		
		namePair[1] = comp.getName();
		widthPair[1] = String.valueOf(comp.getSize().width);
		heightPair[1] = String.valueOf(comp.getSize().height);
		xPair[1] = String.valueOf(comp.getX());
		yPair[1] = String.valueOf(comp.getY());
		textPair[1] = XMLIOHelper.getText(comp);
		opaquePair[1] = String.valueOf(comp.isOpaque());
		backRPair[1] = String.valueOf(comp.getBackground().getRed());
		backGPair[1] = String.valueOf(comp.getBackground().getGreen());
		backBPair[1] = String.valueOf(comp.getBackground().getBlue());
	
		model.addRow(namePair);
		model.addRow(widthPair);
		model.addRow(heightPair);
		model.addRow(xPair);
		model.addRow(yPair);
		model.addRow(textPair);
		model.addRow(opaquePair);
		model.addRow(backRPair);
		model.addRow(backGPair);
		model.addRow(backBPair);
		
		table.setModel(model);
	}
}