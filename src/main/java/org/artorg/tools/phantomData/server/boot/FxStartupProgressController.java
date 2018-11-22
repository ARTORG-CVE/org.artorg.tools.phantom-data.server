package org.artorg.tools.phantomData.server.boot;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import huma.io.ConsoleDiverter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FxStartupProgressController extends StartupProgressFrame {
	private final Stage stage;

	public FxStartupProgressController(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label progressLabel;

	@FXML
	private ProgressBar progressBar;

	@FXML
	void close(MouseEvent event) {
		stage.hide();
		Platform.exit();
		System.exit(0);
	}
	
	@FXML
	void minimize(MouseEvent event) {
		stage.setIconified(true);
	}

	@FXML
	void initialize() {
		assert progressLabel != null : "fx:id=\"progressLabel\" was not injected: check your FXML file 'Boot.fxml'.";
		assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'Boot.fxml'.";
	}

	private void updateStartupProgress(List<String> consoleLines, String newLine) {
		if (isProgressing()) {
			Pattern pattern = Pattern.compile("[^:]: (.*)");
			if (consoleLines.size() > 0) {
				if (consoleLines.size() < 10) {
					Platform.runLater(() -> {
						progressLabel.setText("Launching Spring Boot...");
					});
				} else {
					Double progressValue = (getProgress() + 100.0 / getnConsoleLines()/100.0);
					String progressText = newLine;
					Matcher m = pattern.matcher(newLine);
					if (m.find()) progressText = m.group(1);
					final String text = progressText;
					Platform.runLater(() -> {
						setProgress(progressValue);
						progressLabel.setText(text);
					});
				}
			}
		}
	}

	@Override
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		super.setConsoleDiverter(consoleDiverter);
		consoleDiverter.addOutLineConsumer(this::updateStartupProgress);
	}

	@Override
	public void setProgress(double progress) {
		super.setProgress(progress);
		progressBar.setProgress(progress);
	}

	@Override
	public void setVisible(boolean b) {
		if (b) stage.show();
		else stage.hide();
	}

	@Override
	public void dispose() {
		stage.hide();
	}

	@Override
	public void setTitle(String title) {
		stage.setTitle(title);
	}

	@Override
	public Object getGraphic() {
		return stage;
	}

}