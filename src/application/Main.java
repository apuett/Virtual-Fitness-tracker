package application;

import java.io.IOException;

import account.*;
import diet.*;
import home.GoalsInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	public static Stage stage;
	public static User CURRENTUSER;
	static Parent homebp,workoutbp,dietbp;
	static Scene homeScene,workoutScene,dietScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage=primaryStage;
		stage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Virtual Personal Trainer");
				
			
		homebp = FXMLLoader.load(getClass().getResource("/home/homeScene.fxml"));
		workoutbp = FXMLLoader.load(getClass().getResource("/workout/workoutScene.fxml"));
		dietbp = FXMLLoader.load(getClass().getResource("/diet/dietScene.fxml"));
		
		homeScene = new Scene(homebp,800,600);
		workoutScene = new Scene(workoutbp,800,600);
		dietScene = new Scene(dietbp,800,600);
			
		homeScene.getStylesheets().add(getClass().getResource("/home/homeStyle.css").toExternalForm());
		workoutScene.getStylesheets().add(getClass().getResource("/workout/workoutStyle.css").toExternalForm());
		dietScene.getStylesheets().add(getClass().getResource("/diet/dietStyle.css").toExternalForm());
		
		UserFiles.readUserDir();
		loginStage();	
	}
	
	public static void loginStage() {
		Scene scene = LoginScene.loginScene();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void createAccountStage() {
		Scene scene = CreateAccountScene.createAccountScene();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void loggedIn() {
		stage.setResizable(true);
		stage.setX(stage.getX()-175);
		stage.setY(stage.getY()-60);
		
		GoalsInfo.buildGoals();
		DietSceneFiles.buildArray();
	}

	public static void changeStage(int index) throws IOException {
		Scene scene = null;
		switch(index) {
			case 0:
				scene = homeScene;
				break;
			case 1:
				scene = workoutScene;
				break;
			case 2:
				scene = dietScene;
				break;
		}
		stage.setScene(scene);
		stage.show();
	}
	
	public static void currentUser(User user) {
		CURRENTUSER=user;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static String encrypt(String str) {
		String newStr = "";
		for (var i=str.length()-1;i>=0;i--) {
			newStr +="?"+ str.charAt(i);
		}
		return newStr;
	}
	
	public static String decrypt(String str) {
		String newStr="";
		for (var i=str.length()-1; i>=0;i--) {
			if(i%2!=0)newStr += str.charAt(i);
		}
		return newStr;
	}
}
