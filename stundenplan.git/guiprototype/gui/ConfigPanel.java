package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class ConfigPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3384783527067309086L;
	private JLabel label = new JLabel("Einstellungen");
	private JMenuBar menuBar = new JMenuBar();

	private JMenuItem mA = new JMenuItem("Dauer Planungseinheit");
	private JMenuItem mE = new JMenuItem("Back-Up Intervall");
	private JPanel allgConfig = new AllgConfig();
	private JPanel advConfig = new AdvConfig();
	
	public ConfigPanel(){
		init();
	}
	
	public void init(){
		
		setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
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
		menuBar.add(mA);
		menuBar.add(mE);
		menuBar.setLayout(new GridLayout(0, 1));
		add(menuBar, c);
		
	
		// klick auf mA
		mA.addActionListener(new ActionListener() {
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
				add(allgConfig, c);
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(allgConfig);
				SwingUtilities.updateComponentTreeUI(frame);
			}
		});
		
		// klick auf mE
				mE.addActionListener(new ActionListener() {
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
						add(advConfig, c);
						JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(advConfig);
						SwingUtilities.updateComponentTreeUI(frame);
					}
				});

	}
	
	private void removeOld() {
		remove(allgConfig);
		remove(advConfig);
	}
	
	public class AllgConfig extends JPanel {
		private Label lTime = new Label("Dauer einer Planungseinheit");
		private String[] min = {"1","5","10","15","20","30","45","60"};
		private JComboBox jc = new JComboBox(min);
		private GridBagConstraints c = new GridBagConstraints();
		private JButton button = new JButton("Einstellungen speichern");
		
		public AllgConfig(){
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createTitledBorder("Dauer einer Planungseinheit"));
			c.insets=new Insets(1,1,1,1);
			c.anchor=GridBagConstraints.PAGE_START;
			c.gridx=0;
			c.gridy=0;
			add(lTime,c);
			c.gridx=1;
			add(jc,c);
			c.gridx=2;
			add(new Label("Minuten"),c);
			c.anchor = GridBagConstraints.PAGE_END;  //top padding
			c.gridwidth=2;
			c.gridx=10;
			c.gridy=10;
			add(button, c);
		}
		
	}

	public class AdvConfig extends JPanel {

	}

}
