package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LehreransichtPanel extends JPanel {

	private JTable table;
	
	public LehreransichtPanel(){
		init();
	}

	
public void init(){
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx=0;
		c.gridy=0;
		
		String[] columnNames = {
				"", "", "Deu", "E", "MA", "SU/BGU", "KU"
		};
		String [][] rowData ={{"BER -4","28","3B 6","3B 2 \n 2C 1","-","-","1A/2C 2"}, {"BM -28","28","","","","",""}, {"DOR","10","2A 4","-","-","-","2B 6"}
		, {"VID","12","3B 6","-","-","-","4A 6"}
		, {"WAR","20","-","-","3A 10","-","1C 10"}
		, {"BER","8","-","2C 6","-","-","2C 2"}
		, {"HUE","20","1B 2","3B 8","2A 8","1D 2","-"}
		, {"NIE -28","28","","","","",""}
		, {"RUN -10","10","","","","",""}
		, {"ORD","-20","20","","","",""}};
		
		table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(40);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx =1;
		c.gridy = 0;
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(500,350));
		add(pane,c);
}
}
