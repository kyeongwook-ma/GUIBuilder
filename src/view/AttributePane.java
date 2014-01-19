package view; 

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ComponentController;


/**
 * view 
 * AttributePane.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 13.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class AttributePane extends JPanel {

	private JTable table;

	public AttributePane() {
		setBackground(Color.WHITE);
		setBounds(22, 255, 146, 189);
		setLayout(new BorderLayout(0, 0));

		table = new JTable();
		add(table);
		
		JButton jbtnChange = new JButton("확인");
		add(jbtnChange, BorderLayout.SOUTH);
		
		jbtnChange.addMouseListener(new AttributeTableMouseListener(table));
		ComponentController.getInstance().setOnIUpdateAttrPane(new AttrPaneListener(table));
	}
}

