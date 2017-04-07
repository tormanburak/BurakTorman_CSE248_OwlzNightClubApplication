package p0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
;

public class UserController {
	private User user;
	private LogInView loginView;
	private MainMenuView mainView;
	private Map<String,String> usersMap = new HashMap<>();
	private Set<User> userSet = new HashSet<User>();
	
	private String userDataFile = "data.dat";
	
	private static String customerType ;
	
	public UserController(LogInView loginV, MainMenuView mainV){
		this.loginView=loginV;
		this.mainView= mainV;
		loginView.showLogin();
		
		readUsersSetFile();
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
			System.out.println(usersMap);
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
			System.out.println("user map "+usersMap);
			input.close();
			
		} catch (FileNotFoundException e) {
			loginView.showAlert("Problem occured with reading files");
		} catch (IOException e) {
			loginView.showAlert("IO exception");
		} catch (ClassNotFoundException e) {
			loginView.showAlert("Class not found while reading file");
		}
	}
	
	public boolean checkUser(String name,String password){
		for (Map.Entry<String, String> entry : usersMap.entrySet()) {
		    String userName = entry.getKey();
		    Object userPassWord = entry.getValue();
		    System.out.println(userName+" "+userPassWord);
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

				if(userSet.isEmpty()){
					user = v.getUser();
					user.setId(v.getUser().getId());
					user.setPassword(v.getUser().getPassword());			
				}else{
					user = findUser(v);

				}
				printSet();
				if(user != null){
					if(checkUser(user.getId(),user.getPassword()) == false){
					loginView.showAlert("Password or ID is incorrect");
					}else{
					loginView.showAlert("You have logged in !");
					System.out.println(user.toString());
					//check if user has created a profile before
					//check if its customer or est and show proper view
					mainView.showIntroView();
					//mainView.showProfileGridPane();

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
				//check if profile has been already created
				//pick type
				//show gui
				mainView.showProfileGridPane();
				System.out.println("Clickhed");
				
			}
			@Override
			public void cutomerRadioButtonClicked(MyWindowEvent ev) {
				mainView.showCustomerGridPane();
				customerType = "customer";
				System.out.println("Customer button clicked");
			}
			@Override
			public void establishmentRadioButtonClicked(MyWindowEvent ev) {
				mainView.showEstablishmentGridPane();	
				customerType = "establishment";
				System.out.println("Establishment button clicked");

			}
			@Override
			public void cancelButtonClicked(MyWindowEvent ev) {
				mainView.showIntroView();
				System.out.println("Cancel button clicked");
			}
			@Override
			public void submitButtonClicked(MyWindowEvent ev) {
				String[] info = mainView.getInfo();
				String name = info[0];
				String lastName = info[1];
				String birthday = info[2];
				String phoneNumber = info[3];
				String address = info[4];
				String zip = info[5];
				String type = info[6];
				
				if(customerType.equals("customer")){
					user = new Customer(user.getId(),user.getPassword(),name,phoneNumber,lastName,birthday,address,zip);
					mainView.showCustomerView();
					
				}
				else if(customerType.equals("establishment")){
					user = new Establishment(user.getId(),user.getPassword(),name,phoneNumber,address, zip, type);
					mainView.showEstablishmentView();
				}
				
				addUser(user);
				System.out.println(user.toString());
				System.out.println("Submit button ");
				printSet();
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
	@SuppressWarnings("unchecked")
	public void readUsersSetFile(){
		try {
			@SuppressWarnings("resource")
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("userSet.dat"));
			userSet = (HashSet<User>) reader.readObject();
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
