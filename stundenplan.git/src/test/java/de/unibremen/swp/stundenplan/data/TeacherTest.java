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
package de.unibremen.swp.stundenplan.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Komponententest für die Klasse {@link Teacher}.
 * 
 * @author K. Hölscher
 * @version 0.1
 */
public class TeacherTest {

    /**
     * Teacher-Objekt für die Tests.
     */
    private Teacher teacher;

    /**
     * Erzeugt vor jedem Test ein Teacher-Objekt und mockt die Zeichenkettenlängen-Konstante der Klasse {@link Data}.
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @Before
    public void setUp() throws Exception {
        teacher = new Teacher();
    }

    /**
     * Prüft, ob die Methode {@link Teacher#setName(String)} mit dem Wert {@code null} eine Exception auslöst.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNameWithNullYieldsException() {
        teacher.setName(null);
    }

    /**
     * Testet, ob die Methode {@link Teacher#setName(String)} mit einer Zeichenkette, die länger ist als erlaubt, diese
     * Zeichenkette in verkürzter Form speichert.
     */
    @Test
    public void setNameWithLongStringYieldsAbbreviatedName() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; builder.length() <= Data.MAX_NORMAL_STRING_LEN; i++) {
            builder.append(String.valueOf(i % 10));
        }
        String value = builder.toString();
        builder.delete(0, builder.length());
        for (int i = 0; builder.length() < Data.MAX_NORMAL_STRING_LEN; i++) {
            builder.append(String.valueOf(i % 10));
        }
        String expected = builder.toString();
        teacher.setName(value);
        assertEquals(teacher.getName(), expected);
    }
    
}
