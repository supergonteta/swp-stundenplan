package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("GUI-Prototype");
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		setSize(screen.width, screen.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDaten = new JPanel();
        JPanel panelStundenplan = new JPanel();
        JPanel panelLehrer = new JPanel();
        
        panelDaten.setBackground(Color.LIGHT_GRAY);
        panelStundenplan.setBackground(Color.LIGHT_GRAY);
        panelLehrer.setBackground(Color.LIGHT_GRAY);
		
		JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP);
		
		tabpane.addTab("Daten", panelDaten);
        tabpane.addTab("Stundenpläne", panelStundenplan);
        tabpane.addTab("Lehreransicht", panelLehrer);
		
        add(tabpane);
        
		setVisible(true);
	}
	
	public static void main(final String[] args) {
		final MainFrame mainFrame = new MainFrame();
	}
}
