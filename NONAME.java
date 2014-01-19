import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
public class NONAME extends JFrame {
	public NONAME() {
		JButton JButton0 =  new JButton();
		JButton0.setName("JButton0");
		JButton0.setSize(95,34);
		JButton0.setLocation(39,18);
		JButton0.setText("JButton");
		JButton0.setOpaque(true);
		add(JButton0);
		JLabel JLabel0 =  new JLabel();
		JLabel0.setName("JLabel0");
		JLabel0.setSize(89,32);
		JLabel0.setLocation(42,59);
		JLabel0.setText("JLabel");
		JLabel0.setOpaque(false);
		add(JLabel0);
		JTextField JTextField0 =  new JTextField();
		JTextField0.setName("JTextField0");
		JTextField0.setSize(92,37);
		JTextField0.setLocation(39,101);
		JTextField0.setText("JTextField");
		JTextField0.setOpaque(true);
		add(JTextField0);
		JTextArea JTextArea0 =  new JTextArea();
		JTextArea0.setName("JTextArea0");
		JTextArea0.setSize(89,37);
		JTextArea0.setLocation(41,147);
		JTextArea0.setText("JTextArea");
		JTextArea0.setOpaque(false);
		add(JTextArea0);
		JCheckBox JCheckBox1 =  new JCheckBox();
		JCheckBox1.setName("JCheckBox1");
		JCheckBox1.setSize(85,33);
		JCheckBox1.setLocation(42,195);
		JCheckBox1.setText("JCheckBox");
		JCheckBox1.setOpaque(true);
		add(JCheckBox1);
		JButton JRadioButton2 =  new JButton();
		JRadioButton2.setName("JRadioButton2");
		JRadioButton2.setSize(86,32);
		JRadioButton2.setLocation(41,236);
		JRadioButton2.setText("JRadioButton");
		JRadioButton2.setOpaque(true);
		add(JRadioButton2);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NONAME frame = new NONAME();
					frame.setTitle("NONAME");
					frame.setSize(343,342);
					frame.setLayout(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}