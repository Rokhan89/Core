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

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.xml.AbstractXmlBacklog;

/**
 * Schnittstelle für Backlogs im Sinne der agilen Softwareentwicklung
 * 
 * @author Karsten Schulz
 * @version 1.0
 */
@XmlJavaTypeAdapter( AbstractXmlBacklog.Adapter.class )
public interface IBacklog<T>
{

    /**
     * Fügt eine Anforderung der {@link List} hinzu
     * 
     * @param requirement Hinzuzufügende Anforderung
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> ist
     */
    public abstract void addRequirement( T requirement )
        throws IllegalArgumentException;

    /**
     * Entfernt eine Anforderung aus der {@link List}
     * 
     * @param requirement zu löschende Anforderung
     * @return <code>true</code> wenn Element in der Liste ist
     * @throws IllegalArgumentException wenn der Übergabeparameter <code>null</code> enthält
     */
    public abstract boolean removeRequirement( T requirement )
        throws IllegalArgumentException;

    /**
     * Gibt eine unveränderliche Liste Anforderung zurück
     * 
     * @return unveränderte {@link List} mit allen Anforderungen
     */
    public abstract List<T> getRequirementList();

    /**
     * Fügt eine {@link List} von Anforderung der Liste hinzu
     * 
     * @param requirementList hinzuzufügende Liste mit Anforderungen
     */
    public abstract void addAllRequirement( List<T> requirementList );

}