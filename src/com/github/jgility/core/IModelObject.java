/*
 * 
 * Copyright (c) 2011 by Jgility Development Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christoph Viebig
 *
 */
package com.github.jgility.core;

import java.beans.PropertyChangeListener;

/**
 * Schnittstelle mit grundlegenden Funktionalitäten für Modellobjekte
 * 
 * @author Christoph Viebig
 */
public interface IModelObject
{
    /**
     * Registriert einen PropertyChangeListener, welcher Benachrichtigungen über Änderungen 
     * an allen Eigenschaften entgegennimmt. 
     * 
     * @param listener PropertyChangeListener, welcher die Benachrichtigungen entgegennimmt
     */
    void addPropertyChangeListener( PropertyChangeListener listener );
    
    /**
     * Registriert einen PropertyChangeListener, welcher Benachrichtigungen über Änderungen 
     * einer bestimmten Eigenschaft entgegennimmt. 
     * 
     * @param propertyName Name der Eigenschaft, deren Änderungen überwacht werden sollen
     * @param listener PropertyCangeListener, welcher die Benachrichtigungen entgegennimmt
     */
    void addPropertyChangeListener( String propertyName, PropertyChangeListener listener );
    
    /**
     * Löscht einen PropertyChangeListener
     * 
     * @param listener PropertyChangeListener, welche die Benachrichtigungen bisher entgegennahm
     */
    void removePropertyChangeListener( PropertyChangeListener listener );
    
    /**
     * Löscht einen PropertyChangeListener
     * 
     * @param propertyName Name der Eigenschaft, deren Änderungen bisher überwacht wurden
     * @param listener PropertyChangeListener, welcher die Benachrichtigungen bisher entgegennahm
     */
    void removePropertyChangeListener( String propertyName, PropertyChangeListener listener );
    
    /**
     * Gibt an, ob Listener registriert sind
     * 
     * @param propertyName Name der Eigenschaft, deren Änderungen überwacht werden
     */
    boolean hasListeners(String propertyName);
}
