package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TeacherManager;

public class AddNewTeacher extends Panel {
	
	String name;
	String acro;
	String time;
	
	
	public static PlanList planList;
	
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
				Integer timer = new Integer(time);
				
				 if(timer >40 || timer < 4) {
		            	JFrame jFrame = new JFrame();
		            	jFrame.setLocation(500, 500);
		            	
		            	jFrame.setVisible(false);
		            	
		            	JOptionPane.showMessageDialog(jFrame, "Die Arbeitszeit muss zwischen 4 und 40 Stunden liegen" );
		            	return;
		            }
				
				
				
				try {
					TeacherManager.addTeacher(acro, name, time);
					Stundenplan.pList.dispose();
					Stundenplan.pList = new PlanList();
				} catch (DatasetException e) {
					System.out.println("Teacheradd fehlgeschlagen");
				}
				
				
				nameField.setText("");
				acroField.setText("");
				timeField.setText("");
				
				StartFrame.updateTeacherList();
				
				System.out.println(name +" " + acro + " "+ time);
				
				StartFrame.l.dispose();
			}
		});
	}
	

}
