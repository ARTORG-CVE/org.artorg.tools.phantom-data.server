package org.artorg.tools.phantomData.server.boot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.function.BiConsumer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class SwingConsoleFrame extends JFrame{
	private static final long serialVersionUID = 3224705735339280900L;
	private final JTextPane textArea;
	private final ConsoleDiverter consoleDiverter;
	private boolean errorOccured;
	
	{
		textArea = new JTextPane();
	}
	
	public SwingConsoleFrame(ConsoleDiverter consoleDiverter) {
		this.consoleDiverter = consoleDiverter;
		createConsoleFrame();
	}
	
	private void createConsoleFrame() {
		setSize(1200, 440);
		setTitle("Phantom Database - Console Output");
		
		textArea.setEditable(false);
		Font font = new Font("monospaced", Font.PLAIN, 12);
		textArea.setFont(font);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JPanel noWrapPanel = new JPanel(new BorderLayout());
		noWrapPanel.add(textArea);
		JScrollPane scrollV = new JScrollPane(noWrapPanel);
		
		add(scrollV);
		alignFrame(this);
		addConsoleOutputPrintStream();
	}
	
	private void alignFrame(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	@SuppressWarnings("unchecked")
	private void addConsoleOutputPrintStream() {
		System.setOut(consoleDiverter.addOutPrintStream( 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneOut, newLine)));
		System.setErr(consoleDiverter.addErrPrintStream( 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneErr, newLine),
				(consoleLines, newLine) -> errorOccured = true));
	}
	
	private void updateTextArea(BiConsumer<JTextPane, String> textWriter, String newLine) {
		textWriter.accept(textArea, newLine +"\n");
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
	
	public boolean isErrorOccured() {
		return errorOccured;
	}

}
