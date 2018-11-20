package org.artorg.tools.phantomData.server.boot;

import java.util.Arrays;
import java.util.function.BiConsumer;

import org.artorg.tools.phantomData.server.util.FxUtil;

import huma.io.ConsoleDiverter;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class FxConsoleFrame implements ConsoleFrame {
	private ConsoleDiverter consoleDiverter;
	private boolean errorOccured;
	private TextFlow textArea;
	private Stage stage;
	private ScrollPane scrollPane;
	
	{
		textArea = new TextFlow();
		textArea.setSnapToPixel(true);
		scrollPane = new ScrollPane();
		scrollPane.setContent(textArea);
		
		Scene scene = FxUtil.createScene(scrollPane);
		scrollPane.getStyleClass().add("console-pane");
		
		textArea.maxHeight(Double.MAX_VALUE);
		scrollPane.maxHeight(Double.MAX_VALUE);
		
		stage = new Stage();
		stage.getIcons().add(new Image("img/startup.png"));
		stage.setWidth(1200.0);
		stage.setHeight(440.0);
		stage.setTitle("Phantom Database - Logging panel");
		stage.setScene(scene);
	}
	
	private void updateTextArea(BiConsumer<TextFlow, String> textWriter, String newLine) {
		textWriter.accept(textArea, newLine + "\n");
	}
	
	private void appendToPaneOut(TextFlow tp, String msg) {
		appendToPane(tp, msg, Color.WHITESMOKE);
	}
	
	private void appendToPaneErr(TextFlow tp, String msg) {
		appendToPane(tp, msg, Color.RED);
	}
	
	private void appendToPane(TextFlow tp, String msg, Color color) {
		Platform.runLater(() -> {
			Text text = new Text(msg);
			text.setFont(Font.font("Monospaced", 12));
			text.setFill(color);
			tp.getChildren().add(text);
			scrollPane.setVvalue(scrollPane.getVmax());
		});
	}
	
	@Override
	public void setVisible(boolean visible) {
		if (visible) stage.show();
		else stage.hide();
	}
	
	@Override
	public void setTitle(String title) {
		stage.setTitle(title);
	}
	
	@Override
	public Object getGraphic() {
		return stage;
	}
	
	@Override
	public boolean isErrorOccured() {
		return this.errorOccured;
	}
	
	@Override
	public void setErrorOccured(boolean b) {
		this.errorOccured = b;
	}
	
	@Override
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
		consoleDiverter.addOutLineConsumer((consoleLines, newLine) -> updateTextArea(this::appendToPaneOut, newLine));
		consoleDiverter.addErrLineConsumer(
				Arrays.asList((consoleLines, newLine) -> updateTextArea(this::appendToPaneErr, newLine),
						(consoleLines, newLine) -> setErrorOccured(true)));
	}
	
	@Override
	public ConsoleDiverter getConsoleDiverter() {
		return this.consoleDiverter;
	}
	
}
