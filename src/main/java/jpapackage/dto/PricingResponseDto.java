package jpapackage.dto;

public class PricingResponseDto {
	private String fromPlace;
	private String toPlace;
	private String parcelType;
	private String weightSlab;
	private int distance;
	private int baseFee;
	private int distanceCharge;
	private int parcelTypeCharge;
	private int weightCharge;
	private int total;

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public String getParcelType() {
		return parcelType;
	}

	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}

	public String getWeightSlab() {
		return weightSlab;
	}

	public void setWeightSlab(String weightSlab) {
		this.weightSlab = weightSlab;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(int baseFee) {
		this.baseFee = baseFee;
	}

	public int getDistanceCharge() {
		return distanceCharge;
	}

	public void setDistanceCharge(int distanceCharge) {
		this.distanceCharge = distanceCharge;
	}

	public int getParcelTypeCharge() {
		return parcelTypeCharge;
	}

	public void setParcelTypeCharge(int parcelTypeCharge) {
		this.parcelTypeCharge = parcelTypeCharge;
	}

	public int getWeightCharge() {
		return weightCharge;
	}

	public void setWeightCharge(int weightCharge) {
		this.weightCharge = weightCharge;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
