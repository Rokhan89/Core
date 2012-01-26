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
package com.github.jgility.core.requirement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.github.jgility.core.project.ITeam;
import com.github.jgility.core.project.Team;

/**
 * Konkrete Klasse für {@link IImplementableRequirement}. Erbt Methoden von {@link ProductStory}
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class ImplementableStory
    extends ProductStory
    implements IImplementableRequirement
{

    /**
     * Bezeichner der Eigenschaft {@link #assignee}
     */
    public static final String ASSIGNEE_PROPERTY = "assignee";

    /**
     * Bezeichner der Eigenschaft {@link #implementState}
     */
    public static final String IMPLEMENT_STATE_PROPERTY = "implementState";

    @XmlElement
    private ImplementState implementState;

    @XmlElement
    private ITeam assignee;

    /**
     * Instanziiert ein Objekt der Klasse {@link ImplementableStory} und erstellt auf Basis von
     * {@link ProductStory#ProductStory()} die Default-Werte
     */
    public ImplementableStory()
    {
        this( new ProductStory(), ImplementState.PENDING );
    }

    /**
     * Instanziiert auf Basis einer Bestehenden {@link ProductStory} eine {@link ImplementableStory}
     * 
     * @param productStory {@link ProductStory}
     * @param implementState {@link ImplementState}
     */
    public ImplementableStory( IProductRequirement productStory, ImplementState implementState )
    {
        this( productStory.getID(), productStory.getTitle(), productStory.getDescription(),
              productStory.getEstimated(), productStory.getPriority(), productStory.getRequester(),
              productStory.getRequirementKind(), implementState );
    }

    /**
     * Instanziiert eine komplett neue {@link ImplementableStory}
     * 
     * @param id Ein-eindeutige Nummer der Anforderung
     * @param title Titel der Anforderung
     * @param description Beschreibung der Anforderung
     * @param estimated Schätzung der Anforderung
     * @param priority Priorität der Anforderung
     * @param requester Anforderungsstellers
     * @param requirementKind Anforderungsart
     * @throws IllegalArgumentException wenn die übergeben Parameter den Wertebereich überschreiten
     */
    public ImplementableStory( int id, String title, String description, float estimated,
                               Priority priority, String requester,
                               RequirementKind requirementKind, ImplementState implementState )
        throws IllegalArgumentException
    {
        super( id, title, description, estimated, priority, requester, requirementKind );
        setImplementState( implementState );
        assignee = new Team( String.valueOf( id ) + "-" + title );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.requirement.IImplementableRequirement#getImplementState()
     */
    @Override
    public ImplementState getImplementState()
    {
        return implementState;
    }

    @Override
    public void setImplementState( ImplementState implementState )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, implementState ) )
        {
            ImplementState formerImplementstate = this.implementState;
            this.implementState = implementState;
            changes.firePropertyChange( ImplementableStory.IMPLEMENT_STATE_PROPERTY,
                                        formerImplementstate, this.implementState );
        }
        else
        {
            throw new IllegalArgumentException( "implementstate has to be not null!" );
        }
    }

    @Override
    public ITeam getAssignee()
    {
        return assignee;
    }

    @Override
    public void setAssignee( ITeam assignee )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, assignee ) )
        {
            ITeam formerAssignee = this.assignee;
            this.assignee = assignee;
            changes.firePropertyChange( ImplementableStory.ASSIGNEE_PROPERTY, formerAssignee,
                                        this.assignee );
        }
        else
        {
            throw new IllegalArgumentException( "assignee has to be not null!" );
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "ImplementableStory [implementState=" + implementState + ", team=" + assignee
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
        builder.append( super.hashCode() );
        builder.append( implementState );
        builder.append( assignee );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof ImplementableStory )
        {
            ImplementableStory story = (ImplementableStory) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( implementState, story.implementState );
            builder.append( assignee, story.assignee );
            return builder.isEquals() && super.equals( obj );
        }

        return false;
    }
}
