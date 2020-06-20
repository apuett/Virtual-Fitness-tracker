package diet;

import application.TopOfPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DietSceneController extends TopOfPage{
	
	@FXML
	private VBox Center;
	@FXML
	public TableView<FoodItem> DietTable;
	@FXML
	private TableColumn<String, Integer> ServingSizeColumn,FoodColumn,CarbsColumn,ProtienColumn,FatsColumn,CaloriesColumn;
	@FXML private Button addB;
	@FXML private ToggleButton archiveB;
	@FXML
	private TextField servingSizeTF,foodTF,carbsTF,protienTF,fatsTF ;
	@FXML
	private ScrollPane dietScroller,archiveSP;
	@FXML
	private GridPane foodInputs;
	@FXML 
	private Label alertLabel,totalL;
	@FXML
	private HBox totalBox;
	@FXML
	private StackPane archiveStPn;
	@FXML
	public VBox archiveVB;
	@FXML
	private BorderPane root;
	@FXML
	public Label dateLabel,carbsTotalL,protiensTotalL,fatsTotalL,calTotalL;
	private Boolean foodLimit = false;
	
	@FXML
	private void initialize() {
		dietB.setStyle("-fx-border-color:#000000;");
		Center.setPadding(new Insets(70,0,40,0));
		Center.setSpacing(50);
		GridPane.setMargin(addB,new Insets(8,0,0,0));
		HBox.setMargin(totalL, new Insets(0,92,0,0));
		HBox.setMargin(carbsTotalL, new Insets(0,62,0,0));
		HBox.setMargin(protiensTotalL, new Insets(0,62,0,0));
		HBox.setMargin(fatsTotalL, new Insets(0,62,0,0));
		foodInputs.setHgap(5);
		archiveStPn.setPickOnBounds(false);
		DietSceneFiles.dietSceneController=this;
		
		
		servingSizeTF.setPromptText("Not Req.");
		carbsTF.setPromptText("grams");
		protienTF.setPromptText("grams");
		fatsTF.setPromptText("grams");
		
		DietTable.setMaxWidth(552);
		DietTable.setMinWidth(552);
		DietTable.setMinHeight(450);
		DietTable.setSelectionModel(null);
		Label placeHolder = new Label("No food items entered.");
		placeHolder.setStyle("-fx-font-weight:BOLD;-fx-text-fill:#FFFFFF;");
		DietTable.setPlaceholder(placeHolder);
		
		addCells();
		
		addB.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent args0) {
				addFoodItem();
			}
		});
		
		archiveSP.setManaged(false);
		archiveB.setText("\u276F");
		archiveB.setOnAction(evt->{
			archiveSP.setManaged(archiveB.isSelected());
			if (archiveB.isSelected()){
				archiveB.setText("\u276E");
			}else {
				archiveB.setText("\u276F");
			}
		});
		
		topLayout();
		
		
    }
	
	private void addCells() {
		ServingSizeColumn.setCellValueFactory(new PropertyValueFactory<>("ServingSize"));
		FoodColumn.setCellValueFactory(new PropertyValueFactory<>("Item"));
		CarbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
		ProtienColumn.setCellValueFactory(new PropertyValueFactory<>("protien"));
		FatsColumn.setCellValueFactory(new PropertyValueFactory<>("fats"));
		CaloriesColumn.setCellValueFactory(new PropertyValueFactory<>("cal"));
	}
	
	private void addFoodItem() {
		String strServingSize = servingSizeTF.getText();
		String strFoodItem = foodTF.getText();
		
		if(!strFoodItem.equals("") || ValidNum(carbsTF.getText()) || ValidNum(protienTF.getText()) || ValidNum(fatsTF.getText())) {	
			double dblCarbs = Double.parseDouble(carbsTF.getText());
			double dblProtien = Double.parseDouble(protienTF.getText());
			double dblFats = Double.parseDouble(fatsTF.getText());
			int intCal = (int) Math.round((4*dblCarbs)+(4*dblProtien)+(9*dblFats));
			
			if(!foodLimit && dblCarbs<1000 && dblProtien<1000 && dblFats<1000 && intCal<10000) {
				setSum(dblCarbs,dblProtien,dblFats,intCal);
				DietSceneFiles.createAndAddFoodItem(strFoodItem,strServingSize,dblCarbs,dblProtien,dblFats,intCal);
				
				servingSizeTF.clear();
				foodTF.clear();
				carbsTF.clear();
				protienTF.clear();
				fatsTF.clear();
				alertLabel.setText("");
			}else alertLabel.setText("Carb, Fat, Protien, or Calorie limit reached!");
		}else alertLabel.setText("One or more field Invalid!");
			
	}
	public static boolean ValidNum(String str) {
		char[] validChars= {'1','2','3','4','5','6','7','8','9','0','.'};
		int intValidChars=0;
		for(var i=0;i<str.length();i++) {
			for(char k:validChars) {
				if(k==str.charAt(i))intValidChars++;
			}
		}
		if(str.length()==intValidChars && str.length()>0)return true;
		else return false;
	}
	
	public void setSum(double carb, double prot, double fat, double cal) {
		int intCarbsTotal = Integer.parseInt(carbsTotalL.getText());
		int intProtienTotal = Integer.parseInt(protiensTotalL.getText());
		int intFatTotal = Integer.parseInt(fatsTotalL.getText());
		int intCalTotal = Integer.parseInt(calTotalL.getText());
		
		foodLimit = false;
		intCarbsTotal+=Math.round(carb);
		intProtienTotal +=Math.round(prot);
		intFatTotal += Math.round(fat);
		intCalTotal +=Math.round(cal);
		
		if(intCarbsTotal>=10 && intCarbsTotal<100)HBox.setMargin(carbsTotalL, new Insets(0,54,0,0));
		if(intCarbsTotal>=100)HBox.setMargin(carbsTotalL, new Insets(0,46,0,0));
		if(intProtienTotal>=10 && intCarbsTotal<100)HBox.setMargin(protiensTotalL, new Insets(0,54,0,0));
		if(intProtienTotal>=100)HBox.setMargin(protiensTotalL, new Insets(0,46,0,0));
		if(intFatTotal>=10 && intCarbsTotal<100)HBox.setMargin(fatsTotalL, new Insets(0,54,0,0));
		if(intFatTotal>=100)HBox.setMargin(fatsTotalL, new Insets(0,46,0,0));
		
		if(intCarbsTotal<1000) carbsTotalL.setText(Integer.toString(intCarbsTotal));
		else {
			carbsTotalL.setText("999"); 
			foodLimit=true;
		}
		if(intProtienTotal<1000) protiensTotalL.setText(Integer.toString(intProtienTotal));
		else {
			protiensTotalL.setText("999");
			foodLimit=true;
		}
		if(intFatTotal<1000) fatsTotalL.setText(Integer.toString(intFatTotal));
		else{
			fatsTotalL.setText("999");
			foodLimit=true;
		}
		if(intCalTotal<10000) calTotalL.setText(Integer.toString(intCalTotal));
		else {
			calTotalL.setText("9999");
			foodLimit=true;
		}
	}
		
}
