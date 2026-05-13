package jpapackage.dto;

public class PricingRequestDto {
	private String fromPlace;
	private String toPlace;
	private String parcelType;
	private String weightSlab;

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
}
