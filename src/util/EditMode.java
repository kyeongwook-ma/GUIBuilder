package util; 


/**
 * util 
 * EditMode.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class EditMode {
	public static final int NOTHING = 0;
	
	public static final int CREATE_UI = 1;
	public static final int SELECT_UI = 2;
	public static final int MOVE_UI = 3;
	public static final int IDLE = 4;
	
	public static final int TYPE_JBUTTON = 10;
	public static final int TYPE_JLABEL = 11;
	public static final int TYPE_JTEXTFIELD = 12;
	public static final int TYPE_JTEXTAREA = 13;
	public static final int TYPE_JCHECKBOX = 14;
	public static final int TYPE_JRADIOBUTTON = 15;
	
	private static int CURRENT_COMPONENT = NOTHING;
	
	public static void setCurrentComponentType(int type) {
		CURRENT_COMPONENT = type;
	}
	
	public static int getCurrentComponentType() {
		return CURRENT_COMPONENT;
	}
}
