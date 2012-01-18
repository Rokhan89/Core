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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.github.jgility.core.xml.AbstractXmlProductStroy;

/**
 * Konkrete Klasse für grobe Erfassung einer Anforderung. Implementiert das Marker-Interface
 * {@link IProductRequirement}
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlType( propOrder = { "id", "title", "description", "createDate", "estimated", "priority",
    "requester", "requirementKind" } )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProductStory
    extends AbstractXmlProductStroy
{
    @XmlElement
    private int id;

    @XmlElement
    private String title;

    @XmlElement
    private String description;

    @XmlElement
    private final Calendar createDate;

    @XmlElement
    private float estimated;

    @XmlElement
    private Priority priority;

    @XmlElement
    private String requester;

    @XmlElement
    private RequirementKind requirementKind;

    /**
     * Instanziiert ein Objekt der Klasse {@link ProductStory} und initialisiert "Standard"-Werte
     * als Start-werte.<br>
     * <code>id=0</code><br>
     * <code>title=Default</code><br>
     * <code>description=Default</code><br>
     * <code>estimated=0</code> <br>
     * <code>priority=MINOR</code><br>
     * <code>requester=Nobody</code><br>
     * <code>requirementKind=USER_STORY</code>
     */
    public ProductStory()
    {
        this( 0, "Default", "Default", 0, Priority.MINOR, "Nobody", RequirementKind.USER_STORY );
    }

    /**
     * Instanziiert auf Basis der Parameter ein Objekt der Klasse {@link ProductStory}
     * 
     * @param id ein-eindeutige Anforderungsnummer
     * @param title Titel der Anforderung
     * @param description Beschreibung der Anforderung
     * @param estimated Geschätzte Zeit für Problemlösung der Anforderung
     * @param priority Priorität der Anforderung
     * @param requester Anforderungsstelle der Anforderung
     * @param requirementKind Anforderungsart
     * @throws IllegalArgumentException wenn einer der Parameter keinen gültigen Bereich unterliegt
     */
    public ProductStory( int id, String title, String description, float estimated,
                         Priority priority, String requester, RequirementKind requirementKind )
        throws IllegalArgumentException
    {
        setID( id );
        setDescription( description );
        setEstimated( estimated );
        setPriority( priority );
        setRequester( requester );
        setRequirementKind( requirementKind );
        setTitle( title );
        createDate = Calendar.getInstance();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getID()
     */
    @Override
    public int getID()
    {
        return id;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setID(int id)
     */
    @Override
    public void setID( int id )
        throws IllegalArgumentException
    {
        if ( 0 == this.id )
        {
            if ( 0 <= id )
            {
                this.id = id;
            }
            else
            {
                throw new IllegalArgumentException( "neative id is not allowed" );
            }
        }
        else
        {
            throw new IllegalArgumentException( "change of initialize requirements-id is "
                + "not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getTitle()
     */
    @Override
    public String getTitle()
    {
        return title;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setTitle(java.lang.String)
     */
    @Override
    public void setTitle( String title )
        throws IllegalArgumentException
    {
        if ( StringUtils.isNotBlank( title ) )
        {
            this.title = title;
        }
        else
        {
            throw new IllegalArgumentException( "title has to be not blank!" );
        }

    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setDescription(java.lang.String)
     */
    @Override
    public void setDescription( String description )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, description ) )
        {
            this.description = description;
        }
        else
        {
            throw new IllegalArgumentException( "description has to be not null!" );
        }

    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getCreateDate()
     */
    @Override
    public Calendar getCreateDate()
    {
        return createDate;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getPriority()
     */
    @Override
    public Priority getPriority()
    {
        return priority;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IRequirement#setPriority(com.github.jgility.core.requirement
     * .Priority)
     */
    @Override
    public void setPriority( Priority priority )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, priority ) )
        {
            this.priority = priority;
        }
        else
        {
            throw new IllegalArgumentException( "priority has to be not null!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getRequester()
     */
    @Override
    public String getRequester()
    {
        return requester;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#setRequester(java.lang.String)
     */
    @Override
    public void setRequester( String requester )
        throws IllegalArgumentException
    {
        if ( StringUtils.isNotBlank( requester ) )
        {
            this.requester = requester;
        }
        else
        {
            throw new IllegalArgumentException( "requester has to be not blank!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IRequirement#getRequirementKind()
     */
    @Override
    public RequirementKind getRequirementKind()
    {
        return requirementKind;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IRequirement#setRequirementKind(com.github.jgility.core
     * .requirement.RequirementKind)
     */
    @Override
    public void setRequirementKind( RequirementKind requirementKind )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, requirementKind ) )
        {
            this.requirementKind = requirementKind;
        }
        else
        {
            throw new IllegalArgumentException( "kind of requirement has to be not null!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#getEstimated()
     */
    @Override
    public float getEstimated()
    {
        return estimated;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#getEffective()
     */
    @Override
    public float getEffective()
    {
        return 0;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IEffort#setEstimated(float estimated)
     */
    @Override
    public void setEstimated( float estimated )
        throws IllegalArgumentException
    {
        if ( 0.0f <= estimated )
        {
            this.estimated = estimated;
        }
        else
        {
            throw new IllegalArgumentException( "negativ estimate is not allowed!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ProductStory [id=" + id + ", title=" + title + ", description=" + description
            + ", createDate=" + createDate + ", estimated=" + estimated + ", priority=" + priority
            + ", requester=" + requester + ", requirementKind=" + requirementKind + "]";
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( id );
        builder.append( title );
        builder.append( description );
        builder.append( createDate );
        builder.append( estimated );
        builder.append( priority );
        builder.append( requester );
        builder.append( requirementKind );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof ProductStory )
        {
            ProductStory story = (ProductStory) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( id, story.id );
            builder.append( title, story.title );
            builder.append( description, story.description );
            builder.append( createDate, story.createDate );
            builder.append( estimated, story.estimated );
            builder.append( priority, story.priority );
            builder.append( requester, story.requester );
            builder.append( requirementKind, story.requirementKind );
            return builder.isEquals();
        }
        return false;
    }
}
