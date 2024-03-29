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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class IterationStory
    extends ImplementableStory
    implements IIterationRequirement
{

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IRequirement> dependencies;

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IImplementableRequirement> tasks;

    /**
     * Instanziiert ein Objekt der Klasse {@link IterationStory} mit Default-Werten der Klasse
     * {@link ProductStory#ProductStory()}
     */
    public IterationStory()
    {
        this( new ProductStory(), ImplementState.PENDING );
    }

    /**
     * Instanziiert ein Objekt der Klasse {@link IterationStory} auf Basis einer bestehendem
     * {@link ProductStory}
     * 
     * @param productStory referenzierende {@link ProductStory} (nur Inhalt wird kopiert)
     * @param implementState Start-Implementierung
     */
    public IterationStory( IProductRequirement productRequirement, ImplementState implementState )
    {
        super( productRequirement, implementState );
        dependencies = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    /**
     * Instanziiert ein Objekt der Klasse {@link IterationStory} auf Basis der Parameterliste
     * 
     * @param id Ein-eindeutige Anforderungsnummer
     * @param title Titel der Anforderung
     * @param description Beschreibung der Anforderung
     * @param estimated Schätzung der Anforderung
     * @param priority Priorität der Anforderung
     * @param requester Anforderungssteller
     * @param requirementKind Anforderungsart
     * @throws IllegalArgumentException wird geworfen, wenn die Gültigkeitsprüfung fehlschlägt
     */
    public IterationStory( int id, String title, String description, float estimated,
                           Priority priority, String requester, RequirementKind requirementKind )
        throws IllegalArgumentException
    {
        super( id, title, description, estimated, priority, requester, requirementKind,
               ImplementState.PENDING );
        dependencies = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IIterationRequirement#addDependency(com.github.jgility
     * .core.requirement.IRequirement)
     */
    @Override
    public void addDependency( IRequirement requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, requirement ) )
        {
            dependencies.add( requirement );
        }
        else
        {
            throw new IllegalArgumentException( "null requirement as dependencie is not allowed!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IIterationRequirement#removeDependency(com.github.jgility
     * .core.requirement.IRequirement)
     */
    @Override
    public boolean removeDependency( IRequirement requirement )
    {
        if ( ObjectUtils.notEqual( null, requirement ) )
        {
            return dependencies.remove( requirement );
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IIterationRequirement#getDependencies()
     */
    @Override
    public List<IRequirement> getDependencies()
    {
        return Collections.unmodifiableList( dependencies );
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IIterationRequirement#addTask(com.github.jgility.core
     * .requirement.IImplementableRequirement)
     */
    @Override
    public void addTask( IImplementableRequirement task )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, task ) )
        {
            if ( task.getRequirementKind() == RequirementKind.TASK )
            {
                tasks.add( task );
            }
            else
            {
                throw new IllegalArgumentException( "implementable requirement has to be TASK: "
                    + task.getRequirementKind() );
            }
        }
        else
        {
            throw new IllegalArgumentException( "null-object is not allowed!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.requirement.IIterationRequirement#removeTask(com.github.jgility.core
     * .requirement.IImplementableRequirement)
     */
    @Override
    public boolean removeTask( IImplementableRequirement task )
    {
        if ( ObjectUtils.notEqual( null, task ) )
        {
            return tasks.remove( task );
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IIterationRequirement#getTaskList()
     */
    @Override
    public List<IImplementableRequirement> getTaskList()
    {
        return Collections.unmodifiableList( tasks );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "IterationStory [dependencies=" + dependencies + ", tasks=" + tasks
            + ", getImplementState()=" + getImplementState() + ", getAssignee()=" + getAssignee()
            + ", getID()=" + getID() + ", getTitle()=" + getTitle() + ", getDescription()="
            + getDescription() + ", getCreateDate()=" + getCreateDate() + ", getPriority()="
            + getPriority() + ", getRequester()=" + getRequester() + ", getRequirementKind()="
            + getRequirementKind() + ", getEstimated()=" + getEstimated() + ", getEffective()="
            + getEffective() + "]";
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( dependencies );
        builder.append( tasks );
        builder.append( super.hashCode() );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof IterationStory )
        {
            IterationStory story = (IterationStory) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( tasks, story.tasks );
            builder.append( dependencies, story.dependencies );
            return builder.isEquals() && super.equals( obj );
        }
        return super.equals( obj );
    }
}
