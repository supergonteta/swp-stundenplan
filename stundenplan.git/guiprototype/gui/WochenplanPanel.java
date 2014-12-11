package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;

public class WochenplanPanel extends JPanel {
	
	
	private JFileChooser chooser = new JFileChooser();
	private JFrame f;
	
	public JLabel warning = new JLabel();
	
	public WochenplanPanel() {
		init();
	}
	
	public void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 0.3;
		c.weighty = 1.0;
		c.gridx=0;
		c.gridy=0;
		
		JMenuBar menuBar;
		JMenu menu;
		
		menuBar = new JMenuBar();

		menu = new JMenu("Personal");
		menuBar.add(menu);
		menuBar.setLayout(new GridLayout(0,1));
		add(menuBar, c);
		
		JButton montag = new JButton("Montag");
		JButton dienstag = new JButton("Dienstag");
		JButton mittwoch = new JButton("Mittwoch");
		JButton donnerstag = new JButton("Donnerstag");
		JButton freitag = new JButton("Freitag");
		JButton samstag = new JButton("Samstag");
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx=1;
		add(montag,c);
		c.gridx=2;
		add(dienstag,c);
		c.gridx=3;
		add(mittwoch,c);
		c.gridx=4;
		add(donnerstag,c);
		c.gridx=5;
		add(freitag,c);
		c.gridx=6;
		add(samstag,c);
		
		JButton pdf = new JButton("PDF");
		JButton csv = new JButton("CSV");
		JButton text = new JButton("Text");
		
		c.insets = new Insets(5,5,0,0);
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		add(new JLabel("Exportieren als:"),c);
		c.gridx = 2;
		add(pdf,c);
		c.gridx = 3;
		add(csv,c);
		c.gridx = 4;
		add(text,c);	
		
		buttonOkay(pdf);
		buttonOkay(csv);
		buttonOkay(text);
		
		warning.setText("Warnungsfeld: Keine Probleme");
		warning.setBackground(Color.GREEN);
		warning.setOpaque(true);
		c.gridy = 2;
		c.gridx = 0;
		
		add(warning, c);
		
	}

	
	private void buttonOkay(JButton b) {
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showSaveDialog(f);
			}
		});
	}	
	
}
