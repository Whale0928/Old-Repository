package edu.kh.jsp.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateXML {
	public static void main(String[] args) {

		Properties prop = new Properties();

		try {
			FileOutputStream fos = new FileOutputStream("board-sql.xml");
			prop.storeToXML(fos, "board Service SQL"); 

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
