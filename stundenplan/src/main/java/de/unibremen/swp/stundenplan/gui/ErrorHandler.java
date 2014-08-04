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

import javax.swing.JOptionPane;

import de.unibremen.swp.stundenplan.config.Messages;

/**
 * Stellt eine Utility-Klasse für die Fehlerbehandlung bereit. Aktuell wird lediglich eine Fehlernachricht angezeigt und
 * dann die gesamte Applikation geschlossen.
 * 
 * @author K. Hölscher
 * @version 0.1
 */
public final class ErrorHandler {

    /**
     * Das Hauptfenster, das im Fehlerfall geschlossen wird.
     */
    private static MainFrame mainFrame;

    /**
     * Privater Konstruktor, der eine Instanziierung dieser Utility-Klasse verhindert.
     */
    private ErrorHandler() {
    }

    /**
     * Setzt das Hauptfenster für diese Fehlerbehandlung auf das übergebene Fenster. Löst eine
     * {@link IllegalArgumentException} aus, falls der Parameterwert {@code null} ist.
     * 
     * @param pMainFrame
     *            das Hauptfenster für diese Fehlerbehandlung
     */
    public static void setMainFrame(final MainFrame pMainFrame) {
        if (pMainFrame == null) {
            throw new IllegalArgumentException("Main frame is null!");
        }
        mainFrame = pMainFrame;
    }

    /**
     * Zeigt ein Fenster mit einer Fehlernachricht an und beendet danach die Anwendung durch Schließen des
     * Hauptfensters. Die Fehlernachricht gibt lediglich an, dass es sich um ein Problem mit dem unterliegenden
     * Datenbestand handelt.
     * 
     * Falls das Hauptfenster noch nicht gesetzt wurde, wird eine {@link IllegalStateException} ausgelöst.
     * 
     */
    public static void criticalDatasetError() {
        JOptionPane.showMessageDialog(mainFrame, Messages.getString("MainFrame.datasetError"),
                Messages.getString("MainFrame.datasetErrorTitle"), JOptionPane.ERROR_MESSAGE);
        if (mainFrame == null) {
            throw new IllegalStateException("The main frame has not been set yet!");
        }
        mainFrame.setVisible(false);
        mainFrame.dispose();
    }

}
