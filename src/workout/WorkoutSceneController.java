package workout;

import java.util.ArrayList;

import application.TopOfPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class WorkoutSceneController extends TopOfPage {
	@FXML
	private TableView<WorkoutItem> WT1,WT2,WT3,WT4,WT5,WT6,WT7;
	private ArrayList<TableView<WorkoutItem>> WorkoutTables = new ArrayList<TableView<WorkoutItem>>();
	@FXML
	private Button WB1,WB2,WB3,WB4,WB5,WB6,WB7;
	private ArrayList<Button> WorkoutButtons = new ArrayList<Button>();
	@FXML
	private TextField WTF1_0,WTF1_1,WTF2_0,WTF2_1,WTF3_0,WTF3_1,WTF4_0,WTF4_1,WTF5_0,WTF5_1,WTF6_0,WTF6_1,WTF7_0,WTF7_1;
	private ArrayList<TextField> WorkoutTextFields = new ArrayList<TextField>();
	@FXML
	private TableColumn<String, Integer> WC1_0,WC1_1,WC2_0,WC2_1,WC3_0,WC3_1,WC4_0,WC4_1,WC5_0,WC5_1,WC6_0,WC6_1,WC7_0,WC7_1;
	private ArrayList<TableColumn<String, Integer>> WTableColumns = new ArrayList<TableColumn<String, Integer>>();
	
	@FXML
	private void initialize() {
		workoutB.setStyle("-fx-border-color:#000000;");
		topLayout();
		
		addWorkoutCells();
		buildTables();
    }
	
	private void addWorkoutCells() {
		WTableColumns.add(WC1_0);WTableColumns.add(WC1_1);WTableColumns.add(WC2_0);
		WTableColumns.add(WC2_1);WTableColumns.add(WC3_0);WTableColumns.add(WC3_1);
		WTableColumns.add(WC4_0);WTableColumns.add(WC4_1);WTableColumns.add(WC5_0);
		WTableColumns.add(WC5_1);WTableColumns.add(WC6_0);WTableColumns.add(WC6_1);
		WTableColumns.add(WC7_0);WTableColumns.add(WC7_1);
		
		for(int i=0;i<7;i++) {
			WTableColumns.get(2*i).setCellValueFactory(new PropertyValueFactory<>("name"));
			WTableColumns.get(2*i+1).setCellValueFactory(new PropertyValueFactory<>("reps"));
		}
	}
	
	private void buildTables() {
		WorkoutTables.add(WT1);WorkoutTables.add(WT2);WorkoutTables.add(WT3);
		WorkoutTables.add(WT4);WorkoutTables.add(WT5);WorkoutTables.add(WT6);
		WorkoutTables.add(WT7);
		
		WorkoutButtons.add(WB1);WorkoutButtons.add(WB2);WorkoutButtons.add(WB3);
		WorkoutButtons.add(WB4);WorkoutButtons.add(WB5);WorkoutButtons.add(WB6);
		WorkoutButtons.add(WB7);
		
		WorkoutTextFields.add(WTF1_0);WorkoutTextFields.add(WTF1_1);WorkoutTextFields.add(WTF2_0);
		WorkoutTextFields.add(WTF2_1);WorkoutTextFields.add(WTF3_0);WorkoutTextFields.add(WTF3_1);
		WorkoutTextFields.add(WTF4_0);WorkoutTextFields.add(WTF4_1);WorkoutTextFields.add(WTF5_0);
		WorkoutTextFields.add(WTF5_1);WorkoutTextFields.add(WTF6_0);WorkoutTextFields.add(WTF6_1);
		WorkoutTextFields.add(WTF7_0);WorkoutTextFields.add(WTF7_1);
		
		for(int i=0;i<7;i++) {
			
			final Integer in = i;
			
			WorkoutTables.get(in).setSelectionModel(null);
			Label placeHolder = new Label("No food items entered.");
			placeHolder.setStyle("-fx-font-weight:BOLD;-fx-text-fill:#FFFFFF;");
			WorkoutTables.get(in).setPlaceholder(placeHolder);
			
			WorkoutButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args0) {
				String WorkoutName = WorkoutTextFields.get(2*in).getText();
				String WorkoutReps = WorkoutTextFields.get(2*in+1).getText();
				WorkoutItem tempItem = new WorkoutItem(WorkoutName,WorkoutReps);
				
				WorkoutTables.get(in).getItems().add(tempItem);
				
				WorkoutTextFields.get(2*in).clear();
				WorkoutTextFields.get(2*in+1).clear();
			}});
		}
	}
}
