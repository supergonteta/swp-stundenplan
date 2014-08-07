package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.persistence.Data;

public class StartFrame extends JFrame {
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton buttonS1;
	private JPanel panelButton;

	private JLabel oben;
	public static JFrame f;
	public static JFrame l;
	public static JFrame c;
	
	public StartFrame() {
		super("Add-Panel");
		setLocation(100, 300);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));

		// Buttons erzeugen
		button1 = new JButton("Lehrer hinzufügen");
		
		button2 = new JButton("Fach hinzufügen");
	
		button3 = new JButton("Klasse hinzufügen");
		
		
		

		// Panels erzeugen
		panelButton = new JPanel(new GridLayout(1, 3));


		// Auf Panel Buttons packen
		panelButton.add(button1);
		panelButton.add(button2);
		panelButton.add(button3);


		
		addLehrer(button1);
		addFach(button2);
		addKlasse(button3);


		// Labels erzeugen
		oben = new JLabel("Der Datenbank hinzufuegen");
		// Label zentrieren
		oben.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen
		getContentPane().add(BorderLayout.NORTH, oben);

		// Panels auf Frame packen
		getContentPane().add(BorderLayout.WEST, panelButton);


		pack();
		setVisible(true);
 
	}




	
	private void addLehrer(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AddNewTeacher a = new AddNewTeacher();
				l = new JFrame("neue Lehrer hinzufügen");
				l.setLocation(500, 150);
				l.add(a, BorderLayout.CENTER);
				l.pack();
				l.setVisible(true);
				
			}
		});
	}
	private void addKlasse(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AddNewSchoolclass a = new AddNewSchoolclass();
				c = new JFrame("neue Klasse hinzufügen");
				c.setLocation(500, 150);
				c.add(a, BorderLayout.CENTER);
				c.pack();
				c.setVisible(true);
				
			}
		});
	}
	private void addFach(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AddNewSubject a = new AddNewSubject();
				f = new JFrame("neue Aktivitaet hinzufügen");
				f.setLocation(500, 150);
				f.add(a, BorderLayout.CENTER);
				f.pack();
				f.setVisible(true);
				
			}
		});
	}

	
	

}