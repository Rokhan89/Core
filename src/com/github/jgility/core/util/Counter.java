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
package com.github.jgility.core.util;

/**
 * Ein einfache Counter-Klasse
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public class Counter
{

    private int counter;

    /**
     * Instanziiert ein Objekt der Klasse {@link Counter} mit Wert 1
     */
    public Counter()
    {
        this( 1 );
    }

    /**
     * Instanziiert ein Objekt der Klasse {@link Counter} mit dynamischen Startwert
     * 
     * @param counter Startwert des Zählers
     * @throws IllegalArgumentException, wenn der Startwert in einem invaliden Bereich liegt ( 0 <
     *             counter > 2³¹
     */
    public Counter( int counter )
        throws IllegalArgumentException
    {
        setCounter( counter );
    }

    private void setCounter( int counter )
        throws IllegalArgumentException
    {
        if ( 0 > counter || counter > Integer.MAX_VALUE )
        {
            throw new IllegalArgumentException( "counter has invalid range: 0 < counter > 2³¹" );
        }
        this.counter = counter;
    }

    /**
     * Inkrementiert den Zähler
     */
    public void increment()
    {
        setCounter( ++counter );
    }

    /**
     * Dekrementiert den Zähler
     */
    public void decrement()
    {
        setCounter( --counter );
    }

    /**
     * Gibt den aktuellen Wert des Zählers zurück
     * 
     * @return aktueller Zähler
     */
    public int currentValue()
    {
        return counter;
    }

    /**
     * Gibt den aktuellen Wert des Zählers zurück und inkrementiert
     * 
     * @return aktueller Zähler vor der Inkrementierung
     * @throws IllegalArgumentException, wenn der Wert in einem invaliden Bereich liegt ( 0 <
     *             counter > 2³¹
     */
    public int currentValueAndIncrement()
        throws IllegalArgumentException
    {
        int currentValue = currentValue();
        increment();
        return currentValue;
    }

    /**
     * Gibt den aktuellen Wert des Zählers zurück und drekrementiert
     * 
     * @return aktueller Zähler vor der Dekrementierung
     * @throws IllegalArgumentException, wenn der Wert in einem invaliden Bereich liegt ( 0 <
     *             counter > 2³¹
     */
    public int currentValueAndDecrement()
        throws IllegalArgumentException
    {
        int currentValue = currentValue();
        decrement();
        return currentValue;
    }

}
