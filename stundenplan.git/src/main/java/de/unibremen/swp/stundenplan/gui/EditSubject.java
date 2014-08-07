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
import javax.swing.JFrame;

import de.unibremen.swp.stundenplan.data.Subject;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SubjectManager;
import de.unibremen.swp.stundenplan.logic.TeacherManager;
import de.unibremen.swp.stundenplan.persistence.Data;

public class EditSubject extends Panel {

	String name;
	String acro;
	Subject s;


	TextField nameField;
	TextField acroField;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public EditSubject(Subject sub) {

		setLayout(new BorderLayout());
		Panel p = new Panel();
		p.setLayout(new GridLayout(4, 2));
		s=sub;

		Label lName = new Label("Neuer Name");
		lName.setAlignment(Label.LEFT);
		p.add(lName);
		nameField = new TextField(20);
		p.add(nameField);

		Label lAcro = new Label("Neues Acro");
		lAcro.setAlignment(Label.LEFT);
		p.add(lAcro);
		acroField = new TextField(20);
		p.add(acroField);
		
		
		JButton button = new JButton("Editieren Abschließen");
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
					SubjectManager.editSubject(s, acro, name);
					s.getTimeslot().editSubject(s, name, acro);
				} catch (DatasetException e) {
					System.out.println("Fach add fehlgeschlagen");
				}

				nameField.setText("");

				System.out.println(name);

				EditSubjectDialog.e.dispose();
			}
		});
	}

}

