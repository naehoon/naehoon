package com.bs.vo;

public class EmployeeVO {

	private int no;				//번호
	private String name;		//이름
	private String jobGrade;   //직급
	private int department;   //부서
	private String email;		//이메일
	private String status;	    //업데이트 실행결과값

	public EmployeeVO(int no, String name, String jobGrade, int department, String email) {
		this.no = no;
		this.name = name;
		this.jobGrade = jobGrade;
		this.department = department;
		this.email = email;
	}

	public EmployeeVO(int no, String name, String jobGrade, int department, String email, String status) {
		this.no = no;
		this.name = name;
		this.jobGrade = jobGrade;
		this.department = department;
		this.email = email;
		this.status = status;
	}

	public EmployeeVO() {
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNo() + "," + getName();
	}
}
