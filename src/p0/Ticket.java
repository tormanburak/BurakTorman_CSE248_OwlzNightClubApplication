package p0;

import java.io.Serializable;

public class Ticket implements Serializable {
	private String price;
	
	public Ticket(String price){
		this.setPrice(price);
	}

	public String getPrice() {return price;}

	public void setPrice(String price) {this.price = price;}

	@Override
	public String toString() {
		return "Ticket [price="+ price + "]";
	}
	
}
