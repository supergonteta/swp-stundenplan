package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.*;


public class MainFrame extends JFrame{
	
	
	
	//screenresolution kriegen
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JTabbedPane tabpane = new JTabbedPane(JTabbedPane.TOP);
	
	private JPanel dataPane = new JPanel();
    private JPanel paneStundenplan = new JPanel();
    private JPanel paneLehrer = new JPanel(); 
    
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
    
	public MainFrame() {
		super("GUI-Prototype");	
		//später wieder aktivieren
		//setSize(screen.width, screen.height);
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
       // pack();
		setVisible(true);
	}
	
	private void initComponents(){
		initDataPane();
		
        paneStundenplan.setBackground(Color.LIGHT_GRAY);
        paneLehrer.setBackground(Color.LIGHT_GRAY);
        
        tabpane.addTab("Daten", dataPane);
        tabpane.addTab("Stundenpläne", paneStundenplan);
        tabpane.addTab("Lehreransicht", paneLehrer);
		
        add(tabpane);
        
        
		
	};
	
	private void initDataPane(){
		dataPane.setBackground(Color.LIGHT_GRAY);    
		
		dataPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		dataPane.add(addNewTeacher,c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		dataPane.add(addNewSubject,c);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		dataPane.add(addNewSchoolclass,c);	
		
		// ListModels
		teacherListModel = new TeacherListModel();
		teacherList = new JList<>(teacherListModel);
		teacherList.setLayoutOrientation(JList.VERTICAL);
		teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane teacherListScroller = new JScrollPane(teacherList);
		teacherListScroller.setPreferredSize(new Dimension(250, 80));
//		updateTeacherList();

		schoolclassListModel = new SchoolclassListModel();
		schoolclassList = new JList<>(schoolclassListModel);
		schoolclassList.setLayoutOrientation(JList.VERTICAL);
		schoolclassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane schoolclassListScroller = new JScrollPane(schoolclassList);
		schoolclassListScroller.setPreferredSize(new Dimension(250, 80));
//		updateSchoolclassList();

		subjectListModel = new SubjectListModel();
		subjectList = new JList<>(subjectListModel);
		subjectList.setLayoutOrientation(JList.VERTICAL);
		subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane subjectListScroller = new JScrollPane(subjectList);
		subjectListScroller.setPreferredSize(new Dimension(250, 80));
//		updateSubjectList();

		// Listen der Daten
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		dataPane.add(teacherListScroller, c);
		c.gridy = 1;
		dataPane.add(schoolclassListScroller, c);
		c.gridy = 2;
		dataPane.add(subjectListScroller, c);

		addLehrer(addNewTeacher.button);
		addKlasse(addNewSchoolclass.button);
		addFach(addNewSubject.button);

		// Labels erzeugen
		oben = new JLabel("Datenbank");
		// Label zentrieren
		oben.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen
		getContentPane().add(BorderLayout.NORTH, oben);

		// Panels auf Frame packen
		getContentPane().add(BorderLayout.WEST, dataPane);
		
	}
	
	private void addLehrer(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewTeacher a = new AddNewTeacher();
//				l = new JFrame("neue Lehrer hinzufÃ¼gen");
//				l.setLocation(500, 150);
//				l.add(a, BorderLayout.CENTER);
//				l.pack();
//				l.setVisible(true);
////				updateTeacherList();
			}
		});
	}
	private void addKlasse(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewSchoolclass a = new AddNewSchoolclass();
//				c = new JFrame("neue Klasse hinzufÃ¼gen");
//				c.setLocation(500, 150);
//				c.add(a, BorderLayout.CENTER);
//				c.pack();
//				c.setVisible(true);
////				updateSchoolclassList();
			}
		});
	}
	
	private void addFach(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				AddNewSubject a = new AddNewSubject();
//				f = new JFrame("neue Aktivitaet hinzufügen");
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
	
	public static void main(final String[] args) {
		final MainFrame mainFrame = new MainFrame();
	}
	
}
