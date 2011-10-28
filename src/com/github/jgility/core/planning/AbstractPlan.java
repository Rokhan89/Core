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

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Abstrakte Klasse und implementiert die Grundfunktionalitäten einer planbaren Datenstruktur.
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public abstract class AbstractPlan
    implements IPlan
{

    private Calendar start;

    private Calendar end;

    /**
     * Instanziiert ein Objekt der abstrakten Klasse {@link AbstractPlan}. Der Startwert is das
     * aktuelle {@link Calendar}-Objekt. Das Ende ist auf 14-Tage datiert.
     */
    public AbstractPlan()
    {
        Calendar calStart = new GregorianCalendar();
        Calendar calEnd = new GregorianCalendar();
        calEnd.add( Calendar.DATE, 14 );
        start = calStart;
        end = calEnd;
    }

    /**
     * Instanziiert ein Objekt der abstrakten Klasse {@link AbstractPlan} mit übergebenden Start-
     * und End-Zeitpunkt. Die {@link Calendar}-Objekte werden innerhalb des Konstruktors nur als
     * Hilfsmittel verwendet. Der Inhalt wird kopiert
     * 
     * @param start Start-Datum/Uhrzeit als {@link Calendar}
     * @param end End-Datum/Uhrzeit als {@link Calendar}
     */
    public AbstractPlan( Calendar start, Calendar end )
    {
        this.start = (Calendar) start.clone();
        this.end = (Calendar) end.clone();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#setStart(java.util.Calendar)
     */
    @Override
    public void setStart( Calendar start )
        throws IllegalArgumentException
    {
        if ( start == null )
        {
            throw new IllegalArgumentException( "Null-Object as start-time is not allowed" );
        }
        if ( end.after( start ) )
        {
            this.start = start;
        }

        throw new IllegalArgumentException( "Start-time have to before end-time: " );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#getStart()
     */
    @Override
    public Calendar getStart()
    {
        return (Calendar) start.clone();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#setEnd(java.util.Calendar)
     */
    @Override
    public void setEnd( Calendar end )
        throws IllegalArgumentException
    {
        if ( end == null )
        {
            throw new IllegalArgumentException( "Null-Object as start-time is not allowed" );
        }
        if ( start.before( end ) )
        {
            this.end = end;
        }

        throw new IllegalArgumentException( "End-time have to after start-time: " );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#getEnd()
     */
    @Override
    public Calendar getEnd()
    {
        return (Calendar) end.clone();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#getRange()
     */
    @Override
    public long getRange()
    {
        return end.getTimeInMillis() - start.getTimeInMillis();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#isFinished()
     */
    @Override
    public boolean isFinished()
    {
        Calendar currentDate = new GregorianCalendar();
        return end.before( currentDate );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#isStarted()
     */
    @Override
    public boolean isStarted()
    {
        Calendar currentDate = new GregorianCalendar();
        return start.before( currentDate );
    }

}
