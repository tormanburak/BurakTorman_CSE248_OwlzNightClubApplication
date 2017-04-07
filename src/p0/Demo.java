package p0;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.stage.Stage;

public class Demo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		LogInView login = new LogInView(stage);
		MainMenuView main = new MainMenuView(stage);
		UserController controller = new UserController(login,main);
		


		

	}	
	
}
