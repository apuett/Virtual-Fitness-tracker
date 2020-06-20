package home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class CalorieCalculator {
	
	public static HomeSceneController homeController;

	
	public static void buildCalculator() {
		homeController.calculateB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				boolean valid =ValidNum(homeController.ibsTF.getText())&&ValidNum(homeController.feetTF.getText())&&ValidNum(homeController.inchTF.getText())
						&&ValidNum(homeController.ageTF.getText())&&(homeController.maleRB.isSelected()||homeController.femaleRB.isSelected());
				if(valid) {
					calculateCalories();
					homeController.calorieCalcVB.setManaged(false);
					homeController.backToCalcB.setVisible(true);
					homeController.calcResultsGP.setManaged(true);
					homeController.calcResultsGP.setVisible(true);
					homeController.warningL.setText("");
				}else {
					homeController.warningL.setText("One or more fields inavalid!");
				}
			}
		});
		homeController.backToCalcB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				homeController.calorieCalcVB.setManaged(true);
				homeController.calcResultsGP.setManaged(false);
				homeController.calcResultsGP.setVisible(false);
				homeController.backToCalcB.setVisible(false);
			}
		});
		homeController.backToCalcB.setVisible(false);
		homeController.calcResultsGP.setManaged(false);
	}
	
	private static void calculateCalories() {
		double bodyWeight = Double.parseDouble(homeController.ibsTF.getText());
		double feet = Double.parseDouble(homeController.feetTF.getText());
		double inches = Double.parseDouble(homeController.inchTF.getText());
		double age = Double.parseDouble(homeController.ageTF.getText());
		double totalInch = (12*feet)+inches;
		double BMR, calories;
		double multiplier=multiplier();
		
		if(homeController.maleRB.isSelected()) {
			BMR=66+(6.3*bodyWeight)+(12.9*totalInch)-(6.8*age);
		}else if(homeController.femaleRB.isSelected()) {
			BMR=655+(4.3*bodyWeight)+(4.7*totalInch)-(4.7*age);
		}else {
			BMR=0;
		}
		calories = BMR*multiplier;
		setCalorieResults(calories);
		
	}
	
	private static double multiplier() {
		int index=homeController.activityCBox.getItems().indexOf(homeController.activityCBox.getValue());
		switch(index) {
			case 0:
				return 1.2;
			case 1:
				return 1.375;
			case 2:
				return 1.55;
			case 3:
				return 1.725;
			case 4:
				return 1.9;
		}
		return 1.2;
	}
	
	private static void setCalorieResults(double calories){
		String str=Integer.toString((int) Math.round(calories));
		String minWl =Integer.toString((int) Math.round(calories-250));
		String maxWl =Integer.toString((int) Math.round(calories-750));
		String minWG =Integer.toString((int) Math.round(calories+250));
		String maxWG =Integer.toString((int) Math.round(calories+750));
		
		homeController.maxWGL.setText(maxWG+" calories");
		homeController.minWGL.setText(minWG+" calories");
		homeController.maintainWL.setText(str+" calories");
		homeController.minWLL.setText(minWl+" calories");
		homeController.maxWLL.setText(maxWl+" calories");
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
}
