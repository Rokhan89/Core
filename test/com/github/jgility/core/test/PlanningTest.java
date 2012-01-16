package com.github.jgility.core.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.IRelease;
import com.github.jgility.core.planning.Iteration;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.project.IPerson;
import com.github.jgility.core.project.IProduct;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.util.ReleasePlanningUtils;

public class PlanningTest
{

    private IProduct product;

    private Project project;

    @Before
    public void setUp()
        throws Exception
    {
        IPerson person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        product = new Product( "Test Product", "Test Description", person );
        project = new Project( "Test Project", "Test Beschreiben" );
        product.addProject( project );
    }

    @Test
    public void testIterationReleasePlaning()
    {
        Calendar start = new GregorianCalendar( 2012, 2, 1 );
        Calendar end1 = new GregorianCalendar( 2012, 2, 15 );
        Calendar start1 = new GregorianCalendar( 2012, 2, 15 );
        Calendar end = new GregorianCalendar( 2012, 2, 28 );

        Release release = new Release( start, end );

        Iteration iteration = new Iteration( start, end1 );
        release.addIteration( iteration );
        iteration = new Iteration( start1, end );
        release.addIteration( iteration );

        Assert.assertTrue( release.size() == 2 );

        project.addReleasePlan( release );
    }

    private void checkDate( Calendar first, Calendar second )
    {
        Assert.assertTrue( first.get( Calendar.YEAR ) == second.get( Calendar.YEAR ) );
        Assert.assertTrue( first.get( Calendar.MONTH ) == second.get( Calendar.MONTH ) );
        Assert.assertTrue( first.get( Calendar.DAY_OF_MONTH ) == second.get( Calendar.DAY_OF_MONTH ) );
    }

    @Test
    public void testDateChangeAlgorithmCut()
    {
        testIterationReleasePlaning();
        IRelease release = project.getReleasePlan().get( 0 );
        ReleasePlanningUtils.changePlanCutSubPlan( release, new GregorianCalendar( 2012, 2, 12 ),
                                                  new GregorianCalendar( 2012, 3, 1 ) );
        checkDate( release.getIteration( 0 ).getStart(), new GregorianCalendar( 2012, 2, 12 ) );
        checkDate( release.getIteration( 1 ).getEnd(), new GregorianCalendar( 2012, 3, 1 ) );

        ReleasePlanningUtils.changePlanCutSubPlan( release, new GregorianCalendar( 2012, 2, 16 ),
                                                  new GregorianCalendar( 2012, 2, 30 ) );
        Assert.assertTrue( release.size() == 1 );

        try
        {
            ReleasePlanningUtils.changePlanCutSubPlan( release,
                                                      new GregorianCalendar( 2012, 4, 16 ),
                                                      new GregorianCalendar( 2012, 4, 12 ) );
            Assert.fail();
        }
        catch ( IllegalArgumentException e )
        {
        }
    }

    @Test
    public void testDateChangeAlgorithmPercent()
    {
        testIterationReleasePlaning();
        IRelease release = project.getReleasePlan().get( 0 );
        ReleasePlanningUtils.changePlanPerPercent( release, new GregorianCalendar( 2012, 3, 1 ),
                                                  new GregorianCalendar( 2012, 3, 19 ) );
    }
}
