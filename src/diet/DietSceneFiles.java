package diet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class DietSceneFiles implements Serializable{
	
	private static final long serialVersionUID = 9072522105408650152L;
	
	public static ArrayList<ArrayList<FoodItem>> FoodItems = new ArrayList<ArrayList<FoodItem>>();
	 public static DietSceneController dietSceneController;
	 public static int dayIndex;
	 
	 public static void buildArray() {
		 FoodItems.clear();
		 File dietFile = new File(Main.CURRENTUSER.getDietFile());
		 for(int i=0;i<dietFile.listFiles().length;i++) {
			 FoodItems.add(readFoodArray(Integer.toString(i)));
		 }
		 
		 Calendar createDate=Main.CURRENTUSER.getDateCreated();
		 createDate.set(Calendar.MILLISECOND, 0);
	     createDate.set(Calendar.SECOND, 0);
	     createDate.set(Calendar.MINUTE, 0);
	     createDate.set(Calendar.HOUR_OF_DAY, 0);
		 int days = (int) ChronoUnit.DAYS.between(createDate.toInstant(),Calendar.getInstance().toInstant());

		 if(FoodItems.size()-1<days)createFoodArray(days-FoodItems.size());
		 
		 dayIndex = FoodItems.size()-1;
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 dietSceneController.dateLabel.setText(sdf.format(Calendar.getInstance().getTime()));
		 addArchiveButtons();
		 updateTableView();
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	private static ArrayList<FoodItem> readFoodArray(String index){
		 ArrayList<FoodItem> tempArray = new ArrayList<FoodItem>();
		 try {
				InputStream f = new FileInputStream(Main.CURRENTUSER.getDietFile()+File.separator+index+".txt");
				ObjectInputStream s = new ObjectInputStream(f);
				tempArray = (ArrayList<FoodItem>) s.readObject();
				s.close();
			}catch (Exception e) {e.printStackTrace();}
		 return tempArray;
	 }
	 
	 public static void writeFile(ArrayList<FoodItem> foodItems,String index){
		 try {
				FileOutputStream f = new FileOutputStream(Main.CURRENTUSER.getDietFile()+File.separator+index+".txt");
				ObjectOutputStream s = new ObjectOutputStream(f);
				s.writeObject(foodItems);
				s.close();
			} catch (Exception e) {e.printStackTrace();}
	 }
	 
	 private static void createFoodArray(int days) {
		 	for(int i=0;i<=days;i++) {
		 		ArrayList<FoodItem> tempArray = new ArrayList<FoodItem>();
		 		FoodItems.add(tempArray);
		 		writeFile(tempArray,Integer.toString(FoodItems.size()-1));
		 	}
	 }
	 
	 private static void updateTableView() {
		 dietSceneController.DietTable.getItems().clear();
		 dietSceneController.calTotalL.setText("0");
		 dietSceneController.carbsTotalL.setText("0");
		 dietSceneController.protiensTotalL.setText("0");
		 dietSceneController.fatsTotalL.setText("0");
		 dietSceneController.setSum(0,0,0,0);
		 ArrayList<FoodItem> tempArray = FoodItems.get(dayIndex);
		 for(FoodItem item:tempArray) {
			 dietSceneController.DietTable.getItems().add(item);
			 dietSceneController.setSum(item.getCarbs(), item.getProtien(), item.getFats(), item.getCal());
		 }
	 }
	 
	 public static void createAndAddFoodItem(String strFoodItem,String strServingSize,double dblCarbs,double dblProtien,double dblFats,int intCal) {
		 FoodItem tempItem = new FoodItem(strFoodItem,strServingSize,dblCarbs,dblProtien,dblFats,intCal);
		 FoodItems.get(dayIndex).add(tempItem);
		 writeFile(FoodItems.get(dayIndex), Integer.toString(dayIndex));
		 dietSceneController.DietTable.getItems().add(tempItem);
		
	 }
	
	 private static void addArchiveButtons() {

		 for(int i=FoodItems.size()-1;i>=0;i--) {
			 int day=i;
			 Button button = new Button();
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			 Calendar c = (Calendar) Main.CURRENTUSER.getDateCreated().clone();
			 c.add(Calendar.DATE, day);
			 button.setText(sdf.format(c.getTime()));
			 button.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent args0) {
						dayIndex=day;
						dietSceneController.dateLabel.setText(sdf.format(c.getTime()));
						updateTableView();
					}

			 });
			 dietSceneController.archiveVB.getChildren().add(button);
		 }
	 }
}
