package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AboutDialog extends JDialog{
	private JLabel title = new JLabel("A.F.K Swing GUI Builder");
	private JLabel version = new JLabel("ver. 1.0.0");
	private JLabel date = new JLabel("                                          2013-06-18");
	private JLabel team = new JLabel("A.F.K TEAM BY  ");
	private JLabel teamate1 = new JLabel("이진하");
	private JLabel teamate2 = new JLabel("마경욱");
	private JButton confirm = new JButton("확인");
	
	public AboutDialog() {
		super();
		setTitle("About");
		setLayout(new FlowLayout());
		setSize(210, 130);
		setResizable(false);
		setLocation(500, 334);
		add(title);
		add(version);
		add(date);
		add(team);
		add(teamate1);
		add(teamate2);
		add(confirm);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}
}
