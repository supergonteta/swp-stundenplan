package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Subject;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SchoolclassManager;
import de.unibremen.swp.stundenplan.logic.SubjectManager;
import de.unibremen.swp.stundenplan.logic.TeacherManager;
import de.unibremen.swp.stundenplan.persistence.Data;

public class StartFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8149773824938278639L;
	
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton okButton;
	
	private final JPanel panel;

	private final JLabel oben;
	public static JFrame f;
	public static JFrame l;
	public static JFrame c;
	
	private JList<String> teacherList = new JList<String>();
	private JList<String> schoolclassList = new JList<String>();
	private JList<String> subjectList = new JList<String>();

    /**
     * Das aktuelle TeacherListModel.
     */
    private static TeacherListModel teacherListModel;
    
    /**
     * Das aktuelle SchoolclassListModel.
     */
    private static SchoolclassListModel schoolclassListModel;
    
    /**
     * Das aktuelle SubjectListModel;
     */
    private static SubjectListModel subjectListModel;
	
	public StartFrame() {
		super("Add-Panel");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5, 5));
        GridBagConstraints c = new GridBagConstraints();

		// Buttons erzeugen
		button1 = new JButton("Lehrer hinzufügen");
		button2 = new JButton("Klasse hinzufügen");
		button3 = new JButton("Fach hinzufügen");
		okButton = new JButton("OK");

		// Panels erzeugen
		panel = new JPanel(new GridBagLayout());

		// ListModels
		teacherListModel = new TeacherListModel();
        teacherList = new JList<>(teacherListModel);
        teacherList.setLayoutOrientation(JList.VERTICAL);
        teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane teacherListScroller = new JScrollPane(teacherList);
        teacherListScroller.setPreferredSize(new Dimension(250, 80));
		updateTeacherList();
        
        schoolclassListModel = new SchoolclassListModel();
        schoolclassList = new JList<>(schoolclassListModel);
        schoolclassList.setLayoutOrientation(JList.VERTICAL);
        schoolclassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane schoolclassListScroller = new JScrollPane(schoolclassList);
        schoolclassListScroller.setPreferredSize(new Dimension(250, 80));
        updateSchoolclassList();
        
        subjectListModel = new SubjectListModel();
        subjectList = new JList<>(subjectListModel);
        subjectList.setLayoutOrientation(JList.VERTICAL);
        subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane subjectListScroller = new JScrollPane(subjectList);
        subjectListScroller.setPreferredSize(new Dimension(250, 80));
		updateSubjectList();
        
		// Listen der Daten
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
		panel.add(teacherListScroller, c);
		c.gridx = 1;
		panel.add(schoolclassListScroller, c);
		c.gridx = 2;
		panel.add(subjectListScroller, c);

		// Auf Panel Buttons packen
		c.gridx = 0;
		c.gridy = 2;
		panel.add(button1, c);
		c.gridx = 1;
		panel.add(button2, c);
		c.gridx = 2;
		panel.add(button3, c);
		
		addLehrer(button1);
		addKlasse(button2);
		addFach(button3);

		// Labels erzeugen
		oben = new JLabel("Datenbank");
		// Label zentrieren
		oben.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen
		getContentPane().add(BorderLayout.NORTH, oben);

		// Panels auf Frame packen
		getContentPane().add(BorderLayout.WEST, panel);

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
				updateTeacherList();
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
				updateSchoolclassList();
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
				updateSubjectList();
			}
		});
	}

	public static void updateTeacherList() {
        try {
			Collection<Teacher> teachers = TeacherManager.getAllTeachers();
			teacherListModel.clear();
            for (final Teacher teacher : teachers) {
            	teacherListModel.addTeacher(teacher);
            }
		} catch (DatasetException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateSchoolclassList() {
        try {
			Collection<Schoolclass> schoolclasses = SchoolclassManager.getAllSchoolclasses();
			schoolclassListModel.clear();
            for (final Schoolclass schoolclass : schoolclasses) {
            	schoolclassListModel.addSchoolclass(schoolclass);
            }
		} catch (DatasetException e1) {
			e1.printStackTrace();
		}
	}

	public static void updateSubjectList() {
        try {
			Collection<Subject> subjects = SubjectManager.getAllSubjects();
			subjectListModel.clear();
            for (final Subject subject : subjects) {
            	subjectListModel.addSubject(subject);
            }
		} catch (DatasetException e1) {
			e1.printStackTrace();
		}
	}
	

}
