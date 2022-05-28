package edu.kh.jdbc.model.service;

import java.util.List;

import edu.kh.jdbc.model.dao.EmployeeDAO;
import edu.kh.jdbc.model.vo.Employee;

//Service : 요청에 맞는 기능을 수행하여 결과를 return함
// - 전달 받은 데이터 또는 DAO수행 결과 데이터를 필요한 형태로 가공처리
public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();

	/**
	 * 전체 사원 정보 조회 서비스
	 * 
	 * @return
	 */
	public List<Employee> selectAll() {
		List<Employee> empList = dao.selectAll();
		return empList; // DAO 호출 결과를 바로 View로 반환
	}

	/**
	 * 사번으로 사원정보 한명 조회
	 * 
	 * @param input
	 * @return
	 */
	public Employee selectOne(int input) {
		Employee emp = dao.selectOne(input);
		return emp;
	}
	/**
	 * 월급을 얼마 이상 받는지 조회
	 * 
	 * @param input
	 * @return
	 */
	public List<Employee> selectSal(int input) {
		List<Employee> empList = dao.selectSal(input);
		return empList; // DAO 호출 결과를 바로 View로 반환
	}

	/** 사원 정보 추가
	 * @param emp
	 * @return
	 */
	public int insertEmployee(Employee emp) {
		int result = dao.insertEmployee(emp);
		return result;
	}

	/**사번으로 사원 정보 삭제
	 * @param input
	 * @return
	 */
	public int deleteEmployee(int input) {
		int result = dao.deleteEmployee(input);
		return result;
	}

	/** 사번으로 사원 정보 수정 service
	 * @param emp 매개변수 명
	 * @return result  반환 값
	 */
	public int updateEmployee(Employee emp) {

//		int result = dao.updateEmployee(emp);
		int result = dao.updateEmployee2(emp);
		
		return result;
	}

	public int updateBonus(Employee emp) {
//		int result = dao.updateBonus(emp);
		int result = dao.updateBonus2(emp);
		return result;
	}

	public Employee selectDept(String deptCode) {
		
		return null;
	}
}
