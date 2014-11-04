package gui;

import java.awt.Color;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("GUI-Prototype");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelRot = new JPanel();
        JPanel panelBlue = new JPanel();
        JPanel panelGreen = new JPanel();
        
        panelRot.setBackground(Color.RED);
        panelBlue.setBackground(Color.BLUE);
        panelGreen.setBackground(Color.GREEN);
		
		JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP);
		
		tabpane.addTab("Ich bin rot", panelRot);
        tabpane.addTab("Ich bin blau", panelBlue);
        tabpane.addTab("Ich bin grün", panelGreen);
		
        add(tabpane);
        
		setVisible(true);
	}
	
	public static void main(final String[] args) {
		final MainFrame mainFrame = new MainFrame();
	}
}
