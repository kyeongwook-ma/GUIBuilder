package util; 

import java.awt.Color;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import view.ComponentMoveMouseListener;
import view.EditorPane;
import view.EditorPaneMouseListener;
import view.GUIView;

import components.UIButton;
import components.UICheckBox;
import components.UIGuidePoint;
import components.UILabel;
import components.UIRadioButton;
import components.UITextArea;
import components.UITextField;
import controller.ComponentController;


/**
 * util 
 * XMLGenerator.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class XMLIOHelper extends CodeIOHelper {
	private JTabbedPane tab;
	private ArrayList<EditorPane> editorPane;
	private JTree tree;
	private JScrollPane scroll;	
		
	public XMLIOHelper(String fileName) {
		super(fileName);
	}
	
	public XMLIOHelper(String fileName, JTabbedPane tpane, ArrayList<EditorPane> editorPane, JTree tree, JScrollPane scroll) {
		super(fileName);
		this.tab = tpane;
		this.editorPane = editorPane;
		this.tree = tree;
		this.scroll = scroll;
	}

	@Override
	public void generateCode(String filePath) {		
		JInternalFrame frame = ComponentController.getInstance().getSelectedFrame();
		Document doc = new Document();
		
		Element root = new Element("root");
		Element title = new Element("TITLE");
		title.setText(frame.getTitle());
		root.addContent(title);
		Element fwidth = new Element("WIDTH");
		fwidth.setText(String.valueOf(frame.getWidth()));
		root.addContent(fwidth);
		Element fheight = new Element("HEIGHT");
		fheight.setText(String.valueOf(frame.getHeight()));
		root.addContent(fheight);

		ArrayList<JComponent> list = ComponentController.getInstance().getSelectedEditorPaneComponents();
		for(int i = 0; i < list.size(); i++) {
			Element child = new Element("child"+i);
			Element type = new Element("TYPE");
			type.setText(XMLIOHelper.getType(list.get(i)));
			child.addContent(type);
			Element name = new Element("NAME");
			name.setText(list.get(i).getName());
			child.addContent(name);
			Element width = new Element("WIDTH");
			width.setText(String.valueOf(list.get(i).getWidth()));
			child.addContent(width);
			Element height = new Element("HEIGHT");
			height.setText(String.valueOf(list.get(i).getHeight()));
			child.addContent(height);
			Element x = new Element("X");
			x.setText(String.valueOf(list.get(i).getX()));
			child.addContent(x);
			Element y = new Element("Y");
			y.setText(String.valueOf(list.get(i).getY()));
			child.addContent(y);
			Element text = new Element("TEXT");
			text.setText(XMLIOHelper.getText(list.get(i)));
			child.addContent(text);
			Element opaque = new Element("isOpaque");
			opaque.setText(Boolean.toString(list.get(i).isOpaque()));
			child.addContent(opaque);
			Element r = new Element("R");
			r.setText(String.valueOf(list.get(i).getBackground().getRed()));
			child.addContent(r);
			Element g = new Element("G");
			g.setText(String.valueOf(list.get(i).getBackground().getGreen()));
			child.addContent(g);
			Element b = new Element("B");
			b.setText(String.valueOf(list.get(i).getBackground().getBlue()));
			child.addContent(b);
			root.addContent(child);
		}			
		doc.setRootElement(root);
		
		XMLOutputter xout=new XMLOutputter();
		Format f=xout.getFormat();
		f.setEncoding("euc-kr");
		f.setIndent(" ");
		f.setLineSeparator("\r\n");
		f.setTextMode(Format.TextMode.TRIM);
		
		FileWriter fwriter;
		try {
			fwriter = new FileWriter(filePath);
			xout.setFormat(f);	
			xout.output(doc, fwriter);
			fwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readCode() throws IOException {
		try {
			String filePath = this.file.getPath();
			File srcfile = new File(filePath);
			FileInputStream input = new FileInputStream(srcfile);
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			
			Element root = doc.getRootElement();
			List children = root.getChildren();
			
			EditorPane edPane = new EditorPane(GUIView.tabNumber++);
			edPane.setLayout(null);
			edPane.addComponent(0); //객체 생성 
			EditorPaneMouseListener edpMouseListener = new EditorPaneMouseListener(edPane, tree, scroll);
			edPane.addMouseListener(edpMouseListener);
			edPane.addMouseMotionListener(edpMouseListener);

			JDesktopPane pane = new JDesktopPane();
			JInternalFrame frame = new JInternalFrame(root.getChildText("TITLE"), true, false, true, true);
			frame.setBounds(0, 0, Integer.parseInt(root.getChildText("WIDTH"))
								, Integer.parseInt(root.getChildText("HEIGHT")));
			frame.setVisible(true);
			frame.add(edPane);
			pane.add(frame);
			ComponentController.getInstance().addFrame(frame);
			
			tab.addTab(srcfile.getName(), pane);
			editorPane.add(edPane);
			ComponentController.getInstance().createComponentListRow();
			ComponentController.getInstance().addHasPath(true);
			ComponentController.getInstance().addFilePath(filePath);
			
			ComponentController.getInstance().createTreeRoot(root.getChildText("TITLE"));
			
			ComponentController.getInstance().setSelectedPaneIndex(tab.getTabCount()-1);
			ComponentController.getInstance().setSelectedEditorPane(edPane);
			for(int i = 3; i < children.size(); i++) {
				Element child = (Element) children.get(i);
				String type = child.getChildText("TYPE");
				String name = child.getChildText("NAME");
				int width = Integer.parseInt(child.getChildText("WIDTH"));
				int height = Integer.parseInt(child.getChildText("HEIGHT"));
				int x = Integer.parseInt(child.getChildText("X"));
				int y = Integer.parseInt(child.getChildText("Y"));
				String text = child.getChildText("TEXT");
				boolean opaque = Boolean.parseBoolean(child.getChildText("isOpaque"));
				int r = Integer.parseInt(child.getChildText("R"));
				int g = Integer.parseInt(child.getChildText("G"));
				int b = Integer.parseInt(child.getChildText("B"));
				 
				ComponentMoveMouseListener listener = new ComponentMoveMouseListener();
				UIGuidePoint gp = new UIGuidePoint(width, height);
				switch (type) {
				case "JButton":	
					UIButton btn = new UIButton();
					btn.setName(name);
					btn.setSize(width, height);
					btn.setMargin(new Insets(0, 0, 0, 0));
					//btn.setBackground(new Color(r, g, b));
					btn.setLocation(x, y);
					btn.setText(text);
					btn.setOpaque(opaque);
					btn.addMouseListener(listener);
					btn.addMouseMotionListener(listener);
					btn.setVisible(true);
					btn.setGuidepoint(gp);
					btn.add(gp);
					
					edPane.add(btn);
					ComponentController.getInstance().addTreeNode(btn);
					ComponentController.getInstance().addComponentList(btn);
					btn.registerObserver(ComponentController.getInstance());
					btn.notifyObservers();
					break;
				case "JLabel" :
					UILabel label = new UILabel();
					label.setName(name);
					label.setSize(width, height);
					label.setLocation(x, y);
					label.setText(text);
					label.setOpaque(opaque);
					label.setBackground(new Color(r, g, b));
					label.addMouseListener(listener);
					label.addMouseMotionListener(listener);
					label.setVisible(true);
					label.setGuidepoint(gp);
					label.add(gp);
					
					edPane.add(label);
					ComponentController.getInstance().addTreeNode(label);
					ComponentController.getInstance().addComponentList(label);
					label.registerObserver(ComponentController.getInstance());
					label.notifyObservers();
					break;
				case "JTextField" :
					UITextField tf = new UITextField();
					tf.setName(name);
					tf.setSize(width, height);
					tf.setLocation(x, y);
					tf.setText(text);
					tf.setOpaque(opaque);
					tf.setBackground(new Color(r, g, b));
					tf.addMouseListener(listener);
					tf.addMouseMotionListener(listener);
					tf.setVisible(true);
					tf.setGuidepoint(gp);
					tf.add(gp);
					
					edPane.add(tf);
					ComponentController.getInstance().addTreeNode(tf);
					ComponentController.getInstance().addComponentList(tf);
					tf.registerObserver(ComponentController.getInstance());
					tf.notifyObservers();
					break;
				case "JTextArea" :
					UITextArea ta = new UITextArea();
					ta.setName(name);
					ta.setSize(width, height);
					ta.setLocation(x, y);
					ta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					ta.setText(text);
					ta.setOpaque(opaque);
					ta.setBackground(new Color(r, g, b));
					ta.addMouseListener(listener);
					ta.addMouseMotionListener(listener);
					ta.setVisible(true);
					ta.setGuidepoint(gp);
					ta.add(gp);
					
					edPane.add(ta);
					ComponentController.getInstance().addTreeNode(ta);
					ComponentController.getInstance().addComponentList(ta);
					ta.registerObserver(ComponentController.getInstance());
					ta.notifyObservers();
					break;
				case "JCheckBox" :
					UICheckBox ckb = new UICheckBox();
					ckb.setName(name);
					ckb.setSize(width, height);
					ckb.setLocation(x, y);
					ckb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					ckb.setText(text);
					ckb.setOpaque(opaque);
					ckb.setBackground(new Color(r, g, b));
					ckb.addMouseListener(listener);
					ckb.addMouseMotionListener(listener);
					ckb.setVisible(true);
					ckb.setGuidepoint(gp);
					ckb.add(gp);
					
					edPane.add(ckb);
					ComponentController.getInstance().addTreeNode(ckb);
					ComponentController.getInstance().addComponentList(ckb);
					ckb.registerObserver(ComponentController.getInstance());
					ckb.notifyObservers();
					break;
				case "JRadioButton" :
					UIRadioButton rb = new UIRadioButton();
					rb.setName(name);
					rb.setSize(width, height);
					rb.setLocation(x, y);
					rb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					rb.setText(text);
					rb.setOpaque(opaque);
					rb.setBackground(new Color(r, g, b));
					rb.addMouseListener(listener);
					rb.addMouseMotionListener(listener);
					rb.setVisible(true);
					rb.setGuidepoint(gp);
					rb.add(gp);
					
					edPane.add(rb);
					ComponentController.getInstance().addTreeNode(rb);
					ComponentController.getInstance().addComponentList(rb);
					rb.registerObserver(ComponentController.getInstance());
					rb.notifyObservers();
					break;
				default :
					System.out.println("FileOpenListener");
					return ;
				}
			}
			tree = new JTree(ComponentController.getInstance().getTreeRoot());
			scroll.setViewportView(tree);
			input.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JDOMException e1) {
			e1.printStackTrace();
		}
	}

	public static String getText(JComponent comp) {
		String str = comp.getClass().toString();
		switch(str) {
		case "class components.UIButton" :
			UIButton ubtn = (UIButton) comp;
			return ubtn.getText();
		case "class components.UILabel" :
			UILabel ulabel = (UILabel) comp;
			return ulabel.getText();
		case "class components.UITextField" :
			UITextField utextfield = (UITextField) comp;
			return utextfield.getText();
		case "class components.UITextArea" :
			UITextArea utextarea = (UITextArea) comp;
			return utextarea.getText();
		case "class components.UICheckBox" :
			UICheckBox ucheckbox = (UICheckBox) comp;
			return ucheckbox.getText();
		case "class components.UIRadioButton" :
			UIRadioButton uradiobutton = (UIRadioButton) comp;
			return uradiobutton.getText();
		default :
			return "";
		}
	}
	
	public static String getType(JComponent comp) {
		String str = comp.getClass().toString();
		switch(str) {
		case "class components.UIButton" :
			return "JButton";
		case "class components.UILabel" :
			return "JLabel";
		case "class components.UITextField" :
			return "JTextField";
		case "class components.UITextArea" :
			return "JTextArea";
		case "class components.UICheckBox" :
			return "JCheckBox";
		case "class components.UIRadioButton" :
			return "JRadioButton";
		default :
			return "";
		}
	}
}
