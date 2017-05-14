package p0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;

public class UserController {
	private User user;
	private LogInView loginView;
	private MainMenuView mainView;
	private Map<String, String> usersMap = new HashMap<>();
	private Set<User> userSet = new HashSet<User>();
	private Set<Event> allEventsSet = new LinkedHashSet<Event>();
	private Event event;
	private Event returnEvent;
	private ArrayList<Ticket> ticketArrayList;
	private ArrayList<Ticket> customerTicketArrayList;
	private ArrayList<Event> eventList;
	private ObservableList<Event> events;

	private String userDataFile = "passwordData.dat";

	private static String customerType;

	public UserController(LogInView loginV, MainMenuView mainV) {
		this.loginView = loginV;
		this.mainView = mainV;
		loginView.showLogin();

		readUsersSetFile();
		readEventsSetFile();
		readUsersLoginFieldsFile();
		logInWindowListenerMethods();
		mainMenuViewMethods();
	}

	public void checkPasswordValidty() {
		if (usersMap.containsKey(user.getId()) && usersMap.containsValue(user.getPassword())) {
			loginView.showAlert("ID or Password already exists");
		} else if (loginView.fieldsEmpty()) {
			loginView.showAlert("Please fill in empty fields");
		} else if (user.getPassword().length() < 8) {
			loginView.showAlert("Password cannot be less than 8 characters");
		} else {
			usersMap.put(user.getId().trim(), user.getPassword().trim());

			writeToUsersLoginFieldsDataFile(usersMap);
			loginView.showAlert("Congratulations you are a member now");
			// System.out.println(usersMap);
		}
		clearFields();

	}

