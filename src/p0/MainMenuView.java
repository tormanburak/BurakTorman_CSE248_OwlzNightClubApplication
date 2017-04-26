package p0;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	private Menu eventMenu;
	private MenuItem historyMenuItem;
	private MenuItem favoritesMenuItem ;
	private MenuItem profileMenuItem ;
	private MenuItem createEventMenuItem ;
	private MenuItem searchEvent;


	 private Label nameLabel = new Label("Name");
	 private Label lastNameLabel  = new Label("Last Name");
	 private Label birthDayLabel = new Label("Birthday");
	 private Label phoneNumberLabel = new Label("Phone Number");
	 private Label addressLabel = new Label("Address");
	 private Label zipLabel = new Label("Zip");
	 private Label typeLabel = new Label("Type");


	
	private TextField nameField = new TextField();
	private TextField lastNameField = new TextField();
	private TextField birthDayField = new TextField();
	private TextField phoneNumberField = new TextField();
	private TextField addressField = new TextField();
	private TextField zipField = new TextField();
	private TextField typeField = new TextField();
	private TextField eventNameField;
	private TextField eventAddressField;
	private TextField eventTypeField;
	private TextField eventStartTimeField;
	private TextField eventZIPField;
	private TextField searchEventField;
	private TextField ticketAmountField;
	private TextField ticketPriceField;

	
	private Button submitButton = new Button("Submit");
	private Button cancelButton = new Button("Cancel");
	private Button eventSetButton;
	private Button searchEventButton;
	private Button buyTicketsButton;
	private Button purchaseButton;

	private RadioButton customerRadioButton;
	private RadioButton establishmentRadioButton;

	
	private Stage stage;
	private Scene scene;
	private MyMainWindowListener windowListener;
	private Alert messageAlert;
	private ListView<Event> eventListView;
	
	private String[] userInfo = new String[7];
	private String[] eventInfo = new String[5];
	private String eventZIP;
	private TextField priceField;


	
	public MainMenuView(Stage s){
		this.stage=s;
		stage.setResizable(false);
		stage.setTitle("Owlz");
		
	}
	public void showIntroView(){
		borderPane = new BorderPane();
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		borderPane.setCenter(createProfileGridPane());
				
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

	public GridPane createProfileGridPane(){
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

	public GridPane customerCreateProfileView(){
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
	public GridPane establishmentCreateProfileView(){
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
		borderPane.setCenter(createProfileGridPane());
	}
	public void showCustomerGridPane(){
		borderPane.setCenter(customerCreateProfileView());
	}
	public void showEstablishmentGridPane(){
		borderPane.setCenter(establishmentCreateProfileView());
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
	public String[] getUserInfo(){
		userInfo[0] = nameField.getText();
		userInfo[1] = lastNameField.getText();
		userInfo[2] = birthDayField.getText();
		userInfo[3] = phoneNumberField.getText();
		userInfo[4] = addressField.getText();
		userInfo[5] = zipField.getText();
		userInfo[6] = typeField.getText();

		return userInfo;
		
	}
	public String[] getEventInfo(){
		eventInfo[0] = eventNameField.getText();
		eventInfo[1] = eventAddressField.getText();
		eventInfo[2] = eventZIPField.getText();
		eventInfo[3] = eventTypeField.getText();
		eventInfo[4] = eventStartTimeField.getText();
		return eventInfo;
		
	}
	public int getTicketAmount(){
		int ticketAmount = Integer.parseInt(ticketAmountField.getText());
		return ticketAmount;
	}
	public String getTicketPrice(){
		return ticketPriceField.getText();
	}
	public String getEventZip(){
		return eventZIP = searchEventField.getText();
	}
	public void showCustomerView(){
		borderPane = new BorderPane();
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		profileMenu = new Menu("Profile");
		eventMenu = new Menu ("Event");
		
		historyMenuItem = new MenuItem("History");
		favoritesMenuItem = new MenuItem("Favorites");
		profileMenuItem = new MenuItem("Profile");
		searchEvent = new MenuItem("Search Event");
		
		profileMenu.getItems().addAll(profileMenuItem,historyMenuItem,favoritesMenuItem);
		eventMenu.getItems().add(searchEvent);
		menuBar.getMenus().addAll(profileMenu,eventMenu);
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		
		eventSearchMenuItemAction();
		profileMenuItemAction();
				
		Group root = new Group();
		scene = new Scene(root,900,800,Color.DEEPPINK);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.show();

		
	}
	public void showEstablishmentView(){
		borderPane = new BorderPane();
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.BOTTOM_CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		profileMenu = new Menu("Profile");
		eventMenu = new Menu("Event");
		
		historyMenuItem = new MenuItem("History");
		profileMenuItem = new MenuItem("Profile");
		createEventMenuItem = new MenuItem("Set Event");
		
		profileMenu.getItems().addAll(profileMenuItem,historyMenuItem);
		eventMenu.getItems().add(createEventMenuItem);
		menuBar.getMenus().addAll(profileMenu,eventMenu);
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		
		profileMenuItemAction();
		createEventMenuItemAction();
				
		Group root = new Group();
		scene = new Scene(root,900,800,Color.DEEPPINK);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.show();
	}
	public GridPane eventCreateView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label eventNameLabel = new Label("Event Name");
		Label eventAddressLabel = new Label("Event Address");
		Label eventTypeLabel = new Label("Event Type");
		Label eventStartTimeLabel = new Label("Event Time");
		Label eventZip = new Label("Event ZIP");
		Label ticketAmountLabel = new Label("Ticket quantity");
		Label ticketPriceLabel = new Label("Ticket price");


		 eventNameField = new TextField();
		 eventAddressField = new TextField();
		 eventTypeField = new TextField();
		 eventStartTimeField = new TextField();
		 eventZIPField = new TextField();
		 ticketAmountField = new TextField();
		 ticketPriceField = new TextField();
		
		eventSetButton = new Button("Set Event");
		eventSetButtonAction();
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,eventSetButton);
		
		gridPane.add(eventNameLabel, 0, 1);
		gridPane.add(eventAddressLabel, 0, 2);
		gridPane.add(eventZip, 0, 3);
		gridPane.add(eventTypeLabel, 0, 4);
		gridPane.add(eventStartTimeLabel, 0, 5);
		gridPane.add(ticketAmountLabel, 0, 6);
		gridPane.add(ticketPriceLabel, 0, 7);

		
		gridPane.add(eventNameField, 1, 1);
		gridPane.add(eventAddressField, 1, 2);
		gridPane.add(eventZIPField, 1, 3);
		gridPane.add(eventTypeField, 1, 4);
		gridPane.add(eventStartTimeField, 1, 5);
		gridPane.add(ticketAmountField, 1, 6);
		gridPane.add(ticketPriceField, 1, 7);
		gridPane.add(buttonBox,2,8);
		
		
		cancelButtonSetOnAction();

		return gridPane;
		
	}
	public GridPane searchEventView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label eventZIP = new Label("Search Event by ZIP");		
		searchEventField = new TextField();
		eventListView = new ListView<Event>();
		eventListView.setPrefWidth(600);
		searchEventButton = new Button("Search Event");
		buyTicketsButton = new Button("Buy Tickets!");
		
	
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,buyTicketsButton);
		
		gridPane.add(eventZIP, 0, 1);
		gridPane.add(searchEventField, 1, 1);
		gridPane.add(eventListView, 1, 4,10,10);
		gridPane.add(searchEventButton, 2, 1);
		gridPane.add(buttonBox, 3, 16);
		



		buyTicketButtonAction();
		searchEventButtonAction();
		cancelButtonSetOnAction();

		return gridPane;
		
	}
	public GridPane purchaseTicketView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label ticketLabel = new Label("Ticket Quantity");
		Label priceLabel = new Label("Price");
		priceField = new TextField();
		priceField.setEditable(false);
		purchaseButton = new Button("Purchase");
		ComboBox<Integer> ticketComboBox = new ComboBox<Integer>();
		
		ticketComboBox.getItems().addAll(1,2);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,purchaseButton);
		
		gridPane.add(ticketLabel, 0, 1);
		gridPane.add(ticketComboBox, 1, 1);
		gridPane.add(priceLabel, 0, 2);
		gridPane.add(priceField, 1, 2);
		gridPane.add(buttonBox, 2, 3);
	
		
		cancelButtonSetOnAction();

		
		return gridPane;
		
	}
	public void showPurchaseView(){
		borderPane.setCenter(purchaseTicketView());
	}
	public void setTicketPrice(String price){
		priceField.setText(price);
	}
	
	public Event getListViewItems(){
		Event event = new Event();
		return event = eventListView.getSelectionModel().getSelectedItem();
	}
	public void showFoundEvents(ObservableList<Event> event){
		eventListView.setItems(event);
	}
	public void showEventCreateView(){
		borderPane.setCenter(eventCreateView());
	}
	public void showEventSearchView(){
		borderPane.setCenter(searchEventView());
	}
	public void buyTicketButtonAction(){
		buyTicketsButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
		if(windowListener != null) {
			windowListener.buyTicketsButtonClicked(ev);			
		}
			
		});
	}
	public void profileMenuItemAction(){
		profileMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.profileMenuItemClicked(ev);			
			}
			
		});
	}
	public void eventSearchMenuItemAction(){
		searchEvent.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.eventSearchMenuItemClicked(ev);		
			}
			
		});
	}
	public void eventSetButtonAction(){
		eventSetButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.eventSetButtonClicked(ev);
			}
		});
	}
	public void createEventMenuItemAction(){
		createEventMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.eventCreateMenuItemClicked(ev);
			}
		});
	}
	public void searchEventButtonAction(){
		searchEventButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.searchEventButton(ev);
			}
		});
	}
}

