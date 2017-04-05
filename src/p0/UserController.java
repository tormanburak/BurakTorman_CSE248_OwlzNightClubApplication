package p0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
;

public class UserController {
	private User user;
	private LogInView loginView;
	private MainMenuView mainView;
	private Map<String,String> usersMap = new HashMap<>();
	
	private String userDataFile = "data.dat";
	
	private static String customerType ;
	
	public UserController(LogInView loginV, MainMenuView mainV){
		this.loginView=loginV;
		this.mainView= mainV;
		loginView.showLogin();

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
			
			writeToUsersDataFile(usersMap);
			loginView.showAlert("Congratulations you are a member now");
			System.out.println(usersMap);
		}
		clearFields();
		
	}
	public void writeToUsersDataFile(Map<String,String> map){
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(userDataFile,true));
			out.writeObject(map);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			loginView.showAlert("Problem occured with writing to files");
		} catch (IOException e) {
			loginView.showAlert("IO exception");
		}
	}
	@SuppressWarnings("unchecked")
	public void readFile(){
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(userDataFile));
			usersMap = (Map<String, String>)input.readObject();
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
				user = v.getUser();
				user.setId(v.getUser().getId());
				user.setPassword(v.getUser().getPassword());
				readFile();
				System.out.println(usersMap);
				if(checkUser(user.getId(),user.getPassword()) == false){
					loginView.showAlert("Password or ID is incorrect");
				}else{
					loginView.showAlert("You have logged in !");
					System.out.println(user.toString());
					mainView.show();
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
				user = new Establishment();
				customerType = "establishment";
				System.out.println("Establishment button clicked");

			}
			@Override
			public void cancelButtonClicked(MyWindowEvent ev) {
				mainView.show();
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
					user = new Customer(user.getId(),user.getPassword(),name,lastName,birthday,phoneNumber,address,zip);
					
				}
				if(customerType.equals("establishment")){
					user = new Establishment(user.getId(),user.getPassword(),name,phoneNumber,address, zip, type);
				}

				System.out.println(user.toString());
				System.out.println("Submit button");
			}
			
		});
		
	}

}
