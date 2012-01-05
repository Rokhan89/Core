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
import java.util.Collection;
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

import com.github.jgility.core.util.CalendarUtils;

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
    implements IRelease
{

    @XmlElementWrapper
    @XmlAnyElement( lax = true )
    private final List<IIteration> iterationList;

    /**
     * Instanziiert ein Objekt von der Klasse {@link Release} mit dem heutigen Datum und dem
     * Release-Ende in 14-Tagen.
     */
    public Release()
    {
        super();
        iterationList = new ArrayList<>();
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
        iterationList = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.planning.IRelease#addPlan(com.github.jgility.core.planning.IPlan)
     */
    @Override
    public void addIteration( IIteration iteration )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, iteration ) || ObjectUtils.equals( this, iteration ) )
        {
            throw new IllegalArgumentException( "plan-object has a wrong reference: " + iteration );
        }

        if ( CollectionUtils.isEmpty( iterationList ) )
        {
            iterationList.add( iteration );
        }
        else if ( checkPlanRange( iteration ) )
        {
            if ( checkSubPlan( iteration, iterationList.get( iterationList.size() - 1 ) ) )
            {
                iterationList.add( iteration );
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
        return CalendarUtils.checkDate( start, end );
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.planning.IRelease#removePlan(com.github.jgility.core.planning.IPlan)
     */
    @Override
    public boolean removePlan( IIteration iteration )
    {
        return iterationList.remove( iteration );
    }

    @Override
    public void addAllIterations( Collection<? extends IIteration> iterationCollection )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( iterationCollection ) )
        {
            this.iterationList.addAll( iterationCollection );
        }
        else
        {
            throw new IllegalArgumentException( "empty collection of iteration is not "
                + "allowed to add" );
        }
    }

    @Override
    public List<IIteration> getIterationList()
    {
        return Collections.unmodifiableList( iterationList );
    }

    @Override
    public int size()
    {
        return iterationList.size();
    }

    @Override
    public IIteration getIteration( int index )
    {
        return iterationList.get( index );
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( super.hashCode() );
        builder.append( iterationList );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Release )
        {
            Release plan = (Release) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( iterationList, plan.iterationList );

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
            + sfd.format( getEnd().getTime() ) + " subPlanSet=" + iterationList + "]";
    }
}
