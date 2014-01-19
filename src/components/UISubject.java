package components;

import controller.ViewObserver;


/**
 * vo 
 * UISubject.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public interface UISubject{
	void registerObserver(ViewObserver viewOb);
	void removeObserver(ViewObserver viewOb);
	void notifyObservers();
}
