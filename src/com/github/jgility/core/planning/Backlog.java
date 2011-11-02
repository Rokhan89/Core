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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Abstrakte Klasse zum erstellen von Backlogs im Sinne der agilen Softwareentwicklung
 * 
 * @since 24.10.2011
 * @author Karsten Schulz
 * @version 1.0
 */
public class Backlog<T>
{

    private final List<T> requirements;

    /**
     * Instanziiert ein leeres Backlog mit einer leeren {@link List} von Anforderungen
     */
    public Backlog()
    {
        requirements = new ArrayList<>();
    }

    /**
     * Fügt eine Anforderung der {@link List} hinzu
     * 
     * @param requirement Hinzuzufügende Anforderung
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> ist
     */
    public void addRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "Null-object is not allowed to add" );
        }

        requirements.add( requirement );
    }

    /**
     * Entfernt eine Anforderung aus der {@link List}
     * 
     * @param requirement zu löschende Anforderung
     * @return <code>true</code> wenn Element in der Liste ist
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> enthält
     */
    public boolean removeRequirement( T requirement )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, requirement ) )
        {
            throw new IllegalArgumentException( "Null-object is not allowed to add" );
        }

        return requirements.remove( requirement );
    }

    /**
     * Gibt eine unveränderliche Liste Anforderung zurück
     * 
     * @return unveränderte {@link List} mit allen Anforderungen
     */
    public List<T> getRequirementList()
    {
        return Collections.unmodifiableList( requirements );
    }

    /**
     * Fügt eine {@link List} von Anforderung der Liste hinzu
     * 
     * @param requirementList hinzuzufügende Liste mit Anforderungen
     */
    public void addAllRequirement( List<T> requirementList )
    {
        if ( CollectionUtils.isEmpty( requirementList ) )
        {
            throw new IllegalArgumentException( "Empty requirement list is not allowed" );
        }

        requirements.addAll( requirementList );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Backlog [requirements=" + requirements + "]";
    }

}
