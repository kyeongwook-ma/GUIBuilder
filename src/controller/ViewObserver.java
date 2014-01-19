package controller; 

import javax.swing.JComponent;


/**
 * controller 
 * ViewObserver.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public interface ViewObserver {
	void update(JComponent comp);
}
