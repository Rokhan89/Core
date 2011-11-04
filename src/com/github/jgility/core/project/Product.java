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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.requirement.IProductRequirement;

/**
 * Klasse, welche das Produkt im Sinne der agilen Softwareentwicklung repräsentiert. Beinhaltet
 * Informationen über den ProductOwner in Form einer {@link Person}-Referenz. Zusätzlich beinhaltet
 * dieser auch den ProductBacklog in Form einer {@link Backlog}-Referenz
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Product
{
    private String name;

    private String description;

    private final List<Project> projects;

    private Person productOwner;

    private Backlog<IProductRequirement> productBacklog;

    /**
     * Parameterloser Konstruktor um ein leeres {@link Product} zu instanziieren.<br>
     * Initialisierte Standard-Werte für Name (<code>Default</code>), Beschreibung (
     * <code>Default</code>) und ein Standard-{@link Person}
     * 
     * @see Person#Person()
     */
    public Product()
    {
        this( "Default", "Default", new Person() );
    }

    /**
     * Instanziiert ein leeres {@link Product} mit Namen, Beschreibung und ProductOwner
     * 
     * @param name Name des {@link Product}
     * @param description Beschreibung des {@link Product}
     * @param productOwner ProductOwner in Form einer {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn der Name oder der ProductOwner invalide
     *             Werte beinhaltet
     * @see #setName(String)
     * @see #setProductOwner(Person)
     */
    public Product( String name, String description, Person productOwner )
        throws IllegalArgumentException
    {
        super();
        setName( name );
        setDescription( description );
        setProductOwner( productOwner );
        productBacklog = new Backlog<>();
        projects = new ArrayList<>();
    }

    /**
     * Gibt den Namen des {@link Product} zurück
     * 
     * @return Name des {@link Product}
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setzt den Namen des {@link Product}
     * 
     * @param name Name des {@link Product}
     * @throws IllegalArgumentException wird geworfen, wenn der Name leer ist
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
            throw new IllegalArgumentException( "A empty name for products is not allowed" );
        }
    }

    /**
     * Gibt die Beschreibung des {@link Product} zurück
     * 
     * @return die Beschreibung des {@link Product}
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Setzt die Beschreibung des {@link Product}
     * 
     * @param description Beschreibung des {@link Product}
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Gibt eine nicht modifizierbare {@link List} mit {@link Project}-Referenzen zurück
     * 
     * @return eine {@link List} mit allen {@link Project}
     */
    public List<Project> getProjects()
    {
        final List<Project> projectList = new ArrayList<>( projects );
        return Collections.unmodifiableList( projectList );
    }

    /**
     * Fügt eine {@link List} von {@link Project} der bestehenden {@link List} hinzu
     * 
     * @param projects {@link List} mit {@link Project} welche hinzugefügt werden sollen
     * @throws IllegalArgumentException wird geworfen, wenn die übergebene {@link List} mit
     *             {@link Project} leer oder <code>null</code>
     */
    public void setProjects( List<Project> projects )
        throws IllegalArgumentException
    {
        if ( null != projects && !projects.isEmpty() )
        {
            this.projects.addAll( projects );
        }
        else
        {
            throw new IllegalArgumentException( "A empty list of projects is not allowed to add" );
        }
    }

    /**
     * Fügt ein neues {@link Project} der {@link List} hinzu
     * 
     * @param newProject neues {@link Project} zum Hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn das {@link Project} den Wert
     *             <code>null</code> beinhaltet
     */
    public void addProject( Project newProject )
        throws IllegalArgumentException
    {
        if ( null != newProject )
        {
            this.projects.add( newProject );
        }
        else
        {
            throw new IllegalArgumentException( "Added project is null" );
        }
    }

    /**
     * Entfernt ein {@link Project} aus der {@link List}
     * 
     * @param removeProject {@link Project} welches entfernt wird
     * @return <code>ture</code> wenn das übergebene {@link Project} aus der {@link List} entfernt
     *         wurde
     */
    public boolean removeProject( Project removeProject )
    {
        if ( null != removeProject )
        {
            return projects.remove( removeProject );
        }
        return false;
    }

    /**
     * Entfernt alle in der {@link List} befindlichen {@link Project}s
     */
    public void clearProject()
    {
        projects.clear();
    }

    /**
     * Gibt den ProductOwner in Form einer {@link Person}-Referenz zurück
     * 
     * @return the productOwner
     */
    public Person getProductOwner()
    {
        return productOwner;
    }

    /**
     * Setzt den ProductOwner als {@link Person}
     * 
     * @param productOwner ProductOwner des {@link Product} als {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn der ProductOwner bereits initialisiert
     *             wurde oder der übergebende ProductOwner den Wert <code>null</code> beinhaltet
     * @see #removeProductOwner()
     */
    public void setProductOwner( Person productOwner )
        throws IllegalArgumentException
    {
        if ( null != this.productOwner )
        {
            throw new IllegalArgumentException( "The productowner is already initialize: "
                + this.productBacklog );
        }
        if ( null != productOwner )
        {
            this.productOwner = productOwner;
        }
        else
        {
            throw new IllegalArgumentException( "Null person is not allowed as productowner" );
        }
    }

    /**
     * Entfernt den ProductOwner
     */
    public void removeProductOwner()
    {
        productOwner = null;
    }

    /**
     * Gibt den ProductBacklog in Form einer {@link Backlog}-Referenz zurück
     * 
     * @return der ProductBacklog als {@link Backlog}
     */
    public Backlog<IProductRequirement> getProductBacklog()
    {
        return productBacklog;
    }

    /**
     * Setzt den ProductBacklog
     * 
     * @param productBacklog zu setzenden {@link Backlog}
     * @throws IllegalArgumentException wird geworfen, wenn der productBacklog bereits initialisiert
     *             wurde oder das übergebende {@link Backlog} <code>null</code> beinhaltet
     * @see #removeProductBacklog()
     */
    public void setProductBacklog( Backlog<IProductRequirement> productBacklog )
        throws IllegalArgumentException
    {
        if ( null != this.productBacklog )
        {
            throw new IllegalArgumentException( "The productbacklog is already initialize" );
        }
        else if ( null != productBacklog )
        {
            this.productBacklog = productBacklog;
        }
        else
        {
            throw new IllegalArgumentException( "Null backlog is not allowed as productbacklog" );
        }
    }

    /**
     * Entfernt den ProductBacklog
     */
    public void removeProductBacklog()
    {
        productBacklog = null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Product [name=" + name + ", description=" + description + ", projects=" + projects
            + ", productOwner=" + productOwner + ", productBacklog=" + productBacklog + "]";
    }

}
