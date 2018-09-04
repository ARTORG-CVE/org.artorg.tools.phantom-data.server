package org.artorg.tools.phantomData.server.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RestorableCustomFile {
	private final String resourcePath;
	private final String externalPath;
	private final List<Consumer<InputStream>> reader;
	private final List<Consumer<OutputStream>> writer;
	private final Class<?> mainClass;
	
	public RestorableCustomFile(String resourcePath, String externalPath, Class<?> mainClass) {
		this.resourcePath = resourcePath;
		this.externalPath = externalPath;
		this.reader = new ArrayList<Consumer<InputStream>>();
		this.writer = new ArrayList<Consumer<OutputStream>>();
		this.mainClass = mainClass;
	}
	
	public void readResource() {
		read(createResourceInputStream());
	}
	
	public void readExternal() {
		read(createExternalInputStream());
	}
	
	private void read(InputStream inputStream) {
		reader.forEach(consumer -> consumer.accept(inputStream));
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeResource() throws IOException {
		writeExternal(createResourceOutputStream());
	}
	
	public void writeExternal() throws IOException {
		writeExternal(createExternalOutputStream());
	}

	private void writeExternal(OutputStream outputStream) throws IOException {
		writer.forEach(consumer -> consumer.accept(outputStream));
		outputStream.close();
	}
	
	public void restoreExternal() throws IOException {
		deleteExternal();
		readResource();
		writeExternal();
	}
	
	public void createExternal() throws IOException {
		if (existExternal()) 
			return;
		File file = new File(externalPath);
		File parent = file.getParentFile();
		if (!parent.exists() && !parent.mkdirs())
		    throw new IllegalStateException("Couldn't create dir: " + parent);
		file.createNewFile();
	}
	
	public void deleteExternal() throws IOException {
		if (!existExternal())
			return;
		File file = new File(externalPath);
		if (!file.delete())
			throw new IOException("File couldn't delete.");
	}
	
	public InputStream createResourceInputStream() {
		return ResourceReader.readResourceAsStream(resourcePath, mainClass);
	}
	
	public InputStream createExternalInputStream() {
		return ResourceReader.readResourceAsStream(externalPath, mainClass);
	}
	
	public OutputStream createResourceOutputStream() throws IOException {
		return createOutputStream(resourcePath);
	}
	
	public OutputStream createExternalOutputStream() throws IOException {
		return createOutputStream(externalPath);
	}
	
	private OutputStream createOutputStream(String path) throws IOException {
		return new FileOutputStream(new File(path), false);
	}
	
	public boolean existExternal() {
		return ResourceReader.readResourceAsStream(externalPath, mainClass) != null;
	}
	
	public boolean addReadConsumer(Consumer<InputStream> readConsumer) {
		return reader.add(readConsumer);
	}
	
	public boolean addWriteConsumer(Consumer<OutputStream> writeConsumer) {
		return writer.add(writeConsumer);
	}

}
