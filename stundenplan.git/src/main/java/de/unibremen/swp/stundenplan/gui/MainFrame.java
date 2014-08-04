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

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import de.unibremen.swp.stundenplan.config.Messages;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Timeslot;

/**
 * Das Hauptfenster, in dem die GUI dargestellt wird.
 * 
 * @author D. Lüdemann, K. Hölscher
 * @version 0.1
 * 
 */
public final class MainFrame extends JFrame {

    /**
     * Ein eigener Maushorcher für die Tabelle.
     * 
     * @author D. Lüdemann
     * @version 0.1
     * 
     */
    private class MyMouseListener extends MouseAdapter {
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
    private final JTable table;

    /**
     * Der Dialog, der aufpopt, um einen Lehrer hinzuzufügen.
     */
    private final AddTeacherDialog addTeacherDialog;
    
//    /**
//     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
//     */
//    private final AddSubjectDialog addSubjectDialog;

    /**
     * Die generierte serialVersionUID.
     */
    private static final long serialVersionUID = 8285305580202003358L;

    /**
     * Die Zeilenhöhe einer Tabellenzeile.
     */
    private static final int ROW_HEIGHT = 40;

    /**
     * Der Konstruktor des Frames. Hier werden die wesentlichen Eigenschaften der Darstellung des Frames definiert. Die
     * JTable wird angelegt und dem Frame hinzugefügt. Es werden die Darstellungseigenschaften der JTable festgelegt.
     * 
     */
    public MainFrame() {
        super();
        addTeacherDialog = new AddTeacherDialog(this);
//        addSubjectDialog = new AddSubjectDialog(this);
        table = new JTable(new TimetableModel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Messages.getString("MainFrame.Title"));

        table.addMouseListener(new MyMouseListener());

        table.setDefaultRenderer(Timeslot.class, new TimetableRenderer());
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(table);

        table.setFillsViewportHeight(true);
        table.setGridColor(Color.YELLOW);
        table.setBackground(Color.CYAN);
        table.setRowHeight(ROW_HEIGHT);

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
    private JPopupMenu createPopup(final int row, final int col) {
        final JPopupMenu popmen = new JPopupMenu();
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
