/*
 * = License =
 */
package com.github.jgility.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Util-Klasse welche diverse Methoden bereitstellt, welche für die Handhabung mit {@link Calendar}
 * vereinfachen soll. Im Vordergrund steht hierbei eine vereinfachte Ausgabe (nicht über
 * {@link Calendar#toString()}) und die Vergleichbarkeit von Calendar-Objekten
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public final class CalendarUtils
{

    /**
     * Generiert eine Zeichenkette-Ausgabe im Format "dd.MM.yyyy"
     * 
     * @param date Datum, welches aus gegeben werden soll
     * @return einen Formatierte Datumsausgabe in Form von "dd.MM.yyyy"
     */
    public static String calendarOutput( Calendar date )
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd.MM.yyyy" );
        return sdf.format( date.getTime() );
    }

    /**
     * Überprüft das Start- und End-Datum. Also ob das Start-Datum vor dem End-Datum liegt. Zuvor
     * werden die Uhrzeiten der Datume angepasst. Startdatum wird auf 00:00 gesetzt, Enddatum auf
     * 23:59.
     * 
     * @param start Start-Datum
     * @param end End-Datum
     * @return <code>true</code> wenn Start-Datum vor dem End-Datum liegt<br>
     *         <code>false</code> wenn nicht
     */
    public static boolean checkDate( Calendar start, Calendar end )
    {
        modifyStartEnd( start, end );
        return start.before( end );
    }

    /**
     * Modifiziert das End- und Start-Datum soweit ab, dass Vergleiche auf Basis des gleichen Tages
     * möglich sind. Dabei wird Start-Datum auf 00:00 Uhr gesetzt, End-Datum auf 23:59 Uhr.
     * 
     * @param start Start-Datum
     * @param end End-Datum
     */
    public static void modifyStartEnd( Calendar start, Calendar end )
    {
        start.set( Calendar.HOUR, 0 );
        start.set( Calendar.MINUTE, 0 );
        end.set( Calendar.HOUR, 23 );
        end.set( Calendar.MINUTE, 59 );
    }

    /**
     * Generiert eine einfache Konsolen-Ausgabe im Format "dd.MM.yyyy"
     * 
     * @param date Datum, welches aus gegeben werden soll
     */
    public static void outputCalendar( Calendar date )
    {
        System.out.println( calendarOutput( date ) );
    }
}
