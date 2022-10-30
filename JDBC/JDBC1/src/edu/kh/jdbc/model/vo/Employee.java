package edu.kh.jdbc.model.vo;

import java.sql.Date;

public class Employee {

	private int empId;// 사번
	private String empName;// 이름
	private String empNo;// 주민번호
	private String email; // 이메일
	private String phone;// 전화번호
	private String deptCode; // 부서코드
	private String jobCode;// 부서코드
	private String salLevel;// 월급 구간
	private int salary;// 월급
	private double bonus;// 보너스
	private int managerId; // 관리자 사번
	private Date hireDate;// 입사일
	private Date entDate; // 퇴사일
	private char entYN;// 퇴사여부

	public Employee() {
	}

	public Employee(int empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, String salLevel, int salary, double bonus, int managerId, Date hireDate, Date entDate,
			char entYN) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.entYN = entYN;
	}

	public Employee(int empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, int salary, double bonus) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salary = salary;
		this.bonus = bonus;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmp_no(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getSalLevel() {
		return salLevel;
	}

	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public char getEntYN() {
		return entYN;
	}

	public void setEntYN(char entYN) {
		this.entYN = entYN;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empo=" + empNo + ", email=" + email + ", phone="
				+ phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary="
				+ salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate=" + hireDate + ", entDate="
				+ entDate + ", entYN=" + entYN + "]";
	};

}
