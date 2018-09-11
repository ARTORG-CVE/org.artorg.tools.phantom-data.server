package org.artorg.tools.phantomData.server.boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ConsoleDiverter {
	private final PrintStream SYSTEM_OUT = System.out;
	private final PrintStream SYSTEM_ERR = System.err;
	private List<BiConsumer<List<String>, String>> consoleConsumersOut;
	private List<BiConsumer<List<String>, String>> consoleConsumersErr;
	private String text;
	
	{
		consoleConsumersOut = new ArrayList<BiConsumer<List<String>, String>>();
		consoleConsumersErr = new ArrayList<BiConsumer<List<String>, String>>();
		text = "";
	}
	
	public PrintStream addOutPrintStream(BiConsumer<List<String>, String> consumer) {
		return addPrintStream(SYSTEM_OUT, consumer);
	}
	
	@SuppressWarnings("unchecked")
	public PrintStream addOutPrintStream(BiConsumer<List<String>, String>... consumers) {
		return addPrintStream(SYSTEM_OUT, consumers);
	}
	
	public PrintStream addErrPrintStream(BiConsumer<List<String>, String> consumer) {
		return addPrintStream(SYSTEM_ERR, consumer);
	}
	
	@SuppressWarnings("unchecked")
	public PrintStream addErrPrintStream(BiConsumer<List<String>, String>... consumers) {
		return addPrintStream(SYSTEM_ERR, consumers);
	}
	
	private PrintStream addPrintStream(PrintStream printStream, BiConsumer<List<String>, String> consumer) {
		List<BiConsumer<List<String>, String>> consumers = new ArrayList<BiConsumer<List<String>, String>>();
		consumers.add(consumer);
		return createPrintStream(printStream, consumers);
	}
	
	@SuppressWarnings("unchecked")
	private PrintStream addPrintStream(PrintStream printStream, BiConsumer<List<String>, String>... consumers) {
		return createPrintStream(printStream, Arrays.asList(consumers));
	}
	
	private PrintStream createPrintStream(PrintStream printStream, List<BiConsumer<List<String>, String>> consumers) {
		getPreviousConsumers(printStream).addAll(consumers);
		return new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				char c = (char) b;
				text = text + String.valueOf(c);
				if (text.endsWith("\n")) {
					List<String> consoleLines = lineSplitter(text);	
					String newLine = consoleLines.get(consoleLines.size()-1);
					printStream.println(newLine);
					while (consoleLines.size() > 2000 )
						consoleLines.remove(0);
					text = consoleLines.stream()
							.collect(Collectors.joining("\n")) +"\n";
					
					for (BiConsumer<List<String>, String> consumer: getPreviousConsumers(printStream))
						consumer.accept(consoleLines, newLine);
				}
			}
		});
	}
	
	private List<BiConsumer<List<String>, String>> getPreviousConsumers(PrintStream printStream) {
		if (printStream == SYSTEM_OUT)
			return consoleConsumersOut;
		if (printStream == SYSTEM_ERR)
			return consoleConsumersErr;
		throw new IllegalArgumentException();
	}
	
	private List<String> lineSplitter(String s) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new StringReader(s));
		reader.lines().forEach(line -> list.add(line));

		return list;
	}
	
}
