package org.artorg.tools.phantomData.server;

import java.util.concurrent.Executors;

import org.artorg.tools.phantomData.server.boot.FxConsoleFrame;
import org.artorg.tools.phantomData.server.boot.FxStartupProgressController;
import org.artorg.tools.phantomData.server.boot.ServerBooter;
import org.artorg.tools.phantomData.server.boot.StartupProgressFrame;
import org.artorg.tools.phantomData.server.util.FxUtil;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DesktopFxBootServer extends Application {
	private String[] args;

	public DesktopFxBootServer() {}

	public static void main(String[] args) {
		new DesktopFxBootServer().boot(args);
	}

	public void boot(String[] args) {
		this.args = args;
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StartupProgressFrame startupFrame = new FxStartupProgressController(primaryStage);
		Parent parent = FxUtil.loadFXML("fxml/Boot.fxml", startupFrame, getClass());

		initStartupStage(primaryStage, parent);

		Platform.runLater(() -> {
			if (args == null) args = new String[] {};
			createBooter(startupFrame).catchedBoot(args);
		});
	}

	public static void initStartupStage(Stage stage, Parent parent) {
		Scene scene = FxUtil.createScene(parent);
		FxUtil.addIcon(stage);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Phantom Database");
		FxUtil.addMouseMovingSupport(stage, parent);
		stage.setResizable(false);
		stage.show();
		stage.requestFocus();
		stage.toFront();

		FxUtil.addMouseMovingSupport(stage, parent);
	}

	public static ServerBooter createBooter(StartupProgressFrame controller) {
		return new ServerBooter() {
			@Override
			protected void uncatchedBoot(String[] args) {
				initBeforeServerStart(BootApplication.class, new FxConsoleFrame(), controller);
				if (isDebugConsoleMode()) getConsoleFrame().setVisible(true);
				if (!isConnected()) {
					getStartupFrame().setnConsoleLines(39);
					getStartupFrame().setTitle("Phantom Database");
					getStartupFrame().setVisible(true);
					getStartupFrame().setProgressing(true);
					Task<Void> task = FxUtil.createTask(() -> startSpringServer(args),
							e -> handleException(e));
					task.setOnSucceeded(event -> finish());
					task.setOnFailed(event -> finish());
					task.setOnCancelled(event -> finish());
					Executors.newCachedThreadPool().execute(task);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Starting PhantomData Server");
					alert.setContentText("Server already started on port " +getPort());
					alert.showAndWait();
					System.exit(0);
				}
			}
		};
	}
}
