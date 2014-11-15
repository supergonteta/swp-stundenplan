package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

public class DataPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private AddNewTeacher addNewTeacher = new AddNewTeacher();
    private AddNewSubject addNewSubject = new AddNewSubject();
    private AddNewSchoolclass addNewSchoolclass = new AddNewSchoolclass();
    
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
    
    private JLabel oben;
    public static JFrame f;
	public static JFrame l;
	public static JFrame c;

	public DataPanel(){
		initComponents();
	}

	private void initComponents(){ 
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill=GridBagConstraints.VERTICAL;
		c.anchor=GridBagConstraints.NORTH;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		add(addNewTeacher,c);
		c.gridx = 1;
		add(addNewSubject,c);
		c.gridx = 2;
		add(addNewSchoolclass,c);	
		
		// ListModels
		teacherListModel = new TeacherListModel();
		teacherList = new JList<>(teacherListModel);
		teacherList.setLayoutOrientation(JList.VERTICAL);
		teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane teacherListScroller = new JScrollPane(teacherList);
		teacherListScroller.setPreferredSize(new Dimension(250, 200));
//		updateTeacherList();
		
		subjectListModel = new SubjectListModel();
		subjectList = new JList<>(subjectListModel);
		subjectList.setLayoutOrientation(JList.VERTICAL);
		subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane subjectListScroller = new JScrollPane(subjectList);
		subjectListScroller.setPreferredSize(new Dimension(250, 200));
//		updateSubjectList();

		schoolclassListModel = new SchoolclassListModel();
		schoolclassList = new JList<>(schoolclassListModel);
		schoolclassList.setLayoutOrientation(JList.VERTICAL);
		schoolclassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane schoolclassListScroller = new JScrollPane(schoolclassList);
		schoolclassListScroller.setPreferredSize(new Dimension(250, 200));
//		updateSchoolclassList();


		// Listen der Daten
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		teacherListScroller.setBorder(BorderFactory.createTitledBorder("Existierendes Personal"));
		add(teacherListScroller, c);
		c.gridx = 1;
		subjectListScroller.setBorder(BorderFactory.createTitledBorder("Existierende Fächer"));
		add(subjectListScroller, c);
		c.gridx = 2;
		schoolclassListScroller.setBorder(BorderFactory.createTitledBorder("Existierende Schulklassen"));
		add(schoolclassListScroller, c);

		addTeacher(addNewTeacher.button);
		addSubject(addNewSubject.button);
		addSchoolclass(addNewSchoolclass.button);
		
	}
	
	private void addTeacher(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewTeacher a = new AddNewTeacher();
//				l = new JFrame("neue Lehrer hinzufügen");
//				l.setLocation(500, 150);
//				l.add(a, BorderLayout.CENTER);
//				l.pack();
//				l.setVisible(true);
////				updateTeacherList();
			}
		});
	}
	private void addSchoolclass(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewSchoolclass a = new AddNewSchoolclass();
//				c = new JFrame("neue Klasse hinzufügen");
//				c.setLocation(500, 150);
//				c.add(a, BorderLayout.CENTER);
//				c.pack();
//				c.setVisible(true);
////				updateSchoolclassList();
			}
		});
	}
	
	private void addSubject(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewSubject a = new AddNewSubject();
//				f = new JFrame("neue Aktivitaet hinzuf�gen");
//				f.setLocation(500, 150);
//				f.add(a, BorderLayout.CENTER);
//				f.pack();
//				f.setVisible(true);
////				updateSubjectList();
			}
		});
	}

//	public static void updateTeacherList() {
//        try {
//			Collection<Teacher> teachers = TeacherManager.getAllTeachers();
//			teacherListModel.clear();
//            for (final Teacher teacher : teachers) {
//            	teacherListModel.addTeacher(teacher);
//            }
//		} catch (DatasetException e1) {
//			e1.printStackTrace();
//		}
//	}
//	
//	public static void updateSchoolclassList() {
//        try {
//			Collection<Schoolclass> schoolclasses = SchoolclassManager.getAllSchoolclasses();
//			schoolclassListModel.clear();
//            for (final Schoolclass schoolclass : schoolclasses) {
//            	schoolclassListModel.addSchoolclass(schoolclass);
//            }
//		} catch (DatasetException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public static void updateSubjectList() {
//        try {
//			Collection<Subject> subjects = SubjectManager.getAllSubjects();
//			subjectListModel.clear();
//            for (final Subject subject : subjects) {
//            	subjectListModel.addSubject(subject);
//            }
//		} catch (DatasetException e1) {
//			e1.printStackTrace();
//		}
//	}
}
