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

	private Label nameLabel;
	private Label lastNameLabel;
	private Label birthDayLabel;
	private Label phoneNumberLabel;
	private Label addressLabel;
	private Label zipLabel;
	private Label typeLabel;
	
	private TextField nameField;
	private TextField lastNameField;
	private TextField birthDayField;
	private TextField phoneNumberField;
	private TextField addressField;
	private TextField zipField;
	private TextField typeField;

	
	private Stage stage;
	private Scene scene;
	private MyMainWindowListener windowListener;
	private Alert messageAlert;
	
	
	
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
		
		RadioButton rb1 = new RadioButton("Customer");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("Establishment");
		rb2.setToggleGroup(group);
		
		
		
		HBox top = new HBox();
		top.setAlignment(Pos.BASELINE_CENTER);
		top.getChildren().addAll(info);
		
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(top);
		
		
		gridPane.add(root, 1, 0);
		gridPane.add(rb1, 1, 1);
		gridPane.add(rb2, 1, 2);


		
		return gridPane;
		
	}
	public void showProfileGridPane(){
		borderPane.setCenter(profileGridPane());
	}
	
}

