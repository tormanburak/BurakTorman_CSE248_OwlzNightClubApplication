package p0;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Establishment extends User implements Serializable{
	private String type;
	private Set<Event> eventSet;
	private Set<Employee> employeeSet;
	
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
	public void createEventSet(){
		eventSet = new LinkedHashSet();
	}
	public Set<Event> getEventSet() {
		return eventSet;
	}
	public void setEventSet(Set<Event> eventSet) {
		this.eventSet = eventSet;
	}
	
	
	public void putToEmployeeSet(Employee emp){
		employeeSet.add(emp);
	}
	public void removeFromEmployeeSet(Employee emp){
		employeeSet.remove(emp);
	}
	
	public void createEmployeeSet(){
		employeeSet = new LinkedHashSet();
	}
	public Set<Employee> getEmployeeSet() {
		return employeeSet;
	}
	public void setEmployeeSet(Set<Employee> employeeSet) {
		this.employeeSet = employeeSet;
	}

	@Override
	public String toString() {
		return super.toString()+ "Establishment [type=" + type + "]";
	}
	
	
}
