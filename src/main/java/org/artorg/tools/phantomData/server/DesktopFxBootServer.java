package org.artorg.tools.phantomData.server;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.artorg.tools.phantomData.server.boot.FxStartupProgressContorller;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.SwingConsoleFrame;
import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.util.FxUtil;

import huma.io.ConsoleDiverter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DesktopFxBootServer extends Application {
	private FxStartupProgressContorller controller;
	private String[] args;

	public DesktopFxBootServer(String[] args) {
		this.args = args;
	}
	
	public static void main(String[] args) {
		new DesktopFxBootServer(args);
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		controller = new FxStartupProgressContorller(stage);
		Pane pane =
			FxUtil.loadFXML("fxml/Boot.fxml", controller, getClass());
		Scene scene = new Scene(pane);
		ServerBooter booter = createBooter(controller);
//		scene.getStylesheets().add(FxUtil.readCSSstylesheet("css/application.css"));
		
		stage.getIcons().add(new Image("img/startup.png"));
		stage.setScene(scene);
		stage.setTitle("Phantom Database");
		stage.setWidth(800);
		stage.setHeight(500);
		stage.show();
		stage.requestFocus();
		stage.toFront();
		
		booter.boot(args);
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
						startSpringServer(args);
						getStartupFrame().setVisible(false);
					} else {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame, "Server already started!");
						frame.dispose();
						System.exit(0);
					}
					getStartupFrame().dispose();
				} catch (Exception e) {
					getConsoleFrame().setTitle("Phantom Database - Exception thrown!");
					setErrorOccured(true);
					if (!super.handleException(e)) e.printStackTrace();
				}
				if (!getConsoleFrame().isErrorOccured() && !isErrorOccured()
					&& !isDebugConsoleMode()) getConsoleFrame().setVisible(false);
				else if (isRunnableJarExecution()) getConsoleFrame().setVisible(true);
			}
		};

	}

}
