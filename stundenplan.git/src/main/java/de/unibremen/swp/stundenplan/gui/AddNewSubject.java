package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import de.unibremen.swp.stundenplan.data.Subject;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SubjectManager;
import de.unibremen.swp.stundenplan.logic.TeacherManager;
import de.unibremen.swp.stundenplan.persistence.Data;

public class AddNewSubject extends Panel {

	String name;
	String acro;


	TextField nameField;
	TextField acroField;
	

	JCheckBox pause;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewSubject() {

		setLayout(new BorderLayout());
		Panel p = new Panel();
		p.setLayout(new GridLayout(4, 2));

		Label lName = new Label("Titel");
		lName.setAlignment(Label.LEFT);
		p.add(lName);
		nameField = new TextField(20);
		p.add(nameField);

		Label lAcro = new Label("Acro");
		lAcro.setAlignment(Label.LEFT);
		p.add(lAcro);
		acroField = new TextField(20);
		p.add(acroField);
		
		
		Label lPause = new Label("Pause?");
		lPause.setAlignment(Label.LEFT);
		p.add(lPause);
		pause = new JCheckBox();
		pause.setSelected(false);
		p.add(pause);
		
		JButton button = new JButton("Fach Hinzufügen");
		p.add(button);
		add(p, BorderLayout.NORTH);

		buttonOkay(button);

	}

	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				name = nameField.getText();
				acro = acroField.getText();
				
				
				try {
					SubjectManager.addSubject(acro, name);
				} catch (DatasetException e) {
					System.out.println("Fach add fehlgeschlagen");
				}

				nameField.setText("");

				System.out.println(name);

				StartFrame.f.dispose();
			}
		});
	}

}
