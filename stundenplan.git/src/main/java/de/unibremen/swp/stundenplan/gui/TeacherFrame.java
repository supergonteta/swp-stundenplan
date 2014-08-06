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
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

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
public class TeacherFrame extends JFrame {

    public TeacherFrame() {
        super();
        addSubjectDialog = new AddSubjectDialog(this);
        addSchoolclassDialog = new AddSchoolclassDialog(this);
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
        menu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addTeacherDialog.setTimeslot(Weekday.values()[col], row);
                addTeacherDialog.setVisible(true);
            }
        });
        final JMenuItem menu2 = new JMenuItem(Messages.getString("MainFrame.AddSubject"));
        menu2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addSubjectDialog.setTimeslot(Weekday.values()[col], row);
                addSubjectDialog.setVisible(true);
            }
        });
        final JMenuItem menu3 = new JMenuItem(Messages.getString("MainFrame.AddSchoolclass"));
        menu3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addSchoolclassDialog.setTimeslot(Weekday.values()[col], row);
                addSchoolclassDialog.setVisible(true);
            }
        });
        popmen.add(menu1);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveTeacher")));
        popmen.add(menu2);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveSubject")));
        popmen.add(menu3);
        popmen.add(new JMenuItem(Messages.getString("MainFrame.RemoveSchoolclass")));
        popmen.setVisible(true);
        return popmen;
    }

}
