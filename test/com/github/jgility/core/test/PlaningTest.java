package com.github.jgility.core.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.IPlan;
import com.github.jgility.core.planning.Iteration;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;

public class PlaningTest
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
    public void testIterationReleasePlaning()
    {
        Calendar start = new GregorianCalendar( 2012, 3, 1 );
        Calendar end1 = new GregorianCalendar( 2012, 3, 15 );
        Calendar start1 = new GregorianCalendar( 2012, 3, 16 );
        Calendar end = new GregorianCalendar( 2012, 4, 1 );

        Release release = new Release( start, end );

        Iteration iteration = new Iteration( start, end1 );
        release.addPlan( iteration );
        iteration = new Iteration( start1, end );
        release.addPlan( iteration );

        Assert.assertTrue( release.getPlanningStruct().size() == 2 );

        try
        {
            release.addPlan( release );
            Assert.fail( "no exception was threw" );
        }
        catch ( IllegalArgumentException iae )
        {
        }

        project.addProjectPlan( release );
    }

    private void checkCalendar( Calendar calendar, Calendar otherCalendar )
    {
        Assert.assertEquals( calendar.get( Calendar.YEAR ), otherCalendar.get( Calendar.YEAR ) );
        Assert.assertEquals( calendar.get( Calendar.MONTH ), otherCalendar.get( Calendar.MONTH ) );
        Assert.assertEquals( calendar.get( Calendar.DAY_OF_MONTH ),
                             otherCalendar.get( Calendar.DAY_OF_MONTH ) );
    }

    @Test
    public void testChangeReleasePlaning()
    {
        testIterationReleasePlaning();
        Calendar start = new GregorianCalendar( 2012, 3, 10 );
        Calendar end = new GregorianCalendar( 2012, 4, 5 );

        IPlan release = project.getProjectPlan().get( 0 ); // Release
        release.setStart( start );

        IPlan iteration = release.getPlanningStruct().get( 0 );
        checkCalendar( start, iteration.getStart() );

        // iteration = release.getPlanningStruct().get( 1 );
        // checkCalendar( end, iteration.getEnd() );

        for ( IPlan plan : release.getPlanningStruct() )
        {
            System.out.println( plan );
        }
    }

}
