package com.github.jgility.core.test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;


public class ProductTest
{
    private Product product;

    @Before
    public void setUp()
    {
        Person productOwner = new Person( "Max", "Mustermann", "max@mustermann.de" );
        product = new Product( "Schnursenkel", "Schnursenkel Produkt Beschreibung", productOwner );
    }

    @Test
    public void testProduct()
    {
        Assert.assertEquals( "Schnursenkel", product.getName() );
        Assert.assertEquals( "Schnursenkel Produkt Beschreibung", product.getDescription() );
        Assert.assertNotNull( product.getProductOwner() );
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSetName()
    {
        product.setName( "Test" );
        Assert.assertEquals( "Test", product.getName() );
        exception.expect( IllegalArgumentException.class );
        product.setName( "" );
        exception.expect( IllegalArgumentException.class );
        product.setName( null );
    }

    @Test
    public void testSetDescription()
    {
        product.setDescription( "Testbeschreibung" );
        Assert.assertEquals( "Testbeschreibung", product.getDescription() );
    }

    @Test
    public void testSetProjectsGetProjects()
    {
        List<Project> projectList = new ArrayList<Project>();
        projectList.add( new Project( "Test", "Tetsbeschreibung" ) );
        projectList.add( new Project( "Projekt", "Komische Beschreibung" ) );
        projectList.add( new Project( "Max's Projekt", "Projektbeschreibung" ) );
        product.setProjects( projectList );

        Assert.assertEquals( 3, product.getProjects().size() );

        exception.expect( IllegalArgumentException.class );
        product.setProjects( null );

        Assert.assertEquals( 3, product.getProjects().size() );

        List<Project> projects = product.getProjects();
        Assert.assertNotSame( projectList, projects );
        Assert.assertNotSame( projects, product.getProjects() );

    }

    @Test
    public void testAddProject()
    {
        product.addProject( new Project( "Projekt", "Komische Beschreibung" ) );
        product.addProject( new Project( "Test", "Tetsbeschreibung" ) );
        product.addProject( new Project( "Max's Projekt", "Projektbeschreibung" ) );

        Assert.assertEquals( 3, product.getProjects().size() );

        exception.expect( IllegalArgumentException.class );
        product.addProject( null );

        Assert.assertEquals( 3, product.getProjects().size() );
    }

    @Test
    public void testRemoveProject()
    {
        testAddProject();

        Assert.assertFalse( product.removeProject( null ) );
        for ( Project project : product.getProjects() )
        {
            Assert.assertTrue( product.removeProject( project ) );
        }

        Assert.assertEquals( 0, product.getProjects().size() );
    }

    @Test
    public void testClearProject()
    {
        testAddProject();

        product.clearProject();

        Assert.assertEquals( 0, product.getProjects().size() );
    }

    @Test
    public void testSetProductOwner()
    {
        exception.expect( IllegalArgumentException.class );
        product.setProductOwner( new Person() );
    }

    @Test
    public void testRemoveProductOwner()
    {
        Person productOwner = product.getProductOwner();
        Assert.assertEquals( "Max", productOwner.getFirstname() );

        productOwner = new Person( "Test", "Tester", "test@tester.de" );
        product.removeProductOwner();
        Assert.assertNull( product.getProductOwner() );

        product.setProductOwner( productOwner );
        Assert.assertEquals( "Test", product.getProductOwner().getFirstname() );
    }

    @Test
    public void testSetProductBacklog()
    {
        Backlog productBacklog = new Backlog();
        exception.expect( IllegalArgumentException.class );
        product.setProductBacklog( productBacklog );
    }

    @Test
    public void testRemoveProductBacklog()
    {
        Backlog productBacklog = new Backlog();
        product.removeProductBacklog();
        product.setProductBacklog( productBacklog );
        Assert.assertSame( productBacklog, product.getProductBacklog() );
    }

}
