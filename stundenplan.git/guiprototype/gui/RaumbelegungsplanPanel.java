package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class RaumbelegungsplanPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201806456859333146L;
	public DataPanel data = new DataPanel();
	private JList<String> liste;

	private static SchoolclassListModel schoolclassListModel = new SchoolclassListModel();
	private JTable table;
	
	public RaumbelegungsplanPanel(){
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

		menu = new JMenu("Raeume");
		menuBar.add(menu);
		menuBar.setLayout(new GridLayout(0,1));
		add(menuBar, c);
		
		String[] columnNames = {
				"Uhrzeit", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"
		};
		String [][] rowData ={ {"8-9","1a VID","1a HUE","4b BEL","3b NGY","2c SCH"}, {"9-10","","","4b WAR","",""}, {"10-11","","","1b GOR","",""}, {"11-12","","3c SCH","","",""}, {"12-13","","","","",""}, {"13-14","","","","",""},{"14-15","","","","",""},{"16-17","","","","",""}, {"17-18","","","","",""}};
		table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(40);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.7;
		c.weighty = 1.0;
		c.gridx =1;
		c.gridy = 0;
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(500,350));
		add(pane,c);
		
	}
}
