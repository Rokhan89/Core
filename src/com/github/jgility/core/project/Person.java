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
package com.github.jgility.core.project;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.validator.EmailValidator;

import com.github.jgility.core.ModelObject;

/**
 * Klasse, welche ein Nutzer bzw. eine Person im Sinne der agilen Softwareentwicklung repräsentiert.
 * Findet Verwendung als Teammitglied des {@link Project} oder ProductOwner als Besitzer des
 * {@link Product}.
 * 
 * @since 21.10.2011
 * @author Karsten Schulz
 * @version 1.0
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Person 
    extends ModelObject
    implements IPerson
{

    /**
     *  Bezeichner der Eigenschaft {@link #firstname}
     */
    public static final String FIRSTNAME_PROPERTY = "firstname";

    /**
     *  Bezeichner der Eigenschaft {@link #surname}
     */
    public static final String SURNAME_PROPERTY = "surname";

    /**
     *  Bezeichner der Eigenschaft {@link #eMail}
     */
    public static final String E_MAIL_PROPERTY = "eMail";
    
    @XmlElement
    private String firstname;

    @XmlElement
    private String surname;

    @XmlElement
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

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#setFirstname(java.lang.String)
     */
    @Override
    public void setFirstname( String firstname )
        throws IllegalArgumentException
    {
        if ( checkCapitalFirstLetter( firstname ) )
        {
            String formerFirstname = this.firstname;
            this.firstname = firstname;
            changes.firePropertyChange( Person.FIRSTNAME_PROPERTY, formerFirstname, this.firstname);
        }
        else
        {
            throw new IllegalArgumentException( "firstname has to be a capital first letter: "
                + firstname );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#setSurname(java.lang.String)
     */
    @Override
    public void setSurname( String surname )
        throws IllegalArgumentException
    {
        if ( checkCapitalFirstLetter( surname ) )
        {
            String formerSurname = this.surname;
            this.surname = surname;
            changes.firePropertyChange( Person.SURNAME_PROPERTY, formerSurname, this.surname );
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

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#setEMail(java.lang.String)
     */
    @Override
    public void setEMail( String eMail )
        throws IllegalArgumentException
    {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if ( emailValidator.isValid( eMail ) )
        {
            String formerEMail = this.eMail;
            this.eMail = eMail;
            changes.firePropertyChange( Person.E_MAIL_PROPERTY, formerEMail, this.eMail);
        }
        else
        {
            throw new IllegalArgumentException( "e-mail adress is invalid: " + eMail );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#getFirstname()
     */
    @Override
    public String getFirstname()
    {
        return firstname;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#getSurname()
     */
    @Override
    public String getSurname()
    {
        return surname;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IPerson#getEMail()
     */
    @Override
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
