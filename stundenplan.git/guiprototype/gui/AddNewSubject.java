package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SubjectManager;

public class AddNewSubject extends JPanel {

	private String name;
	private String acro;

	Label lName = new Label("Titel der Aktivität:");
	Label lAcro = new Label("Acronym:");
	Label lPause = new Label("Ist es eine Pause?");

	private TextField nameField = new TextField(20);
	private TextField acroField = new TextField(20);
	
	public JButton button = new JButton("Fach Hinzufügen");
	
	private JCheckBox pause = new JCheckBox();
	
	private GridBagConstraints c = new GridBagConstraints();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewSubject() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Neues Fach hinzufügen"));
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
		add(lPause,c);
		pause.setSelected(false);
		c.gridx=1;
		add(pause,c);
		
		c.gridx=0;
		c.gridy=3;
		c.gridwidth=2;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(button,c);

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
				
	//			StartFrame.updateSubjectList();

				nameField.setText("");

				System.out.println(name);

	//			StartFrame.f.dispose();
			}
		});
	}

}
