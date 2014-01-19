package controller; 

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.tree.DefaultMutableTreeNode;

import util.CodeIOHelper;
import util.EditMode;
import util.JavaCodeIOHelper;
import util.XMLIOHelper;
import view.ComponentMoveMouseListener;
import view.EditorPane;
import components.UIButton;
import components.UICheckBox;
import components.UIGuidePoint;
import components.UILabel;
import components.UIRadioButton;
import components.UITextArea;
import components.UITextField;


/**
 * controller 
 * ComponentController.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class ComponentController implements ViewObserver {

	public interface IViewAdder{
		void addView(JComponent comp, EditorPane selectedPane);
	} 

	IViewAdder viewAdder;

	public void setOnIViewAdder(IViewAdder viewAdder) {
		this.viewAdder = viewAdder;
	}

	IUpdateAttrPane listener;

	public interface IUpdateAttrPane{
		public void updatePane(JComponent comp);
	}

	public void setOnIUpdateAttrPane(IUpdateAttrPane listener) {
		this.listener = listener;
	}

	public static int STATE = EditMode.IDLE;
	private static ComponentController instance = new ComponentController();
	private JComponent selectedComponent = null;
	private ArrayList<DefaultMutableTreeNode> root = new ArrayList<DefaultMutableTreeNode>();
	private ArrayList<JInternalFrame> frameList = new ArrayList<JInternalFrame>();
	//Need 2nd dimension ArrayList for componentList per tab
	private ArrayList<ArrayList<JComponent>> componentList = new ArrayList<ArrayList<JComponent>>();
	//Activated EditorPane in Selected tab
	private EditorPane selectedEditorPane;
	//Activated EditorPane index in Selected tab
	private int selectedPaneIndex;
	private ArrayList<Boolean> hasPath = new ArrayList<Boolean>();
	private ArrayList<String> filePath = new ArrayList<String>();
	private int TEXTFIELD_NUM = 0;
	private int BUTTON_NUM = 0;
	private int LABEL_NUM = 0;
	private int TEXTAREA_NUM = 0;

	private String SEMI_COLON = ";";
	private String DOT = ".";
	private String COMMA = ",";
	private String EQUAL = " = ";
	private String NEW = " new ";
	private String NL = "\n";

	private ComponentController() { }

	public void addFilePath(String file) {
		filePath.add(file);
	}
	
	public String getFilePath() {
		return filePath.get(selectedPaneIndex);
	}
	
	public void setFilePath(String file) {
		filePath.set(selectedPaneIndex, file);
	}
	
	public void addHasPath(boolean b) {
		hasPath.add(b);
	}
	
	public boolean getSelectedPaneHasPath() {
		return hasPath.get(selectedPaneIndex);
	}
	
	public void setSelectedPaneHasPath(boolean b) {
		hasPath.set(selectedPaneIndex, b);
	}
	
	public static ComponentController getInstance() {
		if(instance == null) {
			return new ComponentController();
		} else
			return instance;
	}

	@Override
	public void update(JComponent comp) {
		for(int i = 0; i < componentList.get(selectedPaneIndex).size(); ++i) {
			JComponent component = componentList.get(selectedPaneIndex).get(i);
			String name1 = comp.getName();
			String name2 = component.getName();
			if(name1.equals(name2)) 
				componentList.get(selectedPaneIndex).set(i, comp);
		}
		draw();
	}

	public void removeComponent(JComponent comp) {
		componentList.get(selectedPaneIndex).remove(comp);
	}

	private void draw() {
		for(int i = 0; i < componentList.get(selectedPaneIndex).size(); ++i) {
			JComponent comp = componentList.get(selectedPaneIndex).get(i);
			viewAdder.addView(comp, selectedEditorPane);			
		}
	}

	public JComponent getSelectedComponent() {
		return selectedComponent;
	}

	public JComponent searchComponent(String name) {

		for(JComponent comp : componentList.get(selectedPaneIndex)) {
			String compName = comp.getName();

			if(name.equals(compName))
				return comp;			
		}
		return null;
	}

	public JComponent getClickedCompponent(int x, int y) {

		for(JComponent comp : componentList.get(selectedPaneIndex)) {

			if(comp.contains(x, y))	{
				return comp;
			}
		}
		return selectedComponent;
	}

	public void setSelectedComponent(JComponent selectedComponent) {
		//Draw selectedComponent Guide Point.
		String str = selectedComponent.getClass().toString();
		switch(str) {
		case "class components.UIButton" :
			UIButton ubtn = (UIButton) selectedComponent;
			ubtn.setGuidePointShow(true);
			break;
		case "class components.UILabel" :
			UILabel ulabel = (UILabel) selectedComponent;
			ulabel.setGuidePointShow(true);
			break;
		case "class components.UITextField" :
			UITextField utextfield = (UITextField) selectedComponent;
			utextfield.setGuidePointShow(true);
			break;
		case "class components.UITextArea" :
			UITextArea utextarea = (UITextArea) selectedComponent;
			utextarea.setGuidePointShow(true);
			break;
		case "class components.UICheckBox" :
			UICheckBox ucheckbox = (UICheckBox) selectedComponent;
			ucheckbox.setGuidePointShow(true);
			break;
		case "class components.UIRadioButton" :
			UIRadioButton uradiobutton = (UIRadioButton) selectedComponent;
			uradiobutton.setGuidePointShow(true);
			break;
		}
		if((this.selectedComponent != null) && (this.selectedComponent != selectedComponent)) {
			str = this.selectedComponent.getClass().toString();
			switch(str) {
			case "class components.UIButton" :
				UIButton ubtn = (UIButton) this.selectedComponent;
				ubtn.setGuidePointShow(false);
				break;
			case "class components.UILabel" :
				UILabel ulabel = (UILabel) this.selectedComponent;
				ulabel.setGuidePointShow(false);
				break;
			case "class components.UITextField" :
				UITextField utextfield = (UITextField) this.selectedComponent;
				utextfield.setGuidePointShow(false);
				break;
			case "class components.UITextArea" :
				UITextArea utextarea = (UITextArea) this.selectedComponent;
				utextarea.setGuidePointShow(false);
				break;
			case "class components.UICheckBox" :
				UICheckBox ucheckbox = (UICheckBox) this.selectedComponent;
				ucheckbox.setGuidePointShow(false);
				break;
			case "class components.UIRadioButton" :
				UIRadioButton uradiobutton = (UIRadioButton) this.selectedComponent;
				uradiobutton.setGuidePointShow(false);
				break;
			}
		}
		selectedComponent.getParent().repaint();
		this.selectedComponent = selectedComponent;
	}

	public void createTreeRoot(String name) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
		this.root.add(node);
	}

	public void removeTreeNode(String name) {
		for(int i = 0; i < this.root.get(selectedPaneIndex).getChildCount(); i++) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.root.get(selectedPaneIndex).getChildAt(i);
			String str = (String) node.getUserObject();
			if(name == str) {
				this.root.get(selectedPaneIndex).remove(i);
				break;
			}
		}
	}

	public DefaultMutableTreeNode getTreeRoot() {
		return this.root.get(selectedPaneIndex);
	}

	public void setSelectedEditorPane(EditorPane pane) {
		this.selectedEditorPane = pane;
	}
	
	public ArrayList<JComponent> getSelectedEditorPaneComponents() {
		return componentList.get(selectedPaneIndex);
	}
	
	public void setSelectedPaneIndex(int index) {
		this.selectedPaneIndex = index;
	}

	public void addFrame(JInternalFrame frame) {
		this.frameList.add(frame);
	}
	
	public JInternalFrame getSelectedFrame() {
		return frameList.get(selectedPaneIndex);
	}
	
	public void removeFrame(JInternalFrame frame) {
		this.frameList.remove(frame);
	}

	public void createComponentListRow() {
		this.componentList.add(new ArrayList<JComponent>());
	}

	public void addComponentList(JComponent comp) {
		componentList.get(selectedPaneIndex).add(comp);
	}
	
	public void addTreeNode(JComponent comp) {
		root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(comp.getName()));
	}
	
	public void createComponent(String type, int x1, int y1, int x2, int y2) {
		if(type.equals("JTextField")) {
			UITextField jtf = new UITextField();
			jtf.setText(type);
			jtf.setName(type + TEXTFIELD_NUM++);
			jtf.setSize(x2-x1, y2-y1);
			jtf.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jtf.addMouseListener(listener);
			jtf.addMouseMotionListener(listener);
			jtf.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jtf.setGuidepoint(gp);
			jtf.add(gp);

			componentList.get(selectedPaneIndex).add(jtf);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jtf.getName()));

			jtf.registerObserver(instance);
			jtf.notifyObservers();
		}
		else if(type.equals("JLabel")){
			UILabel jlabel = new UILabel();
			jlabel.setText(type);
			jlabel.setName(type + LABEL_NUM++);
			jlabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			jlabel.setSize(x2-x1, y2-y1);
			jlabel.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jlabel.addMouseListener(listener);
			jlabel.addMouseMotionListener(listener);
			jlabel.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jlabel.setGuidepoint(gp);
			jlabel.add(gp);

			componentList.get(selectedPaneIndex).add(jlabel);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jlabel.getName()));

			jlabel.registerObserver(instance);
			jlabel.notifyObservers();
		}
		else if(type.equals("JButton")){
			UIButton jbtn = new UIButton();
			jbtn.setText(type);
			jbtn.setName(type + BUTTON_NUM++);
			jbtn.setMargin(new Insets(0, 0, 0, 0));
			jbtn.setSize(x2-x1, y2-y1);
			jbtn.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jbtn.addMouseListener(listener);
			jbtn.addMouseMotionListener(listener);
			jbtn.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jbtn.setGuidepoint(gp);
			jbtn.add(gp);

			componentList.get(selectedPaneIndex).add(jbtn);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jbtn.getName()));

			jbtn.registerObserver(instance);
			jbtn.notifyObservers();
		}
		else if(type.equals("JTextArea")) {
			UILabel jta = new UILabel();
			jta.setText(type);
			jta.setName(type + TEXTAREA_NUM++);
			jta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jta.setSize(x2-x1, y2-y1);
			jta.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jta.addMouseListener(listener);
			jta.addMouseMotionListener(listener);
			jta.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jta.setGuidepoint(gp);
			jta.add(gp);

			componentList.get(selectedPaneIndex).add(jta);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jta.getName()));

			jta.registerObserver(instance);
			jta.notifyObservers();
		}
		else if(type.equals("JCheckBox")) {
			UICheckBox jckb = new UICheckBox();
			jckb.setText(type);
			jckb.setName(type + TEXTAREA_NUM++);
			jckb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jckb.setSize(x2-x1, y2-y1);
			jckb.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jckb.addMouseListener(listener);
			jckb.addMouseMotionListener(listener);
			jckb.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jckb.setGuidepoint(gp);
			jckb.add(gp);

			componentList.get(selectedPaneIndex).add(jckb);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jckb.getName()));

			jckb.registerObserver(instance);
			jckb.notifyObservers();
		}
		else if(type.equals("JRadioButton")) {
			UIRadioButton jradiobtn = new UIRadioButton();
			jradiobtn.setText(type);
			jradiobtn.setName(type + TEXTAREA_NUM++);
			jradiobtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jradiobtn.setSize(x2-x1, y2-y1);
			jradiobtn.setLocation(x1, y1);
			ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
			jradiobtn.addMouseListener(listener);
			jradiobtn.addMouseMotionListener(listener);
			jradiobtn.setVisible(true);
			UIGuidePoint gp = new UIGuidePoint(x2-x1, y2-y1);
			jradiobtn.setGuidepoint(gp);
			jradiobtn.add(gp);

			componentList.get(selectedPaneIndex).add(jradiobtn);
			root.get(selectedPaneIndex).add(new DefaultMutableTreeNode(jradiobtn.getName()));

			jradiobtn.registerObserver(instance);
			jradiobtn.notifyObservers();
		}
	}

	public void updateAttr() {
		listener.updatePane(selectedComponent);
	}

	public void getJavaCode() {

		String importStr = new String();
		StringBuilder uiSb = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		StringBuilder mainSb = new StringBuilder();
		CodeIOHelper codeWriter;

		// TODO
		String paneName = getSelectedFrame().getTitle();

		codeWriter = new JavaCodeIOHelper(paneName + ".java");

		sb.append("public class " + paneName +  " extends JFrame {" + NL +

				"public " + paneName + "() {" + NL);

		// ui component 자바 코드 추가
		for(JComponent comp : componentList.get(selectedPaneIndex)) {

			StringBuilder importSB = new StringBuilder();

			String uiType = null;
			String name = comp.getName();
			int width = comp.getSize().width;
			int height = comp.getSize().height;
			int locX = comp.getLocation().x;
			int locY = comp.getLocation().y;
			String text = XMLIOHelper.getText(comp);
			boolean opaque = comp.isOpaque();
			int r = comp.getBackground().getRed();
			int g = comp.getBackground().getGreen();
			int b = comp.getBackground().getBlue();

			if(name.contains("Button")) {
				importSB.append("import javax.swing.JButton;" + NL);
				uiType = "JButton";
			} else if(name.contains("TextField")) {
				importSB.append("import javax.swing.JTextField;"+ NL);
				uiType = "JTextField";
			} else if(name.contains("CheckBox")) {
				importSB.append("import javax.swing.JCheckBox;"+ NL);

				uiType = "JCheckBox";
			} else if(name.contains("Label")) {
				importSB.append("import javax.swing.JLabel;"+ NL);

				uiType = "JLabel";
			} else if(name.contains("RadioButton")) {
				importSB.append("import javax.swing.JRadioButton;"+ NL);

				uiType = "JRaidoButton";
			} else if(name.contains("TextArea")) {
				importSB.append("import javax.swing.JTextArea;"+ NL);

				uiType = "JTextArea";
			} 

			importStr += "import java.awt.EventQueue;" + NL +
							"import javax.swing.JFrame;" + NL + importSB.toString();
			
			if(uiType != null) {
				// ex) JButton 버튼이름 = new JButton();
				uiSb.append(uiType + " " + name + EQUAL + NEW + uiType +"()" + SEMI_COLON + NL);
				// ex) 버튼이름.setName(버튼이름);		
				uiSb.append(name + DOT + "setName" + "(" + "\"" + name + "\""+ ")" + SEMI_COLON + NL);
				// ex) 버튼이름.setSize(x,y);
				uiSb.append(name + DOT + "setSize" + "(" + width + COMMA + height +")" + SEMI_COLON+ NL);
				// ex) 버튼이름.setLocat(x,y);
				uiSb.append(name + DOT + "setLocation" + "(" + locX + COMMA + locY + ")" + SEMI_COLON+ NL);
				// ex) 버튼이름.setText(text);
				uiSb.append(name + DOT + "setText(" + "\"" + text + "\""+  ")" + SEMI_COLON + NL);
				// ex) 버튼이름.setOpaque(true);
				uiSb.append(name + DOT + "setOpaque(" + opaque + ")" + SEMI_COLON + NL);
				// ex) 버튼이름.setBackGround();
				//uiSb.append(name + DOT + "setBackground(" + r + COMMA + g + COMMA + b + ")" + SEMI_COLON + NL);
				// ex) add(버튼이름);
				uiSb.append("add("+name+")"+SEMI_COLON+NL);
			}

		}

		mainSb.append(	
				"}\n" + "public static void main(String[] args) {" + NL +
				"EventQueue.invokeLater(new Runnable() {" + NL +
				"public void run() {"+ NL +
				"try {"+ NL +
				paneName + " frame = new " + paneName +"();"+ NL +
				"frame.setTitle(\"" + paneName + "\");" + NL +
				"frame.setSize(" + getSelectedFrame().getWidth() + COMMA + getSelectedFrame().getHeight() + ");" + NL +
				"frame.setLayout(null);" + NL +
				"frame.setVisible(true);" + NL +
				"} catch (Exception e) {" + NL +
				"e.printStackTrace();" + NL +
				"}" + NL +
				"}" + NL +
				"});"+ NL +
				"}" + NL +
				"}"); 


		codeWriter.generateCode(importStr + sb.toString()+ 
				uiSb.toString()  + mainSb.toString());
	}

}
