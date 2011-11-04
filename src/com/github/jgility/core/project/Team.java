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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Repräsentiert ein Zusammenschluss aus mehreren {@link Person} als Team
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public class Team
{

    private final String name;

    private final List<Person> members;

    /**
     * Instanziiert ein Objekt der Klasse Team auf Basis des Team-Namens
     * 
     * @param name {@link String} als Name des Teams
     */
    public Team( String name )
    {
        this.name = name;
        members = new ArrayList<>();
    }

    /**
     * Gibt den Namen des Teams zurück
     * 
     * @return name als {@link String}
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Fügt eine neues Teammitglied als {@link Person} dem bestehendem Team hinzu
     * 
     * @param person neues Mitglied des Teams als {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn die Person den Wert <code>null</code>
     *             beinhaltet
     */
    public void addMember( Person person )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, person ) )
        {
            members.add( person );
        }
        else
        {
            throw new IllegalArgumentException( "null-person is not allowed!" );
        }
    }

    /**
     * Entfernt auf Basis der {@link Person}-Referenz ein Mitglied aus dem {@link Team}
     * 
     * @param person zu entfernende {@link Person}
     * @return <code>true</code> wenn {@link Person} in der {@link List} enthalten ist
     */
    public boolean removeMember( Person person )
    {
        if ( ObjectUtils.notEqual( null, person ) )
        {
            return members.remove( person );
        }
        return false;
    }

    /**
     * Gibt eine unveränderliche {@link List} mit {@link Person}, welche die Mitglieder des
     * {@link Team} repräsentiert
     * 
     * @return unveränderliche {@link List} mit {@link Person}
     */
    public List<Person> getMembers()
    {
        return Collections.unmodifiableList( members );
    }
}
