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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.github.jgility.core.planning.IIteration;
import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.IRelease;

/**
 * Util-Klasse für diverse Verfahrensweisen für die Änderung an der Release und Iterations-Planung.
 * Diese Klasse beinhaltet diverse Hilfmethoden um ein Release oder Iteration valid beplanbar zu
 * machen. Um sich Arbeit abzunehmen sollte bei Änderung an der Planung (Verschiebung der
 * Datums-Grenzen) diese Hilfs-Klasse Verwendung finden
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public final class ReleasePlanningUtils
{

    private static Calendar addDayOffset( final Calendar date, long offset )
    {
        Calendar newDate = new GregorianCalendar();
        newDate.setTimeInMillis( date.getTimeInMillis() );
        newDate.add( Calendar.DAY_OF_MONTH, safeLongToInt( offset ) );
        return newDate;
    }

    private static long calculateNewDayRange( long oldRange, long newRange, long iterationRange )
    {
        double tmpIterationRange = iterationRange;
        double tmpOldRange = oldRange;
        double tmpNewRange = newRange;
        return Math.round( ( tmpIterationRange / tmpOldRange ) * tmpNewRange );
    }

    private static long calculateRangeInDays( Calendar start, Calendar end )
    {
        long diff = end.getTimeInMillis() - start.getTimeInMillis();
        return diff / ( 1000 * 60 * 60 * 24 );
    }

    /**
     * Wechselt auf Basis der Plannung {@link IPlan} den Start- und End-Zeitpunkt, sofern diese
     * nacheinander erfolgen. Um Fehler und Dateninkonsitentz vorzubeugen nur bei Leeren
     * Sub-Plannungsstrukturen möglich
     * 
     * @param release Zu ändernde Plannungsebene
     * @param start Startzeitpunkt
     * @param end Endzeitpunkt
     * @throws IllegalArgumentException wird geworfen wenn ein invalide Vorgang in der Änderung der
     *             Plannungstruktur erfolgt ist
     */
    public static void changePlan( IRelease release, Calendar start, Calendar end )
        throws IllegalArgumentException
    {
        List<IIteration> iterationList = release.getIterationList();
        if ( CalendarUtils.checkDate( start, end ) )
        {
            if ( null == iterationList || CollectionUtils.isEmpty( iterationList ) )
            {
                release.changeStartEnd( start, end );
            }
            else
            {
                throw new IllegalArgumentException( "a plan with subplan is not allowed to simple "
                    + "change the start- and end-time" );
            }
        }
    }

    /**
     * Algorithmus um den übergebenen Release-Plan die Datumsgrenzen zu ändern und in Abhängigkeit
     * der Unterplanstufen diese Einzuhalten. Der Algorithmus entfernt alle Unterpläne die vor dem
     * neuen Startzeitpunkt Enden und nach dem neuen Endzeitpunkt beginnen
     * 
     * @param release zu Ändernden Plan
     * @param start neuer Startzeitpunkt
     * @param end neuer Endzeitpunkt
     * @throws IllegalArgumentException wird geworfen wenn ein invalide Vorgang in der Änderung der
     *             Plannungstruktur erfolgt ist
     */
    public static void changePlanCutSubPlan( IRelease release, Calendar start, Calendar end )
        throws IllegalArgumentException
    {
        if ( CalendarUtils.checkDate( start, end ) )
        {
            release.changeStartEnd( start, end );
            cutIterationList( release, start, end );
        }
        else
        {
            throw new IllegalArgumentException( "start-time or end-time is invalid" );
        }
    }

    /**
     * Algorithmus um ein Release und den untergeordneten Iterationen prozentual zu im Übergebenden
     * Zeitintervall zu verschieben
     * 
     * @param release zu änderndes {@link IRelease}
     * @param start Startzeitpunkt des neuen {@link IRelease}
     * @param end Endzeitpunkt des neuen {@link IRelease}
     * @throws IllegalArgumentException
     */
    public static void changePlanPerPercent( IRelease release, Calendar start, Calendar end )
        throws IllegalArgumentException
    {
        if ( CalendarUtils.checkDate( start, end ) )
        {
            List<IIteration> iterationList = release.getIterationList();
            if ( CollectionUtils.isNotEmpty( iterationList ) )
            {
                long oldRange = calculateRangeInDays( release.getStart(), release.getEnd() );
                release.changeStartEnd( start, end );
                long newRange = calculateRangeInDays( start, end );

                Calendar preEnd = start;
                for ( IIteration iteration : iterationList )
                {
                    long dayOffset =
                        calculateNewDayRange( oldRange,
                                              newRange,
                                              calculateRangeInDays( iteration.getStart(),
                                                                    iteration.getEnd() ) );
                    iteration.changeStartEnd( preEnd, addDayOffset( preEnd, dayOffset ) );
                    preEnd = iteration.getEnd();
                }

                IIteration iteration = release.getIteration( release.size() - 1 );
                iteration.setEnd( end );
            }
        }
        else
        {
            throw new IllegalArgumentException( "start-time or end-time is invalid" );
        }
    }

    private static void cutIterationList( IRelease release, Calendar start, Calendar end )
        throws IllegalArgumentException
    {
        List<IIteration> iterationList = release.getIterationList();
        if ( CollectionUtils.isNotEmpty( iterationList ) )
        {
            for ( IIteration iteration : iterationList )
            {
                if ( iteration.getEnd().before( start ) )
                {
                    release.removePlan( iteration );
                }
                else if ( iteration.getStart().after( end ) )
                {
                    release.removePlan( iteration );
                }
            }

            release.getIteration( 0 ).setStart( start );
            release.getIteration( release.size() - 1 ).setEnd( end );
        }
        // Nothing to do
    }

    private static int safeLongToInt( long l )
    {
        if ( l < Integer.MIN_VALUE || l > Integer.MAX_VALUE )
        {
            throw new IllegalArgumentException( l
                + " cannot be cast to int without changing its value." );
        }
        return (int) l;
    }

}
