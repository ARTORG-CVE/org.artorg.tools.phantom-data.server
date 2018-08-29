package org.artorg.tools.phantomData.server;

import static org.artorg.tools.phantomData.server.boot.BootUtils.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
	private static String text = "";

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		if (!isConnected()) {
			frame.setSize(1200, 440);
			frame.setTitle("Phantom Data Server");
//			frame.setResizable(false);
//			frame.setUndecorated(true);			
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
			DefaultCaret caret = (DefaultCaret)textArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			JScrollPane scrollV = new JScrollPane(textArea);
			scrollV.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollV.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			frame.add(scrollV);
//			frame.add(textArea);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		    frame.setLocation(x, y);
	    	frame.setVisible(true);
			
			System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
			  text = text +String.valueOf((char) b);
			  if (text.endsWith("\n") || text.endsWith("\r")) {
				  List<String> lines = lineSplitter(text);
				  
				  int start = lines.size() - 2000;
						if (start < 0)
							start = 0;
	
						final String output2 = lines.subList(start, lines.size()).stream()
								.collect(Collectors.joining("\n"));
						textArea.setText(output2);
					}
				}
			}));
			System.setErr(new PrintStream(new OutputStream() {
				@Override
				public void write(int b) throws IOException {
				  text = text +String.valueOf((char) b);
				  if (text.endsWith("\n") || text.endsWith("\r")) {
					  List<String> lines = lineSplitter(text);
					  
					  int start = lines.size() - 2000;
							if (start < 0)
								start = 0;
		
							final String output2 = lines.subList(start, lines.size()).stream()
									.collect(Collectors.joining("\n"));
							textArea.setText(output2);
						}
					}
				}));
			
    	}
		
//		new Thread(() -> {
//			while(!isConnected()) {
//				try {
//					Thread.sleep(1000);
//					
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			frame.setVisible(false);
//			frame.dispose();
//			Thread.currentThread().interrupt();
//		}).start();
		
		try {
			prepareFileStructure();
			logInfos();
			startingServer(args);
			frame.setVisible(false);
			frame.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<String> lineSplitter(String s) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new StringReader(s));
		reader.lines().forEach(line -> list.add(line));
		if (s.endsWith("\r") || s.endsWith("\n")) list.add("");
		
		return list;
    }

}
