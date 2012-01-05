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
package com.github.jgility.core.planning;

import java.util.Calendar;

/**
 * Interface für die Vorschrift einer Plannungs-Klasse. Dient als Schnittstelle zwischen dem Package
 * <code>com.github.jgility.core.planning</code> und <code>com.github.jgility.core.project</code>
 * 
 * @author Karsten Schulz
 */
public interface IPlan
{

    /**
     * Setzt das Start-Datum
     * 
     * @param start
     */
    void setStart( Calendar start );

    /**
     * Gibt das Datum des Starts zurück
     * 
     * @return
     */
    Calendar getStart();

    /**
     * Setzt das Ende-Datum
     * 
     * @param end
     */
    void setEnd( Calendar end );

    /**
     * Gibt das Datum des Ende zurück
     * 
     * @return
     */
    Calendar getEnd();

    /**
     * Setzt den Start und Endzeitpunkt des Plans
     * 
     * @param start Startzeitpunkt
     * @param end Endzeitpunkt
     */
    void changeStartEnd( Calendar start, Calendar end )
        throws IllegalArgumentException;

    /**
     * Gibt die Differenz von Start und Ende in Form eines {@link Long}-Wertes zurück
     * 
     * @return
     */
    long getRange();

    /**
     * Prüft ob der {@link IPlan} beendet ist
     * 
     * @return
     */
    boolean isFinished();

    /**
     * Prüft ob der {@link IPlan} begonnen hat
     * 
     * @return
     */
    boolean isStarted();
}
