package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainFrame extends JFrame{
	
	private MenuBar menu = new MenuBar(this);
	
	private JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP);
	
	private DataPanel dataPanel = new DataPanel();
    private StundenplanPanel paneStundenplan = new StundenplanPanel();
    private JPanel paneLehrer = new LehreransichtPanel(); 
    
	public MainFrame() {
		super("GUI-Prototype");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        pack();
		setVisible(true);
	}
	
	private void initComponents(){
		     
        tabpane.addTab("Daten", dataPanel);
        tabpane.addTab("Stundenpläne", paneStundenplan);
        tabpane.addTab("Lehreransicht", paneLehrer);
		
        setJMenuBar(menu);
        add(tabpane);
	};
	
	public static void main(String[] args){ 
        MainFrame main= new MainFrame();
    } 
}
