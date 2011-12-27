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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Abstrakte Klasse zum erstellen von Backlogs im Sinne der agilen Softwareentwicklung
 * 
 * @since 24.10.2011
 * @author Karsten Schulz
 * @version 1.0
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Backlog<T>
{

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<T> requirements;

    /**
     * Instanziiert ein leeres Backlog mit einer leeren {@link List} von Anforderungen
     */
    public Backlog()
    {
        requirements = new ArrayList<>();
    }

    /**
     * Fügt eine Anforderung der {@link List} hinzu
     * 
     * @param requirement Hinzuzufügende Anforderung
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> ist
     */
    public void addRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "null-object is not allowed to add" );
        }

        requirements.add( requirement );
    }

    /**
     * Entfernt eine Anforderung aus der {@link List}
     * 
     * @param requirement zu löschende Anforderung
     * @return <code>true</code> wenn Element in der Liste ist
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> enthält
     */
    public boolean removeRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "null-object is not allowed to add" );
        }

        return requirements.remove( requirement );
    }

    /**
     * Gibt eine unveränderliche Liste Anforderung zurück
     * 
     * @return unveränderte {@link List} mit allen Anforderungen
     */
    public List<T> getRequirementList()
    {
        return Collections.unmodifiableList( requirements );
    }

    /**
     * Fügt eine {@link List} von Anforderung der Liste hinzu
     * 
     * @param requirementList hinzuzufügende Liste mit Anforderungen
     */
    public void addAllRequirement( List<T> requirementList )
    {
        if ( CollectionUtils.isEmpty( requirementList ) )
        {
            throw new IllegalArgumentException( "empty requirement list is not allowed" );
        }

        requirements.addAll( requirementList );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Backlog [requirements=" + requirements + "]";
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( requirements );
        return builder.toHashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Backlog )
        {
            Backlog<T> backlog = (Backlog<T>) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( requirements, backlog.requirements );
            return builder.isEquals();
        }

        return false;
    }

}
