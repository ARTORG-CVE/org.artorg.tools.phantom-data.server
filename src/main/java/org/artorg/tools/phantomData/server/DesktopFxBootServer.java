package org.artorg.tools.phantomData.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.FxStartupProgressContorller;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.util.FxUtil;

import huma.io.ConsoleDiverter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DesktopFxBootServer extends Application {
	private FxStartupProgressContorller controller;
	private String[] args;
	
	public DesktopFxBootServer() {}
	
	public String[] getArgs() {
		return args;
	}
	
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public static void main(String[] args) {
		DesktopFxBootServer fxBooter = new DesktopFxBootServer();
		fxBooter.setArgs(args);
//		.launch(args);
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		controller = new FxStartupProgressContorller(stage);
		Pane pane = FxUtil.loadFXML("fxml/Boot.fxml", controller, getClass());
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(FxUtil.readCSSstylesheet("css/application.css"));
		ServerBooter booter = createBooter(controller);
//		scene.getStylesheets().add(FxUtil.readCSSstylesheet("css/application.css"));
		
		
		
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("img/startup.png"));
		stage.setScene(scene);
		stage.setTitle("Phantom Database");
		stage.setResizable(false);
//		stage.setWidth(800);
//		stage.setHeight(500);
		
		stage.show();
		stage.requestFocus();
		stage.toFront();
		
		if (args == null) args = new String[] {};
		Platform.runLater(() -> booter.boot(args));
		
	}
	
	public ServerBooter createBooter(FxStartupProgressContorller controller) {
		return new ServerBooter() {
			
			@Override
			public void boot(String[] args) {
				try {
					setBootApplicationClass(BootApplication.class);
					setExternalConfigOverridable(false);
					setConsoleDiverter(new ConsoleDiverter());
					setConsoleFrame(new SwingConsoleFrame());
					
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
								startSpringServer(args);
								return null;
							}	
						};
						Executors.newCachedThreadPool().execute(task);
						while (!isConnected()) {
							Thread.sleep(100);
						}
						
//						getStartupFrame().setVisible(false);
					} else {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Server already started!");
						frame.dispose();
						System.exit(0);
					}
					throw new IllegalArgumentException();
//					getStartupFrame().dispose();
				} catch (Exception e) {
					getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
					setErrorOccured(true);
					if (!super.handleException(e)) e.printStackTrace();
				}
				if (!getConsoleFrame().isErrorOccured() && !isErrorOccured() && !isDebugConsoleMode())
					getConsoleFrame().setVisible(false);
				else 
//					if (isRunnableJarExecution()) 
						getConsoleFrame().setVisible(true);
				
			}
		};
		
	}
	
}
