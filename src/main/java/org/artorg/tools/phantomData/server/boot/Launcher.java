package org.artorg.tools.phantomData.server.boot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.artorg.tools.phantomData.server.io.ResourceReader;

public class Launcher extends org.artorg.tools.phantomData.server.boot.BootUtils {
	private static PrintStream SYSTEM_OUT = System.out;
	private static PrintStream SYSTEM_ERR = System.err;
	private String text = "";
	private  JFrame consoleFrame;
	private JFrame startupFrame;
	private JProgressBar progressBar;
	private JTextPane textArea;
	private double progress;
	private int nLines;
	private JLabel progressLabel;
	private List<BiConsumer<List<String>, String>> consoleConsumersOut;
	private List<BiConsumer<List<String>, String>> consoleConsumersErr;
	private boolean errorOccured;
	private boolean showStartupConsole;

	public boolean isShowStartupConsole() {
		return showStartupConsole;
	}

	public void setShowStartupConsole(boolean showStartupConsole) {
		this.showStartupConsole = showStartupConsole;
	}

	{
		startupFrame = new JFrame();
		consoleFrame = new JFrame();
		textArea = new JTextPane();
		progressBar = new JProgressBar();
		progressLabel = new JLabel();
		text = "";
		progress = 0.0;
		consoleConsumersOut = new ArrayList<BiConsumer<List<String>, String>>();
		consoleConsumersErr = new ArrayList<BiConsumer<List<String>, String>>();
	}
	
	public boolean launch(int nConsoleLines, Runnable rc) {
		this.nLines = nConsoleLines - 1;
		try {
			createConsoleFrame();
			if (showStartupConsole)
				consoleFrame.setVisible(true);
			
			
			if (!isConnected())
				createAndshowStartupFrame();
			
			rc.run();
			startupFrame.setVisible(false);
			startupFrame.dispose();

		} catch (Exception e) {
			consoleFrame.setTitle("Phantom Database - Exception thrown!");
			consoleFrame.setVisible(true);
			e.printStackTrace();
		}
		
		if (!errorOccured)
			consoleFrame.setVisible(false);

		return false;
	}
	
	private void createConsoleFrame() {
		consoleFrame.setSize(1200, 440);
		consoleFrame.setTitle("Phantom Database - Console Output");
		
		textArea.setEditable(false);
		Font font = new Font("monospaced", Font.PLAIN, 12);
		textArea.setFont(font);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JPanel noWrapPanel = new JPanel(new BorderLayout());
		noWrapPanel.add(textArea);
		JScrollPane scrollV = new JScrollPane(noWrapPanel);
		
		consoleFrame.add(scrollV);
		alignFrame(consoleFrame);
		addConsoleOutputPrintStream();
	}
	
	private void createAndshowStartupFrame() {
		startupFrame.setTitle("Phantom Database");
		startupFrame.setResizable(false);
		startupFrame.setUndecorated(true);
		startupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = startupFrame.getContentPane();
		content.setLayout(new BorderLayout());
		
		JLabel closeLabel = new JLabel("x"); 
		closeLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		closeLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {System.exit(0);}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(closeLabel);
		content.add(buttonPane, BorderLayout.NORTH);
		
		BufferedImage phantomImage = ResourceReader.readAsBufferedImage("img/startup.png");
		JLabel phantomLabel = new JLabel(new ImageIcon(phantomImage));
		BufferedImage artortgLogoImage = ResourceReader.readAsBufferedImage("img/artorgLogo.png");
		JLabel artortgLogoLabel = new JLabel(new ImageIcon(artortgLogoImage));
		BufferedImage inselLogoImage = ResourceReader.readAsBufferedImage("img/inselLogo.png");
		JLabel inselLogoLabel = new JLabel(new ImageIcon(inselLogoImage));
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.LINE_AXIS));
		imagePanel.add(phantomLabel);
		imagePanel.add(Box.createRigidArea(new Dimension(20,0)));
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.PAGE_AXIS));
		logoPanel.add(artortgLogoLabel);
		logoPanel.add(Box.createRigidArea(new Dimension(0,50)));
		logoPanel.add(inselLogoLabel);
		imagePanel.add(logoPanel);
		imagePanel.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
		content.add(imagePanel, BorderLayout.CENTER);

		JPanel progressPanel = new JPanel();
		progressPanel.setLayout(new BorderLayout());
		progressLabel.setText("Launching Application...");
		progressLabel.setFont(new Font("monospaced", Font.PLAIN, 11));
		progressLabel.setBorder(new EmptyBorder(new Insets(3, 10, 3, 0)));
		progressBar.setValue(0);
		progressBar.setForeground(Color.green);
		progressPanel.add(progressLabel, BorderLayout.WEST);
		progressPanel.add(progressBar, BorderLayout.PAGE_END);
		content.add(progressPanel, BorderLayout.PAGE_END);
		
		startupFrame.pack();
		alignFrame(startupFrame);
		addProgressPrintStream();
		startupFrame.setVisible(true);
	}
	

	private void addProgressPrintStream() {
		System.setOut(createPrintStream(SYSTEM_OUT, this::updateStartupProgress));
	}
	
	@SuppressWarnings("unchecked")
	private void addConsoleOutputPrintStream() {
		System.setOut(createPrintStream(SYSTEM_OUT, this::updateStartupProgress, 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneOut, newLine)));
		System.setErr(createPrintStream(SYSTEM_ERR, this::updateStartupProgress, 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneErr, newLine),
				(consoleLines, newLine) -> errorOccured = true));
	}
	
	private void updateStartupProgress(List<String> consoleLines, String newLine) {
		Pattern pattern = Pattern.compile("[^:]: (.*)");
		
		progress = progress + 100.0 / nLines;
		progressBar.setValue((int) progress);
		
		if (consoleLines.size() > 0) {
			if (consoleLines.size() < 9)
				progressLabel.setText("Launching Spring Boot...");
			
			Matcher m = pattern.matcher(newLine);
			if (m.find())
				progressLabel.setText(m.group(1));
		}
	}
	
	private void updateTextArea(BiConsumer<JTextPane, String> textWriter, String newLine) {
		textWriter.accept(textArea, newLine +"\n");
	}
	
	private PrintStream createPrintStream(PrintStream printStream, BiConsumer<List<String>, String> consumer) {
		List<BiConsumer<List<String>, String>> consumers = new ArrayList<BiConsumer<List<String>, String>>();
		return createPrintStream(printStream, consumers);
	}
	
	@SuppressWarnings("unchecked")
	private PrintStream createPrintStream(PrintStream printStream, BiConsumer<List<String>, String>... consumers) {
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
	
	
	
	private void appendToPaneOut(JTextPane tp, String msg) {
		appendToPane(tp, msg, Color.BLACK);
	}
	
	private void appendToPaneErr(JTextPane tp, String msg) {
		appendToPane(tp, msg, Color.RED);
    }
	
	private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        
        StyledDocument doc = tp.getStyledDocument();
        try {
			doc.insertString(doc.getLength(), msg, aset);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }

	private List<String> lineSplitter(String s) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new StringReader(s));
		reader.lines().forEach(line -> list.add(line));

		return list;
	}

	private void alignFrame(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

}
