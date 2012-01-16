package com.github.jgility.core.test;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.github.jgility.core.project.IPerson;
import com.github.jgility.core.project.Person;

public class PersonTest
{
    @Test
    public void testPersonTrue()
    {
        IPerson person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        assertEquals( "Max", person.getFirstname() );
        assertEquals( "Mustermann", person.getSurname() );
        assertEquals( "max@mustermann.de", person.getEMail() );
    }

    @SuppressWarnings( "unused" )
    @Test
    public void TestPersonFalseException()
    {
        try
        {
            new Person( "max", "mustermann", "max@mustermann.de" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {

        }
    }

    @Test
    public void testSetFirstname()
    {
        IPerson person = new Person();
        person.setFirstname( "Max" );
        assertEquals( "Max", person.getFirstname() );
    }

    @Test
    public void testSetFirstnameException()
    {
        IPerson person = new Person();

        try
        {
            person.setFirstname( "" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {

        }
    }

    @Test
    public void testSetSurname()
    {
        IPerson person = new Person();
        person.setSurname( "Mustermann" );
        assertEquals( "Mustermann", person.getSurname() );
        person = new Person();
        try
        {
            person.setSurname( "mustermann" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {

        }
    }

    @Test
    public void testSetEMail()
    {
        IPerson person = new Person();
        person.setEMail( "example@mail.com" );
        assertEquals( "example@mail.com", person.getEMail() );
    }

    @Test
    public void testSetEMailException()
    {
        IPerson person = new Person();
        try
        {
            person.setEMail( "blablabla" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {

        }
    }

}
