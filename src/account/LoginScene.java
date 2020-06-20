package account;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginScene {
	
	
	public static Scene loginScene() {
		Text text1 = new Text("Username");
		Text text2 = new Text("Password");
		TextField textField1 = new TextField();
		PasswordField textField2 = new PasswordField();
		Button button1 = new Button("Login");
		Button button2 = new Button("Create Account");
		final Label message = new Label("");
		message.setStyle("-fx-text-fill:#800D29");
		
		textField1.setStyle("-fx-faint-focus-color: transparent;-fx-focus-color:#800D29");
		textField2.setStyle("-fx-faint-focus-color: transparent;-fx-focus-color:#800D29");
		
		button1.setStyle("-fx-background-color:#800D29;"
						+ "-fx-text-fill:#FFFFFF;"
						+ "-fx-border-width:1;"
						+ "-fx-border-color:#000000;");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				String Username = textField1.getText();
				String Password = textField2.getText();
				if(UsersInfo.UserExist(Username)) {
					User currentUser = UsersInfo.getUserByUsername(Username); 
					if(Main.decrypt(currentUser.getPassword()).equals(Password)) {
			
						Main.CURRENTUSER=currentUser;
						try {Main.changeStage(0);} catch (IOException e) {e.printStackTrace();}
						Main.loggedIn();
					}else {
						message.setText("Your password is incorrect!");
					}
				}else{
					message.setText("Username doesn't exist!");
				}
				textField1.clear();
				textField2.clear();
			}
		});
		
		
		button2.setStyle("-fx-background-color:#800D29;"
				+ "-fx-text-fill:#FFFFFF;"
				+ "-fx-border-width:1;"
				+ "-fx-border-color:#000000;");
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				Main.createAccountStage();
			}
		});
		
		
		
		GridPane gridPane = new GridPane();
		
		gridPane.setMinSize(400, 250);
		
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(text1, 0, 0);
		gridPane.add(textField1, 1, 0);
		gridPane.add(text2, 0, 1);
		gridPane.add(textField2, 1, 1);
		gridPane.add(message,1,2);
		
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
