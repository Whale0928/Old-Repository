package edu.kh.rental.member.model.vo;

public class Client {

	private int clientNo;
	private String clientId;
	private String clientPw;
	private String name;
	private String phone;
	private String placeName;
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	private int resDate;
	

	public int getResDate() {
		return resDate;
	}

	public void setResDate(int resDate) {
		this.resDate = resDate;
	}

	public Client() {
	}

	public Client(int clientNo,String clientId, String clientPw, String name,String phone) {
		super();
		this.clientNo = clientNo;
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.name = name;
		this.phone = phone;
	}
	public Client(String clientId, String clientPw, String name,String phone) {
		super();
		this.clientId = clientId;
		this.clientPw = clientPw;
		this.name = name;
		this.phone = phone;
	}
	
	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
		
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientPw() {
		return clientPw;
	}

	public void setClientPw(String clientPw) {
		this.clientPw = clientPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Client [clientNo=" + clientNo + ", clientId=" + clientId + ", clientPw=" + clientPw + ", name=" + name
				+ ", phone=" + phone + "]";
	}
	

}