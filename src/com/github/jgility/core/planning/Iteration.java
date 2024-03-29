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
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.github.jgility.core.requirement.IIterationRequirement;
import com.github.jgility.core.requirement.IterationStory;

/**
 * Implementiert eine konkreten {@link AbstractPlan} für die Iterations-Plannung
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlSeeAlso( IterationStory.class )
@XmlAccessorType( XmlAccessType.FIELD )
public class Iteration
    extends AbstractPlan
    implements IIteration
{
    @XmlElement
    private final Backlog<IIterationRequirement> iterationBacklog;

    /**
     * Instanziiert ein Objekt der Klasse {@link Iteration} und delegiert es an
     * {@link AbstractPlan#AbstractPlan()}. Instanziiert zusätzlich ein leeres {@link Backlog} von
     * {@link IIterationRequirement}.
     */
    public Iteration()
    {
        super();
        iterationBacklog = new Backlog<>();
    }

    /**
     * Instanziiert ein Objekt der Klasse {@link Iteration} und delegiert es weiter an
     * {@link AbstractPlan#AbstractPlan(Calendar, Calendar)}. Instanziiert zusätzlich ein leeres
     * {@link Backlog} von {@link IIterationRequirement}.
     * 
     * @param start Wann die Iteration beginnt
     * @param end Wann die Iteration endet
     */
    public Iteration( Calendar start, Calendar end )
    {
        super( start, end );
        iterationBacklog = new Backlog<>();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.planning.IIteration#getIterationBacklog()
     */
    @Override
    public Backlog<IIterationRequirement> getIterationBacklog()
    {
        return iterationBacklog;
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( super.hashCode() );
        builder.append( iterationBacklog );
        return builder.toHashCode();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Iteration )
        {
            Iteration plan = (Iteration) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( iterationBacklog, plan.iterationBacklog );

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
        return "Iteration [start=" + sfd.format( getStart().getTime() ) + " end="
            + sfd.format( getEnd().getTime() ) + " iterationBacklog=" + iterationBacklog + "]";
    }

}
