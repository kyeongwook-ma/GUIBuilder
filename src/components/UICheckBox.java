package components;

import java.util.ArrayList;

import javax.swing.JCheckBox;

import controller.ViewObserver;

public class UICheckBox extends JCheckBox implements UISubject {
	private ArrayList<ViewObserver> observerList = new ArrayList<ViewObserver>();

	private UIGuidePoint guidepoint;

	public void setGuidePointShow(boolean b) {
		this.guidepoint.setShow(b);
	}
	
	public void setGuidepoint(UIGuidePoint gp) {
		this.guidepoint = gp;
	}
	
	@Override
	public void registerObserver(ViewObserver viewOb) {
		observerList.add(viewOb);
	}

	@Override
	public void removeObserver(ViewObserver viewOb) {
		observerList.remove(viewOb);
	}

	@Override
	public void notifyObservers() {
		for(ViewObserver observer : observerList) {
			observer.update(this);			
		}
	}

	@Override
	public void setSize(int arg0, int arg1) {
		super.setSize(arg0, arg1);
		notifyObservers();
	}

	@Override
	public void setLocation(int arg0, int arg1) {
		super.setLocation(arg0, arg1);
		notifyObservers();
	}

	@Override
	public void setName(String arg0) {
		super.setName(arg0);
		notifyObservers();
	}
}
