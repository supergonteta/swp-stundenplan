package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class StundenplanPanel extends JPanel {

	public DataPanel data = new DataPanel();
	private JList<String> liste;

	private static SchoolclassListModel schoolclassListModel = new SchoolclassListModel();
	private JTable table;
	
	public StundenplanPanel(){
		init();
	}
	
	public void init(){
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx=0;
		c.gridy=0;
		liste = new JList<>(schoolclassListModel);
		liste.setLayoutOrientation(JList.VERTICAL);
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(liste);
		JScrollPane listScroller = new JScrollPane(liste);
		listScroller.setPreferredSize(new Dimension(100, 250));
		add(listScroller,c);
		String[] columnNames = {
				" Uhrzeiten", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"
		};
		String [][] rowData ={ {"8-9","","","","",""}, {"9-10","","","","",""}, {"10-11","","","","",""}, {"11-12","","","","",""}, {"12-13","","","","",""}, {"13-14","","","","",""},{"14-15","","","","",""},{"16-17","","","","",""}, {"17-18","","","","",""}};
		table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx =1;
		c.gridy = 0;
		add(new JScrollPane(table),c);
		
	}
	
}
