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
public class Backlog<T> implements IBacklog<T>
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

    /* (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#addRequirement(T)
     */
    @Override
    public void addRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "null-object is not allowed to add" );
        }

        requirements.add( requirement );
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#removeRequirement(T)
     */
    @Override
    public boolean removeRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "null-object is not allowed to add" );
        }

        return requirements.remove( requirement );
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#getRequirementList()
     */
    @Override
    public List<T> getRequirementList()
    {
        return Collections.unmodifiableList( requirements );
    }

    /* (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#addAllRequirement(java.util.List)
     */
    @Override
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
