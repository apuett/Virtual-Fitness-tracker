package account;


import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable {
	
	private static final long serialVersionUID = -6937934813534278326L;
	private String strUserName,strPassword;
	private String InfoFile,WorkoutFile,DietFile;
	private Calendar dateCreated;
	
	public User(String strUserName,String strPassword,Calendar dateCreated) {
		this.strUserName=strUserName;
		this.strPassword=strPassword;
		this.dateCreated=dateCreated;
	}
	
	public String getUserName() {
		return strUserName;
	}
	
	public void setPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public String getPassword() {
		return strPassword;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setInfoFile(String infoFile2) {
		this.InfoFile=infoFile2;
	}
	public String getInfoFile() {
		return InfoFile;
	}
	
	public void setWorkoutFile(String file) {
		this.WorkoutFile=file;
	}
	public String getWorkoutFile() {
		return WorkoutFile;
	}
	
	public void setDietFile(String file) {
		this.DietFile = file;
	}
	public String getDietFile() {
		return DietFile;
	}
}
