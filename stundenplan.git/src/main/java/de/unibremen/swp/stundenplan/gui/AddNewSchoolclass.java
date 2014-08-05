package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;


public class AddNewSchoolclass extends Panel {
	
	String name;

	

	TextField nameField;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewSchoolclass(){
		
		setLayout(new BorderLayout());
		Panel p = new Panel();
		p.setLayout(new GridLayout(4,2));
		
		Label l = new Label("Klasse: ");
		    l.setAlignment(Label.LEFT);
		    p.add(l);
		nameField = new TextField(20);
	    p.add(nameField);
	   
	    
	    
	   
	    
		JButton button = new JButton("Klasse Hinzufügen");
		p.add(button);
	    add(p,BorderLayout.NORTH);
	    
	    buttonOkay(button);
		
	}
	
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				name = nameField.getText();
				Schoolclass c = new Schoolclass();
				c.setName(name);
				try {
					Data.addSchoolclass(c);
				} catch (DatasetException e) {
					System.out.println("Fehler addTeacher");
				}
				
				
				System.out.println(name);
				nameField.setText("");
				StartFrame.c.dispose();
			}
		});
	}
	

}
