/*
 * 
 * Copyright (c) 2011 by Jgility Development Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Karsten Schulz
 *
 */
package com.github.jgility.core.requirement;

import java.util.Calendar;

/**
 * Interface für die Erfassung von Anforderungen
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public interface IRequirement
    extends IEffort
{

    /**
     * Gibt die ein-eindeutige Nummer einer Anforderung zurück
     * 
     * @return Anforderungsnummer als int
     */
    int getID();

    /**
     * Setzt die ein-eindeutige Nummer der Anforderung
     * 
     * @param id Anforderungsnummer als int
     * @throws IllegalArgumentException wenn der Bereich im negativen Bereich liegt oder eine Nummer
     *             bereits vergeben wurde
     */
    void setID( int id )
        throws IllegalArgumentException;

    /**
     * Gibt den Titel der Anforderung zurück
     * 
     * @return Title als {@link String}
     */
    String getTitle();

    /**
     * Gibt den Titel der Anforderung zurück
     * 
     * @return Title als {@link String}
     * @throws IllegalArgumentException wenn der Titel leer ist
     */
    void setTitle( String title )
        throws IllegalArgumentException;

    /**
     * Gibt die Beschreibung der Anforderung zurück
     * 
     * @return Beschreibung der Anforderung
     */
    String getDescription();

    /**
     * Setzt die Beschreibung der Anforderung
     * 
     * @param description Beschreibung der Anforderung als {@link String}
     * @throws IllegalArgumentException wenn die Beschreibung <code>null</code> beinhaltet
     */
    void setDescription( String description )
        throws IllegalArgumentException;

    /**
     * Gibt das Erstelldatum zurück
     * 
     * @return Erstell-Datum als {@link Calendar}
     */
    Calendar getCreateDate();

    /**
     * Gibt die ausgewählte Priorität zurück
     * 
     * @return Priorität als {@link Priority}
     */
    Priority getPriority();

    /**
     * Setzt die Priorität der Anforderung
     * 
     * @param priority als {@link Priority}
     * @throws IllegalArgumentException wenn Priorität <code>null</code> beinhaltet
     */
    void setPriority( Priority priority )
        throws IllegalArgumentException;

    /**
     * Gibt den Namen des Anforderungsstellers zurück
     * 
     * @return Anforderungsstellers als {@link String}
     */
    String getRequester();

    /**
     * Setzt den Namen des Anforderungsstellers
     * 
     * @param requester Anforderungsstellers als {@link String}
     * @throws IllegalArgumentException wenn der Name des Anforderungsstellers leer ist
     */
    void setRequester( String requester )
        throws IllegalArgumentException;

    /**
     * Gibt die Art der Anforderung zurück
     * 
     * @return Art der Anforderung als {@link RequirementKind}
     */
    RequirementKind getRequirementKind();

    /**
     * Setzt die Art der Anforderung
     * 
     * @param requirementKind als {@link RequirementKind}
     * @throws IllegalArgumentException wenn RequirementKind den Wert <code>null</code> beinhaltet
     */
    void setRequirementKind( RequirementKind requirementKind )
        throws IllegalArgumentException;
}
