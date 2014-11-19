package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SchoolclassManager;


public class AddNewRoom extends JPanel {
	
	private Label lName = new Label("Name des Raumes: ");
	private Label lPos = new Label("In welchem Geb‰ude: ");

	public TextField nameField = new TextField(20);
	public TextField positionField = new TextField(20);
	
	public String name;
	
	public JButton button = new JButton("Raum Hinzuf√ºgen");
	
	private GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewRoom(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Neuen Raum hinzufuegen"));
		c.insets=new Insets(1,1,1,1);
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=0;
		add(lName,c);
		c.gridx=1;
	    add(nameField,c);  
	    c.gridx=0;
		c.gridy=1;
	    add(lPos,c);
	    c.gridx=1;
	    add(positionField,c);   
	    c.gridx=0;
	    c.gridy=2;
	    c.gridwidth=2;
	    c.fill=GridBagConstraints.HORIZONTAL;
		add(button,c);    
	    buttonOkay(button);	
	}
	
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				name = nameField.getText();
//				Room c = new Room();
//				c.setName(name);
//				try {
//					RoomManager.addRoom(name);
//					Stundenplan.pList.dispose();
//					Stundenplan.pList = new PlanList();
//				} catch (DatasetException e) {
//					System.out.println("Fehler addRoom");
//				}
//				
//				StartFrame.updateRoomList();
//				
//				System.out.println(name);
//				nameField.setText("");
//				StartFrame.c.dispose();
			}
		});
	}
	

}
