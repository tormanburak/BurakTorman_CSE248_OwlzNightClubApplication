package p0;

import java.util.EventListener;

public interface MyMainWindowListener extends EventListener {
	
	public void profileMenuItemClicked(MyWindowEvent ev);
	
	public void cutomerRadioButtonClicked(MyWindowEvent ev);
	
	public void establishmentRadioButtonClicked(MyWindowEvent ev);
	
	public void cancelButtonClicked(MyWindowEvent ev);

	public void submitButtonClicked(MyWindowEvent ev);
	
	public void eventCreateMenuItemClicked(MyWindowEvent ev);
	
	public void eventSearchMenuItemClicked(MyWindowEvent ev);
	
	public void eventSetButtonClicked(MyWindowEvent ev);

	public void searchEventButton(MyWindowEvent ev);

	public void buyTicketsButtonClicked(MyWindowEvent ev);

	public void purchaseButtonClicked(MyWindowEvent ev);

	public void historyMenuItemClicked(MyWindowEvent ev);

	public void updateButtonClicked(MyWindowEvent ev);

	public void returnButtonClicked(MyWindowEvent ev);

	public void confirmButtonClicked(MyWindowEvent ev);

	public void infoButtonClicked(MyWindowEvent ev);

	public void allEmployeesItemClicked(MyWindowEvent ev);

	public void addEmployeesItemClicked(MyWindowEvent ev);

	public void hireButtonClicked(MyWindowEvent ev);
	


}
