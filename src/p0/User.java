package p0;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String password;
	private String name;
	private String address;
	private String zip;
	private String phoneNumber;
	
	public User(){
		
	}
	public User(String id, String password){	
		this.id = id;
		this.password = password;
	}

	public User(String id, String password,String name,String phoneNumber,String address,String zip){
		this.id = id;
		this.password = password;
		this.name=name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.zip = zip;
	}
	
	public boolean hasProfile(){
		
		return true;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + ", zip=" + zip
				+ " phoneNumber "+phoneNumber+" ]";
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
