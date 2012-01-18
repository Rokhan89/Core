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

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.IBacklog;
import com.github.jgility.core.requirement.IProductRequirement;
import com.github.jgility.core.xml.AbstractXmlProduct;

/**
 * Schnittstelle, welche das Produkt im Sinne der agilen Softwareentwicklung repräsentiert.
 * Beinhaltet Informationen über den ProductOwner in Form einer {@link Person}-Referenz. Zusätzlich
 * beinhaltet dieser auch den ProductBacklog in Form einer {@link Backlog}-Referenz
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlJavaTypeAdapter( AbstractXmlProduct.Adapter.class )
public interface IProduct
{

    /**
     * Gibt den Namen des {@link Product} zurück
     * 
     * @return Name des {@link Product}
     */
    public abstract String getName();

    /**
     * Setzt den Namen des {@link Product}
     * 
     * @param name Name des {@link Product}
     * @throws IllegalArgumentException wird geworfen, wenn der Name leer ist
     */
    public abstract void setName( String name )
        throws IllegalArgumentException;

    /**
     * Gibt die Beschreibung des {@link Product} zurück
     * 
     * @return die Beschreibung des {@link Product}
     */
    public abstract String getDescription();

    /**
     * Setzt die Beschreibung des {@link Product}
     * 
     * @param description Beschreibung des {@link Product}
     */
    public abstract void setDescription( String description );

    /**
     * Gibt eine nicht modifizierbare {@link List} mit {@link Project}-Referenzen zurück
     * 
     * @return eine {@link List} mit allen {@link Project}
     */
    public abstract List<IProject> getProjects();

    /**
     * Fügt eine {@link List} von {@link Project} der bestehenden {@link List} hinzu
     * 
     * @param projects {@link List} mit {@link Project} welche hinzugefügt werden sollen
     * @throws IllegalArgumentException wird geworfen, wenn die übergebene {@link List} mit
     *             {@link Project} leer oder <code>null</code>
     */
    public abstract void setProjects( List<IProject> projects )
        throws IllegalArgumentException;

    /**
     * Fügt ein neues {@link Project} der {@link List} hinzu
     * 
     * @param newProject neues {@link Project} zum Hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn das {@link Project} den Wert
     *             <code>null</code> beinhaltet
     */
    public abstract void addProject( IProject newProject )
        throws IllegalArgumentException;

    /**
     * Entfernt ein {@link Project} aus der {@link List}
     * 
     * @param removeProject {@link Project} welches entfernt wird
     * @return <code>ture</code> wenn das übergebene {@link Project} aus der {@link List} entfernt
     *         wurde
     */
    public abstract boolean removeProject( IProject removeProject );

    /**
     * Entfernt alle in der {@link List} befindlichen {@link Project}s
     */
    public abstract void clearProject();

    /**
     * Gibt den ProductOwner in Form einer {@link Person}-Referenz zurück
     * 
     * @return the productOwner
     */
    public abstract IPerson getProductOwner();

    /**
     * Setzt den ProductOwner als {@link Person}
     * 
     * @param productOwner ProductOwner des {@link Product} als {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn der ProductOwner bereits initialisiert
     *             wurde oder der übergebende ProductOwner den Wert <code>null</code> beinhaltet
     * @see #removeProductOwner()
     */
    public abstract void setProductOwner( IPerson productOwner )
        throws IllegalArgumentException;

    /**
     * Entfernt den ProductOwner
     */
    public abstract void removeProductOwner();

    /**
     * Gibt den ProductBacklog in Form einer {@link Backlog}-Referenz zurück
     * 
     * @return der ProductBacklog als {@link Backlog}
     */
    public abstract IBacklog<IProductRequirement> getProductBacklog();

    /**
     * Setzt den ProductBacklog
     * 
     * @param productBacklog zu setzenden {@link Backlog}
     * @throws IllegalArgumentException wird geworfen, wenn der productBacklog bereits initialisiert
     *             wurde oder das übergebende {@link Backlog} <code>null</code> beinhaltet
     * @see #removeProductBacklog()
     */
    public abstract void setProductBacklog( IBacklog<IProductRequirement> productBacklog )
        throws IllegalArgumentException;

    /**
     * Entfernt den ProductBacklog
     */
    public abstract void removeProductBacklog();

}