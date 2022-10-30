package edu.kh.rental.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateXML {
	public static void main(String[] args) {
		Properties prop = new Properties();

		try {
			FileOutputStream fos = new FileOutputStream("manager-sql.xml");
			
			prop.storeToXML(fos, "Manager Service SQL");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
