package Program;

import java.io.Serializable;

public class Address implements Serializable{

	private String street;
	private String city;
	private int number;
	
	public Address(String street,String city,int number) {
		this.city=city;
		this.number=number;
		this.street=street;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Address)) {
			return false;
		}
			Address temp=(Address) other;
			return street.equalsIgnoreCase(temp.street)&&
					city.equalsIgnoreCase(temp.city)&&
					number==temp.number;
	}
	
	@Override
	public String toString() {
		return street + ", "+ number+ ", "+city;
	}
}
