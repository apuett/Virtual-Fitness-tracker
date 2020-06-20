package account;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TitleBar {
	
	public static void makeTitleBar(HBox titleBar) {
		Button CloseButton = new Button ("\u2715");
		String closeOnIdle ="-fx-background-color:#000000;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:12pt;"
							+ "-fx-border-radius:0;";
		String closeOnHover ="-fx-background-color:#800D29;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:12pt;"
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
							+ "-fx-font-size:12pt;"
							+ "-fx-border-radius:0;";
		String minOnHover ="-fx-background-color:#333333;"
							+ "-fx-text-fill:#FFFFFF;"
							+ "-fx-font-family:'Arial';"
							+ "-fx-font-size:12pt;"
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
		titleBar.setAlignment(Pos.CENTER_RIGHT);
		titleBar.setPadding(new Insets(0,0,1,0));
		
		dragTitleBar(titleBar);
		
	}
	
	static double x;
	static double y;
	public static void dragTitleBar(HBox titleBar) {
		
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
}
