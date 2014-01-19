package view; 

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import util.EditMode;
import controller.ComponentController;
import controller.ComponentController.IViewAdder;

/**
 * view 
 * GUIView.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class GUIView extends JFrame  {
	JLabel lblJpanel;
	JLabel lblJtextfield;
	JLabel lblJbutton;
	public static int tabNumber = 0;
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);;
	private ArrayList<EditorPane> editorPane = new ArrayList<EditorPane>();
	private JPanel contentPane;
	private AttributePane attributePane;
	private JPanel componentPane;
	private JTree treePane;
	private JScrollPane treeScrollPane = new JScrollPane();
	private JSplitPane mainSplitPane;
	private JSplitPane editorTreeSplitPane;
	private JSplitPane compAttriSplitPane;
	private int fileMenuItemNum = 6;
	private int componentMenuItemNum = 2;
	private int buildMenuItemNum = 1;
	private int helpMenuItemNum = 1;
	private JMenuBar menuBar;
	private JMenu[] menu = new JMenu[4];
	private JMenuItem[] fileMenu = new JMenuItem[fileMenuItemNum];
	private JMenuItem[] componentMenu = new JMenuItem[componentMenuItemNum];
	private JMenuItem[] buildMenu = new JMenuItem[buildMenuItemNum];
	private JMenuItem[] helpMenu = new JMenuItem[helpMenuItemNum];
	
	private int toolBarItemNum = 7;
	private JToolBar toolBar;
	private JButton[] toolsBtn = new JButton[toolBarItemNum];
	
	private ButtonGroup componentPaneButtonGroup;
	private JRadioButton radioJButton;
	private JRadioButton radioJTextField;
	private JRadioButton radioJLabel;
	private JRadioButton radioJTextArea;
	private JRadioButton radioJCheckBox;
	private JRadioButton radioJRadioButton;

	/**
	 * Create the frame.
	 */
	public GUIView() {
		setTitle("A.F.K Swing GUI Builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		createAttributePane();
		createMenuBar();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		createToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		createComponentPane();
		createSplitPane();
		contentPane.add(mainSplitPane, BorderLayout.CENTER);
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu[0] = new JMenu("File");
		menuBar.add(menu[0]);
		NewFileActionListener newfilelistener = new NewFileActionListener(tabbedPane, editorPane, 
																attributePane, treePane, treeScrollPane);
		fileMenu[0] = new JMenuItem("New");
		fileMenu[0].addActionListener(newfilelistener);
		fileMenu[1] = new JMenuItem("Open");
		fileMenu[1].addActionListener(new FileOpenListener(tabbedPane, editorPane, treePane, treeScrollPane));
		fileMenu[2] = new JMenuItem("Save");
		fileMenu[2].addActionListener(new FileSaveListener(tabbedPane));
		fileMenu[3] = new JMenuItem("Save As");
		fileMenu[3].addActionListener(new FileSaveAsListener(tabbedPane));
		fileMenu[4] = new JMenuItem("Recent");
		fileMenu[5] = new JMenuItem("Exit");
		fileMenu[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		for(int i = 0; i < fileMenuItemNum; i++) {
			if(i == 2 || i == 4) menu[0].addSeparator();
			menu[0].add(fileMenu[i]);
		}
		
		menu[1] = new JMenu("Component");
		menuBar.add(menu[1]);
		componentMenu[0] = new JMenuItem("Add");
		ComponentAddDeleteActionListener adddellistener = new ComponentAddDeleteActionListener(tabbedPane, 
																		editorPane, treePane, treeScrollPane);
		componentMenu[0].addActionListener(adddellistener);
		componentMenu[1] = new JMenuItem("Delete");
		componentMenu[1].addActionListener(adddellistener);
		for(int i = 0; i < componentMenuItemNum; i++) {
			//if(i == 2 || i == 4) menu[1].addSeparator();
			menu[1].add(componentMenu[i]);
		}
		
		menu[2] = new JMenu("Build");
		menuBar.add(menu[2]);
		buildMenu[0] = new JMenuItem("Generate Java Code");
		buildMenu[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ComponentController.getInstance().getJavaCode();
			}
		});
		for(int i = 0; i < buildMenuItemNum; i++) {
			//if(i == 2 || i == 4) menu[2].addSeparator();
			menu[2].add(buildMenu[i]);
		}
		
		menu[3] = new JMenu("Help");
		menuBar.add(menu[3]);
		helpMenu[0] = new JMenuItem("About A.F.K Swing GUI Builder");
		helpMenu[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog dial = new AboutDialog();
				dial.setVisible(true);
			}
		});
		for(int i = 0; i < helpMenuItemNum; i++) {
			//if(i == 2 || i == 4) menu[3].addSeparator();
			menu[3].add(helpMenu[i]);
		}
	}
	
	private void createToolBar() {
		toolBar = new JToolBar();
		NewFileActionListener newfilelistener = new NewFileActionListener(tabbedPane, editorPane, 
																	 attributePane, treePane, treeScrollPane);
		toolsBtn[0] = new JButton("New");
		toolsBtn[0].addActionListener(newfilelistener);
		toolsBtn[0].setToolTipText("새로운 파일을 작성합니다.");
		toolsBtn[1] = new JButton("Open");
		toolsBtn[1].addActionListener(new FileOpenListener(tabbedPane, editorPane, treePane, treeScrollPane));
		toolsBtn[1].setToolTipText("기존의 파일을 엽니다.");
		toolsBtn[2] = new JButton("Save");
		toolsBtn[2].addActionListener(new FileSaveListener(tabbedPane));
		toolsBtn[2].setToolTipText("현재 파일을 저장합니다.");
		toolsBtn[3] = new JButton("Save As");
		toolsBtn[3].addActionListener(new FileSaveAsListener(tabbedPane));
		toolsBtn[3].setToolTipText("현재 파일을 다른 이름으로 저장합니다.");
		ComponentAddDeleteActionListener adddellistener = new ComponentAddDeleteActionListener(tabbedPane, 
																		editorPane, treePane, treeScrollPane);
		toolsBtn[4] = new JButton("Add");
		toolsBtn[4].addActionListener(adddellistener);
		toolsBtn[4].setToolTipText("현재 선택된 컴포넌트를 추가합니다.");
		toolsBtn[5] = new JButton("Delete");
		toolsBtn[5].addActionListener(adddellistener);
		toolsBtn[5].setToolTipText("현재 선택된 컴포넌트를 삭제합니다.");
		toolsBtn[6] = new JButton("Generate Java Code");
		toolsBtn[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ComponentController.getInstance().getJavaCode();		
			}
		});
		toolsBtn[6].setToolTipText("현재 선택된 탭의 GUI 상태를 자바 코드화 합니다.");
		for(int i = 0; i < toolBarItemNum; i++) {
			if(i == 2 || i == 4) toolBar.addSeparator();
			toolBar.add(toolsBtn[i]);
		}
	}
	
	private void createComponentPane() {
		componentPane = new JPanel();
		componentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		componentPane.setMinimumSize(new Dimension());
		
		ComponentRadioListener crlistener = new ComponentRadioListener();
		radioJButton = new JRadioButton("JButton", new ImageIcon("img/JButton.png"));
		radioJButton.setBorderPainted(true);
		radioJButton.addItemListener(crlistener);
		radioJLabel = new JRadioButton("JLabel", new ImageIcon("img/JLabel.png"));
		radioJLabel.setBorderPainted(true);
		radioJLabel.addItemListener(crlistener);
		radioJTextField = new JRadioButton("JTextField", new ImageIcon("img/JTextField.png"));
		radioJTextField.setBorderPainted(true);
		radioJTextField.addItemListener(crlistener);
		radioJTextArea = new JRadioButton("JTextArea", new ImageIcon("img/JTextArea.png"));
		radioJTextArea.setBorderPainted(true);
		radioJTextArea.addItemListener(crlistener);
		radioJCheckBox = new JRadioButton("JCheckBox", new ImageIcon("img/JCheckBox.png"));
		radioJCheckBox.setBorderPainted(true);
		radioJCheckBox.addItemListener(crlistener);
		radioJRadioButton = new JRadioButton("JRadioButton", new ImageIcon("img/JRadioButton.png"));
		radioJRadioButton.setBorderPainted(true);
		radioJRadioButton.addItemListener(crlistener);
		
		componentPaneButtonGroup = new ButtonGroup();
		componentPaneButtonGroup.add(radioJButton);
		componentPaneButtonGroup.add(radioJLabel);
		componentPaneButtonGroup.add(radioJTextField);
		componentPaneButtonGroup.add(radioJTextArea);
		componentPaneButtonGroup.add(radioJCheckBox);
		componentPaneButtonGroup.add(radioJRadioButton);
		
		componentPane.add(radioJButton);
		componentPane.add(radioJLabel);
		componentPane.add(radioJTextField);
		componentPane.add(radioJTextArea);
		componentPane.add(radioJCheckBox);
		componentPane.add(radioJRadioButton);
	}
	
	private void createAttributePane() {
		attributePane = new AttributePane();
		attributePane.setMinimumSize(new Dimension());
	}
	
	private void createSplitPane() {
		compAttriSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, componentPane, attributePane);
		compAttriSplitPane.setOneTouchExpandable(true);
		compAttriSplitPane.setDividerLocation(190);
		tabbedPane.setMinimumSize(new Dimension());
		tabbedPane.addMouseListener(new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				JTabbedPane pane = (JTabbedPane) e.getSource();
				int index = pane.getSelectedIndex();
				ComponentController.getInstance().setSelectedPaneIndex(index);
				treePane = new JTree(ComponentController.getInstance().getTreeRoot());
				treePane.addTreeSelectionListener(new TreePaneSelectionListener());
				treeScrollPane.setViewportView(treePane);
				treeScrollPane.repaint();
			}
		});
		editorTreeSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, treeScrollPane);
		editorTreeSplitPane.setOneTouchExpandable(true);
		editorTreeSplitPane.setDividerLocation(600);
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, compAttriSplitPane, editorTreeSplitPane);
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setDividerLocation(150);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIView frame = new GUIView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

