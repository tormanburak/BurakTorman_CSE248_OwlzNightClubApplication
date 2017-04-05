package p0;

import java.util.EventListener;

public interface MyMainWindowListener extends EventListener {
	
	public void profileMenuItemClicked(MyWindowEvent ev);
	
	public void cutomerRadioButtonClicked(MyWindowEvent ev);
	
	public void establishmentRadioButtonClicked(MyWindowEvent ev);
	
	public void cancelButtonClicked(MyWindowEvent ev);

	public void submitButtonClicked(MyWindowEvent ev);


}
