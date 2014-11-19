package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class DataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel label = new JLabel("Daten hinzufuegen");

	private JMenuBar menuBar = new JMenuBar();

	private JMenuItem mT = new JMenuItem("Lehrer hinzufuegen");
	private JMenuItem mS = new JMenuItem("Schulklassen hinzufuegen");
	private JMenuItem mF = new JMenuItem("Faecher hinzufuegen");
	private JMenuItem mR = new JMenuItem("Raueme hinzufuegen");

	private AddNewTeacher addNewTeacher = new AddNewTeacher();
	private AddNewSubject addNewSubject = new AddNewSubject();
	private AddNewSchoolclass addNewSchoolclass = new AddNewSchoolclass();
	private AddNewRoom addNewRoom = new AddNewRoom();

	private JList<String> teacherList = new JList<String>();
	private JList<String> schoolclassList = new JList<String>();
	private JList<String> subjectList = new JList<String>();
	private JList<String> roomList = new JList<String>();

	private JScrollPane teacherListScroller = new JScrollPane(teacherList);
	private JScrollPane schoolclassListScroller = new JScrollPane(schoolclassList);
	private JScrollPane subjectListScroller = new JScrollPane(subjectList);
	private JScrollPane roomListScroller = new JScrollPane(roomList);

	private static TeacherListModel teacherListModel = new TeacherListModel();;
	private static SchoolclassListModel schoolclassListModel = new SchoolclassListModel();;
	private static SubjectListModel subjectListModel = new SubjectListModel();;
	private static RoomListModel roomListModel = new RoomListModel();;

	public DataPanel() {
		initComponents();
	}

	private void initComponents() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// c.insets=new Insets(5,0,0,0);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1.0;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 0;
		label.setFont(new Font("Arial", Font.BOLD, 15));
		add(label, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 0.2;
		c.weighty = 1.8;
		c.gridx = 0;
		c.gridy = 1;
		menuBar.add(mT);
		menuBar.add(mS);
		menuBar.add(mF);
		menuBar.add(mR);
		menuBar.setLayout(new GridLayout(0, 1));
		add(menuBar, c);

		// klick auf mT
		mT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.EAST;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 1.8;
				c.weighty = 1.0;
				removeOld();
				add(addNewTeacher, c);

				teacherList.setLayoutOrientation(JList.VERTICAL);
				teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				teacherListScroller.setPreferredSize(new Dimension(250, 200));
				// updateTeacherList();

				c.gridy = 2;
				teacherListScroller.setBorder(BorderFactory.createTitledBorder("Existierendes Personal"));
				add(teacherListScroller, c);
				addTeacher(addNewTeacher.button);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addNewTeacher);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		// klick auf mS
		mS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.EAST;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 1.8;
				c.weighty = 1.0;
				removeOld();
				add(addNewSchoolclass, c);

				schoolclassList.setLayoutOrientation(JList.VERTICAL);
				schoolclassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				schoolclassListScroller.setPreferredSize(new Dimension(250, 200));
				// updateTeacherList();

				c.gridy = 2;
				schoolclassListScroller.setBorder(BorderFactory.createTitledBorder("Existierende Schulklassen"));
				add(schoolclassListScroller, c);
				addSchoolclass(addNewSchoolclass.button);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addNewSchoolclass);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		// klick auf mF
		mF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.EAST;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 1.8;
				c.weighty = 1.0;
				removeOld();
				add(addNewSubject, c);

				subjectList.setLayoutOrientation(JList.VERTICAL);
				subjectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				subjectListScroller.setPreferredSize(new Dimension(250, 200));
				// updateTeacherList();

				c.gridy = 2;
				subjectListScroller.setBorder(BorderFactory.createTitledBorder("Existierende Faecher"));
				add(subjectListScroller, c);
				addSubject(addNewSubject.button);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addNewSubject);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});

		// klick auf mR
		mR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.EAST;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.gridx = 1;
				c.gridy = 1;
				c.weightx = 1.8;
				c.weighty = 1.0;
				removeOld();
				add(addNewRoom, c);

				roomList.setLayoutOrientation(JList.VERTICAL);
				roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				roomListScroller.setPreferredSize(new Dimension(250, 200));
				// updateTeacherList();

				c.gridy = 2;
				roomListScroller.setBorder(BorderFactory.createTitledBorder("Existierende Räume"));
				add(roomListScroller, c);
				addRoom(addNewRoom.button);
				
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(addNewRoom);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});		
	}

	private void addTeacher(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// updateTeacherList();
			}
		});
	}

	private void addSchoolclass(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//updateSchoolclassList();
			}
		});
	}

	private void addSubject(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//updateSubjectList();
			}
		});
	}
	
	private void addRoom(JButton b) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// updateRoomList();
			}
		});
	}

	private void removeOld() {
		remove(addNewTeacher);
		remove(addNewSubject);
		remove(addNewSchoolclass);
		remove(addNewRoom);
		remove(teacherListScroller);
		remove(subjectListScroller);
		remove(schoolclassListScroller);
		remove(roomListScroller);
	}

	// public static void updateTeacherList() {
	// try {
	// Collection<Teacher> teachers = TeacherManager.getAllTeachers();
	// teacherListModel.clear();
	// for (final Teacher teacher : teachers) {
	// teacherListModel.addTeacher(teacher);
	// }
	// } catch (DatasetException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	// public static void updateSchoolclassList() {
	// try {
	// Collection<Schoolclass> schoolclasses =
	// SchoolclassManager.getAllSchoolclasses();
	// schoolclassListModel.clear();
	// for (final Schoolclass schoolclass : schoolclasses) {
	// schoolclassListModel.addSchoolclass(schoolclass);
	// }
	// } catch (DatasetException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	// public static void updateSubjectList() {
	// try {
	// Collection<Subject> subjects = SubjectManager.getAllSubjects();
	// subjectListModel.clear();
	// for (final Subject subject : subjects) {
	// subjectListModel.addSubject(subject);
	// }
	// } catch (DatasetException e1) {
	// e1.printStackTrace();
	// }
	// }
}
