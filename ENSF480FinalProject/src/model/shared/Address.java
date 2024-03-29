package model.shared;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    
    public Address(String streetAddress, String city, String state, String country, String postalCode) {
    	this.setStreetAddress(streetAddress);
    	this.setCity(city);
    	this.setState(state);
    	this.setCountry(country);
    	this.setPostalCode(postalCode);
    }

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
    
    
}
