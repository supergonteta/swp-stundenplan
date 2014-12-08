package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;

public class WochenplanPanel extends JPanel {
	
	
	public WochenplanPanel() {
		init();
	}
	
	public void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.3;
		c.weighty = 1.0;
		c.gridx=0;
		c.gridy=0;
		
		JMenuBar menuBar;
		JMenu menu;
		
		menuBar = new JMenuBar();

		menu = new JMenu("Personal");
		menuBar.add(menu);
		menuBar.setLayout(new GridLayout(0,1));
		add(menuBar, c);
		
		JButton montag = new JButton("Montag");
		JButton dienstag = new JButton("Dienstag");
		JButton mittwoch = new JButton("Mittwoch");
		JButton donnerstag = new JButton("Donnerstag");
		JButton freitag = new JButton("Freitag");
		JButton samstag = new JButton("Samstag");
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx=1;
		add(montag,c);
		c.gridx=2;
		add(dienstag,c);
		c.gridx=3;
		add(mittwoch,c);
		c.gridx=4;
		add(donnerstag,c);
		c.gridx=5;
		add(freitag,c);
		c.gridx=6;
		add(samstag,c);
		
	}

}
