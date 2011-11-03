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
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

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
public class Release
    extends AbstractPlan
{

    private final Set<IPlan> subPlanSet;

    /**
     * Instanziiert ein Objekt von der Klasse {@link Release} mit dem heutigen Datum und dem
     * Release-Ende in 14-Tagen.
     */
    public Release()
    {
        super();
        subPlanSet = new HashSet<>();
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
        subPlanSet = new HashSet<>();
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
            throw new IllegalArgumentException( "Plan-object has a bad reference: " + plan );
        }

        if ( checkPlanRange( plan ) )
        {
            subPlanSet.add( plan );
        }
        else
        {
            throw new IllegalArgumentException( "Start or end-time is invalid" );
        }

    }

    private boolean checkPlanRange( IPlan plan )
    {
        return ( !getStart().after( plan.getStart() ) && !getEnd().before( plan.getEnd() ) );
    }

    /**
     * Entfernt ein {@link IPlan} aus der Unterstruktur
     * 
     * @param plan bestehender {@link IPlan} zum entfernen aus der {@link List}
     */
    public boolean removePlan( IPlan plan )
    {
        return subPlanSet.remove( plan );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IPlan#getPlanningStruct()
     */
    @XmlElementWrapper
    @XmlAnyElement
    @Override
    public List<IPlan> getPlanningStruct()
    {
        final List<IPlan> subPlanList = new ArrayList<>( subPlanSet );
        return Collections.unmodifiableList( subPlanList );
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( super.hashCode() );
        builder.append( subPlanSet );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Release )
        {
            Release plan = (Release) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( subPlanSet, plan.subPlanSet );

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
        return "Release [start=" + getStart() + "end=" + getEnd() + "subPlanSet=" + subPlanSet
            + "]";
    }
}
