package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.gui.PlanList;
import de.unibremen.swp.stundenplan.logic.TeacherManager;

public class EditTeacher extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1579191984515127831L;
	private String name;
	private String acro;
	private String time;

	private Label lName = new Label("Name des Lehrers");
	private Label lAcro = new Label("Acronym:");
	private Label lTime = new Label("Zeitverpflichtung");
	private Label lSubjects = new Label("Stundeninhalte:");
	private Label lPrefTime = new Label("Zeitwunsch:");

	public TextField nameField = new TextField(20);
	public TextField acroField = new TextField(20);
	public TextField timeField= new TextField(20);
	public TextField subjectsField= new TextField(20);
	public TextField prefTimeFieldFrom= new TextField(10);
	public TextField prefTimeFieldTo=new TextField(10);
	
	public JButton okButton = new JButton("OK");
	public JButton noButton = new JButton("Abbrechen");
	
	public static PlanList planList;
	private GridBagConstraints c = new GridBagConstraints();

	public EditTeacher(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Neuen Stundeninhalt hinzufügen"));
		c.insets=new Insets(1,1,1,1);
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.gridy=0;
		add(lName,c);
		c.gridx=1;
		add(nameField,c);
		c.gridx=0;
		c.gridy=1;
		add(lAcro,c);
		c.gridx=1;
		add(acroField,c);
			
		c.gridx=0;
		c.gridy=2;
		add(lTime,c);
		c.gridx=1;
		add(timeField,c);
		
		c.gridx=0;
		c.gridy=3;
		add(lSubjects, c);
		c.gridx=1;
		Label deutsch = new Label("Deutsch");
		add(deutsch,c);
		c.gridx=2;
		Label mathe = new Label("Mathe");
		add(mathe,c);
		c.gridx=3;
		Label musik=new Label("Musik");
		add(musik,c);
		
		c.gridx=1;
		c.gridy=4;
		JCheckBox box1 = new JCheckBox();
		add(box1,c);
		c.gridx=2;
		JCheckBox box2= new JCheckBox();
		add(box2,c);
		c.gridx=3;
		JCheckBox box3= new JCheckBox();
		add(box3,c);
		
		c.gridx=0;
		c.gridy=5;
		add(lPrefTime, c);
		c.gridx=1;
		add(prefTimeFieldFrom,c);
		c.gridx=2;
		Label bis = new Label("bis");
		add(bis,c);
		c.gridx=3;
		add(prefTimeFieldTo,c);
		
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=2;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(okButton,c);
		c.gridx=2;
		c.gridwidth=2;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(noButton,c);
		

		buttonOkay(okButton);
		buttonOkay(noButton);
		nameField.requestFocus();

	}

	private void buttonOkay(JButton okButton2) {
		System.exit(0);
	}
}
