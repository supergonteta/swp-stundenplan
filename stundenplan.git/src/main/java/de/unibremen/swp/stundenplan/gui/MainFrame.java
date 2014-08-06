/*
 * Copyright 2014 AG Softwaretechnik, University of Bremen, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

/**
 * Das Hauptfenster, in dem die GUI dargestellt wird.
 * 
 * @author D. Lüdemann, K. Hölscher
 * @version 0.1
 * 
 */
public class MainFrame extends JFrame {

    /**
     * Ein eigener Maushorcher für die Tabelle.
     * 
     * @author D. Lüdemann
     * @version 0.1
     * 
     */
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
    protected final JTable table;
    
    /**
     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
     */
    protected final AddSubjectDialog addSubjectDialog;

    /**
     * Die generierte serialVersionUID.
     */
    protected static final long serialVersionUID = 8285305580202003358L;

    /**
     * Die Zeilenhöhe einer Tabellenzeile.
     */
    protected static final int ROW_HEIGHT = 40;

    /**
     * Der Konstruktor des Frames. Hier werden die wesentlichen Eigenschaften der Darstellung des Frames definiert. Die
     * JTable wird angelegt und dem Frame hinzugefügt. Es werden die Darstellungseigenschaften der JTable festgelegt.
     * 
     */
    public MainFrame() {
        super();
        addSubjectDialog = new AddSubjectDialog(this);
        table = new JTable(new TimetableModel());

        setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
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
        final JPopupMenu popmen = new JPopupMenu();
        final JMenuItem menu1 = new JMenuItem(Messages.getString("MainFrame.AddSubject"));
        menu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
            	try {
					Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.values()[col], row);
					if(!timeslot.getSubjectAcronymList().equals("")) {
						JOptionPane.showMessageDialog(menu1, "Dort ist bereits ein Fach eingetragen!", "Fehler", JOptionPane.PLAIN_MESSAGE);
					}else {
		                addSubjectDialog.setTimeslot(Weekday.values()[col], row);
		                addSubjectDialog.setVisible(true);
					}
				} catch (DatasetException e) {
					e.printStackTrace();
				}
            }
        });
        popmen.add(menu1);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveSubject")));
        popmen.setVisible(true);
        return popmen;
    }

}
