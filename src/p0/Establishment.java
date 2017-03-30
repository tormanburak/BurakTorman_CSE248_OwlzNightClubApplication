package p0;

public class Establishment extends User{
	private String name ;
	public Establishment(String id, String password, String name) {
		super(id, password);
		this.name = name;
	}

}
