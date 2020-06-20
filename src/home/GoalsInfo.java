package home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GoalsInfo {
		
		public static HomeSceneController homeController;
		static String[] goals = new String[2];
		
		public static void buildGoals() {
			homeController.welcomeText.setText("Welcome "+Main.CURRENTUSER.getUserName()+",");
			homeController.saveGoalB.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					goals[0]=homeController.goalCBox.getValue();
					goals[1]=homeController.personalGoalsTA.getText();
					writeGoals();
				}
			});
			readGoalArray();
			homeController.goalCBox.setValue(goals[0]);
			homeController.personalGoalsTA.setText(goals[1]);
			
		}
		
		private static void readGoalArray(){
			 try {
					InputStream f = new FileInputStream(Main.CURRENTUSER.getInfoFile()+File.separator+"goals.txt");
					ObjectInputStream s = new ObjectInputStream(f);
					goals = (String[]) s.readObject();
					s.close();
				}catch (Exception e) {e.printStackTrace();}
		 }
		 
		 public static void writeGoals(){
			 try {
					FileOutputStream f = new FileOutputStream(Main.CURRENTUSER.getInfoFile()+File.separator+"goals.txt");
					ObjectOutputStream s = new ObjectOutputStream(f);
					s.writeObject(goals);
					s.close();
				} catch (Exception e) {e.printStackTrace();}
		 }
}
