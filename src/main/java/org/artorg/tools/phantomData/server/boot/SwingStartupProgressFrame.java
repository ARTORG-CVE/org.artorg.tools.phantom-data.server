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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import huma.io.ConsoleDiverter;
import huma.io.IOutil;

public class SwingStartupProgressFrame extends StartupProgressFrame {
	private final JProgressBar progressBar;
	private final JLabel progressLabel;
	private JFrame frame;
	
	{
		frame = new JFrame();
		progressBar = new JProgressBar();
		progressLabel = new JLabel();
		
		frame.setTitle("Phantom Database");
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = frame.getContentPane();
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
		
		BufferedImage phantomImage = IOutil.readResourceAsBuffered("img/startup.png");
		JLabel phantomLabel = new JLabel(new ImageIcon(phantomImage));
		BufferedImage artortgLogoImage = IOutil.readResourceAsBuffered("img/artorgLogo.png");
		JLabel artortgLogoLabel = new JLabel(new ImageIcon(artortgLogoImage));
		BufferedImage inselLogoImage = IOutil.readResourceAsBuffered("img/inselLogo.png");
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
		
		frame.pack();
		alignFrame(frame);
	}

	private void alignFrame(JFrame frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
	
	private void updateStartupProgress(List<String> consoleLines, String newLine) {
		if (isProgressing()) {
			Pattern pattern = Pattern.compile("[^:]: (.*)");
			
			setProgress(getProgress() + 100.0 / getnConsoleLines());
			progressBar.setValue((int) getProgress());
			
			if (consoleLines.size() > 0) {
				if (consoleLines.size() < 9)
					progressLabel.setText("Launching Spring Boot...");
				
				Matcher m = pattern.matcher(newLine);
				if (m.find())
					progressLabel.setText(m.group(1));
			}
		}
	}
	
	@Override
	public void setConsoleDiverter(ConsoleDiverter consoleDiverter) {
		super.setConsoleDiverter(consoleDiverter);
		System.setOut(consoleDiverter.addOutPrintStream(this::updateStartupProgress));
		
	}
	
	// Getters & Setters
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JLabel getProgressLabel() {
		return progressLabel;
	}

	@Override
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	@Override
	public JFrame getGraphic() {
		return frame;
	}

	@Override
	public void dispose() {
		frame.dispose();
	}

	@Override
	public void setTitle(String title) {
		frame.setTitle(title);
		
	}

}
