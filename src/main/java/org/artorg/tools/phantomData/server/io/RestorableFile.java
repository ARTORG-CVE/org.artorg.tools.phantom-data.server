package org.artorg.tools.phantomData.server.io;

//import static org.artorg.tools.phantomData.server.io.IOutil.addExternalDirectoryToClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RestorableFile {
	private String externalPath;
	private final List<Consumer<InputStream>> reader;
	private final List<Consumer<OutputStream>> writer;
	
	public RestorableFile() {
		this.reader = new ArrayList<Consumer<InputStream>>();
		this.writer = new ArrayList<Consumer<OutputStream>>();
	}
	
	public void readExternal() throws IOException {
		readExternal(createExternalInputStream());
	}
	
	public void readExternal(String externalPath) throws IOException {
		readExternal(createExternalInputStream(externalPath), externalPath);
	}
	
	private void readExternal(InputStream inputStream) throws IOException {
		readExternal(inputStream, getExternalPath());
	}
	
	private void readExternal(InputStream inputStream, String externalPath) throws IOException {
		if (!existExternal(externalPath)) createExternal(externalPath);
		read(inputStream, externalPath);
	}
	
	private void read(InputStream inputStream, String path) throws IOException {
		reader.forEach(consumer -> consumer.accept(inputStream));
		inputStream.close();
	}
	
	public void writeExternal() throws IOException {
		writeExternal(createExternalOutputStream(), getExternalPath());
	}
	
	public void writeExternal(String externalPath) throws IOException {
		writeExternal(createExternalOutputStream(externalPath), externalPath);
	}
	
	private void writeExternal(OutputStream outputStream, String externalPath) throws IOException {
		if (!existExternal(externalPath)) createExternal(externalPath);
		writer.forEach(consumer -> consumer.accept(outputStream));
		outputStream.close();
	}
	
	public void restoreExternal() throws Exception {
		deleteExternal();
		writeExternal();
	}
	
	public void createExternal() throws IOException {
		createExternal(getExternalPath());
	}
	
	public void createExternal(String externalPath) throws IOException {
		if (existExternal(externalPath)) 
			return;
		File file = new File(externalPath);
		File parent = file.getParentFile();
		if (!parent.exists() && !parent.mkdirs())
		    throw new IllegalStateException("Couldn't create dir: " + parent);
		file.createNewFile();
	}
	
	public void deleteExternal() throws IOException {
		deleteExternal(getExternalPath());
	}
	
	private void deleteExternal(String externalPath) throws IOException {
		if (!existExternal(externalPath))
			return;
		File file = new File(externalPath);
		if (!file.delete())
			throw new IOException("File couldn't delete.");
	}
	
	public InputStream createExternalInputStream() throws FileNotFoundException {
		return IOutil.readExternalFile(getExternalPath());
	}
	
	public InputStream createExternalInputStream(String externalPath) throws FileNotFoundException {
		return IOutil.readExternalFile(externalPath);
	}
	
	public OutputStream createExternalOutputStream() throws IOException {
		return createExternalOutputStream(getExternalPath());
	}
	
	private OutputStream createExternalOutputStream(String path) throws IOException {
		return new FileOutputStream(new File(path), false);
	}
	
	public boolean existExternal() {
		return existExternal(getExternalPath());
	}
	
	private boolean existExternal(String externalPath) {
		return new File(externalPath).exists();
	}
	
	protected boolean addReadConsumer(Consumer<InputStream> readConsumer) {
		return reader.add(readConsumer);
	}
	
	protected boolean addWriteConsumer(Consumer<OutputStream> writeConsumer) {
		return writer.add(writeConsumer);
	}

	public String getExternalPath() {
		if (externalPath == null) throw new NullPointerException();
		return externalPath;
	}

	public void setExternalPath(String externalPath) {
		this.externalPath = externalPath.replace("\\", "/");
	}

}
