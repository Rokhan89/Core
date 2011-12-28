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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implementiert eine konkrete Klasse für die Release-Plannung
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlSeeAlso( Iteration.class )
@XmlAccessorType( XmlAccessType.FIELD )
public class Release
    extends AbstractPlan
{

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IPlan> subPlanList;

    /**
     * Instanziiert ein Objekt von der Klasse {@link Release} mit dem heutigen Datum und dem
     * Release-Ende in 14-Tagen.
     */
    public Release()
    {
        super();
        subPlanList = new ArrayList<>();
    }

    /**
     * Instanziiert ein Objekt von der Klasse {@link Release} mit übergebenen Start- und End-Werten
     * 
     * @param start Wann das Release anfängt
     * @param end Wann das Release endet
     */
    public Release( Calendar start, Calendar end )
    {
        super( start, end );
        subPlanList = new ArrayList<>();
    }

    /**
     * Fügt ein neuen {@link IPlan} der Unterstruktur des {@link Release} hinzu
     * 
     * @param plan neuer {@link IPlan} zum hinzufügen
     */
    public void addPlan( IPlan plan )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, plan ) || ObjectUtils.equals( this, plan ) )
        {
            throw new IllegalArgumentException( "plan-object has a wrong reference: " + plan );
        }

        if ( CollectionUtils.isEmpty( subPlanList ) )
        {
            subPlanList.add( plan );
        }
        else if ( checkPlanRange( plan ) )
        {
            if ( checkSubPlan( plan, subPlanList.get( subPlanList.size() - 1 ) ) )
            {
                subPlanList.add( plan );
            }
            else
            {
                throw new IllegalArgumentException( "plan-object has a occupied start or end-time" );
            }
        }
        else
        {
            throw new IllegalArgumentException( "start or end-time is invalid" );
        }

    }

    private boolean checkPlanRange( IPlan plan )
    {
        final boolean checkStart = getStart().after( plan.getStart() );
        final boolean checkEnd = getEnd().before( plan.getEnd() );
        return ( !checkStart && !checkEnd );
    }

    private boolean checkSubPlan( IPlan plan, IPlan subplan )
    {
        Calendar start = plan.getStart();
        Calendar end = subplan.getEnd();
        start.set( Calendar.HOUR, 0 );
        start.set( Calendar.MINUTE, 1 );
        end.set( Calendar.HOUR, 0 );
        end.set( Calendar.MINUTE, 0 );
        return start.after( end );
    }
    /**
     * Entfernt ein {@link IPlan} aus der Unterstruktur
     * 
     * @param plan bestehender {@link IPlan} zum entfernen aus der {@link List}
     */
    public boolean removePlan( IPlan plan )
    {
        return subPlanList.remove( plan );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#getPlanningStruct()
     */
    @Override
    public List<IPlan> getPlanningStruct()
    {
        return Collections.unmodifiableList( subPlanList );
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( super.hashCode() );
        builder.append( subPlanList );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Release )
        {
            Release plan = (Release) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( subPlanList, plan.subPlanList );

            return builder.isEquals() && super.equals( obj );
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
        SimpleDateFormat sfd = new SimpleDateFormat( "dd.MM.yyyy" );
        return "Release [start=" + sfd.format( getStart().getTime() ) + " end="
            + sfd.format( getEnd().getTime() ) + " subPlanSet=" + subPlanList + "]";
    }
}
