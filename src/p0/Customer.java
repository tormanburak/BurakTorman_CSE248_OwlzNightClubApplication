package p0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Customer extends User implements Serializable {
	private String lastName;
	private String birthday;
	private Set<Event> eventSet;

	public Customer(String id, String password, String name,String phoneNumber,String lastName, String birthday,String address,String zip) {
		super(id, password,name,phoneNumber, address, zip);
		this.lastName =lastName;
		this.birthday= birthday;
	}
	public Customer(String id, String password, String name,String phoneNumber,String lastName, String birthday,String address,String zip,Set<Event> eventSet) {
		super(id, password,name,phoneNumber, address, zip);
		this.lastName =lastName;
		this.birthday= birthday;
		this.eventSet = eventSet;
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
	public void putToSet(Event event){
		eventSet.add(event);
	}
	
	public Set<Event> getEventSet() {
		return eventSet;
	}
	public void setEventSet(Set<Event> eventSet) {
		this.eventSet = eventSet;
	}

	
	
}
