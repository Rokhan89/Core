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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.IRelease;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.xml.AbstractXmlProject;

/**
 * Klasse, welche das Project im Sinne der agilen Softwareentwicklung repräsentiert. Besitzt eine
 * {@link Collection} von {@link Person} als Projektmitglieder, bzw. Bearbeiter und eine
 * {@link Collection} von {@link IPlan} um die Plannungsstruktur abbilden zu können.
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlSeeAlso( Release.class )
@XmlAccessorType( XmlAccessType.FIELD )
public class Project
    extends AbstractXmlProject
{
    @XmlElement
    private String name;

    @XmlElement
    private String description;

    @XmlElement
    private ITeam team;

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IRelease> releasePlan;

    /**
     * Parameterloser Konstruktor um ein leeres {@link Project} zu instanziieren.<br>
     * Initialisiert Standard-Werte für Name (<code>Default</code>) und Beschreibung (
     * <code>Default</code>)
     */
    public Project()
    {
        this( "Default", "Default" );
    }

    /**
     * Instanziiert ein leeres {@link Project}-Objekt mit Namen und Beschreibung
     * 
     * @param name Name des Projekts
     * @param description Beschreibung des Projekts
     * @throws IllegalArgumentException wenn der Name nicht den Vorgaben entspricht
     * @see #setName(String)
     */
    public Project( String name, String description )
        throws IllegalArgumentException
    {
        super();
        setName( name );
        setDescription( description );
        team = new Team( name );
        releasePlan = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#getName()
     */
    @Override
    public String getName()
    {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#setName(java.lang.String)
     */
    @Override
    public void setName( String name )
        throws IllegalArgumentException
    {
        if ( StringUtils.isNotBlank( name ) )
        {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException( "empty name for projects is not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#setDescription(java.lang.String)
     */
    @Override
    public void setDescription( String description )
    {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#getTeam()
     */
    @Override
    public ITeam getTeam()
    {
        return this.team;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#setTeam(com.github.jgility.core.project.Team)
     */
    @Override
    public void setTeam( ITeam team )
    {
        if ( null != team && 0 < team.getMembers().size() )
        {
            this.team = team;
        }
        else
        {
            throw new IllegalArgumentException( "empty team is not allowed to set" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#setMembers(java.util.List)
     */
    @Override
    public void setMembers( List<IPerson> members )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( members ) )
        {
            for ( IPerson member : members )
            {
                this.team.addMember( member );
            }
        }
        else
        {
            throw new IllegalArgumentException( "empty list of person is not allowed to add" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProject#addMember(com.github.jgility.core.project.Person)
     */
    @Override
    public void addMember( IPerson newMember )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, newMember ) )
        {
            team.addMember( newMember );
        }
        else
        {
            throw new IllegalArgumentException( "empty person is not allowed to add" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProject#removeMember(com.github.jgility.core.project.IPerson
     * )
     */
    @Override
    public boolean removeMember( IPerson removeMember )
    {
        if ( ObjectUtils.notEqual( null, removeMember ) )
        {
            return team.removeMember( removeMember );
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#clearMembers()
     */
    @Override
    public void clearMembers()
    {
        team.clearMembers();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#getReleasePlan()
     */
    @Override
    public List<IRelease> getReleasePlan()
    {
        return Collections.unmodifiableList( releasePlan );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#setReleasePlan(java.util.List)
     */
    @Override
    public void setReleasePlan( List<IRelease> projectPlan )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( projectPlan ) )
        {
            this.releasePlan.addAll( projectPlan );
        }
        else
        {
            throw new IllegalArgumentException( "empty list of IPlan is not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProject#addReleasePlan(com.github.jgility.core.planning.
     * IRelease)
     */
    @Override
    public void addReleasePlan( IRelease newPlan )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, newPlan ) )
        {
            this.releasePlan.add( newPlan );
        }
        else
        {
            throw new IllegalArgumentException( "empty new IPlan is not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProject#removeReleasePlan(com.github.jgility.core.planning
     * .IRelease)
     */
    @Override
    public boolean removeReleasePlan( IRelease removePlan )
    {
        if ( ObjectUtils.notEqual( null, removePlan ) )
        {
            return releasePlan.remove( removePlan );
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProject#clearReleasePlan()
     */
    @Override
    public void clearReleasePlan()
    {
        releasePlan.clear();
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( name );
        builder.append( description );
        builder.append( team );
        builder.append( releasePlan );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Project )
        {
            IProject project = (IProject) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( name, project.getName() );
            builder.append( description, project.getDescription() );
            builder.append( team, project.getTeam() );
            builder.append( releasePlan, project.getReleasePlan() );
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Project [name=" + name + ", description=" + description + ", members=" + team
            + ", projectPlan=" + releasePlan + "]";
    }

}
