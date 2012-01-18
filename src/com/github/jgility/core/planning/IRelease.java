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

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.xml.AbstractXmlPlan;

/**
 * Interface zur Generalisierung der Planungsstruktur für eine hierachischen Abbildung mit
 * Unterebenen.
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
@XmlJavaTypeAdapter( AbstractXmlPlan.Adapter.class )
public interface IRelease
    extends IPlan
{

    /**
     * Fügt ein neuen {@link IIteration} der Unterstruktur des {@link Release} hinzu
     * 
     * @param iteration neuer {@link IIteration} zum hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn ein Fehler beim Übergabeparameter
     *             entsteht
     */
    void addIteration( IIteration iteration )
        throws IllegalArgumentException;

    /**
     * Fügt alle in der Liste befindlichen Elemente von {@link IIteration} in die Plannungsstruktur
     * {@link IRelease} mit ein
     * 
     * @param iterationCollection eine {@link Collection} mit eine Menge von {@link IIteration} zum
     *            hinzufügen
     * @throws IllegalArgumentException wird geworfen, wenn ein Fehler beim Hinzufügen passiert
     */
    void addAllIterations( Collection<? extends IIteration> iterationCollection )
        throws IllegalArgumentException;

    /**
     * Entfernt ein {@link IIteration} aus der Unterstruktur
     * 
     * @param iteration bestehender {@link IIteration} zum entfernen aus der {@link List}
     * @return Erfolgsrückmeldung ob Element entfernt wurde
     */
    boolean removePlan( IIteration iteration );

    /**
     * Gibt eine unveränderbare Liste der Unterstruktur zurück in Form von Iterationen
     * 
     * @return unveränderliche Liste mit {@link IIteration}
     */
    List<IIteration> getIterationList();

    /**
     * Gibt die Anzahl an Verfügbaren Elementen der Unterbaumstruktur zurück.
     * 
     * @return eine Ganze Zahle
     */
    int size();

    /**
     * Gibt die Iteration an der Stelle <code>index</code> zurück
     * 
     * @param index Stelligkeit innerhalb der Liste
     * @return eine {@link IIteration} an der Stelle <code>index</code>
     */
    IIteration getIteration( int index );
}