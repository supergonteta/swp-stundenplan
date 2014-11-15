package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainFrame extends JFrame{
	//screenresolution kriegen
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP);
	
	private DataPanel dataPanel = new DataPanel();
    private StundenplanPanel paneStundenplan = new StundenplanPanel();
    private JPanel paneLehrer = new JPanel(); 
    
	public MainFrame() {
		super("GUI-Prototype");	
		//später wieder aktivieren
		//setSize(screen.width, screen.height);
		//setSize(800, 500); 
		//setSize soll laut stackoverflowkommentar zu problemen führen, 
		//lieber pack() benutzen oder componenten vergrößern
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        pack();
		setVisible(true);
	}
	
	private void initComponents(){
		
        paneStundenplan.setBackground(Color.LIGHT_GRAY);
        paneLehrer.setBackground(Color.LIGHT_GRAY);
        
        tabpane.addTab("Daten", dataPanel);
        tabpane.addTab("Stundenpläne", paneStundenplan);
        tabpane.addTab("Lehreransicht", paneLehrer);
		
        add(tabpane);
	};
	
	public static void main(String[] args){ 
        MainFrame main= new MainFrame();
    } 
}
