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

import huma.io.ConsoleDiverter;

public class SwingConsoleFrame extends ConsoleFrame {
	private final JTextPane textArea;
	private JFrame frame;
	
	{
		frame = new JFrame();
		textArea = new JTextPane();
		frame.setSize(1200, 440);
		setTitle("Phantom Database - Console Output");
		
		textArea.setEditable(false);
		Font font = new Font("monospaced", Font.PLAIN, 12);
		textArea.setFont(font);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JPanel noWrapPanel = new JPanel(new BorderLayout());
		noWrapPanel.add(textArea);
		JScrollPane scrollV = new JScrollPane(noWrapPanel);
		
		frame.add(scrollV);
		alignFrame(frame);
	}
	
	private void alignFrame(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
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

	@SuppressWarnings("unchecked")
	@Override
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		super.setConsoleDiverter(consoleDiverter);
		System.setOut(consoleDiverter.addOutPrintStream( 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneOut, newLine)));
		System.setErr(consoleDiverter.addErrPrintStream( 
				(consoleLines, newLine) -> updateTextArea(this::appendToPaneErr, newLine),
				(consoleLines, newLine) -> setErrorOccured(true)));
	}

	@Override
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	@Override
	public JFrame getGraphic() {
		return frame;
	}

}
