package home;

import application.TopOfPage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class HomeSceneController extends TopOfPage{
	
	@FXML
	public Label welcomeText,maxWGL,minWGL,maintainWL,minWLL,maxWLL,warningL;
	@FXML
	public ComboBox<String> goalCBox,activityCBox;
	@FXML
	public TextField feetTF,inchTF,ibsTF,ageTF;
	@FXML
	public GridPane calorieCalcGP,calcResultsGP;
	@FXML
	public Button calculateB,backToCalcB,saveGoalB;
	@FXML
	public RadioButton femaleRB,maleRB;
	@FXML
	public VBox calorieCalcVB;
	@FXML
	public TextArea personalGoalsTA;
	
	@FXML
	private void initialize() {
		
		GoalsInfo.homeController=this;
		CalorieCalculator.homeController=this;
		
		feetTF.setPromptText("feet");
		inchTF.setPromptText("inches");
		ibsTF.setPromptText("Ibs.");
		homeB.setStyle("-fx-border-color:#000000;");
		calorieCalcGP.setHgap(15);
		calorieCalcGP.setVgap(30);
		calcResultsGP.setHgap(50);
		calcResultsGP.setVgap(10);
		
		
		topLayout();
		CalorieCalculator.buildCalculator();
		setCBoxs();
		
    }
	
	private void setCBoxs() {
		goalCBox.getItems().addAll("Lose Weight","Build Muscle","Get Stronger",
				"Improve Endurance/Conditioning","Improve Athletic Skills","Improve Joint Flexibility");
		
		activityCBox.getItems().addAll("sedentary","lightly active (1-3 days/week)",
				"moderately active (3-5 days/week)", "very active (6-7 days a week)",
				"extra active (2x training)");
	}
	

}
