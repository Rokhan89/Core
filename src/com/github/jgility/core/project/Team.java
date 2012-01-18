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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Repräsentiert ein Zusammenschluss aus mehreren {@link Person} als Team
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Team implements ITeam
{

    private String name;

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IPerson> members;

    /**
     * Instanziiert ein Objekt der Klasse {@link Team} mit Standardwerten<br>
     * Leere Liste mit Mitgliedern und als Team-Namen (Default)
     */
    public Team()
    {
        this( "Default" );
    }

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

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#getName()
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#setName(java.lang.String)
     */
    @Override
    public void setName( String name )
    {
        if ( StringUtils.isNotBlank( name ) )
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException( "an empty-String is not allowed" );
        }
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#addMember(com.github.jgility.core.project.Person)
     */
    @Override
    public void addMember( IPerson person )
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

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#removeMember(com.github.jgility.core.project.IPerson)
     */
    @Override
    public boolean removeMember( IPerson person )
    {
        if ( ObjectUtils.notEqual( null, person ) )
        {
            return members.remove( person );
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#getMembers()
     */
    @Override
    public List<IPerson> getMembers()
    {
        return Collections.unmodifiableList( new ArrayList<>( members ) );
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.project.ITeam#clearMembers()
     */
    @Override
    public void clearMembers()
    {
        members.clear();
    }
}
