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
package com.github.jgility.core.project;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.validator.EmailValidator;

/**
 * Klasse, welche ein Nutzer bzw. eine Person im Sinne der agilen Softwareentwicklung repräsentiert.
 * Findet Verwendung als Teammitglied des {@link Project} oder ProductOwner als Besitzer des
 * {@link Product}.
 * 
 * @since 21.10.2011
 * @author Karsten Schulz
 * @version 1.0
 */
public class Person
{
    private String firstname;

    private String surname;

    private String eMail;

    /**
     * Instanziiert ein Standardisierte Person.<br>
     * <code>firstname = "Default";</code> <br>
     * <code>surname = "Default";</code><br>
     * <code>eMail = "example@mail.com"</code>
     */
    public Person()
    {
        this( "Default", "Default", "example@mail.com" );
    }

    /**
     * Instanziiert anhand der Parameter eine Person
     * 
     * @param firstname Vorname der Person
     * @param surname Nachname der Person
     * @param eMail E-Mail-Adresse
     * @throws IllegalArgumentException wird geworfen, wenn einer der Bedingungen nicht zutrifft
     */
    public Person( String firstname, String surname, String eMail )
        throws IllegalArgumentException
    {
        super();
        setFirstname( firstname );
        setSurname( surname );
        setEMail( eMail );
    }

    /**
     * Setzt den Vornamen der Person und überprüft dessen Wert
     * 
     * @param firstname Vorname der Person.<br>
     *            Es wird überprüft ob der erste Buchstabe ein Großbuchstabe ist.<br>
     *            Leere Eingaben oder <code>null</code> ist nicht erlaubt
     * @throws IllegalArgumentException wird geworfen, wenn einer der Bedingungen nicht zutrifft
     */
    public void setFirstname( String firstname )
        throws IllegalArgumentException
    {
        if ( checkCapitalFirstLetter( firstname ) )
        {
            this.firstname = firstname;
        }
        else
        {
            throw new IllegalArgumentException( "firstname has to be a capital first letter: "
                + firstname );
        }
    }

    /**
     * Setzt den Nachnamen und überprüft dessen Wert
     * 
     * @param surname Nachname der Person.<br>
     *            Identische Prüfung wie für <code>firstname</code>
     * @see #setFirstname(String)
     * @throws IllegalArgumentException wird geworfen, wenn einer der Bedingungen nicht zutrifft
     */
    public void setSurname( String surname )
        throws IllegalArgumentException
    {
        if ( checkCapitalFirstLetter( surname ) )
        {
            this.surname = surname;
        }
        else
        {
            throw new IllegalArgumentException( "surname has to be a capital first lettter: "
                + surname );
        }
    }

    /*
     * Überprüft ob der erste Buchstabe Groß geschrieben wurde
     */
    private boolean checkCapitalFirstLetter( String string )
    {
        return StringUtils.isNotBlank( string ) && Character.isUpperCase( string.charAt( 0 ) );
    }

    /**
     * Setzt die E-Mail-Adresse und überprüft dessen Wert
     * 
     * @param eMail E-Mail-Adresse <br>
     *            muss dem Standard entsprechen. Zum Beispiel: <code>example@mail.com</code>
     * @throws IllegalArgumentException wird geworfen, wenn Parameter keine valide E-Mail-Adresse
     *             ist
     */
    public void setEMail( String eMail )
        throws IllegalArgumentException
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if ( emailValidator.isValid( eMail ) )
        {
            this.eMail = eMail;
        }
        else
        {
            throw new IllegalArgumentException( "e-mail adress is invalid: " + eMail );
        }
    }

    /**
     * Gibt den Vornamen zurück
     * 
     * @return firstname
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * Gibt den Nachnamen zurück
     * 
     * @return surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Gibt die E-Mail-Adresse zurück
     * 
     * @return eMail
     */
    public String getEMail()
    {
        return eMail;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Person )
        {
            Person person = (Person) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( firstname, person.getFirstname() );
            builder.append( surname, person.getSurname() );
            builder.append( eMail, person.eMail );
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( firstname );
        builder.append( surname );
        builder.append( eMail );
        return builder.toHashCode();
    }

    @Override
    public String toString()
    {
        return "Person [firstname=" + firstname + ", surname=" + surname + ", eMail=" + eMail + "]";
    }

}
