package edu.kh.jsp.common;


import java.io.FileInputStream;
import java.util.Properties;

public class LoadXML {

	public static void main(String[] args) {
		Properties prop = new Properties(); 
		try {			
			prop.loadFromXML(new FileInputStream("driver.xml"));
			System.out.println("driver  : "+prop.getProperty("driver"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		System.out.println(JDBCTemplate.getConnection());

	}

}
