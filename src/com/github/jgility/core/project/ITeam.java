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

import java.util.List;

/**
 * Repräsentiert ein Zusammenschluss aus mehreren {@link Person} als Team
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public interface ITeam
{

    /**
     * Gibt den Namen des Teams zurück
     * 
     * @return name als {@link String}
     */
    public abstract String getName();

    /**
     * Setzt den Namen des Teams<br>
     * Parameter <code>name</code> darf nicht <code>null</code> oder <code>""</code> beinhalten
     * 
     * @param name als {@link String}
     */
    public abstract void setName( String name );

    /**
     * Fügt eine neues Teammitglied als {@link Person} dem bestehendem Team hinzu
     * 
     * @param person neues Mitglied des Teams als {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn die Person den Wert <code>null</code>
     *             beinhaltet
     */
    public abstract void addMember( IPerson person )
        throws IllegalArgumentException;

    /**
     * Entfernt auf Basis der {@link Person}-Referenz ein Mitglied aus dem {@link Team}
     * 
     * @param person zu entfernende {@link Person}
     * @return <code>true</code> wenn {@link Person} in der {@link List} enthalten ist
     */
    public abstract boolean removeMember( IPerson person );

    /**
     * Gibt eine unveränderliche {@link List} mit {@link Person}, welche die Mitglieder des
     * {@link Team} repräsentiert
     * 
     * @return unveränderliche {@link List} mit {@link Person}
     */
    public abstract List<IPerson> getMembers();

    /**
     * Entfernt alle Elemente (<code>Person</code>) aus dem Team
     */
    public abstract void clearMembers();

}