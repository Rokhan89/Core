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
import java.beans.PropertyChangeSupport;

/**
 * Implementiert grundlegende Funktionalitäten für Modellobjekte
 * 
 * @author Christoph Viebig
 */
public class ModelObject
    implements IModelObject
{
    /**
     * Verwaltet die PropertyChangeListener
     */
    protected PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Initialisiert ModelObject und die Datenstruktur für die Listener
     */
    public ModelObject()
    {
        // empty
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#addPropertyChangeListener(com.github.jgility.core.IModelObject.
     * PropertyChangeListener)
     */
    @Override
    public synchronized void addPropertyChangeListener( PropertyChangeListener listener )
    {
        changes.addPropertyChangeListener( listener );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#addPropertyChangeListener(java.lang.String,
     * com.github.jgility.core.IModelObject.PropertyChangeListener)
     */
    @Override
    public synchronized void addPropertyChangeListener( String propertyName, PropertyChangeListener listener )
    {
        changes.addPropertyChangeListener( propertyName, listener );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#removePropertyChangeListener(com.github.jgility.core.IModelObject.
     * PropertyChangeListener)
     */
    @Override
    public synchronized void removePropertyChangeListener( PropertyChangeListener listener )
    {
        changes.removePropertyChangeListener( listener );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#removePropertyChangeListener(java.lang.String,
     * com.github.jgility.core.IModelObject.PropertyChangeListener)
     */
    @Override
    public synchronized void removePropertyChangeListener( String propertyName, PropertyChangeListener listener )
    {
        changes.removePropertyChangeListener( propertyName, listener );
    }

    @Override
    public boolean hasListeners( String propertyName )
    {
        return changes.hasListeners( propertyName );
    }

}
