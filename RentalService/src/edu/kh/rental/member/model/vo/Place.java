package edu.kh.rental.member.model.vo;

public class Place {
	private int placeNo;
	private int managerNo;
	private String placeName;
	private String placeAddress;
	
	public Place() {}

	public Place(int placeNo, int managerNo, String placeName, String placeAddress) {
		super();
		this.placeNo = placeNo;
		this.managerNo = managerNo;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	@Override
	public String toString() {
		return "Place [placeNo=" + placeNo + ", managerNo=" + managerNo + ", placeName=" + placeName + ", placeAddress="
				+ placeAddress + "]";
	};
	
	
}