package p0;

import java.io.Serializable;
import java.util.Arrays;

public class Event implements Serializable{
	private String eventName;
	private String eventAddress;
	private String eventZIP;
	private String eventType;
	private String eventStartTime;
	private Ticket[] ticketArray;
	private Ticket ticket;
	
	public Event(String eventName,String eventAddress,String eventZIP, String eventType , String eventStartTime){
		this.setEventName(eventName);
		this.eventAddress = eventAddress;
		this.eventZIP= eventZIP;
		this.eventType = eventType;
		this.eventStartTime = eventStartTime;
	}

	public Event() {
	}
	public void createTicketArray(int amount){
		ticketArray = new Ticket[amount];
		for(int i =0; i<amount; i++){
			ticketArray[i] = ticket;
		}
	}
	public Ticket[] getTicketArray(){
		return ticketArray;
	}
	public void setTicketArray(Ticket[] array){
		this.ticketArray = array;
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
		return "Event Name = " + eventName + " |Event Address = " + eventAddress + " |Event ZIP = " + eventZIP
				+ " |Event Type = " + eventType + " |Event Start-Time = " + eventStartTime + " |Tickets Available = "
				+ ticketArray.length+ " |Ticket price = "+ticket.getPrice()+"";
	}

	public Ticket getTicket() {
		return ticket;
	}
	

	public void setTicket(String price) {
		ticket = new Ticket(price);
	}


	
	
}
