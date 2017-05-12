package p0;

import java.io.Serializable;
import java.util.Set;

public class Establishment extends User implements Serializable{
	private String type;
	private Set<Event> eventSet;
	
	public Establishment(String id, String password, String name,String phoneNumber ,String address,String zip,String type) {
		super(id, password,name,phoneNumber, address, zip);
		this.type=type;
	}
	public Establishment(String id, String password, String name,String phoneNumber ,String address,String zip,String type,Set<Event> set) {
		super(id, password,name,phoneNumber, address, zip);
		this.type=type;
		this.eventSet = set;
	}
	public Establishment(){
		
	}
	public String getType() {
		return type;
	}
	
	public void putToSet(Event event){
		eventSet.add(event);
	}
	public void removeFromSet(Event event){
		eventSet.remove(event);
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Event> getEventSet() {
		return eventSet;
	}
	public void setEventSet(Set<Event> eventSet) {
		this.eventSet = eventSet;
	}

	@Override
	public String toString() {
		return super.toString()+ "Establishment [type=" + type + "]";
	}
	
	
}
