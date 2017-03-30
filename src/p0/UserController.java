package p0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
;

public class UserController {
	private User user;
	private LogInView loginView;
	private MainMenuView mainView;
	private Map<String,String> usersMap = new HashMap<>();
	
	private String userDataFile = "data.dat";

	public UserController(LogInView loginView, MainMenuView mainView){
		this.loginView=loginView;
		this.mainView= mainView;
		loginView.showLogin();

		loginView.setWindowListener(new MyWindowListener() {

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
					mainView.show();
				}
				clearFields();
			}
			
		});
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
}
