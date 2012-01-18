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
package com.github.jgility.core.project;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.xml.AbstractXmlPlan;

/**
 * Schnittstelle, welche ein Nutzer bzw. eine Person im Sinne der agilen Softwareentwicklung
 * repräsentiert. Findet Verwendung als Teammitglied des {@link Project} oder ProductOwner als
 * Besitzer des {@link Product}.
 * 
 * @author Karsten Schulz
 * @version 1.0
 */
@XmlJavaTypeAdapter( AbstractXmlPlan.Adapter.class )
public interface IPerson
{

    /**
     * Setzt den Vornamen der Person und überprüft dessen Wert
     * 
     * @param firstname Vorname der Person.<br>
     *            Es wird überprüft ob der erste Buchstabe ein Großbuchstabe ist.<br>
     *            Leere Eingaben oder <code>null</code> ist nicht erlaubt
     * @throws IllegalArgumentException wird geworfen, wenn einer der Bedingungen nicht zutrifft
     */
    public abstract void setFirstname( String firstname )
        throws IllegalArgumentException;

    /**
     * Setzt den Nachnamen und überprüft dessen Wert
     * 
     * @param surname Nachname der Person.<br>
     *            Identische Prüfung wie für <code>firstname</code>
     * @see #setFirstname(String)
     * @throws IllegalArgumentException wird geworfen, wenn einer der Bedingungen nicht zutrifft
     */
    public abstract void setSurname( String surname )
        throws IllegalArgumentException;

    /**
     * Setzt die E-Mail-Adresse und überprüft dessen Wert
     * 
     * @param eMail E-Mail-Adresse <br>
     *            muss dem Standard entsprechen. Zum Beispiel: <code>example@mail.com</code>
     * @throws IllegalArgumentException wird geworfen, wenn Parameter keine valide E-Mail-Adresse
     *             ist
     */
    public abstract void setEMail( String eMail )
        throws IllegalArgumentException;

    /**
     * Gibt den Vornamen zurück
     * 
     * @return firstname
     */
    public abstract String getFirstname();

    /**
     * Gibt den Nachnamen zurück
     * 
     * @return surname
     */
    public abstract String getSurname();

    /**
     * Gibt die E-Mail-Adresse zurück
     * 
     * @return eMail
     */
    public abstract String getEMail();

}