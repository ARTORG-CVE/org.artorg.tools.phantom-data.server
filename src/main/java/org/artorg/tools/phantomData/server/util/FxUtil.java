package org.artorg.tools.phantomData.server.util;

import static huma.io.IOutil.readResource;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FxUtil {
	
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

}
