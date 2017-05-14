package p0;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.layout.Region;
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
	private Button updateButton = new Button("Update");
	private Button cancelButton = new Button("Cancel");
	private Button eventSetButton;
	private Button searchEventButton;
	private Button buyTicketsButton;
	private Button purchaseButton;
	private Button returnButton;
	private Button confirmButton;
	private Button infoButton;


	private RadioButton customerRadioButton;
	private RadioButton establishmentRadioButton;

	
	private Stage stage;
	private Scene scene;
	private MyMainWindowListener windowListener;
	private Alert messageAlert;
	private ListView<Event> eventListView;
	private ListView<Event> customerAllEventListView;
	private ListView<Event> establishmentHistoryListView;
	private ListView<Customer> myCustomersListView;
	private ListView<Event> establishmentMyEventListView;
	private ListView<Event> customerHistoryListView;

	private String[] userInfo = new String[7];
	private String[] eventInfo = new String[5];
	private String[] employeeInfo = new String[4];
	private String eventZIP;
	private TextField priceField;
	private ComboBox<Integer> ticketComboBox;
	
	private TextField ticketReturningAmountField;
	
	private TextField ticketSoldField;
	private TextField netProfitField;
	private TextField intialTicketssoldField;
	private TextField ticketsReturnedField;
	private MenuItem addEmployeesMenuItem;
	private Menu employeeMenu;
	private MenuItem allEmployeesMenuItem;
	private Button employeeInfoButton;
	private Button fireEmployeeButton;
	private TextField searchEmployeeField;
	private Region employeeListView;
	private Button searchEmployeeButton;
	private TextField employeeNameField;
	private TextField employeeLastNameField;
	private TextField employeePositionField;
	private TextField employeeSalaryField;
	private Button hireButton;


	
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
		scene = new Scene(root,1500,800,Color.DEEPPINK);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.show();
		
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
	public GridPane customerMyProfileView(){
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
		updateButtonSetOnAction();

		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		gridPane.add(cancelButton, 2, 1);
		gridPane.add(updateButton, 3, 1);

		return gridPane;
		
	}
	
	public GridPane establishmentMyProfileView(){
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
		updateButtonSetOnAction();
		
		gridPane.add(labelBox, 0, 0);
		gridPane.add(fieldBox, 1, 0);
		gridPane.add(cancelButton, 2, 1);
		gridPane.add(updateButton, 3, 1);

		return gridPane;
		
	}
	public GridPane customerTransactionHistoryView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label info = new Label("All recently booked events");	
		info.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 20));

		customerHistoryListView = new ListView<Event>();
		customerHistoryListView.setPrefWidth(600);
		returnButton = new Button("Return Ticket");
	
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,returnButton);
		
		gridPane.add(info, 0, 1);
		gridPane.add(customerHistoryListView, 0, 4,50,10);
		gridPane.add(buttonBox, 3, 16);
		
		returnButtonSetOnAction();
		cancelButtonSetOnAction();

		return gridPane;
		
	}
	public GridPane establishmentHistoryView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label info = new Label("All created events by me");	
		info.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 20));

		establishmentHistoryListView = new ListView<Event>();
		establishmentHistoryListView.setPrefWidth(600);
		infoButton = new Button("More Info");
	
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,infoButton);
		
		gridPane.add(info, 0, 1);
		gridPane.add(establishmentHistoryListView, 0, 4,50,10);
		gridPane.add(buttonBox, 3, 16);
		

		cancelButtonSetOnAction();
		infoButtonSetOnAction();
		
		return gridPane;
		
	}
	public GridPane establishmentInfoView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label info = new Label("My Customers for this event");
		Label totalTicketsSold = new Label("Total tickets sold");
		Label initialTicketsSold = new Label("Initial tickets sold");
		Label ticketsReturned = new Label("Tickets returned");
		Label netProfit = new Label("Net Profit plus 8.875% tax");
		
		ticketSoldField = new TextField();
		ticketSoldField.setEditable(false);
		netProfitField = new TextField();
		netProfitField.setEditable(false);
		intialTicketssoldField = new TextField();
		intialTicketssoldField.setEditable(false);
		ticketsReturnedField = new TextField();
		ticketsReturnedField.setEditable(false);
		
		info.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 20));

		myCustomersListView = new ListView<Customer>();
		myCustomersListView.setPrefWidth(600);
		
	
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton);
		VBox initalTicketSoldAndReturned = new VBox(10);
		initalTicketSoldAndReturned.getChildren().addAll(initialTicketsSold,ticketsReturned);
		VBox initialField = new VBox(10);
		initialField.getChildren().addAll(intialTicketssoldField,ticketsReturnedField );
		VBox ticketSoldBox = new VBox(10);
		ticketSoldBox.getChildren().addAll(totalTicketsSold,netProfit);
		VBox profitBox = new VBox(10);
		profitBox.getChildren().addAll(ticketSoldField,netProfitField);
		HBox addAll = new HBox(10);
		addAll.getChildren().addAll(initalTicketSoldAndReturned,initialField, ticketSoldBox,profitBox);
		
		gridPane.add(info, 0, 1);
		gridPane.add(addAll,0,16);

		
		gridPane.add(myCustomersListView, 0, 4,50,10);
		gridPane.add(buttonBox, 3, 16);
		

		cancelButtonSetOnAction();
		infoButtonSetOnAction();
		
		return gridPane;
		
	}
	
	
	public void showCustomerView(){
		borderPane = new BorderPane();
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		profileMenu = new Menu("Profile");
		eventMenu = new Menu ("Event");
		
		historyMenuItem = new MenuItem("History");
		profileMenuItem = new MenuItem("Profile");
		searchEvent = new MenuItem("Search Event");
		customerAllEventListView = new ListView<Event>();
		customerAllEventListView.setPrefWidth(1000);
		customerAllEventListView.setPrefHeight(600);
		
		profileMenu.getItems().addAll(profileMenuItem,historyMenuItem);
		eventMenu.getItems().add(searchEvent);
		menuBar.getMenus().addAll(profileMenu,eventMenu);
		
		Label infoLabel = new Label("All Recent Events");
		infoLabel.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 40));
		gridPane.add(customerAllEventListView, 0,1,50,10);
		gridPane.add(infoLabel, 0, 0);
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		
		historyMenuItemAction();
		eventSearchMenuItemAction();
		profileMenuItemAction();
				
		Group root = new Group();
		scene = new Scene(root,1500,800,Color.DEEPPINK);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.show();

		
	}
	public void showEstablishmentView(){
		borderPane = new BorderPane();
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(5);
		gridPane.setVgap(10);
		
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		profileMenu = new Menu("Profile");
		eventMenu = new Menu("Event");
		employeeMenu = new Menu("Employees");
		establishmentMyEventListView = new ListView<Event>();
		establishmentMyEventListView.setPrefWidth(1000);
		establishmentMyEventListView.setPrefHeight(600);
		
		historyMenuItem = new MenuItem("History");
		profileMenuItem = new MenuItem("Profile");
		createEventMenuItem = new MenuItem("Set Event");
		addEmployeesMenuItem = new MenuItem("Add Employee");
		allEmployeesMenuItem = new MenuItem("All Employess");
		
		profileMenu.getItems().addAll(profileMenuItem,historyMenuItem);
		eventMenu.getItems().add(createEventMenuItem);
		employeeMenu.getItems().addAll(addEmployeesMenuItem,allEmployeesMenuItem);
		menuBar.getMenus().addAll(profileMenu,eventMenu,employeeMenu);
		Label infoLabel = new Label("My Events");
		infoLabel.setFont(Font.font("AR CARTER", FontPosture.ITALIC, 40));

		gridPane.add(establishmentMyEventListView, 0,1);
		gridPane.add(infoLabel, 0, 0);
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		
		profileMenuItemAction();
		createEventMenuItemAction();
		historyMenuItemAction();
		allEmployeesItemAction();
		addEmployeesItemAction();
		
		Group root = new Group();
		scene = new Scene(root,1500,800,Color.DEEPPINK);
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
		gridPane.add(eventListView, 1, 4,50,10);
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
		 ticketComboBox = new ComboBox<Integer>();
		
		ticketComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,purchaseButton);
		
		gridPane.add(ticketLabel, 0, 1);
		gridPane.add(ticketComboBox, 1, 1);
		gridPane.add(priceLabel, 0, 2);
		gridPane.add(priceField, 1, 2);
		gridPane.add(buttonBox, 2, 3);
	
		purchaseButtonAction();
		cancelButtonSetOnAction();

		
		return gridPane;
		
	}
	public GridPane returnTicketView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label ticketLabel = new Label("Ticket Quantity Returning");
		priceField = new TextField();
		priceField.setEditable(false);
		 ticketReturningAmountField = new TextField();
		confirmButton = new Button("Confirm");
		
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,confirmButton);
		
		gridPane.add(ticketLabel, 0, 1);
		gridPane.add(ticketReturningAmountField, 1, 1);
		gridPane.add(buttonBox, 2, 3);
	
		cancelButtonSetOnAction();
		confirmButtonSetOnAction();
		
		return gridPane;
		
	}
	public GridPane myEmployeesView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label searchEmployee = new Label("Search by last name");
		searchEmployeeField = new TextField();
		 searchEmployeeButton = new Button("Search");	
		 employeeInfoButton = new Button("Info");
		 fireEmployeeButton = new Button("Fire Employee");
		 employeeListView = new ListView<Event>();
		 employeeListView.setPrefWidth(600);
		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,employeeInfoButton, fireEmployeeButton);
		
		gridPane.add(searchEmployee, 0, 1);
		gridPane.add(searchEmployeeField, 1, 1);
		gridPane.add(searchEmployeeButton, 2, 1);
		gridPane.add(employeeListView, 1, 4,50,10);
		gridPane.add(buttonBox, 2, 16);
	
		cancelButtonSetOnAction();

		
		return gridPane;
		
	}
	public GridPane addEmployeeView(){
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		
		Label employeeNameLabel = new Label("Name");
		Label employeeLastNameLabel = new Label("Last Name");
		Label employeePositionLabel = new Label("Position");
		Label employeeSalaryLabel = new Label("Hourly Salary");
		


		 employeeNameField = new TextField();
		 employeeLastNameField = new TextField();
		 employeePositionField = new TextField();
		 employeeSalaryField = new TextField();

		
		hireButton = new Button("Hire");

		
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(cancelButton,hireButton);
		
		gridPane.add(employeeNameLabel, 0, 1);
		gridPane.add(employeeLastNameLabel, 0, 2);
		gridPane.add(employeePositionLabel, 0, 3);
		gridPane.add(employeeSalaryLabel, 0, 4);
		

		
		gridPane.add(employeeNameField, 1, 1);
		gridPane.add(employeeLastNameField, 1, 2);
		gridPane.add(employeePositionField, 1, 3);
		gridPane.add(employeeSalaryField, 1, 4);
		
		gridPane.add(buttonBox,2,6);
		
		hireButtonAction();
		cancelButtonSetOnAction();

		return gridPane;
		
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------
	//below this line includes all methods for buttons, menu items, etc.
	public int getTicketReturningAmount(){
		int ticketQuantity = Integer.valueOf(ticketReturningAmountField.getText());
		return ticketQuantity;
	}
	public int getTicketAmountPurchased(){
		int ticketQuantity = ticketComboBox.getValue();
		
		return ticketQuantity;
		
	}
	public int getTicketPricePurchased(){
		int ticketPrice = Integer.valueOf(priceField.getText());
		return ticketPrice;
	}
	public int calculateTicketPrice(){
		int total = getTicketAmountPurchased() * getTicketPricePurchased();
		
		return total;
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
	public Event getHistoryListViewItems(){
		Event event = new Event();
		return event = customerHistoryListView.getSelectionModel().getSelectedItem();
	}
	public Event getEstablishmentHistoryViewItems(){
		Event event = new Event();
		return event = establishmentHistoryListView.getSelectionModel().getSelectedItem();
	}
	public void showFoundEvents(ObservableList<Event> event){
		eventListView.setItems(event);
	}
	public void showAllEvents(ObservableList<Event> event){
		customerAllEventListView.setItems(event);
	}
	public void showEstablishmentEvents(ObservableList<Event> event){
		establishmentMyEventListView.setItems(event);
	}
	public void showCustomerHistory(ObservableList<Event> event){
		customerHistoryListView.setItems(event);
	}
	public void showEstablishmentHistory(ObservableList<Event> event){
		establishmentHistoryListView.setItems(event);
	}
	public void showMyCustomerList(ObservableList<Customer> event){
		myCustomersListView.setItems(event);
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
	public void historyMenuItemAction(){
		historyMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.historyMenuItemClicked(ev);			
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
	public void purchaseButtonAction(){
		purchaseButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.purchaseButtonClicked(ev);
			}
		});
	}
	public void hireButtonAction(){
		hireButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.hireButtonClicked(ev);
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
	public void allEmployeesItemAction(){
		allEmployeesMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.allEmployeesItemClicked(ev);
			}
		});
	}
	public void addEmployeesItemAction() {
		addEmployeesMenuItem.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null ){
				windowListener.addEmployeesItemClicked(ev);
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
	public void showProfileGridPane(){
		borderPane.setCenter(createProfileGridPane());
	}
	public void showCustomerGridPane(){
		borderPane.setCenter(customerCreateProfileView());
	}
	public void showEstablishmentGridPane(){
		borderPane.setCenter(establishmentCreateProfileView());
	}
	public void showCustomerMyProfile(){
		borderPane.setCenter(customerMyProfileView());
	}
	public void showEstablishmentMyProfile(){
		borderPane.setCenter(establishmentMyProfileView());
	}
	public void showCustomerTransactionHistory(){
		borderPane.setCenter(customerTransactionHistoryView());
	}
	public void showCustomerReturnView(){
		borderPane.setCenter(returnTicketView());
	}
	public void showEstablishmentHistoryView(){
		borderPane.setCenter(establishmentHistoryView());
	}
	public void showEstablishmentInfoView(){
		borderPane.setCenter(establishmentInfoView());
	}
	public void showAllEmployeesView(){
		borderPane.setCenter(myEmployeesView());
	}
	public void showAddEmployeesView(){
		borderPane.setCenter(addEmployeeView());
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
	public void updateButtonSetOnAction(){
		updateButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.updateButtonClicked(ev);
			}	
		});
	}
	public void returnButtonSetOnAction(){
		returnButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.returnButtonClicked(ev);
			}	
		});
	}
	public void confirmButtonSetOnAction(){
		confirmButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.confirmButtonClicked(ev);
			}	
		});
	}
	public void infoButtonSetOnAction(){
		infoButton.setOnAction(e->{
			MyWindowEvent ev = new MyWindowEvent(this);
			if(windowListener != null) {
				windowListener.infoButtonClicked(ev);
			}	
		});
	}
	public void setCustomerMyProfileFields(String name,String lastName, String birthDay, String phoneNumber,String address,String zip){
		nameField.setText(name);
		lastNameField.setText(lastName);
		birthDayField.setText(birthDay);
		phoneNumberField.setText(phoneNumber);
		addressField.setText(address);
		zipField.setText(zip);
	}
	public void setEstablishmentMyProfileFields(String name,String phoneNumber,String address, String zip, String type){
		nameField.setText(name);
		phoneNumberField.setText(phoneNumber);
		addressField.setText(address);
		zipField.setText(zip);
		typeField.setText(type);
	}
	public void setFinancialInfo(int initalTicketBought, int ticketsReturned, int totalTicketsSold, double netProfit){
		String ticketBought = String.valueOf(initalTicketBought);
		String ticketReturned = String.valueOf(ticketsReturned);
		String totalSold = String.valueOf(totalTicketsSold);
		String profit = String.valueOf(netProfit);
		if(ticketBought == null){
			intialTicketssoldField.setText("");
		}
		if(ticketReturned == null){
			ticketsReturnedField.setText("");

		}
		if(totalSold == null){
			ticketSoldField.setText("");

		}
		if(profit == null){
			netProfitField.setText("");

		}
		else{
		ticketSoldField.setText(totalSold);
		netProfitField.setText("$"+profit);
		intialTicketssoldField.setText(ticketBought);
		ticketsReturnedField.setText(ticketReturned);
		}
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
	public String[] getEmployeeInfo(){
		employeeInfo[0]= employeeNameField.getText();
		employeeInfo[1]= employeeLastNameField.getText();
		employeeInfo[2]= employeePositionField.getText();
		employeeInfo[3]= employeeSalaryField.getText();
		return employeeInfo;
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
	/**
	 * 
	 */

}

