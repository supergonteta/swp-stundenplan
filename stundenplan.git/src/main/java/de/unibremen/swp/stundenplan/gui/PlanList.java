package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.config.Config;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.DayTable;
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
	
	Teacher[] teacher;
	
	Schoolclass[] schoolclasses;
	
	public static JFrame pList;
	
	public PlanList() {
		super("StundenplanListe");
		setLocation(50, 225);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));
		pList = this;
		checkBoxen = new ArrayList<JCheckBox>();		
		try {
			panelTeacher = new JPanel(new GridLayout(Data.getAllTeachers().size(),2));			
			teacher = new Teacher[Data.getAllTeachers().size()];
			Data.getAllTeachers().toArray(teacher);			
			for(int i=0; i < teacher.length; i++){
				JCheckBox current = new JCheckBox(teacher[i].getName());
				checkBoxen.add(current);
				panelTeacher.add(current);				
			}
		} catch (DatasetException e) {
			System.out.println("PlanList for-schleife fuer Lehrer Fehler");
		}	
		try {
			panelSchoolclass = new JPanel(new GridLayout(Data.getAllSchoolclasses().size(),2));			
			schoolclasses = new Schoolclass[Data.getAllSchoolclasses().size()];
			Data.getAllSchoolclasses().toArray(schoolclasses);			
			for(int i=0; i < schoolclasses.length; i++){			
				JCheckBox current = new JCheckBox(schoolclasses[i].getName());
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
					System.out.println("nur eine Box markieren bitte");
				} else {
					System.out.println("Markiert: "+checkBoxen.get(position).getText());
					// geht die angezeigten Klassen durch
					for(Schoolclass s : schoolclasses){
						// Ist die angeklickte Klasse im Klassenverzeichnis 
						// wird ein Stundenplan fuer diese Klasse erstellt
						if(s.getName().equals(checkBoxen.get(position).getText())){
							final SchoolclassFrame schoolclassFrame = new SchoolclassFrame(s);
							schoolclassFrame.setLocation(275, 225);
			            	break;
						}
					}
					// geht die angezeigten Lehrer durch
					for(Teacher t: teacher){
						if(t.getName().equals(checkBoxen.get(position).getText())){
							final TeacherFrame teacherFrame = new TeacherFrame(t);
							teacherFrame.setLocation(275, 225);
							break;
						} 	
					}	
					
				}
			}				
		});
	}
}
