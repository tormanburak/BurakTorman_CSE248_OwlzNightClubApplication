package p0;

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
		UserController uc = new UserController(login,main);
	}

}
