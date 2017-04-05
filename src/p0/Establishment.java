package p0;

public class Establishment extends User{
	private String type;
	public Establishment(String id, String password, String name,String phoneNumber ,String address,String zip,String type) {
		super(id, password,name,phoneNumber, address, zip);
		this.type=type;
	}
	public Establishment(){
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return super.toString()+ "Establishment [type=" + type + "]";
	}
	
}
