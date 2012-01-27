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

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Hilfsklasse zum einfachen überprüfen von Parametern innerhalb der Bean-Struktur
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public final class BeanCheckUtils
{

    /**
     * Überprüft das übergebene {@link Object} auf den Wert <code>null</code>. Wenn dieser vorhanden
     * ist, wird eine {@link IllegalArgumentException} geworfen mit der übergebenen Nachricht als
     * {@link String}
     * 
     * @param object zu prüfende {@link Object}
     * @param message die geworfene Nachricht
     * @throws IllegalArgumentException wird geworfen, wenn das {@link Object} den Wert
     *             <code>null</code> enthällt
     * @see ObjectUtils#equals(Object, Object)
     */
    public static void checkObjectNotNull( Object object, String message )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, object ) )
        {
            throw new IllegalArgumentException( message );
        }
    }

    /**
     * Überprüft ob die übergeben {@link Collection} keine Elemente beinhaltet. Wenn dies so ist,
     * wird eine {@link IllegalArgumentException} geworfen mit der Übergeben Nachricht als
     * {@link String}
     * 
     * @param collection zu überprüfende {@link Collection}
     * @param message die geworfene Nachricht
     * @throws IllegalArgumentException wird geworfen, wenn die {@link Collection} keine Elemente
     *             enthält
     * @see CollectionUtils#isEmpty(Collection)
     */
    public static void checkCollectionNotEmpty( Collection<?> collection, String message )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isEmpty( collection ) )
        {
            throw new IllegalArgumentException( message );
        }
    }

    /**
     * Überprüft ob das übergeben Referenz-{@link Object} nicht den Wert <code>null</code> enthält
     * oder die selbe Referenz wie das ausgangs {@link Object} beinhaltet. Wenn dies so ist wird
     * eine {@link IllegalArgumentException} mit der übergebene Nachricht als {@link String}
     * geworfen
     * 
     * @param object das Ausgangsobjekt (z.B. <code>this</code>)
     * @param refObject das Referenzobjekt (z.B. das neue Objekt)
     * @param message die geworfene Nachricht
     * @throws IllegalArgumentException wird geworfen, wenn das Referenzobjekt den Wert
     *             <code>null</code> enthält oder die beiden Objekte die selbe Referenz haben.#
     * @see ObjectUtils#equals(Object, Object)
     */
    public static void checkObjectNotSame( Object object, Object refObject, String message )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.equals( null, refObject ) || ObjectUtils.equals( object, refObject ) )
        {
            throw new IllegalArgumentException( message );
        }
    }

    /**
     * Überprüft ob die übergebene {@link CharSequence} nicht leer ist. Ist dies der Fall wird eine
     * {@link IllegalArgumentException} mit der übergebenen Nachricht als {@link String} geworfen
     * 
     * @param sequence die zu prüfende {@link CharSequence}
     * @param message die geworfene Nachricht
     * @throws IllegalArgumentException wird geworfen, wenn die {@link CharSequence} leer ist
     * @see StringUtils#isBlank(CharSequence)
     */
    public static void checkStringNotBlank( CharSequence sequence, String message )
        throws IllegalArgumentException
    {
        if ( StringUtils.isBlank( sequence ) )
        {
            throw new IllegalArgumentException( message );
        }
    }
}
