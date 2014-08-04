package de.unibremen.swp.stundenplan.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import de.unibremen.swp.stundenplan.persistence.Data;

public class Subject implements Serializable {

    /**
     * Die eineindeutige ID für Serialisierung.
     */
    private static final long serialVersionUID = 2597139574206115533L;
    
    /**
     * Der Name diese Faches.
     */
    @Column(nullable = false, length = Data.MAX_NORMAL_STRING_LEN)
    private String name;

    /**
     * Das K�rzel dieses Faches. Ein Kürzel muss systemweit eindeutig sein.
     */
    @Id
    @Column(length = Data.MAX_ACRONYM_LEN)
    private String acronym;
    
    /**
     * Gibt den Namen dieses Faches zurück.
     * 
     * @return den Namen dieses Faches
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen dieses faches auf den gegebenen Wert. Ein Parameterwert von {@code null} wird ignoriert.
     * 
     * @param pName
     *            der neue Name dieses Faches (falls nicht {@code null}
     */
    public void setName(final String pName) {
        name = pName;
    }
}
