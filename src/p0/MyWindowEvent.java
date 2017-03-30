package p0;

import java.util.EventObject;

public class MyWindowEvent extends EventObject{
	
	private User user;
	
	public MyWindowEvent(Object source) {
		super(source);
	}
	public MyWindowEvent(Object source, User user){
		super(source);
		this.user=user;
	}
	
	public User getUser(){
		return user;
	}

}
