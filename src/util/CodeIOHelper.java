package util; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.JDOMException;

/**
 * util 
 * CodeGenerator.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 4.
 * @author : kyeongwookma
 * @version : v1.0
 */
public abstract class CodeIOHelper {

	File file;
	FileInputStream in;
	FileOutputStream out;
	
	public CodeIOHelper(String fileName) {
		file = new File(fileName);
	}
	
	public abstract void readCode() throws IOException;
	public abstract void generateCode(String str);
}
