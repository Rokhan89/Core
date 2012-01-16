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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementiert grundlegende Funktionalitäten für Modellobjekte
 * 
 * @author Christoph Viebig
 */
public class ModelObject
    implements IModelObject
{
    /**
     * Identifier für Listener, welche Benachrichtigungen für alle Eigenschaften erwarten
     */
    public final String ALL_PROPERTIES = "";
    
    /**
     * Beinhaltet die Datenstruktur für die Listener
     */
    private Map<String, List<PropertyChangeListener>> listeners;

    /**
     * Initialisiert ModelObject und die Datenstruktur für die Listener
     */
    public ModelObject()
    {
        this.listeners = new HashMap<>();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#addPropertyChangeListener(com.github.jgility.core.IModelObject.
     * PropertyChangeListener)
     */
    @Override
    public synchronized void addPropertyChangeListener( PropertyChangeListener listener )
    {
        addPropertyChangeListener( ALL_PROPERTIES, listener );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#addPropertyChangeListener(java.lang.String,
     * com.github.jgility.core.IModelObject.PropertyChangeListener)
     */
    @Override
    public synchronized void addPropertyChangeListener( String propertyName, PropertyChangeListener listener )
    {
        if ( this.listeners.containsKey( propertyName ) )
        {
            this.listeners.get( propertyName ).add( listener );
        }
        else
        {
            List<PropertyChangeListener> list = new ArrayList<>();
            list.add( listener );
            this.listeners.put( propertyName, list );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#removePropertyChangeListener(com.github.jgility.core.IModelObject.
     * PropertyChangeListener)
     */
    @Override
    public synchronized void removePropertyChangeListener( PropertyChangeListener listener )
    {
        removePropertyChangeListener( ALL_PROPERTIES, listener );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.IModelObject2#removePropertyChangeListener(java.lang.String,
     * com.github.jgility.core.IModelObject.PropertyChangeListener)
     */
    @Override
    public synchronized void removePropertyChangeListener( String propertyName, PropertyChangeListener listener )
    {
        if ( this.listeners.containsKey( propertyName ) && this.listeners.get( propertyName ).contains( listener ) )
        {
            this.listeners.get( propertyName ).remove( listener );
        }
    }

    /**
     * Feuert ein PropertyChangeEvent an die registrierten Listener
     * 
     * @param propertyName Name der Eigenschaft
     * @param oldValue Alter Wert der Eigenschaft
     * @param newValue Neuer Wert der Eigenschaft
     */
    protected synchronized void firePropertyChangeEvent( String propertyName, Object oldValue, Object newValue )
    {
        PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent( this, propertyName, oldValue, newValue );
        String[] indices = { ALL_PROPERTIES, propertyName };
        for ( String index : indices )
        {
            if ( this.listeners.containsKey( index ) )
            {
                for ( PropertyChangeListener listener : listeners.get( index ) )
                {
                    listener.propertyChange( propertyChangeEvent );
                }
            }
        }
    }

}
