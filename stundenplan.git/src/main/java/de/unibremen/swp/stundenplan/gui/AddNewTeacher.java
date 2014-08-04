package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class AddNewTeacher extends Panel {
	
	String name;
	String acro;
	String time;
	
	
	
	
	TextField nameField;
	TextField acroField;
	TextField timeField;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewTeacher(){
		
		setLayout(new BorderLayout());
		String[] labels = {"Name: ", "Akronym: ", "Max. Arbeitstunden:"};
		
		int labelslength = labels.length;
		Panel p = new Panel();
		p.setLayout(new GridLayout(4,2));
		
		Label l = new Label(labels[0]);
		    l.setAlignment(Label.LEFT);
		    p.add(l);
		nameField = new TextField(20);
	    p.add(nameField);
	    
	    Label l2 = new Label(labels[1]);
	    l2.setAlignment(Label.LEFT);
	    p.add(l2);
		acroField = new TextField(4);
	    p.add(acroField);
	    
	    Label l3 = new Label(labels[2]);
	    l3.setAlignment(Label.LEFT);
	    p.add(l3);
		timeField = new TextField(2);
	    p.add(timeField);
	    
		JButton button = new JButton("Lehrer Hinzufügen");
		p.add(button);
	    add(p,BorderLayout.NORTH);
	    
	    buttonOkay(button);

	}
	
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				name = nameField.getText();
				acro = acroField.getText();
				time = timeField.getText();
				nameField.setText("");
				acroField.setText("");
				timeField.setText("");
				
				System.out.println(name +" " + acro + " "+ time);
				
				StartFrame.l.dispose();
			}
		});
	}
	

}
