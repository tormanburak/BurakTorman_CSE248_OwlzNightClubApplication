package p0;

public class Customer extends User {
	private String birthday;
	private String phoneNumber;
	public Customer(String id, String password, String name,String address,String zip) {
		super(id, password,name, address, zip);
		
	}
	public Customer(){
		
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
