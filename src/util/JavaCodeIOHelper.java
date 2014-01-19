package util; 

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * util 
 * JavaCodeIOHelper.java
 *
 * 설명 :
 * 
 * @since : 2013. 6. 16.
 * @author : kyeongwookma
 * @version : v1.0
 */
public class JavaCodeIOHelper extends CodeIOHelper {

	public JavaCodeIOHelper(String fileName) {
		super(fileName);
	}

	@Override
	public void readCode() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateCode(String str) {
	
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			out.write(str.getBytes());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
