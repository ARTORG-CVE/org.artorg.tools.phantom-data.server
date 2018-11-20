package org.artorg.tools.phantomData.server.util;

import static huma.io.IOutil.readResource;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxUtil {
	
	
	public static Scene createScene(Parent root) {
		Scene scene = new Scene(root);
		addCss(scene);
		return scene;
	}
	
	public static Task<Void> createTask(Runnable rc, Consumer<Exception> exceptionHandler) {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					rc.run();
				} catch (Exception e) {
					exceptionHandler.accept(e);
				}
				return null;
			}
		};
	}
	
	public static void addCss(Scene scene) {
		scene.getStylesheets().add(readCSSstylesheet("css/application.css"));
	}
	
	public static void addIcon(Stage stage) {
		stage.getIcons().add( new Image("img/startup.png"));
	}
	
	public static String readCSSstylesheet(String path) {
		return readResource(path).toExternalForm();
	}
	
	public static <T> T loadFXML(String path, Object controller, Class<?> mainFxClass) {
		FXMLLoader loader = new FXMLLoader(mainFxClass.getClassLoader().getResource(path));
		loader.setController(controller);
		try {
			return loader.<T>load();
		} catch (IOException e) {
		}
		throw new IllegalArgumentException("path: " + path);
	}
	
	public static void addToPane(Pane parentPane, Node child) {
		parentPane.getChildren().add(child);
		setAnchorZero(child);
	}

	public static void setAnchorZero(Node node) {
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
	}
	
	public static void addMouseMovingSupport(Stage stage, Parent parent) {
		final double[] frameLocationOffset = new double[] {0.0,0.0};
		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				frameLocationOffset[0] = event.getSceneX();
				frameLocationOffset[1] = event.getSceneY();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() - frameLocationOffset[0]);
				stage.setY(event.getScreenY() - frameLocationOffset[1]);
			}
		});
	}

}
