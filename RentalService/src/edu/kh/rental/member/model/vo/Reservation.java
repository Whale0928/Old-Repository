package edu.kh.rental.member.model.vo;

public class Reservation {
	private int placeNo;
	private int clientNo;
	private int resDate;
	private char endUse;

	private String clientName;
	private String PlaceName;
	
	public Reservation() {}

	public Reservation(int placeNo, int clientNo, int resDate, char endUse) {
		super();
		this.placeNo = placeNo;
		this.clientNo = clientNo;
		this.resDate = resDate;
		this.endUse = endUse;
	}

	
	
	
	
	public String getPlaceName() {
		return PlaceName;
	}

	public void setPlaceName(String placeName) {
		PlaceName = placeName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public int getResDate() {
		return resDate;
	}

	public void setResDate(int resDate) {
		this.resDate = resDate;
	}

	public char getEndUse() {
		return endUse;
	}

	public void setEndUse(char endUse) {
		this.endUse = endUse;
	}

	@Override
	public String toString() {
		return "Reservation [placeNo=" + placeNo + ", clientNo=" + clientNo + ", resDate=" + resDate + ", endUse="
				+ endUse + "]";
	};
}
