package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;

public class PlanList extends JFrame {

	
	private JPanel panelButton;
	private JButton ok;
	private JLabel oben;
	
	
	private JPanel panelTeacher;
	
	Teacher[] lehrer;
	int anzLehrer;
	
	public static JFrame pList;

	
	public PlanList() {
		super("StundenplanListe");
		setLocation(1000, 300);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));
		pList = this;
		
		try {
			anzLehrer = Data.getAllTeachers().size();
			panelTeacher = new JPanel(new GridLayout(anzLehrer,2));
			System.out.println(anzLehrer);
			lehrer = new Teacher[anzLehrer];
			Data.getAllTeachers().toArray(lehrer);
			
			for(int i=0; i < lehrer.length; i++){
				
				panelTeacher.add(new JCheckBox(lehrer[i].getName()));
				
			}
		} catch (DatasetException e) {
			System.out.println("PlanList for-schleife fuer Lehrer Fehler");
		}

		ok = new JButton("ok");
		
		// Panels erzeugen
		panelButton = new JPanel(new GridLayout(1, 3));


		// Auf Panel Buttons packen
		panelButton.add(ok);

	

		
		auswahl(ok);


		// Labels erzeugen
		oben = new JLabel("Lehrer, Aktivitaet oder Klasse hinzufuegen");
		// Label zentrieren
		oben.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen
		getContentPane().add(BorderLayout.NORTH, oben);

		// Panels auf Frame packen
		getContentPane().add(BorderLayout.WEST, panelButton);
		getContentPane().add(BorderLayout.EAST, panelTeacher);

		pack();
		setVisible(true);
 
	}



	
	private void auswahl(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				
			}
		});
	}
	



	
	

}