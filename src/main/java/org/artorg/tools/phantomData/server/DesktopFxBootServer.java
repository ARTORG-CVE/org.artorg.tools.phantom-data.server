package org.artorg.tools.phantomData.server;

import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.FxConsoleFrame;
import org.artorg.tools.phantomData.server.boot.FxStartupProgressController;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.util.FxUtil;

import huma.io.ConsoleDiverter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DesktopFxBootServer extends Application {
	private FxStartupProgressController controller;
	private String[] args;
	private double xOffset = 0;
	private double yOffset = 0;
	
	public DesktopFxBootServer() {}
	
	public String[] getArgs() {
		return args;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public static void main(String[] args) {
		new DesktopFxBootServer().boot(args);
	}
	
	public void boot(String[] args) {
		setArgs(args);
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		controller = new FxStartupProgressController(stage);
		Pane pane = FxUtil.loadFXML("fxml/Boot.fxml", controller, getClass());
		Scene scene = new Scene(pane, Color.DARKSLATEGRAY);
		
		scene.getStylesheets().add(FxUtil.readCSSstylesheet("css/application.css"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("img/startup.png"));
		stage.setScene(scene);
		stage.setTitle("Phantom Database");
		stage.setResizable(false);
		stage.show();
		stage.requestFocus();
		stage.toFront();
		
		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			}
		});
		
		Platform.runLater(() -> {
			if (args == null) args = new String[] {};
			ServerBooter booter = createBooter(controller);
			booter.boot(args);
		});
		
	}
	
	public ServerBooter createBooter(FxStartupProgressController controller) {
		return new ServerBooter() {
			
			@Override
			public void boot(String[] args) {
				try {
					setBootApplicationClass(BootApplication.class);
					setExternalConfigOverridable(false);
					setConsoleDiverter(new ConsoleDiverter());
					setConsoleFrame(new FxConsoleFrame());
					
					setStartupFrame(controller);
					init();
					prepareFileStructure();
					DbFile.setFilesPath(getFilesPath());
					
					if (isDebugConsoleMode()) getConsoleFrame().setVisible(true);
					if (!isConnected()) {
						getStartupFrame().setnConsoleLines(39);
						getStartupFrame().setTitle("Phantom Database");
						getStartupFrame().setVisible(true);
						getStartupFrame().setProgressing(true);
						Task<Void> task = new Task<Void>() {
							@Override
							protected Void call() throws Exception {
								try {
									startSpringServer(args);
								} catch (Exception e) {
									handleException(e);
								}
								return null;
							}
						};
						task.setOnSucceeded(event -> finish());
						task.setOnFailed(event -> finish());
						task.setOnCancelled(event -> finish());
						Executors.newCachedThreadPool().execute(task);
					} else {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Server already started!");
						frame.dispose();
						System.exit(0);
					}
					
				} catch (Exception e) {
					getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
					setErrorOccured(true);
					if (!super.handleException(e)) e.printStackTrace();
					finish();
				}
			}
			
			@Override
			public boolean handleException(Exception e) {
				if (!super.handleException(e)) e.printStackTrace();
				getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
				setErrorOccured(true);
				getConsoleFrame().setVisible(true);
				return true;
			}
			
			private void finish() {
				getStartupFrame().setVisible(false);
				getStartupFrame().dispose();
				if (!getConsoleFrame().isErrorOccured() && !isErrorOccured() && !isDebugConsoleMode())
					getConsoleFrame().setVisible(false);
				else
//					if (isRunnableJarExecution()) 
					getConsoleFrame().setVisible(true);
			}
		};
		
	}
	
}
