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

import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;

public class PlanList extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2303548835180015438L;
	
	private JPanel panelButton;
	private JButton ok;
	private JLabel oben;
	
	ArrayList<JCheckBox> checkBoxen;
	
	private JPanel panelTeacher;
	private JPanel panelSchoolclass;
	
	Teacher[] lehrer;
	int anzLehrer;
	
	Schoolclass[] klassen;
	int anzKlassen;
	
	public static JFrame pList;
	
	public PlanList() {
		super("StundenplanListe");
		setLocation(800, 500);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));
		pList = this;
		checkBoxen = new ArrayList<JCheckBox>();		
		try {
			anzLehrer = Data.getAllTeachers().size();
			panelTeacher = new JPanel(new GridLayout(anzLehrer,2));			
			lehrer = new Teacher[anzLehrer];
			Data.getAllTeachers().toArray(lehrer);			
			for(int i=0; i < lehrer.length; i++){
				JCheckBox current = new JCheckBox(lehrer[i].getName());
				checkBoxen.add(current);
				panelTeacher.add(current);				
			}
		} catch (DatasetException e) {
			System.out.println("PlanList for-schleife fuer Lehrer Fehler");
		}	
		try {
			anzKlassen = Data.getAllSchoolclasses().size();
			panelSchoolclass = new JPanel(new GridLayout(anzKlassen,2));			
			klassen = new Schoolclass[anzKlassen];
			Data.getAllSchoolclasses().toArray(klassen);			
			for(int i=0; i < klassen.length; i++){			
				JCheckBox current = new JCheckBox(klassen[i].getName());
				checkBoxen.add(current);
				panelSchoolclass.add(current);
			}
		} catch (DatasetException e) {
			System.out.println("PlanList for-schleife fuer Klassen Fehler");
		}
		ok = new JButton("ok");		
		// Panels erzeugen
		panelButton = new JPanel(new GridLayout(1, 3));
		// Auf Panel Buttons packen
		panelButton.add(ok);	
		auswahl(ok);
		// Labels erzeugen
		oben = new JLabel("Stundenplan anzeigen von");
		// Label zentrieren
		oben.setHorizontalAlignment(JLabel.CENTER);
		// Labels auf Frame packen
		getContentPane().add(BorderLayout.NORTH, oben);
		// Panels auf Frame packen
		getContentPane().add(BorderLayout.SOUTH, panelButton);
		getContentPane().add(BorderLayout.EAST, panelTeacher);
		getContentPane().add(BorderLayout.WEST, panelSchoolclass);
		pack();
		setVisible(true);
	}

	private void auswahl(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int isChecked = 0;
				int position =0;
				for(int i=0; i < checkBoxen.size(); i++) {
					if(checkBoxen.get(i).isSelected()){
						isChecked = isChecked+1;
						position =i;
					}
				}
				if(isChecked != 1){
					System.out.println("mehrere gecheckt");
				} else {
					System.out.println("ein gecheckt"+checkBoxen.get(position).getText());
					for(Schoolclass s : klassen){
						if(s.getName().equals(checkBoxen.get(position).getText())){
							final SchoolclassFrame schoolclassFrame = new SchoolclassFrame();
			            	schoolclassFrame.setLocation(300, 300);
			            	schoolclassFrame.pack();
			            	schoolclassFrame.setVisible(true);
			            	break;
						}else{
							final TeacherFrame teacherFrame = new TeacherFrame();
			            	teacherFrame.setLocation(300, 300);
			            	teacherFrame.pack();
			            	teacherFrame.setVisible(true);
			            	break;
						}
					}				
				}				
			}
		});
	}
}