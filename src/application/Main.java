package application;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Main extends Application {
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		try {

			Group root = new Group();
			Scene scene = new Scene(root, size.getWidth(), size.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.setFill(Color.web("#d3d3d3"));

			Text title = new Text(30,30, "Alex's Aim Trainer");
			Text selectDifficulty = new Text(30, 80, "Select Difficulty: ");
			title.setStyle("-fx-font: 35 arial;");
			selectDifficulty.setStyle("-fx-font: 25 arial;");
			@SuppressWarnings("rawtypes")
			ComboBox difficulty = new ComboBox();
			difficulty.setPrefWidth(100);
			difficulty.setPrefHeight(10);
			difficulty.setLayoutX(225);
			difficulty.setLayoutY(60);
			difficulty.getItems().addAll("Easy","Medium","Hard");

			Circle test = new Circle(500, 300, 500);
			root.getChildren().addAll(title,selectDifficulty,difficulty);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
