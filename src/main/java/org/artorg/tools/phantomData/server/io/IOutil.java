package org.artorg.tools.phantomData.server.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class IOutil {
//	@SuppressWarnings("deprecation")
//	public static void addExternalDirectoryToClassPath(String path) throws Exception {
//		addExternalDirectoryToClassPath(new File(path).toURL());
//	}
//	
//	public static void addExternalDirectoryToClassPath(URL url) throws Exception {
//		URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
//		Method method = URLClassLoader.class.getDeclaredMethod("addURL",
//				new Class[] { URL.class });
//		method.setAccessible(true);
//		method.invoke(classLoader, new Object[] { url });
//	}

	public static UnicodeProperties readProperties(String path) throws IOException {
		UnicodeProperties properties = new UnicodeProperties();
		properties.load(readResourceAsStream(path));
		return properties;
	}

	public static InputStream readExternalFile(String path) throws FileNotFoundException {
		File file = new File(path);
		return new FileInputStream(file);
	}

	public static InputStream readResourceAsStream(String path) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (inputStream == null) throw new IllegalArgumentException("path: " +path);
		return inputStream;
	}

	public static URL readResource(String path) {
		return Thread.currentThread().getContextClassLoader().getResource(path);
	}

	public static BufferedImage readResourceAsBuffered(String path) {
		try {
			return ImageIO.read(readResourceAsStream(path));
		} catch (IOException e) {
		}
		throw new IllegalArgumentException();
	}

	public static File readResourceAsFile(String path) {
		try {
			File file = File.createTempFile("model", "stl");
			InputStream inputStream = readResourceAsStream(path);
			FileUtils.copyInputStreamToFile(inputStream, file);
			return file;
		} catch (IOException e1) {
		}
		;
		throw new IllegalArgumentException();
	}

}
