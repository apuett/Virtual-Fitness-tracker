package account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.Main;

public class UserFiles implements Serializable{
	
	
	private static final long serialVersionUID = 3834460860436277498L;


	public static void createUserFolder(User user) {
		String UserName = user.getUserName();
		
		File dir = new File("Users"+ File.separator +UserName);
		dir.mkdir();
		
		try {
			createInfoFile(user);
			createWorkoutFile(user);
			createDietFile(user);
		}catch(IOException e) {e.printStackTrace();}
		
	}
	
	public static void createInfoFile(User user) throws IOException {
		File file = new File("Users"+File.separator+user.getUserName() +File.separator+"InfoFile");
		file.mkdir();
		String InfoFile = "Users"+File.separator+user.getUserName() +File.separator+"InfoFile";
			user.setInfoFile(InfoFile);
	}
	
	public static void createWorkoutFile(User user) throws IOException {
		File file = new File("Users"+File.separator+user.getUserName()+File.separator+"WorkoutFile");
		file.mkdir();
		String WorkoutFile="Users"+File.separator+user.getUserName()+File.separator+"WorkoutFile";
			user.setWorkoutFile(WorkoutFile);
	}
	
	public static void createDietFile(User user) throws IOException {
		File file = new File("Users"+File.separator+user.getUserName() +File.separator+"DietFile");
		file.mkdir();
		String DietFile = "Users"+File.separator+user.getUserName() +File.separator+"DietFile";
			user.setDietFile(DietFile);
	}
	
	
	public static void writeUserDir() {
		try {
				FileOutputStream f = new FileOutputStream("Users"+File.separator+"UserDir.txt");
				ObjectOutputStream s = new ObjectOutputStream(f);
				s.writeObject(UsersInfo.Users);
				s.close();
			} catch (Exception e) {e.printStackTrace();}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void readUserDir() {
		try {
			InputStream f = new FileInputStream("Users"+File.separator+"UserDir.txt");
			ObjectInputStream s = new ObjectInputStream(f);
			UsersInfo.Users = (ArrayList<User>) s.readObject();
			s.close();
		}catch (Exception e) {e.printStackTrace();}
	}
	
	public static void removeUser() {
		UsersInfo.Users.remove(Main.CURRENTUSER);
		File dir = new File("Users"+ File.separator +Main.CURRENTUSER.getUserName());
		Main.CURRENTUSER=null;
		File[] listFiles = dir.listFiles();
		for(File file : listFiles) {
			File[] listFilesFiles = file.listFiles();
			for(File filefile : listFilesFiles) {
				filefile.delete();
			}
			file.delete();
		}
		dir.delete();
		writeUserDir();
		System.out.println("Account Deleted!");
		Main.stage.setX(Main.stage.getX()+175);
		Main.stage.setY(Main.stage.getY()+60);
		Main.loginStage();
	}
	
}
