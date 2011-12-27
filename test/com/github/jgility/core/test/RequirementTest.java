package com.github.jgility.core.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.Iteration;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.requirement.IIterationRequirement;
import com.github.jgility.core.requirement.IProductRequirement;
import com.github.jgility.core.requirement.ImplementState;
import com.github.jgility.core.requirement.IterationStory;
import com.github.jgility.core.requirement.Priority;
import com.github.jgility.core.requirement.ProductStory;
import com.github.jgility.core.requirement.RequirementKind;

public class RequirementTest
{

    private Product product;

    private Project project;

    @Before
    public void setUp()
        throws Exception
    {
        Person person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        product = new Product( "Test Product", "Test Description", person );
        project = new Project( "Test Project", "Test Beschreiben" );
        product.addProject( project );
    }

    @Test
    public void testProductRequirement()
    {
        Backlog<IProductRequirement> productBacklog = product.getProductBacklog();
        ProductStory story =
            new ProductStory( 1, "Test", "Test Beschreibung", 0.0f, Priority.MINOR, "Max",
                              RequirementKind.USER_STORY );
        productBacklog.addRequirement( story );

        List<IProductRequirement> productRequirements = new ArrayList<>();
        productRequirements.add( new ProductStory( 2, "Test", "Test Beschreibung", 1.0f,
                                                   Priority.MINOR, "Max",
                                                   RequirementKind.USER_STORY ) );
        productRequirements.add( new ProductStory( 3, "Test", "Test Beschreibung", 1.0f,
                                                   Priority.MINOR, "Max",
                                                   RequirementKind.USER_STORY ) );
        productBacklog.addAllRequirement( productRequirements );

        int counter = 0;
        for ( IProductRequirement productRequirement : product.getProductBacklog().getRequirementList() )
        {
            counter++;
            Assert.assertNotNull( productRequirement );
        }

        Assert.assertEquals( counter, 3 );

        try
        {
            story.setID( 4 );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testReleaseIterationPlanning()
    {
        Calendar start = new GregorianCalendar( 2012, 3, 2 );
        Calendar end = new GregorianCalendar( 2012, 4, 1 );

        Release release = new Release( start, end );

        Iteration iteration = new Iteration( start, end );
        release.addPlan( iteration );

        for ( IPlan plan : release.getPlanningStruct() )
        {
            Assert.assertEquals( plan, iteration );
        }

        try
        {
            release.addPlan( release );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }
    }

    @Test
    public void testProductBacklogToIterationBacklog()
    {
        Iteration iteration = new Iteration();
        Backlog<IProductRequirement> productBacklog = product.getProductBacklog();
        Backlog<IIterationRequirement> iterationBacklog = iteration.getIterationBacklog();

        productBacklog.addRequirement( new ProductStory( 1, "Test", "Test Beschreibung", 0.0f,
                                                         Priority.MINOR, "Max",
                                                         RequirementKind.USER_STORY ) );
        productBacklog.addRequirement( new ProductStory( 2, "Test", "Test Beschreibung", 1.0f,
                                                         Priority.MINOR, "Max",
                                                         RequirementKind.USER_STORY ) );
        for ( IProductRequirement requirement : productBacklog.getRequirementList() )
        {
            iterationBacklog.addRequirement( new IterationStory( requirement,
                                                                 ImplementState.PENDING ) );
        }
    }

    @Test
    public void testIterationStoryToProductBacklog()
    {
        Iteration iteration = new Iteration();
        Backlog<IProductRequirement> productBacklog = product.getProductBacklog();
        Backlog<IIterationRequirement> iterationBacklog = iteration.getIterationBacklog();

        iterationBacklog.addRequirement( new IterationStory(
                                                             new ProductStory(
                                                                               1,
                                                                               "Test",
                                                                               "Test Beschreibung",
                                                                               0.0f,
                                                                               Priority.MINOR,
                                                                               "Max",
                                                                               RequirementKind.USER_STORY ),
                                                             ImplementState.PENDING ) );
        iterationBacklog.addRequirement( new IterationStory(
                                                             new ProductStory(
                                                                               2,
                                                                               "Test",
                                                                               "Test Beschreibung",
                                                                               1.0f,
                                                                               Priority.MINOR,
                                                                               "Max",
                                                                               RequirementKind.USER_STORY ),
                                                             ImplementState.PENDING ) );
        for ( IIterationRequirement requirement : iterationBacklog.getRequirementList() )
        {
            productBacklog.addRequirement( requirement );
        }
    }
}
