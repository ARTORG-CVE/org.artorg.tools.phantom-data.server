package org.artorg.tools.phantomData.server.boot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.artorg.tools.phantomData.server.logging.Logger;
import org.artorg.tools.phantomData.server.util.FxUtil;

import huma.io.ConsoleDiverter;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * An implementation of {@link ConsoleFrame} using JavaFX.
 */
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
		
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem menuItem;
		menuItem = new MenuItem("Save");
		menuItem.setOnAction(event -> saveAsFile());
		contextMenu.getItems().add(menuItem);
		
		textArea.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			
			@Override
			public void handle(ContextMenuEvent event) {
				contextMenu.show(textArea, event.getScreenX(), event.getScreenY());
			}
			
		});
	}
	
	private void saveAsFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save log output");
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            ); 
		fileChooser.setInitialFileName(dtf.format(LocalDateTime.now()) +"_log.txt");
		File file = fileChooser.showSaveDialog(stage);
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String text = textArea.getChildren().stream().map(node -> ((Text)node).getText()).collect(Collectors.joining(""));
		    writer.write(text);
		     
		    writer.close();
		    Logger.info.println("File created \"" +file.getPath() +"\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void updateTextArea(BiConsumer<TextFlow, String> textWriter, String newLine) {
		textWriter.accept(textArea, newLine + "\n");
	}
	
	private void appendToPaneOut(TextFlow tp, String msg) {
		appendToPane(tp, msg, Color.BLACK);
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
	
	/** {@inheritDoc} */
	@Override
	public void setVisible(boolean visible) {
		if (visible) stage.show();
		else stage.hide();
	}
	
	/** {@inheritDoc} */
	@Override
	public void setTitle(String title) {
		stage.setTitle(title);
	}
	
	/** {@inheritDoc} */
	@Override
	public Object getGraphic() {
		return stage;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isErrorOccured() {
		return this.errorOccured;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setErrorOccured(boolean b) {
		this.errorOccured = b;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
		consoleDiverter.addOutLineConsumer((consoleLines,
				newLine) -> updateTextArea(this::appendToPaneOut, newLine));
		consoleDiverter.addErrLineConsumer(Arrays.asList(
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneErr, newLine),
				(consoleLines, newLine) -> setErrorOccured(true)));
	}
	
	/** {@inheritDoc} */
	@Override
	public ConsoleDiverter getConsoleDiverter() {
		return this.consoleDiverter;
	}
	
}
