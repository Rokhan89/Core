package com.github.jgility.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.IBacklog;
import com.github.jgility.core.project.IPerson;
import com.github.jgility.core.project.IProduct;
import com.github.jgility.core.project.IProject;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.requirement.IProductRequirement;

public class ProductTest
{
    private IProduct product;

    @Before
    public void setUp()
    {
        IPerson productOwner = new Person( "Max", "Mustermann", "max@mustermann.de" );
        product = new Product( "Schnursenkel", "Schnursenkel Produkt Beschreibung", productOwner );
    }

    @Test
    public void testProduct()
    {
        Assert.assertEquals( "Schnursenkel", product.getName() );
        Assert.assertEquals( "Schnursenkel Produkt Beschreibung", product.getDescription() );
        Assert.assertNotNull( product.getProductOwner() );
    }

    @Test
    public void testSetName()
    {
        product.setName( "Test" );
        Assert.assertEquals( "Test", product.getName() );
        try
        {
            product.setName( "" );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }

        try
        {
            product.setName( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
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
        List<IProject> projectList = new ArrayList<>();
        projectList.add( new Project( "Test", "Tetsbeschreibung" ) );
        projectList.add( new Project( "Projekt", "Komische Beschreibung" ) );
        projectList.add( new Project( "Max's Projekt", "Projektbeschreibung" ) );
        product.setProjects( projectList );

        Assert.assertEquals( 3, product.getProjects().size() );

        try
        {
            product.setProjects( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }

        Assert.assertEquals( 3, product.getProjects().size() );

        List<IProject> projects = product.getProjects();
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

        try
        {
            product.addProject( null );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }

        Assert.assertEquals( 3, product.getProjects().size() );
    }

    @Test
    public void testRemoveProject()
    {
        testAddProject();

        Assert.assertFalse( product.removeProject( null ) );
        for ( IProject project : product.getProjects() )
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
        try
        {
            product.setProductOwner( new Person() );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testRemoveProductOwner()
    {
        IPerson productOwner = product.getProductOwner();
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
        IBacklog<IProductRequirement> productBacklog = new Backlog<>();
        try
        {
            product.setProductBacklog( productBacklog );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testRemoveProductBacklog()
    {
        IBacklog<IProductRequirement> productBacklog = new Backlog<>();
        product.removeProductBacklog();
        product.setProductBacklog( productBacklog );
        Assert.assertSame( productBacklog, product.getProductBacklog() );
    }

}