	public void writeToUsersLoginFieldsDataFile(Map<String, String> map) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userDataFile, false));
			out.writeObject(map);
			out.close();

		} catch (FileNotFoundException e) {
			loginView.showAlert("Problem occured with writing to files");
		} catch (IOException e) {
			loginView.showAlert("IO exception");
		}
	}

	@SuppressWarnings("unchecked")
	public void readUsersLoginFieldsFile() {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(userDataFile));
			usersMap = (Map<String, String>) input.readObject();
			// System.out.println("user map "+usersMap);
			input.close();

		} catch (FileNotFoundException e) {
			//loginView.showAlert("Problem occured with reading files");
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			//loginView.showAlert("Class not found while reading file");
		}
	}

	public boolean checkUser(String name, String password) {
		for (Map.Entry<String, String> entry : usersMap.entrySet()) {
			String userName = entry.getKey();
			Object userPassWord = entry.getValue();
			// System.out.println(userName+" "+userPassWord);
			if ((userName.equals(name)) && (userPassWord.equals(password))) {
				return true;
			}
		}
		return false;
	}

	public void clearFields() {
		loginView.clearFields();
	}

	public void logInWindowListenerMethods() {

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

				if (userSet.isEmpty() || !userSet.contains(findUser(v))) {
					user = v.getUser();
					user.setId(v.getUser().getId());
					user.setPassword(v.getUser().getPassword());
				} else {
					user = findUser(v);

				}
				// printSet();
				if (user != null) {
					if (checkUser(user.getId(), user.getPassword()) == false) {
						loginView.showAlert("Password or ID is incorrect");
					} else {
						loginView.showAlert("You have logged in !");
						// System.out.println(user.toString());
						if (user.hasProfile() && user instanceof Customer) {
							ArrayList<Event> eventList = getAllEvents();
							ObservableList<Event> events = FXCollections.observableArrayList(eventList);

							mainView.showCustomerView();
							if (eventList.isEmpty()) {
								loginView.showAlert("No events at this moment");
							} else {
								mainView.showAllEvents(events);
							}
						} else if (user.hasProfile() && user instanceof Establishment) {
							

							ArrayList<Event> eventList = getEstablishmentEvents();
							ObservableList<Event> events = FXCollections.observableArrayList(eventList);

							mainView.showEstablishmentView();
								if (eventList.isEmpty()) {
									 eventList = new ArrayList<Event>();
									events = FXCollections.observableArrayList(eventList);

									mainView.showEstablishmentEvents(events);

								} else {

								mainView.showEstablishmentEvents(events);
								}
							

						}

						else {

							mainView.showIntroView();
						}
					}
				} else {
					loginView.showAlert("Password or ID is incorrect2");
				}
				clearFields();
			}
		});
	}

	public void mainMenuViewMethods() {
		mainView.setWindowListener(new MyMainWindowListener() {

			@Override
			public void profileMenuItemClicked(MyWindowEvent ev) {
				if (user.hasProfile() && user instanceof Customer) {
					mainView.showCustomerMyProfile();
					Customer user1 = (Customer) (user);
					mainView.setCustomerMyProfileFields(user1.getName(), user1.getLastName(), user1.getBirthday(),
							user1.getPhoneNumber(), user1.getAddress(), user1.getZip());

				} else if (user.hasProfile() && user instanceof Establishment) {
					mainView.showEstablishmentMyProfile();
					Establishment user2 = (Establishment) (user);
					mainView.setEstablishmentMyProfileFields(user2.getName(), user2.getPhoneNumber(),
							user2.getAddress(), user2.getZip(), user2.getType());
				}

			}

			@Override
			public void cutomerRadioButtonClicked(MyWindowEvent ev) {
				mainView.showCustomerGridPane();
				customerType = "customer";
				// System.out.println("Customer button clicked");
			}

			@Override
			public void establishmentRadioButtonClicked(MyWindowEvent ev) {
				mainView.showEstablishmentGridPane();
				customerType = "establishment";
				// System.out.println("Establishment button clicked");

			}

			@Override
			public void cancelButtonClicked(MyWindowEvent ev) {
				if (user.hasProfile() && user instanceof Customer) {
					eventList = getAllEvents();
					events = FXCollections.observableArrayList(eventList);

					mainView.showCustomerView();
					showAllEvents();

				} else if (user.hasProfile() && user instanceof Establishment) {
					eventList = getEstablishmentEvents();
					events = FXCollections.observableArrayList(eventList);

					mainView.showEstablishmentView();
					showEstablishmentEvents();
				} else {
					mainView.showIntroView();
				}
				// System.out.println("Cancel button clicked");
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
				
				
				
				if (customerType.equals("customer")) {
					if(name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || zip.isEmpty() || birthday.isEmpty() || lastName.isEmpty() ){
						loginView.showAlert("You have left one or more fields blanks");
					}else{
					user = new Customer(user.getId(), user.getPassword(), name, phoneNumber, lastName, birthday,
							address, zip);
					eventList = getAllEvents();
					events = FXCollections.observableArrayList(eventList);

					mainView.showCustomerView();
					showAllEvents();
					}
				} else if (customerType.equals("establishment")) {
					if(name.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || zip.isEmpty() || type.isEmpty()){
						loginView.showAlert("You have left one or more fields blanks");
					}else{
					user = new Establishment(user.getId(), user.getPassword(), name, phoneNumber, address, zip, type);
					mainView.showEstablishmentView();
					}
				}

				addUser(user);
				// System.out.println(user.toString());
				// System.out.println("Submit button ");
				printSet();
				
			}

			@Override
			public void eventCreateMenuItemClicked(MyWindowEvent ev) {
				mainView.showEventCreateView();
			}

			@Override
			public void eventSetButtonClicked(MyWindowEvent ev) {
				event = new Event();
				String[] eventInfo = mainView.getEventInfo();
				String eventName = eventInfo[0];
				String eventAddress = eventInfo[1];
				String eventZIP = eventInfo[2];
				String eventType = eventInfo[3];
				String eventStartTime = eventInfo[4];
				String eventDate = eventInfo[5];
				
				String price = mainView.getTicketPrice();
				int amount = mainView.getTicketAmount();
				if(amount == 0){
					loginView.showAlert("You have left ticket amount empty");
				}else{
				
				if(checkIfFieldsAreWrong(eventInfo)== true || price.isEmpty() || String.valueOf(amount).isEmpty()){
					loginView.showAlert("You have left one or more fields blanks");
				}else{
				
				Establishment establishment = ((Establishment) user);
				event = new Event(eventName, eventAddress, eventZIP, eventType, eventStartTime,eventDate);
				event.setTicket(price);
				event.createTicketArrayList(amount);
				ticketArrayList = event.getTicketArrayList();
				event.setTicketArrayList(ticketArrayList);

				if (establishment.getEventSet() == null) {
					establishment.createEventSet();
					establishment.putToSet(event);

				} else {
					establishment.putToSet(event);
				}
				loginView.showAlert("Event Successfully set !");
				System.out.println(establishment.getEventSet());
				writeUserSetFile(userSet);
				allEventsSet.add(event);
				writeEventsSetFile(allEventsSet);
				// System.out.println(event.toString());
				// System.out.println(establishment.getEventSet());
				}
				}
			}

			@Override
			public void searchEventButton(MyWindowEvent ev) {
				String eventZIP = mainView.getEventZip();
				ArrayList<Event> eventList = findEventZip(eventZIP);
				ObservableList<Event> events = FXCollections.observableArrayList(eventList);

				if (eventList.isEmpty()) {
					loginView.showAlert("No Events Found at that given ZIP");
				} else {
					mainView.showFoundEvents(events);
				}

			}

			@Override
			public void eventSearchMenuItemClicked(MyWindowEvent ev) {
				mainView.showEventSearchView();

			}

			@Override
			public void buyTicketsButtonClicked(MyWindowEvent ev) {
				event = mainView.getListViewItems();
				if (event == null) {
					loginView.showAlert("No event is picked");
				} else {
					// change buy ticket view
					mainView.showPurchaseView();
					mainView.setTicketPrice(event.getTicket().getPrice());
					System.out.println(event.toString());

				}

			}

			@Override
			public void purchaseButtonClicked(MyWindowEvent ev) {
				double price = mainView.getTicketPricePurchased();
				int ticketAmount = mainView.getTicketAmountPurchased();
				double total = calculateCost(ticketAmount, price, event.getTax());

				Customer customer = (Customer) (user);
				Establishment establishment = new Establishment();
				establishment = (Establishment) findEstablishment(event);
				Event estEvent = findEstEvent(establishment, event);
				if (event.getCustomerArrayList() == null) {
					event.createCustomerArrayList();
					event.addToCustomersArrayList(customer);
				} else {
					event.addToCustomersArrayList(customer);
				}

				Event customerEvent = new Event();

				setCustomersEventInfo(customerEvent, String.valueOf(total), ticketAmount);
				customerTicketArrayList = customerEvent.getTicketArrayList();

				if (customer.getEventSet() == null) {
					customer.createEventSet();
					customer.putToSet(customerEvent);

				} else {
					customer.putToSet(customerEvent);
				}
				if (ticketAmount > event.getTicketArrayList().size()) {
					loginView.showAlert(
							"There are " + event.getTicketArrayList().size() + " tickets left for this event");
				} else {

					loginView.showAlert("You have purchased " + ticketAmount
							+ " tickets.\nYour total plus 8.875% tax is = $" + total);
					removeTicketsFromArrayList(event, ticketAmount);
					establishment.removeFromSet(estEvent);
					establishment.putToSet(event);

				}
				System.out.println(event.getCustomerArrayList());
				writeUserSetFile(userSet);
				writeEventsSetFile(allEventsSet);

			}

			@Override
			public void historyMenuItemClicked(MyWindowEvent ev) {
				ArrayList<Event> eventList;
				ObservableList<Event> events;
				

				if (user instanceof Customer) {
					eventList = getCustomerEvents();
					events = FXCollections.observableArrayList(eventList);
					mainView.showCustomerTransactionHistory();
					mainView.showCustomerHistory(events);
				}
				if (user instanceof Establishment) {
					eventList = getEstablishmentEvents();
					events = FXCollections.observableArrayList(eventList);

					mainView.showEstablishmentHistoryView();
					mainView.showEstablishmentHistory(events);

				}

			}

			@Override
			public void updateButtonClicked(MyWindowEvent ev) {
				String[] userInfo = mainView.getUserInfo();
				
				if (user instanceof Customer) {
					if(userInfo[0].isEmpty() || userInfo[1].isEmpty() || userInfo[2].isEmpty() ||
					   userInfo[3].isEmpty() || userInfo[4].isEmpty() || userInfo[5].isEmpty()){
						loginView.showAlert("You have left one or more field blank");
					}
					else{
					user.setName(userInfo[0]);
					((Customer) user).setLastName(userInfo[1]);
					((Customer) user).setBirthday(userInfo[2]);
					user.setPhoneNumber(userInfo[3]);
					user.setAddress(userInfo[4]);
					user.setZip(userInfo[5]);
					loginView.showAlert("Your information has been updated");

					}
				}
				if (user instanceof Establishment) {
					if(userInfo[0].isEmpty() || userInfo[3].isEmpty() || userInfo[4].isEmpty() || userInfo[5].isEmpty()){
						loginView.showAlert("You have left one or more field blank");

					}
					else{
					user.setName(userInfo[0]);
					user.setPhoneNumber(userInfo[3]);
					user.setAddress(userInfo[4]);
					user.setZip(userInfo[5]);
					((Establishment) user).setType(userInfo[6]);
					loginView.showAlert("Your information has been updated");

					}
				}
				System.out.println(user.toString());
				writeUserSetFile(userSet);
				
			}

			@Override
			public void returnButtonClicked(MyWindowEvent ev) {
				returnEvent = mainView.getHistoryListViewItems();
				event = findEvent(returnEvent);
				mainView.showCustomerReturnView();
				System.out.println(event);
			}

			@Override
			public void confirmButtonClicked(MyWindowEvent ev) {

				int returningTickets = mainView.getTicketReturningAmount();
				int size = returnEvent.getTicketArrayList().size();
				double total = returningTickets * Integer.valueOf(event.getTicket().getPrice());
				double totalWithTax = total * event.getTax();
				total = total + totalWithTax;

				Establishment establishment = new Establishment();
				establishment = (Establishment) findEstablishment(event);
				Event estEvent = findEstEvent(establishment, event);
				double customerMoney = Double.valueOf(returnEvent.getTicket().getPrice()) - total;

				if (returningTickets > size) {
					loginView.showAlert(
							"You have " + size + " tickets, you can not return " + returningTickets + " tickets.");
				} else {
					returnEvent.setTicketPrice(String.valueOf(customerMoney));
					returnEvent.removeTicketsArrayList(returningTickets);

					event.addToTicketsArrayList(returningTickets);

					establishment.removeFromSet(estEvent);
					establishment.putToSet(event);
					loginView.showAlert("You have returned " + returningTickets + " tickets, Your total refund is $"
							+ total + "\nThank you.");
				}
				System.out.println(returnEvent.getTicketArrayList().size());
				writeUserSetFile(userSet);
				writeEventsSetFile(allEventsSet);

			}

			@Override
			public void infoButtonClicked(MyWindowEvent ev) {
				event = mainView.getEstablishmentHistoryViewItems();
				if(event == null){
					loginView.showAlert("No event is picked");
				}
				else if (event.getCustomerArrayList() == null) {
					loginView.showAlert("No Customers for this even yet");
				} else {

					ArrayList<Customer> customerList = event.getCustomerArrayList();
					ObservableList<Customer> customers = FXCollections.observableArrayList(customerList);

					Customer[] custArray = new Customer[customerList.size()];
					customerList.toArray(custArray);
					Set<Event> set = new HashSet<Event>();

					for (int i = 0; i < custArray.length; i++) {
						set = custArray[i].getEventSet();
						// System.out.println(set);
					}
					// System.out.println(event.getInitialTicketSold());
					// System.out.println(event.getTicketReturned());
					// System.out.println(event.getTotalTicketSold());
					// System.out.println(event.getProfit());

					mainView.showEstablishmentInfoView();
					mainView.showMyCustomerList(customers);
					mainView.setFinancialInfo(event.getInitialTicketSold(), event.getTicketReturned(),
							event.getTotalTicketSold(), event.getProfit());

				}
			}

			@Override
			public void allEmployeesItemClicked(MyWindowEvent ev) {
				Establishment establishment = (Establishment)(user);
				
				ArrayList<Employee> eventList = getAllEmployess(establishment);
				ObservableList<Employee> events = FXCollections.observableArrayList(eventList);
				
				mainView.showAllEmployeesView();
				mainView.showEmployeeList(events);
			}

			@Override
			public void addEmployeesItemClicked(MyWindowEvent ev) {
				mainView.showAddEmployeesView();
				
			}

			@Override
			public void hireButtonClicked(MyWindowEvent ev) {
				String[] employeeInfo = mainView.getEmployeeInfo();
				String name = employeeInfo[0];
				String lastName = employeeInfo[1];
				String position = employeeInfo[2];
				String salary = employeeInfo[3];
				String status = "hired";
				if(checkIfFieldsAreWrong(employeeInfo)== true){
					loginView.showAlert("You have left one or more fields blanks");
				}else{
				
				Establishment establishment = (Establishment)(user);
				Employee employee = new Employee(name,lastName,position,salary,status);
				if(establishment.getEmployeeSet() == null){
					establishment.createEmployeeSet();
					establishment.putToEmployeeSet(employee);
				}else{
					establishment.putToEmployeeSet(employee);
				}
				loginView.showAlert("You have hired - \nName : "+name+"\nLast Name : "+lastName+"\nPosition : "+position+"\nSalary : "+salary);
				writeUserSetFile(userSet);
				System.out.println(establishment.getEmployeeSet());
				
				//System.out.println(Arrays.toString(employeeInfo));
				}
			}

			@Override
			public void searchEmployeeButtonClicked(MyWindowEvent ev) {
				Establishment establishment = (Establishment)(user);
				String lastName = mainView.getEmployeeLastName().toLowerCase();
				ArrayList<Employee> employeeList = findEmployee(lastName);
				
				if(employeeList.isEmpty()){
					loginView.showAlert("No Employe Found");
					ArrayList<Employee> allEmployees = getAllEmployess(establishment);
					ObservableList<Employee> employees = FXCollections.observableArrayList(allEmployees);
					mainView.showEmployeeList(employees);


				}else{
					
					ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
					
					mainView.showEmployeeList(employees);
				}
				
			}

			@Override
			public void firedEmployeeInfoButtonClicked(MyWindowEvent ev) {
				Establishment establishment = (Establishment)(user);
				
				ArrayList<Employee> eventList = getAllFiredEmployess(establishment);
				ObservableList<Employee> events = FXCollections.observableArrayList(eventList);
				
				mainView.showEmployeeList(events);
				
			}

			

			@Override
			public void hiredEmployeeInfoButtonClicked(MyWindowEvent ev) {
				Establishment establishment = (Establishment)(user);
				
				ArrayList<Employee> eventList = getAllHiredEmployess(establishment);
				ObservableList<Employee> events = FXCollections.observableArrayList(eventList);
				
				mainView.showEmployeeList(events);
				
			}

			@Override
			public void fireEmployeeButtonClicked(MyWindowEvent ev) {
				Employee employee = mainView.getEmployeeListViewItems();
				if(employee == null){
					loginView.showAlert("No Employee is selected");
				}if(employee.getStatus().equals("fired")){
					loginView.showAlert("This employee is already fired");
				}
				else{
					employee.setStatus("fired");
					loginView.showAlert("You have fired : "+employee);
				}
				writeUserSetFile(userSet);

			}

		});

	}

	public void writeUserSetFile(Set<User> userSet) {

		try {
			@SuppressWarnings("resource")
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("userSet.dat", false));
			writer.writeObject(userSet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEventsSetFile(Set<Event> eventSet) {

		try {
			@SuppressWarnings("resource")
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("allEventsSet.dat", false));
			writer.writeObject(allEventsSet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readUsersSetFile() {
		try {
			@SuppressWarnings("resource")
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("userSet.dat"));
			userSet = (HashSet<User>) reader.readObject();
			// printSet();
		} catch (FileNotFoundException e) {
			System.out.println("Fof exp reading set");
		} catch (IOException e) {
			System.out.println("Ioexp reading set");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exp reading set");
		}
	}

	@SuppressWarnings("unchecked")
	public void readEventsSetFile() {
		try {
			@SuppressWarnings("resource")
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("allEventsSet.dat"));
			allEventsSet = (HashSet<Event>) reader.readObject();
			// printSet();
		} catch (FileNotFoundException e) {
			System.out.println("Fof exp reading set");
		} catch (IOException e) {
			System.out.println("Ioexp reading set");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exp reading set");
		}
	}

	public boolean checkIfUserExist(User user) {
		User[] array = new User[userSet.size()];
		userSet.toArray(array);

		for (int i = 0; i < array.length; i++) {
			if (array[i].getId().equals(user.getId())) {
				return true;
			}
		}
		return false;

	}

	public User findEstablishment(Event event) {
		User[] array = new User[userSet.size()];
		userSet.toArray(array);
		Establishment findUser = new Establishment();
		for (int i = 0; i < array.length; i++) {
			if (array[i] instanceof Establishment) {
				findUser = (Establishment) (array[i]);
				if (findUser.getEventSet().contains(event)) {
					findUser = (Establishment) array[i];
				}
			}
		}
		return findUser;
	}

	public Event findEstEvent(Establishment est, Event event) {
		Event[] array = new Event[est.getEventSet().size()];
		est.getEventSet().toArray(array);
		Event getEvent = new Event();

		for (int i = 0; i < array.length; i++) {
			if (array[i].getEventZIP().equals(event.getEventZIP())
					&& array[i].getEventName().equals(event.getEventName())
					&& array[i].getEventStartTime().equals(event.getEventStartTime())) {
				getEvent = array[i];
			}
		}
		return getEvent;

	}

	public ArrayList<Event> findEventZip(String zip) {
		Event[] array = new Event[allEventsSet.size()];
		allEventsSet.toArray(array);
		ArrayList<Event> eventList = new ArrayList<Event>();

		for (int i = 0; i < array.length; i++) {
			if (array[i].getEventZIP().equals(zip)) {
				eventList.add(array[i]);
			}
		}
		Collections.reverse(eventList);
		return eventList;

	}

	public Event findEvent(Event event) {
		Event[] array = new Event[allEventsSet.size()];
		allEventsSet.toArray(array);
		Event getEvent = new Event();

		for (int i = 0; i < array.length; i++) {
			if (array[i].getEventZIP().equals(event.getEventZIP())
					&& array[i].getEventName().equals(event.getEventName())
					&& array[i].getEventStartTime().equals(event.getEventStartTime())) {
				getEvent = array[i];
			}
		}
		return getEvent;

	}

	public ArrayList<Event> getAllEvents() {
		Event[] array = new Event[allEventsSet.size()];

		allEventsSet.toArray(array);
		ArrayList<Event> eventList = new ArrayList<Event>();

		for (int i = 0; i < array.length; i++) {

			eventList.add(array[i]);

		}
		Collections.reverse(eventList);
		return eventList;

	}

	public ArrayList<Event> getCustomerEvents() {
		Customer customer = (Customer) (user);
		if(customer.getEventSet() == null){
			loginView.showAlert("You have not made any purchases");
			ArrayList<Event> emptyList = new ArrayList<Event>();
			return emptyList;
		}else{
		Event[] array = new Event[customer.getEventSet().size()];
		customer.getEventSet().toArray(array);
		ArrayList<Event> eventList = new ArrayList<Event>();

		for (int i = 0; i < array.length; i++) {

			eventList.add(array[i]);

		}
		Collections.reverse(eventList);
		return eventList;
		}
	}

	public ArrayList<Event> getEstablishmentEvents() {
		Establishment establishment = (Establishment) (user);
		if(establishment.getEventSet() == null){
			ArrayList<Event> emptyList = new ArrayList<Event>();
			return emptyList;
		}else{
		Event[] array = new Event[establishment.getEventSet().size()];

		establishment.getEventSet().toArray(array);
		ArrayList<Event> eventList = new ArrayList<Event>();
		
		for (int i = 0; i < array.length; i++) {

			eventList.add(array[i]);

		}
		Collections.reverse(eventList);
		return eventList;
		}
	}

	public double calculateCost(int amount, double price, double tax) {
		double cost = amount * price;
		double taxOfCost = tax * cost;
		double total = cost + taxOfCost;
		return total;
	}

	public void setCustomersEventInfo(Event customerEvent, String total, int ticketAmount) {

		customerEvent.setEventName(event.getEventName());
		customerEvent.setEventAddress(event.getEventAddress());
		customerEvent.setEventZIP(event.getEventZIP());
		customerEvent.setEventType(event.getEventType());
		customerEvent.setEventStartTime(event.getEventStartTime());
		customerEvent.setTicket(String.valueOf(total));
		customerEvent.createTicketArrayList(ticketAmount);
	}

	public void addUser(User user) {
		if (checkIfUserExist(user) == false) {
			userSet.add(user);
			writeUserSetFile(userSet);
		}
	}

	public void printSet() {
		for (User u : userSet) {
			System.out.println(u.toString());
		}
	}

	public void removeTicketsFromArrayList(Event event, int amount) {
		event.removeTicketsArrayList(amount);

	}

	public void showAllEvents() {
		if (eventList.isEmpty()) {
			loginView.showAlert("No events at this moment");
		} else {
			mainView.showAllEvents(events);
		}
	}

	public void showEstablishmentEvents() {
		if (eventList.isEmpty()) {
			
		} else {
			mainView.showEstablishmentEvents(events);
		}
	}

	public User findUser(MyWindowEvent v) {
		User[] array = new User[userSet.size()];
		userSet.toArray(array);

		for (int i = 0; i < array.length; i++) {
			if (array[i].getId().equals(v.getUser().getId())) {
				user = array[i];
				return user;
			}
		}
		return null;
	}
	public ArrayList<Employee> findEmployee(String lastName){
		Establishment establishment = (Establishment)(user);
		Employee[] array = new Employee[establishment.getEmployeeSet().size()];
		establishment.getEmployeeSet().toArray(array);
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		for(int i =0; i<array.length;i++){
			if(array[i].getLastName().equals(lastName)){
				 employeeList.add(array[i]);
			}
		}
		return employeeList;
	}
	public ArrayList<Employee> getAllEmployess(Establishment establishment){
		
		if(establishment.getEmployeeSet() == null){
			ArrayList<Employee> emptyList = new ArrayList<Employee>();
			return emptyList;
		}else{
			Employee[] array = new Employee[establishment.getEmployeeSet().size()];

		establishment.getEmployeeSet().toArray(array);
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		for (int i = 0; i < array.length; i++) {

			employeeList.add(array[i]);

		}
		Collections.reverse(employeeList);
		return employeeList;
		}
	}
		
		private ArrayList<Employee> getAllFiredEmployess(Establishment establishment) {
			if(establishment.getEmployeeSet() == null){
				ArrayList<Employee> emptyList = new ArrayList<Employee>();
				return emptyList;
			}else{
				Employee[] array = new Employee[establishment.getEmployeeSet().size()];

			establishment.getEmployeeSet().toArray(array);
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			
			for (int i = 0; i < array.length; i++) {
				if(array[i].getStatus().equals("fired")){
					employeeList.add(array[i]);
				}

			}
			Collections.reverse(employeeList);
			return employeeList;
			}
	}
		private ArrayList<Employee> getAllHiredEmployess(Establishment establishment) {
			if(establishment.getEmployeeSet() == null){
				ArrayList<Employee> emptyList = new ArrayList<Employee>();
				return emptyList;
			}else{
				Employee[] array = new Employee[establishment.getEmployeeSet().size()];

			establishment.getEmployeeSet().toArray(array);
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			
			for (int i = 0; i < array.length; i++) {
				if(array[i].getStatus().equals("hired")){
					employeeList.add(array[i]);
				}

			}
			Collections.reverse(employeeList);
			return employeeList;
			}
	}
	public boolean checkIfFieldsAreWrong(String[] info){
		for(int i=0; i<info.length;i++){
			if(info[i].isEmpty()){
				return true;
			}
		}
		return false;
	}
}
