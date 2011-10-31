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

    private Project generellProject;

    @Before
    public void initProject()
    {
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        generellProject = new Project( "Test Projekt", "Dies ist eine Test Beschreibung" );
        generellProject.addMember( person );

        person = new Person( "Test", "Tester", "test@tester.de" );
        generellProject.addMember( person );

        person = new Person( "Martina", "Musterfrau", "martina@musterfrau.de" );
        generellProject.addMember( person );
    }

    @Test
    public void testProjectTrue()
    {
        Assert.assertEquals( "Test Projekt", generellProject.getName() );
        Assert.assertEquals( "Dies ist eine Test Beschreibung", generellProject.getDescription() );
    }

    @SuppressWarnings( "unused" )
    @Test( expected = IllegalArgumentException.class )
    public void testProjectFailsException()
    {
        new Project( "", "Dies ist eine Test Beschreibung" );
    }

    @Test
    public void testSetName()
    {
        generellProject.setName( "Test" );
        Assert.assertEquals( "Test", generellProject.getName() );
        generellProject.setName( "Test Projekt" );
        Assert.assertEquals( "Test Projekt", generellProject.getName() );
        generellProject.setName( "test projekt" );
        Assert.assertEquals( "test projekt", generellProject.getName() );

        exception.expect( IllegalArgumentException.class );
        generellProject.setName( "" );
        exception.expect( IllegalArgumentException.class );
        generellProject.setName( null );
    }

    @Test
    public void testSetDescription()
    {
        generellProject.setDescription( "Test" );
        Assert.assertEquals( "Test", generellProject.getDescription() );
        generellProject.setDescription( "Test Projekt" );
        Assert.assertEquals( "Test Projekt", generellProject.getDescription() );
        generellProject.setDescription( "test projekt" );
        Assert.assertEquals( "test projekt", generellProject.getDescription() );
    }

    @Test
    public void testAddAndRemoveMember()
    {
        exception.expect( IllegalArgumentException.class );
        generellProject.addMember( null );
        List<Person> members = generellProject.getMembers();
        Assert.assertEquals( 3, members.size() );
        for ( Person member : members )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( generellProject.removeMember( member ) );
        }
        Assert.assertTrue( generellProject.getMembers().isEmpty() );
        Assert.assertEquals( 3, members.size() );
    }

    @Test
    public void testSetMembers()
    {
        List<Person> personList = new ArrayList<Person>();
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        personList.add( person );
        person = new Person( "Test", "Tester", "test@tester.de" );
        personList.add( person );
        person = new Person( "Martina", "Musterfrau", "martina@musterfrau.de" );
        personList.add( person );

        Project project = new Project();
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
        List<Person> members = generellProject.getMembers();
        Assert.assertEquals( 3, members.size() );
        for ( Person member : members )
        {
            Assert.assertNotNull( member );
            Assert.assertTrue( generellProject.removeMember( member ) );
        }
        Assert.assertTrue( generellProject.getMembers().isEmpty() );
        Assert.assertEquals( 3, members.size() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetMembersFailsException()
    {
        List<Person> personList = new ArrayList<Person>();

        Project project = new Project();
        project.setMembers( personList );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetMembersFailsException2()
    {
        Project project = new Project();
        project.setMembers( null );
    }

    @Test
    public void testClearMember()
    {
        generellProject.clearMembers();
        Assert.assertTrue( generellProject.getMembers().isEmpty() );
    }
}
