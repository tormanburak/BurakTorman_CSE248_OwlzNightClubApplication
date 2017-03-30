package p0;

import java.util.HashMap;
import java.util.Map;
;

public class UserController {
	private User user;
	private LogInView view;
	private Map<String,String> hashMap = new HashMap<>();
	
	public UserController(LogInView v){
		this.view = v;
		this.view=v;
		view.setWindowListener(new MyWindowListener() {

			@Override
			public void buttonClicked(MyWindowEvent ev) {
				user = ev.getUser();
				user.setId(ev.getUser().getId());
				user.setPassword(ev.getUser().getPassword());
				
				check();
			}
			
		});
	}

	public void check(){
		if( hashMap.containsKey(user.getId()) && hashMap.containsValue(user.getPassword())){
			view.showAlert("ID or Password already exists");
		}
		else if(view.fieldsEmpty()){
			view.showAlert("Please fill in empty fields");
		}
		else if (user.getPassword().length()<8){
			view.showAlert("Password cannot be less than 8 characters");
		}
		else{
			hashMap.put(user.getId().trim(), user.getPassword().trim());
			//write to a file
			System.out.println(hashMap);
		}
	}
	
}
