package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class StundenplanPanel extends JPanel {

	public DataPanel data = new DataPanel();

	private static SchoolclassListModel schoolclassListModel = new SchoolclassListModel();
	private JTable table;
	private JFileChooser chooser = new JFileChooser();
	private JFrame f;
	
	public StundenplanPanel(){
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

		menu = new JMenu("Lehrer");
		menuBar.add(menu);
		
		menu = new JMenu("Klasse");
		menuBar.add(menu);
		
		menuBar.setLayout(new GridLayout(0,1));
		add(menuBar, c);
		
		String[] columnNames = {
				" Uhrzeiten", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"
		};
		String [][] rowData ={ {"8-9","","","","",""}, {"9-10","","","","",""}, {"10-11","","","","",""}, {"11-12","","","","",""}, {"12-13","","","","",""}, {"13-14","","","","",""},{"14-15","","","","",""},{"16-17","","","","",""}, {"17-18","","","","",""}};
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
		
		JButton pdf = new JButton("PDF");
		JButton cvs = new JButton("CVS");
		JButton text = new JButton("Text");
		
		c.anchor = GridBagConstraints.SOUTHWEST;
		c.gridx = 4;
		c.gridy= 1;
		add(new JLabel("Exportieren als:"),c);
		c.gridx=5;
		add(pdf,c);
		c.gridx=6;
		add(cvs,c);
		c.gridx=7;
		add(text,c);	
		
		buttonOkay(pdf);
		buttonOkay(cvs);
		buttonOkay(text);
	}
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showSaveDialog(f);
			}
		});
	}	
}
