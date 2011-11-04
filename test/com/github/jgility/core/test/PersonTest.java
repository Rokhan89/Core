package com.github.jgility.core.test;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.jgility.core.project.Person;

public class PersonTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testPersonTrue()
    {
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        assertEquals( "Max", person.getFirstname() );
        assertEquals( "Mustermann", person.getSurname() );
        assertEquals( "max@mustermann.de", person.getEMail() );
    }

    @SuppressWarnings( "unused" )
    @Test
    public void TestPersonFalseException()
    {
        exception.expect( IllegalArgumentException.class );
        new Person( "max", "mustermann", "max@mustermann.de" );
    }

    @Test
    public void testSetFirstname()
    {
        Person person = new Person();
        person.setFirstname( "Max" );
        assertEquals( "Max", person.getFirstname() );
    }

    @Test
    public void testSetFirstnameException()
    {
        Person person = new Person();
        exception.expect( IllegalArgumentException.class );
        person.setFirstname( "" );
    }

    @Test
    public void testSetSurname()
    {
        Person person = new Person();
        person.setSurname( "Mustermann" );
        assertEquals( "Mustermann", person.getSurname() );
        person = new Person();
        exception.expect( IllegalArgumentException.class );
        person.setSurname( "mustermann" );
    }

    @Test
    public void testSetEMail()
    {
        Person person = new Person();
        person.setEMail( "example@mail.com" );
        assertEquals( "example@mail.com", person.getEMail() );
    }

    @Test
    public void testSetEMailException()
    {
        Person person = new Person();
        exception.expect( IllegalArgumentException.class );
        person.setEMail( "blablabla" );
    }

}
