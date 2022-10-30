package edu.kh.jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.service.EmployeeService;
import edu.kh.jdbc.model.vo.Employee;

//View :  입 출력 담당 클래스 
//사용자 담당 인터페이스 요소로
//사용자의 요청과 응답을 보여주는 화면 역활
public class EmployeeView {
	private Scanner sc = new Scanner(System.in);

	// 공통적으로 호출할 service객체를 필드에서 생성
	private EmployeeService service = new EmployeeService();

	/**
	 * 메인 메뉴
	 */
	public void displayMenu() {

		int menuNum = -1;
		do {
			try {
				System.out.println();
				System.out.println("====================================");
				System.out.println("[사원 관리 프로그램]");
				System.out.println("1. 전체 사원 정보 조회");
				System.out.println("2. 사번으로 사원 정보 조회");
				System.out.println("3. 새로운 사원 정보 추가");
				System.out.println("4. 사번으로 사원 정보 수정");
				System.out.println("5. 사번으로 사원 정보 삭제");
				System.out.println("6. 입력 받은 급여 이상으로 받는 모든 직원 조회");
				// 메소드명 selectSalary()
				// [실행 화면]
				// 급여를 입력 하세요 : 2000000
				// (DB에서 조회된 사원)
				// 총 인원 22명


				System.out.println("7. 부서코드, 보너스율을 입력받아 해당 부서의 보너스를 모드 수정");
				//메소드명 : updateBonus()
				//실행 화면 
				//부서코드를 입력하세요 : d1
				//보너스 율을 입력하세요 : 0.3
				//성공 시 :D1 부서의 보너스율이 0.3으로 변경되었습니다
				//실패 시 : 일치하는 부서 코드가 존재하지 않습니다.
				
				//DAO를 Stmt방식으로 작성
				System.out.println("8. 부서코드로 부서의 정보 받아오기");
				
				System.out.println("0. 프로그램 종료");
				System.out.println("====================================");

				System.out.print("메뉴 선택 : ");
				menuNum = sc.nextInt();
				System.out.println();

				switch (menuNum) {
				case 1:
					selectAll();
					break;
				case 2:
					selectOne();
					break;
				case 3:
					insertEmployee();
					break;
				case 4:updateEmployee();
					break;
				case 5:
					deleteEmployee();
					break;
				case 6:
					selectSal();
					break;
				case 7: 
					updateBonus();
					break;
				case 8: 
					selectDept();
					break;
				case 0:
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다 다시 입력해주세요.");
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("입력 형식 오류입니다 , 다시 시도 해주세요.");
				menuNum = -1;
				sc.nextLine();// 입력버퍼에 남아 있는 잘못된 문자열 제거
			}

		} while (menuNum != 0);
	}

	/**
	 * Employee List 출력용 View 전체 사원 정보 조회 view
	 */
	public void selectAll() {
		System.out.println("[전체 사원 정보 조회]");

		// 1) 전체 사원 정보를 반환하는 서비스 메소드 호출
		List<Employee> empList = service.selectAll();

		// 2) 서비스 호출 결과를 출력용 메서드를 이용해 풀력
		printList(empList);
	}

	/*
	 * @param empList
	 */
	public void printList(List<Employee> empList) {
		// Employee로 타입이 제한된 리스트==Employee만 담긴 List
		if (empList.isEmpty()) { // empList가 비어있을 경우. == 조회 결과가 없다.
		} else { // 비어있지 않은 경우
			for (Employee emp : empList) {
				System.out.println(emp);
			}
		}
	}

	/**
	 * 사번 입력용 View (메뉴 2,4,5 메뉴에 공통된 요소여서)
	 * 
	 * @return
	 */
	public int inputEmpId() {
		System.out.print("사번을 입력하세요 : ");
		int empNo = sc.nextInt();
		sc.nextLine();
		return empNo;
	}

	/**
	 * 사번으로 사원 정보 조회 view
	 * 
	 */
	public void selectOne() {
		System.out.println("[사번으로 사원 정보 조회]");
		int input = inputEmpId();

		Employee emp = service.selectOne(input);

		List<Employee> empList = new ArrayList<Employee>();

		if (emp != null) {
			empList.add(emp);
		}
		printList(empList);
	}

	/**
	 * 월급 기준 직원 조회
	 * 
	 */
	public void selectSal() {
		System.out.println("입력 받은 급여 이상으로 받는 모든 직원 조회");

		System.out.print("월급 입력 : ");
		int input = inputEmpId();

		List<Employee> empList = service.selectSal(input);
		printList(empList);

		System.out.println("\n총 인원수 : " + empList.size());
	}

	/**
	 * 새로운 사원 정보 추가
	 * 
	 */
	public void insertEmployee() {
		System.out.println("3. 새로운 사원 정보 추가");

		System.out.print("사번 : ");
		int empId = sc.nextInt();
		sc.nextLine();

		System.out.print("이름 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();

		System.out.print("부서코드(D1~D9) :  : ");
		String deptCode = sc.next();
		System.out.print("직급 코드(J1~J9 : ");
		String jobCode = sc.next();
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		System.out.print("보너스 수치 : ");
		double bonus = sc.nextDouble();
		sc.nextLine();

		// 입력받은 값을 Employee 객체에 저장

		Employee emp = new Employee(empId, empName, empNo, email, phone, deptCode, jobCode, salary, bonus);

		int result = service.insertEmployee(emp);

		if (result > 0) {
			System.out.println("사원 정보가 추가 되었습니다");
		} else {
			System.out.println("사원 정보 추가 실패");
		}

	}

	/**
	 * 사번으로 사원 정보 삭제
	 * 
	 */
	public void deleteEmployee() {
		// 조건 1) PreparedStatement 사용
		// 조건 2) 삭제 성공 시 - > 삭제되었습니다.
		// 삭제 실패 시 - > 일치 하는 사번의 사원이 없습니다 출력
		System.out.println("[사번으로 사원 정보 삭제]");
		int input = inputEmpId();

		int result = service.deleteEmployee(input);

		if (result > 0) {
			System.out.println("사원 정보가 삭제 되었습니다");
		} else {
			System.out.println("사원 정보 삭제 실패");
		}

	}

	/**사번으로 사원 정보 수정
	 * 
	 */
	public void updateEmployee() {
		System.out.println("[사번으로 사원 정보 수정]");
		int empId = inputEmpId();
		
		System.out.print("변경된 이메일 : ");
		String email = sc.next();
		System.out.print("변경된 전화번호 입력 : ");
		String phone = sc.next();
		System.out.print("변경된 급여 : ");
		int salary = sc.nextInt();
		sc.nextLine();
		
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setSalary(salary);
		
		int result = service.updateEmployee(emp); 
		
		if(result > 0) {
			System.out.println("수정이 완료 되었습니다");
		}
		else{
			System.out.println("일치하는 사번의 사원이 없습니다");
		}
	}

	public String dCode() {
		System.out.print("부서코드 입력 : ");
		String dCode = sc.next().toUpperCase();
		return dCode;
	}
	
	public void updateBonus() {
		System.out.println("[부서별 보너스 수치 변경]");
		
		String deptCode = dCode();
		System.out.print("보너스율 입력 : ");
		double bonus = sc.nextDouble();
		sc.nextLine();
		
		Employee emp = new Employee();
		emp.setDeptCode(deptCode);
		emp.setBonus(bonus);
		

		int result = service.updateBonus(emp); 
		
		if(result > 0) {
			System.out.printf("%s부서의 보너스율이 %.1f으로 변경되었습니다",deptCode,bonus);
		}else {
			System.out.println("부서코드가 잘못되었습니다.");
		}
	}
	public void selectDept() {
	System.out.println("[부서 정보 소개]");
		
		String deptCode = dCode();
		System.out.println("아직 미구현");
	}
}
