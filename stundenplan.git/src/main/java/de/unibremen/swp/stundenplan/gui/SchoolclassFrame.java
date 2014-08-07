package de.unibremen.swp.stundenplan.gui;

	import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import de.unibremen.swp.stundenplan.config.Messages;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.gui.MainFrame.MyMouseListener;

public class SchoolclassFrame extends MainFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5648861390882189564L;
	
	/**
     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
     */
    private final AddTeacherDialog addTeacherDialog;

    /**
     * Der Konstruktor des Frames. Hier werden die wesentlichen Eigenschaften der Darstellung des Frames definiert. Die
     * JTable wird angelegt und dem Frame hinzugefügt. Es werden die Darstellungseigenschaften der JTable festgelegt.
     * 
     */
    public SchoolclassFrame() {
        super();
        addTeacherDialog = new AddTeacherDialog(this);
        setTitle(super.getTitle() + " von der Klasse");
    }

    public SchoolclassFrame(Schoolclass schoolclass) {
        super();
        addTeacherDialog = new AddTeacherDialog(this);
        setTitle("Stundenplan der Klasse : "+schoolclass.getName());
        addSubjectDialog = new AddSubjectDialog(this);
        table = new JTable(new SchoolclasstableModel(schoolclass));

        setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
        setTitle(Messages.getString("MainFrame.Title"));

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
        final JPopupMenu popmen = super.createPopup(row, col);
        final JMenuItem menu1 = new JMenuItem(Messages.getString("MainFrame.AddTeacher"));
        menu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addTeacherDialog.setTimeslot(Weekday.values()[col], row);
                addTeacherDialog.setVisible(true);
            }
        });
        popmen.add(menu1);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveTeacher")));
        popmen.setVisible(true);
        return popmen;
    }
}
