package view; 

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import components.UIButton;
import components.UICheckBox;
import components.UILabel;
import components.UIRadioButton;
import components.UITextArea;
import components.UITextField;

import controller.ComponentController;


/**
 * view 
 * AttributeTableMouseListener.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 15.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class AttributeTableMouseListener extends MouseAdapter {
	
	private JTable table;
	private DefaultTableModel model;
	private Vector<Vector<String>> data;
	
	public AttributeTableMouseListener(JTable table) {
		this.table = table;
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Vector<String> name;
		String strName;
		Vector<String> width;
		int strWidth;
		Vector<String> height;
		int strHeight;
		Vector<String> x;
		int strX;
		Vector<String> y;
		int strY;
		Vector<String> text;
		String strText;
		Vector<String> opaque;
		boolean strOpaque;
		Vector<String> backR;
		int strBackR;
		Vector<String> backG;
		int strBackG;
		Vector<String> backB;
		int strBackB;
			
		model = (DefaultTableModel) table.getModel();
		
		data = model.getDataVector();
		name = data.get(0);
		strName = name.get(1);
		width = data.get(1);
		strWidth = Integer.parseInt(width.get(1));
		height = data.get(2);
		strHeight = Integer.parseInt(height.get(1));
		x = data.get(3);
		strX = Integer.parseInt(x.get(1));
		y = data.get(4);
		strY = Integer.parseInt(y.get(1));		
		text = data.get(5);
		strText = text.get(1);
		opaque = data.get(6);
		strOpaque = Boolean.valueOf(opaque.get(1));
		backR = data.get(7);
		strBackR = Integer.parseInt(backR.get(1));
		backG = data.get(8);
		strBackG = Integer.parseInt(backG.get(1));
		backB = data.get(9);
		strBackB = Integer.parseInt(backB.get(1));
		
		JComponent comp = ComponentController.getInstance().searchComponent(strName);
		
		if(comp instanceof JButton){
			UIButton btn = (UIButton) comp;
			btn.setName(strName);
			btn.setSize(strWidth, strHeight);
			btn.setLocation(strX, strY);
			btn.setText(strText);
			btn.setOpaque(strOpaque);
			btn.setBackground(new Color(strBackR, strBackG, strBackB));
		} else if(comp instanceof JLabel) {
			UILabel label = (UILabel) comp;
			label.setName(strName);
			label.setSize(strWidth,strHeight);
			label.setLocation(strX, strY);
			label.setText(strText);	
			label.setOpaque(strOpaque);
			label.setBackground(new Color(strBackR, strBackG, strBackB));
		} else if(comp instanceof JTextField) {
			UITextField field = (UITextField) comp;
			field.setName(strName);
			field.setSize(strWidth, strHeight);
			field.setLocation(strX, strY);
			field.setText(strText);
			field.setOpaque(strOpaque);
			field.setBackground(new Color(strBackR, strBackG, strBackB));
		} else if(comp instanceof JTextArea) {
			UITextArea area = (UITextArea) comp;
			area.setName(strName);
			area.setSize(strWidth, strHeight);
			area.setLocation(strX, strY);
			area.setText(strText);
			area.setOpaque(strOpaque);
			area.setBackground(new Color(strBackR, strBackG, strBackB));
		} else if(comp instanceof JCheckBox) {
			UICheckBox ckbox = (UICheckBox) comp;
			ckbox.setName(strName);
			ckbox.setSize(strWidth, strHeight);
			ckbox.setLocation(strX, strY);
			ckbox.setText(strText);
			ckbox.setOpaque(strOpaque);
			ckbox.setBackground(new Color(strBackR, strBackG, strBackB));
		} else if(comp instanceof JRadioButton) {
			UIRadioButton radiobtn = (UIRadioButton) comp;
			radiobtn.setName(strName);
			radiobtn.setSize(strWidth, strHeight);
			radiobtn.setLocation(strX, strY);
			radiobtn.setText(strText);
			radiobtn.setOpaque(strOpaque);
			radiobtn.setBackground(new Color(strBackR, strBackG, strBackB));
		}
	}
}
