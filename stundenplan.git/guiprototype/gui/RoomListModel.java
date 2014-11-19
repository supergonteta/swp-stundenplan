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
package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import de.unibremen.swp.stundenplan.data.Room;

/**
 * Das ListModel für die Klassen.
 * 
 * @author F. Vidjaja,
 * @version 0.1
 * 
 */
public final class RoomListModel extends DefaultListModel<String> {

    /**
     * Die generierte serialVersionUID.
     */
    private static final long serialVersionUID = 5564982556376658908L;

    /**
     * Die Liste der Schulklassen dieses Modells.
     */
    private final List<Room> rooms;

    /**
     * Der Konstruktor für des SchoolclassListModel. Hier wird nur die Liste der schoolclass initialisiert.
     */
    protected RoomListModel() {
        super();
        rooms = new ArrayList<>();
    }

    /**
     * Fügt eine Schulklasse hinzu.
     * 
     * @param schoolclass
     *            die hinzuzuf�gende schulklasse.
     */
    protected void addRoom(final Room room) {
        rooms.add(room);
        addElement(String.format("%s", room.getName()));
    }

    /**
     * Gibt die Schulklasse am gegebenen Index aus der Liste zurück.
     * 
     * @param index
     *            Der Index an dem die Schulklasse steht, der zurückgegeben werden soll.
     * @return die Schulklasse am gegebenen Index
     */
    protected Room getRoomsAt(final int index) {
        return rooms.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.DefaultListModel#clear()
     */
    @Override
    public void clear() {
        super.clear();
        rooms.clear();
    }

}

