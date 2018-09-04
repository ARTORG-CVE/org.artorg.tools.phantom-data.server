package org.artorg.tools.phantomData.server.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.jboss.jandex.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ResourceReader {
	
	public static Properties readProperties(String path, Class<?> mainClass) {
		Properties properties = new Properties();
		try {
			properties.load(readResourceAsStream(path, mainClass));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static InputStream readResourceAsStream(String path, Class<?> mainClass) { 
		return mainClass.getClassLoader().getResourceAsStream(path);
	}
	
	public static URL readResource(String path, Class<?> mainClass) {
		return mainClass.getClassLoader().getResource(path);
	}
	
	public static BufferedImage readAsBufferedImage(String path, Class<?> mainClass) {
		try {
			return ImageIO.read(readResourceAsStream(path, mainClass));
		} catch (IOException e) {}
		throw new IllegalArgumentException();
	}
	
	public static File readAsFile(String path, Class<?> mainClass) {
		try {
			File file = File.createTempFile("model", "stl");
			InputStream inputStream = readResourceAsStream(path, mainClass);
			FileUtils.copyInputStreamToFile(inputStream, file);
			return file;
		} catch (IOException e1) {};
		throw new IllegalArgumentException();
	}
	
	public static <T> T loadFXML(String path, Object controller) {
		FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource(path));
		loader.setController(controller);
		try {
			return loader.<T>load();
		} catch (IOException e) {}
		throw new IllegalArgumentException();
	}
	
//	public static <T> T loadFXML(String path, Object controller, Class<?> mainClass) {
//		FXMLLoader loader = new FXMLLoader(readResource(path, mainClass));
//		loader.setController(controller);
//		try {
//			return loader.<T>load();
//		} catch (IOException e) {}
//		throw new IllegalArgumentException();
//	}
	
	public static AnchorPane loadFXMLanchorPane(String path, Object controller, Class<?> mainClass) {
		FXMLLoader loader = new FXMLLoader(readResource(path, Main.class));
		loader.setController(controller);
		try {
			return loader.load();
		} catch (IOException e) {}
		throw new IllegalArgumentException();
	}
	
	public static String readCSSstylesheet(String path, Class<?> mainClass) {
		return readResource(path, mainClass).toExternalForm();
	}
	
	
	
}
