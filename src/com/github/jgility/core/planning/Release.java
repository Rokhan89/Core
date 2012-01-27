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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.util.BeanCheckUtils;
import com.github.jgility.core.util.CalendarUtils;

/**
 * Implementiert eine konkrete Klasse für die Release-Plannung
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Release
    extends AbstractPlan
    implements IRelease
{

    /**
     * Bezeichner der Eigenschaft {@link #iterationList}
     */
    public static final String ITERATION_LIST = "iterationList";

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
        BeanCheckUtils.checkObjectNotSame( this, iteration, "plan-object has a wrong reference: "
            + iteration );

        if ( CollectionUtils.isEmpty( iterationList ) )
        {
            List<IIteration> formerIterationList = iterationList;
            iterationList.add( iteration );
            changes.firePropertyChange( Release.ITERATION_LIST, formerIterationList, iterationList );
        }
        else if ( checkPlanRange( iteration ) )
        {
            if ( checkSubPlan( iteration, iterationList.get( iterationList.size() - 1 ) ) )
            {
                List<IIteration> formerIterationList = iterationList;
                iterationList.add( iteration );
                changes.firePropertyChange( Release.ITERATION_LIST, formerIterationList,
                                            iterationList );
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
        boolean result;
        List<IIteration> formerIterationList = iterationList;
        result = iterationList.remove( iteration );
        changes.firePropertyChange( Release.ITERATION_LIST, formerIterationList, iterationList );
        return result;
    }

    @Override
    public void addAllIterations( Collection<? extends IIteration> iterationCollection )
        throws IllegalArgumentException
    {
        BeanCheckUtils.checkCollectionNotEmpty( iterationCollection,
                                                "empty collection of iteration is not "
                                                    + "allowed to add" );

        List<IIteration> formerIterationList = iterationList;
        this.iterationList.addAll( iterationCollection );
        changes.firePropertyChange( Release.ITERATION_LIST, formerIterationList, iterationList );
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
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "start", CalendarUtils.calendarOutput( getStart() ) );
        builder.append( "end", CalendarUtils.calendarOutput( getStart() ) );
        builder.append( "iterationList", iterationList );
        return builder.build();
    }
}
