import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
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
public class frame0 extends JFrame {
public frame0() {
JButton JButton0 =  new JButton();
JButton0.setName("JButton0");
JButton0.setSize(113,53);
JButton0.setLocation(45,39);
JTextField JTextField0 =  new JTextField();
JTextField0.setName("JTextField0");
JTextField0.setSize(210,116);
JTextField0.setLocation(219,66);
JTextArea JTextArea0 =  new JTextArea();
JTextArea0.setName("JTextArea0");
JTextArea0.setSize(117,86);
JTextArea0.setLocation(62,136);
JCheckBox JCheckBox1 =  new JCheckBox();
JCheckBox1.setName("JCheckBox1");
JCheckBox1.setSize(146,69);
JCheckBox1.setLocation(243,214);
JButton JRadioButton2 =  new JButton();
JRadioButton2.setName("JRadioButton2");
JRadioButton2.setSize(92,49);
JRadioButton2.setLocation(84,242);
}
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
frame0 frame = new frame0();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}
}