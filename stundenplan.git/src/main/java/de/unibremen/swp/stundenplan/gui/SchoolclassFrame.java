package de.unibremen.swp.stundenplan.gui;

	import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import de.unibremen.swp.stundenplan.config.Messages;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

public class SchoolclassFrame extends JFrame {
	protected class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(final MouseEvent event) {
            final int row = table.rowAtPoint(event.getPoint());
            final int col = table.columnAtPoint(event.getPoint());
            if (row >= 0 && row < table.getRowCount() && col >= 1 && col < table.getColumnCount()) {
                table.changeSelection(row, col, false, false);
            } else {
                table.clearSelection();
            }
            checkPopup(event);
            event.consume();
        }

        @Override
        public void mouseReleased(final MouseEvent event) {
            checkPopup(event);
        }

        /**
         * Prüft, ob es sich bei dem gegebenen Mausereignis um einen Rechtsklick handelt. Falls das so ist, wird ein
         * entsprechendes Popup-Menu an den durch das Mausereignis übermittelten Koordinaten geöffnet.
         * 
         * Vermeidet Redundanz in den Listenern für mouse-pressed und mouse-released-Ereignisse. Beide Listener sind
         * nötig, da Windoof den Popup-Trigger erst auf Loslassen der Maustaste meldet, Linux und MacOs aber bereits
         * beim Klicken der Maus.
         * 
         * @param event
         *            das zu prüfende Mausevent
         */
        private void checkPopup(final MouseEvent event) {
            final int row = table.rowAtPoint(event.getPoint());
            final int col = table.columnAtPoint(event.getPoint());
            if (event.isPopupTrigger()) {
                /*
                 * Verhindert den nochmaligen Aufruf unter Linux und MacOs.
                 */
                event.consume();
                if (event.getComponent() instanceof JTable && row >= 0 && col >= 1) {
                    final JPopupMenu popup = createPopup(row, col - 1);
                    popup.show(table, event.getX(), event.getY());
                }
            }
        }
    }
	
	/**
     * Zur Darstellung der Aktivitäten in einer Tabelle, wird die JTable benutzt.
     */
    protected JTable table;
    
    /**
     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
     */
    protected AddSubjectDialog addSubjectDialog;

    private EditSubjectDialog editSubjectDialog;
   /**
     * Die Zeilenhöhe einer Tabellenzeile.
     */
    protected static final int ROW_HEIGHT = 40;
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5648861390882189564L;
	
	/**
     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
     */
    private final AddTeacherDialog addTeacherDialog;
    
    /**
     * 
     */
    private Schoolclass schoolclass;

    /**
     * Der Konstruktor des Frames. Hier werden die wesentlichen Eigenschaften der Darstellung des Frames definiert. Die
     * JTable wird angelegt und dem Frame hinzugefügt. Es werden die Darstellungseigenschaften der JTable festgelegt.
     * 
     */
    public SchoolclassFrame(Schoolclass pSchoolclass) {
    	super();
    	schoolclass = pSchoolclass;
    	addSubjectDialog = new AddSubjectDialog(this);
        addTeacherDialog = new AddTeacherDialog(this);
        editSubjectDialog = new EditSubjectDialog(this);
        setTitle("Stundenplan der Klasse: "+pSchoolclass.getName());
        table = new JTable(new SchoolclasstableModel(pSchoolclass));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        table.addMouseListener(new MyMouseListener());

        table.setDefaultRenderer(Timeslot.class, new TimetableRenderer());
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(table);

        table.setFillsViewportHeight(true);
        table.setGridColor(Color.YELLOW);
        table.setRowHeight(ROW_HEIGHT);
        
        Timer t = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	Random r = new Random();
            	table.setGridColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            	table.setBackground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            }
        });
        t.start();

        add(scrollPane);
        pack();
        setVisible(true);
    }
    
    /**
     * Erzeugt ein Popup zum Hinzufügen eines Lehrers.
     * 
     * @param row
     *            Die Zeile.
     * @param col
     *            Die Spalte.
     * @return das neue Popup-Menu
     */
    protected JPopupMenu createPopup(final int row, final int col) {
        final JPopupMenu popmen = new JPopupMenu();
        final JMenuItem menu1 = new JMenuItem(Messages.getString("MainFrame.AddTeacher"));
        final JMenuItem menu2 = new JMenuItem(Messages.getString("MainFrame.RemoveTeacher"));
        menu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addTeacherDialog.setTimeslot(Weekday.values()[col], row, schoolclass);
                addTeacherDialog.setVisible(true);
            }
        });
        final JMenuItem menu3 = new JMenuItem(Messages.getString("MainFrame.AddSubject"));
        menu3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
            	try {
					Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.values()[col], row, schoolclass);
					if(!timeslot.getSubjectAcronymList().equals("")) {
						JOptionPane.showMessageDialog(menu1, "Dort ist bereits ein Fach eingetragen!", "Fehler", JOptionPane.PLAIN_MESSAGE);
					}else {
		                addSubjectDialog.setTimeslot(Weekday.values()[col], row, schoolclass);
		                addSubjectDialog.setVisible(true);
					}
				} catch (DatasetException e) {
					e.printStackTrace();
				}
            }
        });
        popmen.add(menu3);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveSubject")));
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	try {
					Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.values()[col], row, schoolclass);
					if(!timeslot.getTeacherAcronymList().equals("")) timeslot.getTeachers().clear();					
				} catch (DatasetException e) {
					e.printStackTrace();
				}
            }
        });
        final JMenuItem menu4 = new JMenuItem(Messages.getString("MainFrame.EditSubject"));
        menu4.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(final ActionEvent event) {
            try {
              Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.values()[col], row, schoolclass);
              if(timeslot.getSubjectAcronymList() == null){
                JOptionPane.showMessageDialog(menu4, "Dort ist kein Fach zum editieren!", "Fehler", JOptionPane.PLAIN_MESSAGE);
              }else{
                editSubjectDialog.setTimeslot(Weekday.values()[col], row , schoolclass);
                editSubjectDialog.setVisible(true);
              }
            }catch (DatasetException e){
              e.printStackTrace();
            } 
          }
          
        });
        popmen.add(menu4);
        popmen.add(menu1);
        popmen.add(menu2);
        popmen.setVisible(true);
        return popmen;
    }
}
