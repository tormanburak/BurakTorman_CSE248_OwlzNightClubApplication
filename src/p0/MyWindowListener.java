package p0;

import java.util.EventListener;

public interface MyWindowListener extends EventListener {
	public void signUpButtonClicked(MyWindowEvent ev);
	
	public void loginButtonClicked(MyWindowEvent v);
}
