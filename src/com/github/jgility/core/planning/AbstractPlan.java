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
 *     Christoph Viebig
 *
 */
package com.github.jgility.core.planning;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.util.BeanCheckUtils;
import com.github.jgility.core.util.CalendarUtils;
import com.github.jgility.core.xml.AbstractXmlPlan;

/**
 * Abstrakte Klasse und implementiert die Grundfunktionalitäten einer planbaren Datenstruktur.
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlType( propOrder = { "start", "end" } )
@XmlAccessorType( XmlAccessType.FIELD )
public abstract class AbstractPlan
    extends AbstractXmlPlan
{

    /**
     * Bezeichner der Eigenschaft {@link #start}
     */
    public static final String START_PROPERTY = "start";

    /**
     * Bezeichner der Eigenschaft {@link #end}
     */
    public static final String END_PROPERTY = "end";

    @XmlElement
    private Calendar start;

    @XmlElement
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
     * @throws IllegalArgumentException wird geworfen, wenn das Ende-Datum vor dem Start-Datum liegt
     */
    public AbstractPlan( Calendar start, Calendar end )
        throws IllegalArgumentException
    {
        changeStartEnd( start, end );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#setStart(java.util.Calendar)
     */
    @Override
    public void setStart( Calendar start )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkObjectNotNull( start, "null-Object as start-time is not allowed" );

        if ( end.after( start ) )
        {
            Calendar formerStart = this.start;
            this.start = (Calendar) start.clone();
            changes.firePropertyChange( AbstractPlan.START_PROPERTY, formerStart, this.start );
        }
        else
        {
            throw new IllegalArgumentException( "start-time have to before end-time" );
        }
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
        BeanCheckUtils.checkObjectNotNull( end, "null-Object as end-time is not allowed" );

        if ( start.before( end ) )
        {
            Calendar formerEnd = this.end;
            this.end = (Calendar) end.clone();
            changes.firePropertyChange( AbstractPlan.END_PROPERTY, formerEnd, this.end );
        }
        else
        {
            throw new IllegalArgumentException( "start-time have to before end-time" );
        }
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
     * @see
     * com.github.jgility.core.planning.IPlan#changeStartEnd(java.util.Calendar,java.util.Calendar)
     */
    @Override
    public void changeStartEnd( Calendar newStart, Calendar newEnd )
        throws IllegalArgumentException
    {
        if ( CalendarUtils.checkDate( newStart, newEnd ) )
        {
            Calendar formerStart = this.start;
            this.start = newStart;
            changes.firePropertyChange( AbstractPlan.START_PROPERTY, formerStart, this.start );

            Calendar formerEnd = this.end;
            this.end = newEnd;
            changes.firePropertyChange( AbstractPlan.END_PROPERTY, formerEnd, this.start );
        }
        else
        {
            throw new IllegalArgumentException( "start-time has to be before end-time" );
        }

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( start );
        builder.append( end );
        return builder.toHashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof AbstractPlan )
        {
            AbstractPlan plan = (AbstractPlan) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( start, plan.getStart() );
            builder.append( end, plan.getEnd() );
            return builder.isEquals();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "start", CalendarUtils.calendarOutput( start ) );
        builder.append( "end", CalendarUtils.calendarOutput( end ) );
        return builder.build();
    }

}
