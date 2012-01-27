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
 *     Christoph Viebig
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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.xml.AbstractXmlBacklog;

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
    extends AbstractXmlBacklog<T>
{

    /**
     * Bezeichner der Eigenschaft {@link #requirements}
     */
    private static final String REQUIREMENTS_PROPERTY = "requirements";

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

    /*
     * (non-Javadoc)
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

        List<T> formerRequirements = this.requirements;
        requirements.add( requirement );
        changes.firePropertyChange( REQUIREMENTS_PROPERTY, formerRequirements, this.requirements );
    }

    /*
     * (non-Javadoc)
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

        boolean result;
        List<T> formerRequirements = this.requirements;
        result = requirements.remove( requirement );
        changes.firePropertyChange( REQUIREMENTS_PROPERTY, formerRequirements, this.requirements );
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#getRequirementList()
     */
    @Override
    public List<T> getRequirementList()
    {
        return Collections.unmodifiableList( requirements );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IBacklog#addAllRequirement(java.util.List)
     */
    @Override
    public void addAllRequirement( List<T> requirementList )
    {
        if ( CollectionUtils.isEmpty( requirementList ) )
        {
            throw new IllegalArgumentException( "empty requirement list is not allowed" );
        }

        List<T> formerRequirements = this.requirements;
        requirements.addAll( requirementList );
        changes.firePropertyChange( REQUIREMENTS_PROPERTY, formerRequirements, this.requirements );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "requirements", requirements );
        return builder.build();
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
