package com.github.jgility.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Project;

public class ProjectTest
{

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Project project;

    @Before
    public void initProject()
    {
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        project = new Project( "Test Projekt", "Dies ist eine Test Beschreibung" );
        project.addMember( person );

        person = new Person( "Test", "Tester", "test@tester.de" );
        project.addMember( person );

        person = new Person( "Martina", "Musterfrau", "martina@musterfrau.de" );
        project.addMember( person );
    }

    @Test
    public void testProjectTrue()
    {
        Assert.assertEquals( "Test Projekt", project.getName() );
        Assert.assertEquals( "Dies ist eine Test Beschreibung", project.getDescription() );
    }

    @SuppressWarnings( "unused" )
    @Test
    public void testProjectFailsException()
    {
        exception.expect( IllegalArgumentException.class );
        new Project( "", "Dies ist eine Test Beschreibung" );
    }

    @Test
    public void testSetName()
    {
        project.setName( "Test" );
        Assert.assertEquals( "Test", project.getName() );
        project.setName( "Test Projekt" );
        Assert.assertEquals( "Test Projekt", project.getName() );
        project.setName( "test projekt" );
        Assert.assertEquals( "test projekt", project.getName() );

        exception.expect( IllegalArgumentException.class );
        project.setName( "" );
        exception.expect( IllegalArgumentException.class );
        project.setName( null );
    }

    @Test
    public void testSetDescription()
    {
        project.setDescription( "Test" );
        Assert.assertEquals( "Test", project.getDescription() );
        project.setDescription( "Test Projekt" );
        Assert.assertEquals( "Test Projekt", project.getDescription() );
        project.setDescription( "test projekt" );
        Assert.assertEquals( "test projekt", project.getDescription() );
    }

    @Test
    public void testAddAndRemoveMember()
    {
        exception.expect( IllegalArgumentException.class );
        project.addMember( null );
        List<Person> members = project.getMembers();
        Assert.assertEquals( 3, members.size() );
        for ( Person member : members )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( project.removeMember( member ) );
        }
        Assert.assertTrue( project.getMembers().isEmpty() );
        Assert.assertEquals( 3, members.size() );
    }

    @Test
    public void testSetMembers()
    {
        List<Person> personList = new ArrayList<>();
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        personList.add( person );
        person = new Person( "Test", "Tester", "test@tester.de" );
        personList.add( person );
        person = new Person( "Martina", "Musterfrau", "martina@musterfrau.de" );
        personList.add( person );

        project.setMembers( personList );

        List<Person> members = project.getMembers();
        Assert.assertNotSame( personList, members );
        Assert.assertEquals( 3, members.size() );
        for ( Person member : members )
        {
            Assert.assertNotNull( member );
        }
    }

    @Test
    public void testGetMembers()
    {
        List<Person> members = project.getMembers();
        Assert.assertEquals( 3, members.size() );
        for ( Person member : members )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( project.removeMember( member ) );
        }
        Assert.assertTrue( project.getMembers().isEmpty() );
        Assert.assertEquals( 3, members.size() );
    }

    @Test
    public void testSetMembersFailsException()
    {
        List<Person> personList = new ArrayList<>();

        exception.expect( IllegalArgumentException.class );
        project.setMembers( personList );
    }

    @Test
    public void testSetMembersFailsException2()
    {
        exception.expect( IllegalArgumentException.class );
        project.setMembers( null );
    }

    @Test
    public void testClearMember()
    {
        project.clearMembers();
        Assert.assertTrue( project.getMembers().isEmpty() );
    }
}
