package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class AddNewTeacher extends Panel {
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
		for (int i = 0; i < labelslength; i++) {
		    Label l = new Label(labels[i]);
		    l.setAlignment(Label.LEFT);
		    p.add(l);
		    TextField textField = new TextField(10);
		    p.add(textField);
		}
		JButton button = new JButton("Lehrer Hinzufügen");
		p.add(button);
	    add(p,BorderLayout.NORTH);
		
	}
	
	public static void main(String[] args) {
		AddNewTeacher a = new AddNewTeacher();
		JFrame f = new JFrame("neue Lehrer hinzufügen");
		f.add(a, BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	

}
