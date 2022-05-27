package edu.kh.collection.model.run;

import edu.kh.collection.model.service.StudentService;

public class StudentRun {

	public static void main(String[] args) {

		StudentService service = new StudentService();
			
//		service.ex();
		service.displayMenu();
	}

}
