package p0;

public class Customer extends User {
	private String lastName;
	private String birthday;
	private String phoneNumber;
	public Customer(String id, String password, String name,String phoneNumber,String lastName, String birthday,String address,String zip) {
		super(id, password,name,phoneNumber, address, zip);
		this.setLastName(lastName);
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
		return super.toString() + "Customer [birthday=" + birthday + ", phoneNumber=" + phoneNumber + "]";
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
