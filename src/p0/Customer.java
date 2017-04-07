package p0;

import java.io.Serializable;

public class Customer extends User implements Serializable {
	private String lastName;
	private String birthday;
	public Customer(String id, String password, String name,String phoneNumber,String lastName, String birthday,String address,String zip) {
		super(id, password,name,phoneNumber, address, zip);
		this.lastName =lastName;
		this.birthday= birthday;
	}
	public Customer(){
		
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return super.toString() + "Customer [birthday=" + birthday + " lastName "+lastName+"]";
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
