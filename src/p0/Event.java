package p0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Event implements Serializable{
	private String eventName;
	private String eventAddress;
	private String eventZIP;
	private String eventType;
	private String eventStartTime;
	private String eventDate;
	private ArrayList<Ticket> ticketArrayList;
	private ArrayList<Customer> customerArrayList;
	private Ticket ticket;
	private Customer customer;
	private ArrayList<Integer> boughtTicketList = new ArrayList<Integer>();
	private ArrayList<Integer> returnedTicketList  = new ArrayList<Integer>();
	private ArrayList<Integer> profitList  = new ArrayList<Integer>();

	private int boughtTicket =0;
	private int returnedTicket =0;
	private double profit = 0.0;
	private double tax = 0.08875;
	
	public Event(String eventName,String eventAddress,String eventZIP, String eventType , String eventStartTime, String eventDate){
		this.setEventName(eventName);
		this.eventAddress = eventAddress;
		this.eventZIP= eventZIP;
		this.eventType = eventType;
		this.eventStartTime = eventStartTime;
		this.eventDate = eventDate;
	}

	public Event() {
	}
	public void createTicketArrayList(int amount){
		ticketArrayList = new ArrayList<Ticket>(amount);
		for(int i =0; i<amount; i++){
			ticketArrayList.add(ticket);			
		}
		
	}
	public void removeTicketsArrayList(int amount){
		for(int i =0; i<amount; i++){
			ticketArrayList.remove(ticket);
			boughtTicketList.add(amount);
			boughtTicket = 0 + amount;
		}
	}
	public void addToTicketsArrayList(int amount){
		for(int i =0; i<amount; i++){
			ticketArrayList.add(ticket);
			returnedTicketList.add(amount);
			returnedTicket = 0 - amount;
		}

	}
	public ArrayList<Ticket> getTicketArrayList(){
		return ticketArrayList;
	}
	public void setTicketArrayList(ArrayList<Ticket> list){
		this.ticketArrayList = list;
	}
	public void createCustomerArrayList(){
		customerArrayList = new ArrayList<Customer>();
		
	}

	public void addToCustomersArrayList(Customer cust){
			customerArrayList.add(cust);		

	}
	public ArrayList<Customer> getCustomerArrayList(){
		return customerArrayList;
	}
	public void setCustomerArrayList(ArrayList<Customer> list){
		this.customerArrayList = list;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(String eventStartTime) {
		this.eventStartTime = eventStartTime;
	}



	public String getEventZIP() {
		return eventZIP;
	}

	public void setEventZIP(String eventZIP) {
		this.eventZIP = eventZIP;
	}

	

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "Event Name = " + eventName + " |||Event Address = " + eventAddress + " |||Event ZIP = " + eventZIP
				+ " |||Event Type = " + eventType + " |||Event Start-Time = " + eventStartTime + " |||Event Date = "+eventDate+ " |||Tickets Available = "
				+ ticketArrayList.size()+ " |||Ticket price = $"+ticket.getPrice()+"";
	}

	public Ticket getTicket() {
		return ticket;
	}
	public void setTicketPrice(String price){
		ticket.setPrice(price);
	}
	

	public void setTicket(String price) {
		ticket = new Ticket(price);
	}
	
	public double getProfit(){
		double price = Double.valueOf(getTicket().getPrice());
		profit = ((getTotalTicketSold()*price) );
		double taxForProfit = profit*tax;
		return profit + taxForProfit;
		
	}
	public int getInitialTicketSold(){
		return boughtTicketList.size();
	}
	public int getTicketReturned(){
		return returnedTicketList.size();
	}
	
	public int getTotalTicketSold(){
		return getInitialTicketSold() - getTicketReturned();
	}
	public double getTax(){
		return tax;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}


	
	
}
