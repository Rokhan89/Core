package com.github.jgility.core.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.IBacklog;
import com.github.jgility.core.planning.Iteration;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.project.IPerson;
import com.github.jgility.core.project.IProduct;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.project.Team;
import com.github.jgility.core.requirement.IIterationRequirement;
import com.github.jgility.core.requirement.IProductRequirement;
import com.github.jgility.core.requirement.ImplementableStory;
import com.github.jgility.core.requirement.IterationStory;
import com.github.jgility.core.requirement.Priority;
import com.github.jgility.core.requirement.ProductStory;
import com.github.jgility.core.requirement.RequirementKind;

public class JAXBTest
{

    private IPerson person;

    private IProduct product;

    @Before
    public void setUp()
        throws Exception
    {
        product = new Product();
        person = new Person( "Max", "Mustermann", "max@mustermann.de" );
        product.removeProductOwner();
        product.setProductOwner( person );
        Project project = new Project();
        product.addProject( project );
        Calendar start = new GregorianCalendar( 2012, 3, 1 );
        Calendar end = new GregorianCalendar( 2012, 7, 1 );
        Release release = new Release( start, end );
        project.addReleasePlan( release );
        Iteration iteration = new Iteration( start, end );
        release.addIteration( iteration );

        IBacklog<IProductRequirement> productBacklog = product.getProductBacklog();
        productBacklog.addRequirement( new ProductStory( 2, "Test", "Beschreibung", 3.0f,
                                                         Priority.BLOCKER, "Requester",
                                                         RequirementKind.BUG ) );

        IBacklog<IIterationRequirement> iterationBacklog = iteration.getIterationBacklog();
        iterationBacklog.addRequirement( new IterationStory() );
    }

    @Test
    public void testWrite()
    {
        JAXBContext context;
        try (final FileWriter w = new FileWriter( "database.xml" ))
        {
            context =
                JAXBContext.newInstance( Product.class, Project.class, Backlog.class,
                                         ProductStory.class, ImplementableStory.class, Team.class,
                                         Person.class, Release.class, Iteration.class );
            Marshaller m = context.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            m.marshal( product, w );
        }
        catch ( final JAXBException | IOException e )
        {
            Assert.fail( e.getLocalizedMessage() );
        }
    }

    @Test
    public void testRead()
    {
        try
        {
            JAXBContext context =
                JAXBContext.newInstance( Product.class, Project.class, Backlog.class,
                                         ProductStory.class, ImplementableStory.class, Team.class,
                                         Person.class, Release.class, Iteration.class );
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Product> element =
                um.unmarshal( new StreamSource( new File( "database.xml" ) ), Product.class );

            product = element.getValue();
        }
        catch ( JAXBException e )
        {
            System.out.println( e );
            Assert.fail( e.getLocalizedMessage() );
        }

    }
}
