package p0;

public class User {
	private String id;
	private String password;
	private String name;
	private String address;
	private String zip;
	
	public User(){
		
	}
	public User(String id, String password){
		
		this.id = id;
		this.password = password;
	}
	public User(String id, String password,String name,String address,String zip){
		this.id = id;
		this.password = password;
		this.name=name;
		this.setAddress(address);
		this.setZip(zip);
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
}
