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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.Release;

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
{
    private String name;

    private String description;

    private Team team;

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final Set<IPlan> projectPlan;

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
        projectPlan = new HashSet<>();
    }

    /**
     * Gibt den Namen des {@link Project} zurück
     * 
     * @return der Name des {@link Project}
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setzt den Namen des {@link Project}
     * 
     * @param Name des {@link Project}
     * @throws IllegalArgumentException wird geworfen, wenn der Übergabeparameter leer oder
     *             <code>null</code> ist
     */
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

    /**
     * Gibt die Beschreibung des {@link Project} zurück
     * 
     * @return Beschreibung des {@link Project}
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Setzt die Beschreibung des {@link Project}
     * 
     * @param description die Beschreibung des {@link Project}
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Gibt eine unmodifizierbare {@link List} der Projektbeteiligten zurück
     * 
     * @return {@link List} von {@link Person}
     */
    public Team getTeam()
    {
        return this.team;
    }

    /**
     * Bestimmt das Team des Projekts. Prüft ob das Team nicht <code>null</code> ist und
     * Mitgliederzahl größer als 0
     * 
     * @param team als zu bearbeitenden Projektgruppe
     * @throws IllegalArgumentException wenn der Übergabeparameter nicht den Vorgaben entspricht
     */
    public void setTeam( Team team )
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

    /**
     * Fügt eine {@link List} mit Projektbeteiligten der bestehenden {@link List} hinzu
     * 
     * @param members {@link List} mit allen Projektbeteiligten
     * @throws IllegalArgumentException wird geworfen, wenn eine leere {@link List} mit
     *             {@link Person} übergeben wird
     */
    public void setMembers( List<Person> members )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( members ) )
        {
            for ( Person member : members )
            {
                this.team.addMember( member );
            }
        }
        else
        {
            throw new IllegalArgumentException( "empty list of person is not allowed to add" );
        }
    }

    /**
     * Fügt einen neue {@link Person} der Mitglieder hinzu
     * 
     * @param newMember neue {@link Person} zum Hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn die neue {@link Person}
     *             <code>null</code> ist
     */
    public void addMember( Person newMember )
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

    /**
     * Entfernt auf Basis des Parameters eine {@link Person} aus der {@link List}
     * 
     * @param removeMember zu entfernende {@link Person}
     * @return <code>true</code> wenn die übergebene {@link Person} erfolgreich aus der Liste
     *         entfernt wurde
     */
    public boolean removeMember( Person removeMember )
    {
        if ( ObjectUtils.notEqual( null, removeMember ) )
        {
            return team.removeMember( removeMember );
        }
        return false;
    }

    /**
     * Entfernt alle Projektbeteiligten aus der {@link List}
     */
    public void clearMembers()
    {
        team.clearMembers();
    }

    /**
     * Gibt eine {@link List} des Projektplans zurück
     * 
     * @return {@link List} von {@link IPlan}
     */
    public List<IPlan> getProjectPlan()
    {
        final List<IPlan> planList = new ArrayList<>( projectPlan );
        return Collections.unmodifiableList( planList );
    }

    /**
     * Setzt die {@link List} des Projektplans
     * 
     * @param projectPlan {@link List} von {@link IPlan}
     * @throws IllegalArgumentException wird geworfen, wenn die {@link List} von {@link IPlan} leer
     *             oder <code>null</code>
     */
    public void setProjectPlan( List<IPlan> projectPlan )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( projectPlan ) )
        {
            this.projectPlan.addAll( projectPlan );
        }
        else
        {
            throw new IllegalArgumentException( "empty list of IPlan is not allowed" );
        }
    }

    /**
     * Fügt ein neuen {@link IPlan} der {@link List} hinzu
     * 
     * @param newPlan neuer {@link IPlan} zum hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn der {@link IPlan} den Wert
     *             <code>null</code> beinhaltet
     */
    public void addProjectPlan( IPlan newPlan )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, newPlan ) )
        {
            this.projectPlan.add( newPlan );
        }
        else
        {
            throw new IllegalArgumentException( "empty new IPlan is not allowed" );
        }
    }

    /**
     * Entfernt auf Basis des Parameters einen {@link IPlan} aus der {@link List}
     * 
     * @param removePlan zu entfernender {@link IPlan}
     * @return <code>true</code> wenn der übergebende {@link IPlan} erfolgreich aus der {@link List}
     *         entfernt wurde
     */
    public boolean removeProjectPlan( IPlan removePlan )
    {
        if ( ObjectUtils.notEqual( null, removePlan ) )
        {
            return projectPlan.remove( removePlan );
        }
        return false;
    }

    /**
     * Entfernt alle geplanten {@link IPlan}-Referenzen aus dem {@link Project}
     */
    public void clearProjectPlan()
    {
        projectPlan.clear();
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( name );
        builder.append( description );
        builder.append( team );
        builder.append( projectPlan );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Project )
        {
            Project project = (Project) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( name, project.getName() );
            builder.append( description, project.getDescription() );
            builder.append( team, project.getTeam() );
            builder.append( projectPlan, project.getProjectPlan() );
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Project [name=" + name + ", description=" + description + ", members=" + team
            + ", projectPlan=" + projectPlan + "]";
    }

}
