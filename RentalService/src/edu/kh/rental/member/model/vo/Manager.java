package edu.kh.rental.member.model.vo;

public class Manager {
	private int managerNo;
	private String managerId;
	private String managerPw;
	private String phone;
	private int placeNo;
	private String placeName;
	private String placeAddress;
	
	public Manager() {}

	public Manager(int managerNo, String managerId, String managerPw, String phone, String placeName) {
		super();
		this.managerNo = managerNo;
		this.managerId = managerId;
		this.managerPw = managerPw;
		this.phone = phone;
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Manager [managerNo=" + managerNo + ", managerId=" + managerId + ", managerPw=" + managerPw + ", phone="
				+ phone + ", placeName=" + placeName + "]";
	}


	
	
	
}
