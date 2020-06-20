package application;

import java.io.IOException;

import account.UserFiles;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class TopOfPage {

	
	@FXML
	private Label titleText;
	@FXML
	private HBox menuPane,titleBar;
	@FXML
	protected Button homeB,workoutB,dietB;
	@FXML
	protected StackPane settingPane;
	@FXML
	protected MenuButton settingsB;
	@FXML
	protected MenuItem logoutMI,deleteAccountMI;
	
	private double x,y;
	
	protected void topLayout() {
		menuPane.setPadding(new Insets(1,12,1,12));
		menuPane.setSpacing(1);
		titleText.setPadding(new Insets(0,12,15,24));
        titleText.setText("FitnessTracker");
        settingsB.setText("\u2735");
        settingsB.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
        setMenuItems();
        menuButtons();
        makeTitleBar();
	}
	
	private void menuButtons() {
		
		homeB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
			
				try {
					Main.changeStage(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}});
		workoutB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				
				try {
					Main.changeStage(1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}});
		dietB.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				
				try {
					Main.changeStage(2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}});
		
	}
	
	private void makeTitleBar() {
		Button CloseButton = new Button ("\u2715");
		String closeOnIdle ="-fx-background-color:#000000;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:14pt;"
							+ "-fx-border-radius:0;";
		String closeOnHover ="-fx-background-color:#800D29;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:14pt;"
							+ "-fx-border-radius:0;";
		CloseButton.setPadding(new Insets(4,18,4,18));
		CloseButton.setStyle(closeOnIdle);
		CloseButton.setOnMouseEntered(e->CloseButton.setStyle(closeOnHover));
		CloseButton.setOnMouseExited(e->CloseButton.setStyle(closeOnIdle));
		CloseButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent argo) {
				Platform.exit();
			}
		});
		
		Button MinButton = new Button ("\u2014");
		String minOnIdle ="-fx-background-color:#000000;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:14pt;"
							+ "-fx-border-radius:0;";
		String minOnHover ="-fx-background-color:#333333;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:14pt;"
							+ "-fx-border-radius:0;";
		MinButton.setPadding(new Insets(4,18,4,18));
		MinButton.setStyle(closeOnIdle);
		MinButton.setOnMouseEntered(e->MinButton.setStyle(minOnHover));
		MinButton.setOnMouseExited(e->MinButton.setStyle(minOnIdle));
		MinButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent argo) {
				Main.stage.setIconified(true);
			}
		});

		titleBar.getChildren().addAll(MinButton,CloseButton);
		titleBar.setStyle("-fx-background-color:#000000;");
		titleBar.setPadding(Insets.EMPTY);
		
		dragTitleBar();
		
	}
	
	public void dragTitleBar() {
		
		titleBar.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				x =Main.stage.getX()-event.getScreenX();
				y =Main.stage.getY()-event.getScreenY();
			}	
		});
		titleBar.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				Main.stage.setX(event.getScreenX()+x);
				Main.stage.setY(event.getScreenY()+y);
			}	
		});
		titleBar.setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				Main.stage.setX(event.getScreenX()+x);
				Main.stage.setY(event.getScreenY()+y);
			}	
		});
		
	}
	
	private void setMenuItems() {
		logoutMI.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Main.CURRENTUSER=null;
				Main.stage.setX(Main.stage.getX()+175);
				Main.stage.setY(Main.stage.getY()+60);
				Main.loginStage();
				System.out.println("Logout Successful!");
			}
		});
		
		deleteAccountMI.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				UserFiles.removeUser();
			}
		});
	}
}
