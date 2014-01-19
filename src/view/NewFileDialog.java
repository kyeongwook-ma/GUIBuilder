package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewFileDialog extends JDialog{
	private JLabel fileNameLabel= new JLabel("File Name");
	private JTextField fileNameTextField = new JTextField("New File", 10);
	private JLabel titleLabel = new JLabel("Title          ");
	private JTextField titleTextField = new JTextField("NONAME", 10);
	private JLabel widthLabel = new JLabel("Width       ");
	private JTextField widthTextField = new JTextField("200", 10);
	private JLabel heightLabel = new JLabel("Height      ");
	private JTextField heightTextField = new JTextField("200", 10);
	private JButton okBtn = new JButton("    OK    ");
	private JButton cancelBtn = new JButton("Cancel");
	
	private boolean ok = false;
	private String filename;
	private String title;
	private int width;
	private int height;
	
	public NewFileDialog() {
		super();
		setTitle("New File");
		setLayout(new FlowLayout());
		add(fileNameLabel);
		add(fileNameTextField);
		add(titleLabel);
		add(titleTextField);
		add(widthLabel);
		add(widthTextField);
		add(heightLabel);
		add(heightTextField);
		add(okBtn);
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filename = fileNameTextField.getText();
				title = titleTextField.getText();
				width = Integer.parseInt(widthTextField.getText());
				height = Integer.parseInt(heightTextField.getText());
				ok = true;
				setVisible(false);
			}
		});
		add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ok = false;
				setVisible(false);
			}
		});
		setSize(200, 170);
		setLocation(500, 334);
		setResizable(false);
	}
	
	public String getDataFileName() {
		return this.filename;
	}
	
	public Dimension getDataSize() {
		Dimension size = new Dimension(this.width, this.height);
		return size;
	}
	
	public String getDataTitle() {
		return this.title;
	}
	
	public void setOk(boolean b) {
		this.ok = b;
	}
	
	public boolean getOk() {
		return this.ok;
	}
}
