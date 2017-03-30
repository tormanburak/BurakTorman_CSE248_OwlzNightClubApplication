package p0;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LogInView {
	
	private Label idLabel;
	private Label passwordLabel;
	private TextField idField;
	private PasswordField passwordField;
	private Button logIn;
	private Button signUp;
	
	private BorderPane borderPane;
	private GridPane gridPane;
	
	private HBox bottom;
	
	private Stage stage;
	private Scene scene;
	private MyWindowListener windowListener;
	private Alert messageAlert;
	
	//private String[] info = new String[2];
	
	public LogInView(Stage stage){
		this.stage = stage;
		stage.setResizable(false);
		stage.setTitle("Owlz");
		
	}
	public void show(){
		borderPane = new BorderPane();
		
		borderPane.setPadding(new Insets(100,100,300,100));
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		
		idLabel = new Label("ID");
		idField = new TextField();
		passwordLabel = new Label("Password");
		passwordField = new PasswordField();
		logIn = new Button("Log In");
		signUp = new Button("Sign Up");
		
		idLabel.setTextFill(Color.WHITE);
		passwordLabel.setTextFill(Color.WHITE);

		idField.textProperty().addListener(
			     (observable, old_value, new_value) -> {
			          if(new_value.contains(" "))
			          {
			                //prevents from the new space char
			                idField.setText(old_value); 
			          }
			     }
			);
		
		passwordField.textProperty().addListener(
			     (observable, old_value, new_value) -> {
			          if(new_value.contains(" "))
			          {
			                //prevents from the new space char
			                passwordField.setText(old_value); 
			          }
			     }
			);
		
		signUp.setOnAction(e->{
//			info = getUserDetails();
			
			MyWindowEvent ev = new MyWindowEvent(this, new User(idField.getText(),passwordField.getText()));
			if(windowListener != null) {
				windowListener.buttonClicked(ev);
				
			}
		});
	
		gridPane.add(idLabel, 0, 0);
		gridPane.add(idField, 1, 0);
		gridPane.add(passwordLabel, 0, 1);
		gridPane.add(passwordField, 1, 1);
		
		bottom = new HBox(10);
		bottom.setAlignment(Pos.CENTER);
		bottom.getChildren().addAll(logIn,signUp);
		
		borderPane.setCenter(gridPane);
		borderPane.setBottom(bottom);
		
		Group root = new Group();
		
		scene = new Scene(root,600,600,Color.DARKMAGENTA);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		
		stage.setScene(scene);
		stage.show();
		
	}
//	public String[] getUserDetails(){
//		String id = idField.getText();
//		String password = passwordField.getText();
//		
//		info[0]=id;
//		info[1]=password;
//		
//		return info;
//	}
	public void setWindowListener(MyWindowListener windowListener) {
		this.windowListener = windowListener;
	}
	public void showAlert(String message){
		messageAlert = new Alert(AlertType.INFORMATION);
		messageAlert.setTitle("Information Dialog");
		messageAlert.setHeaderText(null);
		messageAlert.setContentText(message);

		messageAlert.showAndWait();
	}
	public boolean fieldsEmpty(){
		if(idField.getText().isEmpty() || passwordField.getText().isEmpty()){
		return true;
		
	}
		return false;
	
	}
	
}
