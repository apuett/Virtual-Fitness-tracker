package account;

import java.io.IOException;

import application.Main;
import home.GoalsInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreateAccountScene {
	
	public static Scene createAccountScene() {
		Text text1 = new Text("	Username");
		Text text2 = new Text("	Password");
		Text text3 = new Text("Confirm Password");
		TextField textField1 = new TextField();
		PasswordField textField2 = new PasswordField();
		PasswordField textField3 = new PasswordField();
		Button button1 = new Button("Back");
		Button button2 = new Button("Create Account");
		final Label message = new Label("");
		message.setStyle("-fx-text-fill:#800D29");
		
		textField1.setStyle("-fx-faint-focus-color: transparent;-fx-focus-color:#800D29");
		textField2.setStyle("-fx-faint-focus-color: transparent;-fx-focus-color:#800D29");
		textField3.setStyle("-fx-faint-focus-color: transparent;-fx-focus-color:#800D29");
		
		String strUserCred = "Username must be at least 4 characters,\r\n"
							+ "Username must be less than 16 characters,\r\n"
							+ "Username can only conatin alphabet, numbers,\r\n"
							+ "these characters: ! () - . ? [] _ ~ ; : @ # $ % ^ & * + =";
		Tooltip userCred = new Tooltip(strUserCred);
		userCred.setAutoHide(false);
		textField1.setTooltip(userCred);
		
		String strPassCred = "Password must be at least 6 characters,\r\n"
							+ "Password must be less than 16 characters,\r\n"
							+ "Password can only conatin alphabet, numbers,\r\n"
							+ "these characters: ! () - . ? [] _ ~ ; : @ # $ % ^ & * + =";
		Tooltip passCred = new Tooltip(strPassCred);
		userCred.setAutoHide(false);
		textField2.setTooltip(passCred);
		
		button1.setStyle("-fx-background-color:#800D29;"
						+ "-fx-text-fill:#FFFFFF;"
						+ "-fx-border-width:1;"
						+ "-fx-border-color:#000000;");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Main.loginStage();
			}
		});
		
		
		button2.setStyle("-fx-background-color:#800D29;"
				+ "-fx-text-fill:#FFFFFF;"
				+ "-fx-border-width:1;"
				+ "-fx-border-color:#000000;");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				String Username = textField1.getText();
				String Password = textField2.getText();
				String ConfPassword = textField3.getText();
				if(!UsersInfo.UserExist(Username) && Username.length()>=4 && Username.length()<16 && UsersInfo.ValidChar(Username)) {
					if(Password.equals(ConfPassword) && Password.length()>=6 && Password.length()<16 && UsersInfo.ValidChar(Password)) {
						
						UsersInfo.addUser(Username, Password);
						Main.CURRENTUSER = UsersInfo.getUserByUsername(Username);
						UserFiles.createUserFolder(Main.CURRENTUSER);
						GoalsInfo.writeGoals();
						UserFiles.writeUserDir();
						try {Main.changeStage(0);} catch (IOException e) {e.printStackTrace();}
						Main.loggedIn();
					}
					else if(!Password.equals(ConfPassword))message.setText("Password doesn't match!");
					else message.setText("Invalid Password!");
				}
				else if (UsersInfo.UserExist(Username)) message.setText("Username already exist!");
				else message.setText("Invalid Username!");
				textField1.clear();
				textField2.clear();
				textField3.clear();
			}
		});
		
		
		
		GridPane gridPane = new GridPane();
		
		gridPane.setMinSize(400, 250);
		
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(text1, 0, 0);
		gridPane.add(textField1, 1, 0);
		gridPane.add(text2, 0, 1);
		gridPane.add(textField2, 1, 1);
		gridPane.add(text3, 0, 2);
		gridPane.add(textField3, 1, 2);
		gridPane.add(message, 1, 3);
		
		gridPane.setHgap(10);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(10,10,10,10));
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(button1,button2);
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setAlignment(Pos.CENTER);
		hbox.setMinSize(400, 40);
		hbox.setSpacing(30);
		
		HBox titleBar = new HBox();
		TitleBar.makeTitleBar(titleBar);
		
		StackPane titleBarPane = new StackPane();
		titleBarPane.setAlignment(Pos.CENTER_LEFT);
		titleBarPane.setPadding(new Insets(0,0,0,10));
		titleBarPane.setStyle("-fx-background-color:#000000;");
		Label nametitle = new Label();
		nametitle.setText("FitnessTracker");
		nametitle.setStyle("-fx-text-fill:#FFFFFF;");
		titleBarPane.getChildren().addAll(titleBar,nametitle);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(titleBarPane,gridPane,hbox);
		
		
		vbox.setStyle("-fx-background-color:#CCCCCC;"
				+ "-fx-font-family:'fantasy';"
				+ "-fx-font-size:12pt;");
		
		vbox.setMinSize(400, 400);
		
		Scene scene = new Scene(vbox);
		
		
		return scene;
	}
}
