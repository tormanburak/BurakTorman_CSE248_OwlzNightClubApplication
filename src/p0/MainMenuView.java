package p0;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class MainMenuView {
	

	
	private BorderPane borderPane;
	private GridPane gridPane;
	
	private MenuBar menuBar;
	private Menu profileMenu;
	private MenuItem historyMenuItem;
	private MenuItem favoritesMenuItem ;
	private MenuItem profileMenuItem ;

	 private static Label nameLabel = new Label("Name");
	 private static Label lastNameLabel  = new Label("Last Name");
	 private static Label birthDayLabel = new Label("Birthday");
	 private static Label phoneNumberLabel = new Label("Phone Number");
	 private static Label addressLabel = new Label("Address");
	 private static Label zipLabel = new Label("Zip");
	 private static Label typeLabel = new Label("Type");


	
	private static TextField nameField = new TextField();
	private static TextField lastNameField = new TextField();
	private static TextField birthDayField = new TextField();
	private static TextField phoneNumberField = new TextField();
	private static TextField addressField = new TextField();
	private static TextField zipField = new TextField();
	private static TextField typeField = new TextField();
	
	private static Button submitButton = new Button("Submit");
	private static Button cancelButton = new Button("Cancel");

	private static RadioButton customerRadioButton;
	private static RadioButton establishmentRadioButton;

	
	private Stage stage;
	private Scene scene;
	private MyMainWindowListener windowListener;
	private Alert messageAlert;
	
	private String[] info = new String[7];
	
	public MainMenuView(Stage s){
		this.stage=s;
		stage.setResizable(false);
		stage.setTitle("Owlz");
		
	}
	public void show(){
		borderPane = new BorderPane();
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		profileMenu = new Menu("Profile");
		historyMenuItem = new MenuItem("History");
		favoritesMenuItem = new MenuItem("Favorites");
		profileMenuItem = new MenuItem("Profile");
		profileMenu.getItems().addAll(profileMenuItem,historyMenuItem,favoritesMenuItem);
		menuBar.getMenus().add(profileMenu);
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		
		profileMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.profileMenuItemClicked(ev);			
			}
			
		});
				
		Group root = new Group();
		scene = new Scene(root,900,800,Color.DEEPPINK);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.show();
		
	}

	public void setWindowListener(MyMainWindowListener windowListener) {
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
		return false;
	}

	public GridPane profileGridPane(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		ColumnConstraints column1 = new ColumnConstraints(50);
		gridPane.getColumnConstraints().add(column1);
		
		Label info = new Label("Use your account as a Customer or an Establishment ?");
		info.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 20));
		
		ToggleGroup group = new ToggleGroup();
		customerRadioButton = new RadioButton("Customer");
		customerRadioButton.setToggleGroup(group);
		establishmentRadioButton = new RadioButton("Establishment");
		establishmentRadioButton.setToggleGroup(group);
		radioButtonsSetAction();
		
		HBox top = new HBox();
		top.setAlignment(Pos.BASELINE_CENTER);
		top.getChildren().addAll(info);
		
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(top);
		
		cancelButtonSetOnAction();
		
		gridPane.add(root, 1, 0);
		gridPane.add(customerRadioButton, 1, 1);
		gridPane.add(establishmentRadioButton, 1, 2);
		gridPane.add(cancelButton, 2, 3);

		return gridPane;
		
	}

	public GridPane customerProfile(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		
		VBox labelBox = new VBox(20);
		labelBox.setAlignment(Pos.CENTER);		
		labelBox.getChildren().addAll(nameLabel,lastNameLabel,birthDayLabel,phoneNumberLabel,addressLabel,zipLabel);
		
		VBox fieldBox = new VBox(10);
		fieldBox.setAlignment(Pos.CENTER);
		fieldBox.getChildren().addAll(nameField,lastNameField,birthDayField,phoneNumberField,addressField,zipField);
		
		cancelButtonSetOnAction();
		submitButtonSetOnAction();
		
		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		gridPane.add(cancelButton, 2, 1);
		gridPane.add(submitButton, 3, 1);

		return gridPane;
		
	}
	public GridPane establishmentProfile(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		Label nameLabel = new Label("Establishment Name");
		
		VBox labelBox = new VBox(20);
		labelBox.setAlignment(Pos.CENTER);		
		labelBox.getChildren().addAll(nameLabel,phoneNumberLabel,addressLabel,zipLabel,typeLabel);
		
		VBox fieldBox = new VBox(10);
		fieldBox.setAlignment(Pos.CENTER);
		fieldBox.getChildren().addAll(nameField,phoneNumberField,addressField,zipField,typeField);
		
		cancelButtonSetOnAction();
		submitButtonSetOnAction();
		
		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		gridPane.add(cancelButton, 2, 1);
		gridPane.add(submitButton, 3, 1);

		return gridPane;
		
	}
	public void showProfileGridPane(){
		borderPane.setCenter(profileGridPane());
	}
	public void showCustomerGridPane(){
		borderPane.setCenter(customerProfile());
	}
	public void showEstablishmentGridPane(){
		borderPane.setCenter(establishmentProfile());
	}
	public void radioButtonsSetAction(){
		customerRadioButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.cutomerRadioButtonClicked(ev);		
			}	
		});
		establishmentRadioButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.establishmentRadioButtonClicked(ev);		
			}	
		});	
	}
	public void cancelButtonSetOnAction(){
		cancelButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.cancelButtonClicked(ev);
			}	
		});
	}
	public void submitButtonSetOnAction(){
		submitButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.submitButtonClicked(ev);
			}	
		});
	}
	public String[] getInfo(){
		info[0] = nameField.getText();
		info[1] = lastNameField.getText();
		info[2] = birthDayField.getText();
		info[3] = phoneNumberField.getText();
		info[4] = addressField.getText();
		info[5] = zipField.getText();
		info[6] = typeField.getText();

		return info;
		
	}
}

