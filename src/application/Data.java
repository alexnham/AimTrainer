package application;

import javafx.stage.Stage;

public class Data {
	private static String difficulty;
	private static int score;
	private static Stage stage;

	public static void setStage(Stage s) {
		stage = s;
	}
	public static  Stage getStage() {
		return stage;
	}
	public static void setDifficulty(String d) {
		difficulty = d;
	}

	public static String getDifficulty() {
		return difficulty;
	}

	public static void setScore(int s) {
		score = s;
	}

	public static int getScore() {
		return score;
	}
}
