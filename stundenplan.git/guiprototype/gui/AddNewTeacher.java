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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.unibremen.swp.stundenplan.Stundenplan;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.gui.PlanList;
import de.unibremen.swp.stundenplan.logic.TeacherManager;

public class AddNewTeacher extends JPanel {
	
	private String name;
	private String acro;
	private String time;

	Label lName = new Label("Name des Lehrers");
	Label lAcro = new Label("Acronym:");
	Label lTime = new Label("Max. Arbeitstunden:");

	private TextField nameField = new TextField(20);
	private TextField acroField = new TextField(20);
	private TextField timeField= new TextField(20);
	
	public JButton button = new JButton("Lehrer Hinzufügen");
	
	private GridBagConstraints c = new GridBagConstraints();	
	
	public static PlanList planList;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1219589162309740553L;

	public AddNewTeacher(){
	    setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Neues Personal hinzufügen"));
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
				time = timeField.getText();
				Integer timer = new Integer(time);
				
				 if(timer >40 || timer < 4) {
		            	JFrame jFrame = new JFrame();
		            	jFrame.setLocation(500, 500);
		            	
		            	jFrame.setVisible(false);
		            	
		            	JOptionPane.showMessageDialog(jFrame, "Die Arbeitszeit muss zwischen 4 und 40 Stunden liegen" );
		            	return;
		            }
				
				
				
				try {
					TeacherManager.addTeacher(acro, name, time);
					Stundenplan.pList.dispose();
					Stundenplan.pList = new PlanList();
				} catch (DatasetException e) {
					System.out.println("Teacheradd fehlgeschlagen");
				}
				
				
				nameField.setText("");
				acroField.setText("");
				timeField.setText("");
				
//				StartFrame.updateTeacherList();
				
				System.out.println(name +" " + acro + " "+ time);
				
	//			StartFrame.l.dispose();
			}
		});
	}
	

}
