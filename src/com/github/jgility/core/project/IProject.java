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

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.IRelease;
import com.github.jgility.core.xml.AbstractXmlProject;

/**
 * Schnittstelle, welche das Project im Sinne der agilen Softwareentwicklung repräsentiert. Besitzt
 * eine {@link Collection} von {@link Person} als Projektmitglieder, bzw. Bearbeiter und eine
 * {@link Collection} von {@link IPlan} um die Plannungsstruktur abbilden zu können.
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlJavaTypeAdapter( AbstractXmlProject.Adapter.class )
public interface IProject
{

    /**
     * Gibt den Namen des {@link Project} zurück
     * 
     * @return der Name des {@link Project}
     */
    public abstract String getName();

    /**
     * Setzt den Namen des {@link Project}
     * 
     * @param Name des {@link Project}
     * @throws IllegalArgumentException wird geworfen, wenn der Übergabeparameter leer oder
     *             <code>null</code> ist
     */
    public abstract void setName( String name )
        throws IllegalArgumentException;

    /**
     * Gibt die Beschreibung des {@link Project} zurück
     * 
     * @return Beschreibung des {@link Project}
     */
    public abstract String getDescription();

    /**
     * Setzt die Beschreibung des {@link Project}
     * 
     * @param description die Beschreibung des {@link Project}
     */
    public abstract void setDescription( String description );

    /**
     * Gibt eine unmodifizierbare {@link List} der Projektbeteiligten zurück
     * 
     * @return {@link List} von {@link Person}
     */
    public abstract ITeam getTeam();

    /**
     * Bestimmt das Team des Projekts. Prüft ob das Team nicht <code>null</code> ist und
     * Mitgliederzahl größer als 0
     * 
     * @param team als zu bearbeitenden Projektgruppe
     * @throws IllegalArgumentException wenn der Übergabeparameter nicht den Vorgaben entspricht
     */
    public abstract void setTeam( ITeam team );

    /**
     * Fügt eine {@link List} mit Projektbeteiligten der bestehenden {@link List} hinzu
     * 
     * @param members {@link List} mit allen Projektbeteiligten
     * @throws IllegalArgumentException wird geworfen, wenn eine leere {@link List} mit
     *             {@link Person} übergeben wird
     */
    public abstract void setMembers( List<IPerson> members )
        throws IllegalArgumentException;

    /**
     * Fügt einen neue {@link Person} der Mitglieder hinzu
     * 
     * @param newMember neue {@link Person} zum Hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn die neue {@link Person}
     *             <code>null</code> ist
     */
    public abstract void addMember( IPerson newMember )
        throws IllegalArgumentException;

    /**
     * Entfernt auf Basis des Parameters eine {@link Person} aus der {@link List}
     * 
     * @param removeMember zu entfernende {@link Person}
     * @return <code>true</code> wenn die übergebene {@link Person} erfolgreich aus der Liste
     *         entfernt wurde
     */
    public abstract boolean removeMember( IPerson removeMember );

    /**
     * Entfernt alle Projektbeteiligten aus der {@link List}
     */
    public abstract void clearMembers();

    /**
     * Gibt eine {@link List} des Projektplans zurück
     * 
     * @return {@link List} von {@link IRelease}
     */
    public abstract List<IRelease> getReleasePlan();

    /**
     * Setzt die {@link List} des Projektplans
     * 
     * @param projectPlan {@link List} von {@link IRelease}
     * @throws IllegalArgumentException wird geworfen, wenn die {@link List} von {@link IRelease}
     *             leer oder <code>null</code>
     */
    public abstract void setReleasePlan( List<IRelease> projectPlan )
        throws IllegalArgumentException;

    /**
     * Fügt ein neuen {@link IPlan} der {@link List} hinzu
     * 
     * @param newPlan neuer {@link IPlan} zum hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn der {@link IPlan} den Wert
     *             <code>null</code> beinhaltet
     */
    public abstract void addReleasePlan( IRelease newPlan )
        throws IllegalArgumentException;

    /**
     * Entfernt auf Basis des Parameters einen {@link IPlan} aus der {@link List}
     * 
     * @param removePlan zu entfernender {@link IPlan}
     * @return <code>true</code> wenn der übergebende {@link IPlan} erfolgreich aus der {@link List}
     *         entfernt wurde
     */
    public abstract boolean removeReleasePlan( IRelease removePlan );

    /**
     * Entfernt alle geplanten {@link IPlan}-Referenzen aus dem {@link Project}
     */
    public abstract void clearReleasePlan();

}