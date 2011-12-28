package com.github.jgility.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.project.Team;

public class ProjectTest
{

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
        try
        {
            new Project( "", "Dies ist eine Test Beschreibung" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
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

        try
        {
            project.setName( "" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {

        }
        try
        {
            project.setName( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
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
        try
        {
            project.addMember( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }

        Team members = project.getTeam();
        Assert.assertEquals( 3, members.getMembers().size() );
        for ( Person member : members.getMembers() )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( project.removeMember( member ) );
        }
        Assert.assertTrue( project.getTeam().getMembers().isEmpty() );
        Assert.assertEquals( 3, members.getMembers().size() );
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

        Team members = project.getTeam();
        Assert.assertNotSame( personList, members );
        Assert.assertEquals( 3, members.getMembers().size() );
        for ( Person member : members.getMembers() )
        {
            Assert.assertNotNull( member );
        }
    }

    @Test
    public void testGetMembers()
    {
        Team members = project.getTeam();
        Assert.assertEquals( 3, members.getMembers().size() );
        for ( Person member : members.getMembers() )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( project.removeMember( member ) );
        }
        Assert.assertTrue( project.getTeam().getMembers().isEmpty() );
        Assert.assertEquals( 3, members.getMembers().size() );
    }

    @Test
    public void testSetMembersFailsException()
    {
        List<Person> personList = new ArrayList<>();

        try
        {
            project.setMembers( personList );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testSetMembersFailsException2()
    {
        try
        {
            project.setMembers( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testClearMember()
    {
        project.clearMembers();
        Assert.assertTrue( project.getTeam().getMembers().isEmpty() );
    }
}
