package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	@FXML
	ComboBox difficulty;
	@FXML
	Text hardScore;
	@FXML
	Text mediumScore;
	@FXML
	Text easyScore;

	public void start(ActionEvent e) throws Exception {

		try {
			if (difficulty.getValue() == null) {
				throw new Exception();
			} else {
				Data.setDifficulty((String) difficulty.getValue());
				Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		} catch (Exception f) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Select Difficulty");
			alert.show();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		difficulty.getItems().addAll("Easy", "Medium", "Hard");
		Scanner s;
		try {
			s = new Scanner(getClass().getClassLoader().getResourceAsStream("highScore.txt"));
			hardScore.setText("Hard: " + s.next());
			mediumScore.setText("Medium: " + s.next());
			easyScore.setText("Easy: " + s.next());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
