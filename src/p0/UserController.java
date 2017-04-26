package p0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;
;

public class UserController {
	private User user;
	private LogInView loginView;
	private MainMenuView mainView;
	private Map<String,String> usersMap = new HashMap<>();
	private Set<User> userSet = new HashSet<User>();
	private Set<Event> allEventsSet = new HashSet<Event>();
	
	private String userDataFile = "data.dat";
	
	private static String customerType ;
	
	public UserController(LogInView loginV, MainMenuView mainV){
		this.loginView=loginV;
		this.mainView= mainV;
		loginView.showLogin();
		
		readUsersSetFile();
		readEventsSetFile();
		readUsersLoginFieldsFile();
		logInWindowListenerMethods();
		mainMenuViewMethods();
	}

	public void checkPasswordValidty(){
		if( usersMap.containsKey(user.getId()) && usersMap.containsValue(user.getPassword())){
			loginView.showAlert("ID or Password already exists");
		}
		else if(loginView.fieldsEmpty()){
			loginView.showAlert("Please fill in empty fields");
		}
		else if (user.getPassword().length()<8){
			loginView.showAlert("Password cannot be less than 8 characters");
		}
		else{
			usersMap.put(user.getId().trim(), user.getPassword().trim());
			
			writeToUsersLoginFieldsDataFile(usersMap);
			loginView.showAlert("Congratulations you are a member now");
			//System.out.println(usersMap);
		}
		clearFields();
		
	}
	public void writeToUsersLoginFieldsDataFile(Map<String,String> map){
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(userDataFile,false));
			out.writeObject(map);
			out.close();
			
		} catch (FileNotFoundException e) {
			loginView.showAlert("Problem occured with writing to files");
		} catch (IOException e) {
			loginView.showAlert("IO exception");
		}
	}
	@SuppressWarnings("unchecked")
	public void readUsersLoginFieldsFile(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(userDataFile));
			usersMap = (Map<String, String>)input.readObject();
			//System.out.println("user map "+usersMap);
			input.close();
			
		} catch (FileNotFoundException e) {
			loginView.showAlert("Problem occured with reading files");
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			loginView.showAlert("Class not found while reading file");
		}
	}
	
	public boolean checkUser(String name,String password){
		for (Map.Entry<String, String> entry : usersMap.entrySet()) {
		    String userName = entry.getKey();
		    Object userPassWord = entry.getValue();
		    //System.out.println(userName+" "+userPassWord);
		    if((userName.equals(name)) && (userPassWord.equals(password))){
		    	return true;
		   }
		}
		return false;
	}
	public void clearFields(){
		loginView.clearFields();
	}
	public void logInWindowListenerMethods(){
		
		loginView.setWindowListener(new MyLoginWindowListener() {

			@Override
			public void signUpButtonClicked(MyWindowEvent ev) {
				user = ev.getUser();
				user.setId(ev.getUser().getId());
				user.setPassword(ev.getUser().getPassword());
				
				checkPasswordValidty();
			}

			@Override
			public void loginButtonClicked(MyWindowEvent v) {

				if(userSet.isEmpty() || !userSet.contains(findUser(v))){
					user = v.getUser();
					user.setId(v.getUser().getId());
					user.setPassword(v.getUser().getPassword());			
				}else{
					user = findUser(v);

				}
				//printSet();
				if(user != null){
					if(checkUser(user.getId(),user.getPassword()) == false){
					loginView.showAlert("Password or ID is incorrect");
					}else{
					loginView.showAlert("You have logged in !");
					//System.out.println(user.toString());
					if(user.hasProfile() && user instanceof Customer){
						mainView.showCustomerView();
					}else if(user.hasProfile() && user instanceof Establishment){
						mainView.showEstablishmentView();
					}

					else{
					mainView.showIntroView();
						}
					}
				}else{
					loginView.showAlert("Password or ID is incorrect2");
				}
				clearFields();
			}
		});
	}
	
	public void mainMenuViewMethods(){
		mainView.setWindowListener(new MyMainWindowListener(){

			@Override
			public void profileMenuItemClicked(MyWindowEvent ev) {
				if(user.hasProfile() && user instanceof Customer){
					mainView.showCustomerGridPane();
				}else if (user.hasProfile() && user instanceof Establishment){
					mainView.showEstablishmentGridPane();
				}
				
			}
			@Override
			public void cutomerRadioButtonClicked(MyWindowEvent ev) {
				mainView.showCustomerGridPane();
				customerType = "customer";
				//System.out.println("Customer button clicked");
			}
			@Override
			public void establishmentRadioButtonClicked(MyWindowEvent ev) {
				mainView.showEstablishmentGridPane();	
				customerType = "establishment";
				//System.out.println("Establishment button clicked");

			}
			@Override
			public void cancelButtonClicked(MyWindowEvent ev) {
				if(user.hasProfile() && user instanceof Customer){
					mainView.showCustomerView();
				}else if (user.hasProfile() && user instanceof Establishment){
					mainView.showEstablishmentView();
				}else{
				mainView.showIntroView();
				}
				//System.out.println("Cancel button clicked");
			}
			@Override
			public void submitButtonClicked(MyWindowEvent ev) {
				String[] userInfo = mainView.getUserInfo();
				String name = userInfo[0];
				String lastName = userInfo[1];
				String birthday = userInfo[2];
				String phoneNumber = userInfo[3];
				String address = userInfo[4];
				String zip = userInfo[5];
				String type = userInfo[6];
				
				if(customerType.equals("customer")){
					user = new Customer(user.getId(),user.getPassword(),name,phoneNumber,lastName,birthday,address,zip);
					mainView.showCustomerView();
					
				}
				else if(customerType.equals("establishment")){
					user = new Establishment(user.getId(),user.getPassword(),name,phoneNumber,address, zip, type);
					mainView.showEstablishmentView();
				}
				
				addUser(user);
				//System.out.println(user.toString());
				//System.out.println("Submit button ");
				printSet();
			}
			@Override
			public void eventCreateMenuItemClicked(MyWindowEvent ev) {
					mainView.showEventCreateView();
			}
			@Override
			public void eventSetButtonClicked(MyWindowEvent ev) {
				Event event = new Event();
				String[] eventInfo = mainView.getEventInfo();
				String eventName = eventInfo[0];
				String eventAddress = eventInfo[1];
				String eventZIP = eventInfo[2];
				String eventType = eventInfo[3];
				String eventStartTime = eventInfo[4];
				
				String price = mainView.getTicketPrice();
				int amount = mainView.getTicketAmount();
				
				Establishment establishment = ((Establishment)user);
				event = new Event(eventName,eventAddress,eventZIP,eventType,eventStartTime);
				event.setTicket(price);
				event.createTicketArray(amount);
				Ticket[] ticketArray = event.getTicketArray();
				event.setTicketArray(ticketArray);
				
				if( establishment.getEventSet() == null){
					Set<Event> eventSet = new HashSet<Event>();
					eventSet.add(event);
					establishment.setEventSet(eventSet);
					
				}else{
					establishment.putToSet(event);
				}
				loginView.showAlert("Event Successfully set !");
				writeUserSetFile(userSet);
				allEventsSet.add(event);
				writeEventsSetFile(allEventsSet);
				System.out.println(event.toString());
				//System.out.println(establishment.getEventSet());
				

			}
			@Override
			public void searchEventButton(MyWindowEvent ev) {
				String eventZIP = mainView.getEventZip();
				ArrayList<Event> eventList = findEventZip(eventZIP);
				ObservableList<Event> events = FXCollections.observableArrayList(eventList);
				
				if(eventList.isEmpty()){
					loginView.showAlert("No Events Found at that given ZIP");
				}else{
					mainView.showFoundEvents(events);
				}
				
			}
			@Override
			public void eventSearchMenuItemClicked(MyWindowEvent ev) {
				mainView.showEventSearchView();
				
			}
			@Override
			public void buyTicketsButtonClicked(MyWindowEvent ev) {
				Event event = mainView.getListViewItems();
				if(event == null){
					loginView.showAlert("No event is picked");
				}else{
					// change buy ticket view
					mainView.showPurchaseView();
					mainView.setTicketPrice(event.getTicket().getPrice());
					System.out.println(event.toString());

				}
				
			}	
		});	
	}

	public void writeUserSetFile(Set<User> userSet){
	  
	        try {
	        	@SuppressWarnings("resource")
				ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("userSet.dat",false));
	     		writer.writeObject(userSet);
	      	    
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	public void writeEventsSetFile(Set<Event> eventSet){
		  
        try {
        	@SuppressWarnings("resource")
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("allEventsSet.dat",false));
     		writer.writeObject(allEventsSet);
      	    
		} catch (IOException e) {
			e.printStackTrace();
		} 
}
	@SuppressWarnings("unchecked")
	public void readUsersSetFile(){
		try {
			@SuppressWarnings("resource")
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("userSet.dat"));
			userSet = (HashSet<User>) reader.readObject();
			//printSet();
		} catch (FileNotFoundException e) {
			System.out.println("Fof exp reading set");
		} catch (IOException e) {
			System.out.println("Ioexp reading set");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exp reading set");
		}
	}
	@SuppressWarnings("unchecked")
	public void readEventsSetFile(){
		try {
			@SuppressWarnings("resource")
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("allEventsSet.dat"));
			allEventsSet = (HashSet<Event>) reader.readObject();
			printSet();
		} catch (FileNotFoundException e) {
			System.out.println("Fof exp reading set");
		} catch (IOException e) {
			System.out.println("Ioexp reading set");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exp reading set");
		}
	}
	public  boolean checkIfUserExist(User user){
		User[] array = new User[userSet.size()];
		userSet.toArray(array);
		
		for(int i =0; i< array.length;i++){
			if(array[i].getId().equals(user.getId())){
				return true;
			}
		}
		return false;
		
	}
	public  ArrayList<Event> findEventZip(String zip){
		Event[] array = new Event[allEventsSet.size()];
		allEventsSet.toArray(array);
		ArrayList<Event> eventList = new ArrayList<Event>();

		for(int i =0; i< array.length;i++){
			if(array[i].getEventZIP().equals(zip)){
				 eventList.add(array[i]);
			}
		}
		return eventList;
		
	}
	public void addUser(User user){
	if(checkIfUserExist(user) == false){
		userSet.add(user);
		writeUserSetFile(userSet);
		}
	}
	public void printSet(){
		for(User u : userSet){
			System.out.println(u.toString());
		}
	}
	public User findUser(MyWindowEvent v){
		User[] array = new User[userSet.size()];
		userSet.toArray(array);
		
		for(int i =0; i<array.length;i++){
			if(array[i].getId().equals(v.getUser().getId())){
				user = array[i];
				return user;
			}
		}
		return null;
		
	}
}
