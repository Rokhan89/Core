package com.github.jgility.core.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.jgility.core.project.Person;

public class PersonTest
{

    @Test
    public void testPersonTrue()
    {
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        assertEquals( "Max", person.getFirstname() );
        assertEquals( "Mustermann", person.getSurname() );
        assertEquals( "max@mustermann.de", person.getEMail() );
    }

    @SuppressWarnings( "unused" )
    @Test( expected = IllegalArgumentException.class )
    public void TestPersonFalseException()
    {
        new Person( "max", "mustermann", "max@mustermann.de" );
    }

    @Test
    public void testSetFirstname()
    {
        Person person = new Person();
        person.setFirstname( "Max" );
        assertEquals( "Max", person.getFirstname() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetFirstnameException()
    {
        Person person = new Person();
        person.setFirstname( "" );
    }

    @Test
    public void testSetSurname()
    {
        Person person = new Person();
        person.setSurname( "Mustermann" );
        assertEquals( "Mustermann", person.getSurname() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetSurnameException()
    {
        Person person = new Person();
        person.setSurname( "mustermann" );
    }

    @Test
    public void testSetEMail()
    {
        Person person = new Person();
        person.setEMail( "example@mail.com" );
        assertEquals( "example@mail.com", person.getEMail() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetEMailException()
    {
        Person person = new Person();
        person.setEMail( "blablabla" );
    }

}
