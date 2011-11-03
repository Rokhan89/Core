package com.github.jgility.core.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.Iteration;
import com.github.jgility.core.planning.Release;
import com.github.jgility.core.project.Person;
import com.github.jgility.core.project.Product;
import com.github.jgility.core.project.Project;
import com.github.jgility.core.requirement.IIterationRequirement;
import com.github.jgility.core.requirement.IProductRequirement;
import com.github.jgility.core.requirement.IterationStory;
import com.github.jgility.core.requirement.ProductStory;

public class JAXBTest
{

    private Person person;

    private Product product;

    @Before
    public void setUp()
        throws Exception
    {
        product = new Product();
        person = new Person();
        product.removeProductOwner();
        product.setProductOwner( person );
    }

    @Test
    public void testWrite()
    {
        Project project = new Project();
        product.addProject( project );
        Calendar start = new GregorianCalendar( 2012, 3, 1 );
        Calendar end = new GregorianCalendar( 2012, 7, 1 );
        Release release = new Release( start, end );
        project.addProjectPlan( release );
        Iteration iteration = new Iteration( start, end );
        release.addPlan( iteration );

        Backlog<IProductRequirement> productBacklog = product.getProductBacklog();
        productBacklog.addRequirement( new ProductStory() );

        Backlog<IIterationRequirement> iterationBacklog = iteration.getIterationBacklog();
        iterationBacklog.addRequirement( new IterationStory() );

        JAXBContext context;
        try
        {
            context = JAXBContext.newInstance( Product.class );
            Marshaller m = context.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            Writer w = null;
            try
            {
                w = new FileWriter( "database.xml" );
                m.marshal( product, w );
            }
            finally
            {
                try
                {
                    w.close();
                }
                catch ( Exception e )
                {
                }
            }
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
            JAXBContext context = JAXBContext.newInstance( Product.class );
            Unmarshaller um = context.createUnmarshaller();
            product = (Product) um.unmarshal( new FileReader( "database.xml" ) );
        }
        catch ( final JAXBException | IOException e )
        {
            Assert.fail( e.getLocalizedMessage() );
        }

    }
}
