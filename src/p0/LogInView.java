package p0;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
	private MyLoginWindowListener windowListener;
	private Alert messageAlert;
	private Image image;
	private int sceneX = 1500;
	private int sceneY = 800;
		
	public LogInView(Stage stage){
		this.stage = stage;
		stage.setResizable(false);
		stage.setTitle("Owlz");
		
	}
	public void showLogin(){
		borderPane = new BorderPane();
		
		borderPane.setPadding(new Insets(100,170,300,100));
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(25));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		
		idLabel = new Label("ID");
		idField = new TextField();
		passwordLabel = new Label("Password");
		passwordField = new PasswordField();
		logIn = new Button("Log In");
		signUp = new Button("Sign Up");
		
		image = new Image(getClass().getResourceAsStream("/pic/club logo 2.png"), 250, 250, true, true);
		ImageView iv = new ImageView(image);
		iv.setSmooth(true);
		Label label = new Label("Owlz");
		label.setLayoutX(100);
		label.setFont(Font.font("Californian FB", FontPosture.ITALIC, 50));
		label.setTextFill(Color.AZURE);
		
		VBox imgbox = new VBox();
		imgbox.setLayoutX(sceneX / 2 - 110);
		imgbox.setLayoutY(sceneY / 2 - 350);
		imgbox.getChildren().addAll(iv);
		
		VBox labelbox = new VBox();
		labelbox.setLayoutX(sceneX/2 - 40);
		labelbox.setLayoutY(sceneY/2 - 100);
		labelbox.getChildren().addAll(label);
		
		idLabel.setTextFill(Color.WHITE);
		passwordLabel.setTextFill(Color.WHITE);

		noSpacesInTextFields();
		buttonActions();
		
		bottom = new HBox(10);
		bottom.setAlignment(Pos.CENTER);
		bottom.getChildren().addAll(logIn,signUp);
	
		gridPane.add(idLabel, 0, 0);
		gridPane.add(idField, 1, 0);
		gridPane.add(passwordLabel, 0, 1);
		gridPane.add(passwordField, 1, 1);
		gridPane.add(bottom, 1, 2);
		
	
		
		borderPane.setCenter(gridPane);
		
		Group root = new Group();
		
		scene = new Scene(root,sceneX,sceneY,Color.DARKMAGENTA);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().addAll(borderPane,imgbox,labelbox);
		
		stage.setScene(scene);
		stage.show();
		
	}
	public void buttonActions(){
		signUp.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this, new User(idField.getText(),passwordField.getText()));
			if(windowListener != null) {
				windowListener.signUpButtonClicked(ev);
				
			}
		});
		
		logIn.setOnAction(e->{
			
			MyWindowEvent v = new MyWindowEvent(this, new User(idField.getText(),passwordField.getText()));
			if(windowListener != null) {
				windowListener.loginButtonClicked(v);
				
			}
		});
	}

	public void setWindowListener(MyLoginWindowListener windowListener) {
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
	public void noSpacesInTextFields(){
		idField.textProperty().addListener(
			     (observable, old_value, new_value) -> {
			          if(new_value.contains(" "))
			          {
			                //allows no spaces in log in fields
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
	}
	public void clearFields(){
		idField.clear();
		passwordField.clear();
	}

}
