package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.gui.PlanList;
import de.unibremen.swp.stundenplan.logic.SchoolclassManager;


public class AddNewSchoolclass extends JPanel {
	
	private Label l = new Label("Name der Klasse: ");

	public TextField nameField = new TextField(20);
	
	public String name;
	
	public JButton button = new JButton("Klasse Hinzufügen");
	
	private GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewSchoolclass(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Neue Schulklasse hinzufügen"));
		c.insets=new Insets(1,1,1,1);
		c.anchor=GridBagConstraints.PAGE_START;
		c.gridx=0;
		c.gridy=0;
		add(l,c);
		c.gridx=1;
	    add(nameField,c);   
	    c.gridx=0;
		c.gridy=1;
		add(new Label("Klassenlehrer/In"),c);
		c.gridx=1;
		String[] s = {"VID","NGU","HUE"};
		add(new JComboBox(s),c);
	    c.anchor=GridBagConstraints.PAGE_START;
	    c.gridx=0;
	    c.gridy=2;
	    c.gridwidth=2;
	    c.fill=GridBagConstraints.HORIZONTAL;
		add(button,c);    
	    buttonOkay(button);
	    c.gridy=3;
	    c.gridx=0;
	    c.gridwidth=3;
	    c.anchor=GridBagConstraints.PAGE_END;
	    add(new Label("Stunden pro Woche"),c);
	    final DefaultListModel<String> dummyList = new DefaultListModel<String>();
	    for ( String ss : ("English		5h,Mathe		5h,		," +
	                      "			, 		,		,").split(",") )
	      dummyList.addElement( ss );
	    JList<String> list = new JList<String>( dummyList );
	    c.gridy=4;
	    c.gridx=0;
	    add(list,c);
	}
	
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				name = nameField.getText();
				Schoolclass c = new Schoolclass();
				c.setName(name);
				try {
					SchoolclassManager.addSchoolclass(name);
					Stundenplan.pList.dispose();
					Stundenplan.pList = new PlanList();
				} catch (DatasetException e) {
					System.out.println("Fehler addSchoolclass");
				}				
	//			StartFrame.updateSchoolclassList();				
				System.out.println(name);
				nameField.setText("");
	//			StartFrame.c.dispose();
			}
		});
	}
}
