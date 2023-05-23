package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController implements Initializable {
	@FXML
	Circle targetOne;
	@FXML
	Circle targetTwo;
	@FXML
	Circle targetThree;
	@FXML
	Text score;
	@FXML
	Text time;
	@FXML
	Text finalScore;
	@FXML
	Text highScore;
	@FXML
	Button home;
	String replaceScores = "";
	String hold = "";
	int highValue;
	int scoreValue = -3;
	int minX = 0;
	int maxX = 1100;
	int minY = 0;
	int maxY = 600;

	public void changeOne(MouseEvent e) {
		System.out.println("test");
		targetOne.setCenterX(Math.random() * (maxX - minX) + minX);
		targetOne.setCenterY(Math.random() * (maxY - minY) + minY);
		changeScore();
	}

	public void changeTwo(MouseEvent e) {
		System.out.println("test");

		targetTwo.setCenterX(Math.random() * (maxX - minX) + minX);
		targetTwo.setCenterY(Math.random() * (maxY - minY) + minY);
		changeScore();
	}

	public void changeThree(MouseEvent e) {
		System.out.println("test");

		targetThree.setCenterX(Math.random() * (maxX - minX) + minX);
		targetThree.setCenterY(Math.random() * (maxY - minY) + minY);
		changeScore();
	}

	public void changeScore() {
		scoreValue++;
		score.setText("Score: " + scoreValue);
	}
	public void subtractScore() {
		scoreValue--;
		score.setText("Score: " + scoreValue);

	}
	public void switchToEnd(MouseEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("End.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void home(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Title.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public String checkHighScore(int value, int hold) {
		if(value > hold) {
			return String.valueOf(value);
		} else {
			return String.valueOf(hold);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		if (Data.getDifficulty().equals("Easy")) {
			System.out.println("true");
			targetOne.setRadius(50);
			targetTwo.setRadius(50);
			targetThree.setRadius(50);
		} else if (Data.getDifficulty().equals("Medium")) {
			targetOne.setRadius(25);
			targetTwo.setRadius(25);
			targetThree.setRadius(25);
		} else {
			targetOne.setRadius(10);
			targetTwo.setRadius(10);
			targetThree.setRadius(10);
		}
		MouseEvent mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 1, 2, 3, 4, MouseButton.PRIMARY, 5, true, true,
				true, true, true, true, true, true, true, true, null);
		changeOne(mouseEvent);
		changeTwo(mouseEvent);
		changeThree(mouseEvent);

		Timer rate = new Timer();
		TimerTask timer = new TimerTask() {
			int counter = 30;

			@Override
			public void run() {
				counter--;
				if (counter >= 0) {
					time.setText("Time:" + counter);
				} else {
					rate.cancel();
					score.setOpacity(0);
					score.setDisable(false);
					time.setOpacity(0);
					time.setDisable(false);
					home.setOpacity(1);
					home.setDisable(false);
					finalScore.setOpacity(1);
					finalScore.setText("Your Score: " + scoreValue);
					try {
						File f = new File("src/highScore.txt");
						Scanner s = new Scanner(f);
						if (Data.getDifficulty().equals("Hard")) {
							replaceScores += s.nextInt();
							highValue = Integer.valueOf(replaceScores);
							replaceScores = checkHighScore(scoreValue, highValue);
							replaceScores += "\n" + s.nextInt() + "\n" + s.nextInt();

						} else if (Data.getDifficulty().equals("Medium")) {
							replaceScores += s.nextInt();
							hold += s.nextInt();
							highValue = Integer.valueOf(hold);
							hold = checkHighScore(scoreValue, highValue);
							replaceScores += "\n" + hold + "\n" + s.nextInt();

						} else {
							replaceScores += s.nextInt() + "\n" + s.nextInt() + "\n";
							hold += s.nextInt();
							highValue = Integer.valueOf(hold);
							hold = checkHighScore(scoreValue, highValue);
							replaceScores += hold;
						}
						s.close();
						if (scoreValue > highValue) {
							highScore.setText("New High Score For " + Data.getDifficulty());
							FileWriter overwrite = new FileWriter(f);
							System.out.println(replaceScores);
							overwrite.write(replaceScores);
							System.out.println("test");
							overwrite.flush();
							overwrite.close();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		rate.schedule(timer, 0, 1000);
	}
}
