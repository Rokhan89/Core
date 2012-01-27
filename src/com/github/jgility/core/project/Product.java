/*
 * 
 * Copyright (c) 2011 by Jgility Development Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Karsten Schulz
 *     Christoph Viebig
 *
 */
package com.github.jgility.core.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.jgility.core.planning.Backlog;
import com.github.jgility.core.planning.IBacklog;
import com.github.jgility.core.requirement.IProductRequirement;
import com.github.jgility.core.xml.AbstractXmlProduct;

/**
 * Klasse, welche das Produkt im Sinne der agilen Softwareentwicklung repräsentiert. Beinhaltet
 * Informationen über den ProductOwner in Form einer {@link Person}-Referenz. Zusätzlich beinhaltet
 * dieser auch den ProductBacklog in Form einer {@link Backlog}-Referenz
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Product
    extends AbstractXmlProduct
{

    /**
     * Bezeichner der Eigenschaft {@link #name}
     */
    public static final String NAME_PROPERTY = "name";

    /**
     * Bezeichner der Eigenschaft {@link #projects}
     */
    public static final String PROJECTS_PROPERTY = "projects";

    /**
     * Bezeichner der Eigenschaft {@link #description}
     */
    public static final String DESCRIPTION_PROPERTY = "description";

    /**
     * Bezeichner der Eigenschaft {@link #productOwner}
     */
    public static final String PRODUCT_OWNER_PROPERTY = "productOwner";

    /**
     * Bezeichner der Eigenschaft {@link #productBacklog}
     */
    public static final String PRODUCT_BACKLOG_PROPERTY = "productBacklog";

    @XmlElement
    private String name;

    @XmlElement
    private String description;

    @XmlElement
    private final Set<IProject> projects;

    @XmlElement
    private IPerson productOwner;

    @XmlElement
    private IBacklog<IProductRequirement> productBacklog;

    /**
     * Parameterloser Konstruktor um ein leeres {@link Product} zu instanziieren.<br>
     * Initialisierte Standard-Werte für Name (<code>Default</code>), Beschreibung (
     * <code>Default</code>) und ein Standard-{@link Person}
     * 
     * @see Person#Person()
     */
    public Product()
    {
        this( "Default", "Default", new Person() );
    }

    /**
     * Instanziiert ein leeres {@link Product} mit Namen, Beschreibung und ProductOwner
     * 
     * @param name Name des {@link Product}
     * @param description Beschreibung des {@link Product}
     * @param productOwner ProductOwner in Form einer {@link Person}
     * @throws IllegalArgumentException wird geworfen, wenn der Name oder der ProductOwner invalide
     *             Werte beinhaltet
     * @see #setName(String)
     * @see #setProductOwner(Person)
     */
    public Product( String name, String description, IPerson productOwner )
        throws IllegalArgumentException
    {
        super();
        setName( name );
        setDescription( description );
        setProductOwner( productOwner );
        productBacklog = new Backlog<>();
        projects = new HashSet<>();
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#getName()
     */
    @Override
    public String getName()
    {
        return name;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#setName(java.lang.String)
     */
    @Override
    public void setName( String name )
        throws IllegalArgumentException
    {
        if ( StringUtils.isNotBlank( name ) )
        {
            String formerName = this.name;
            this.name = name;
            changes.firePropertyChange( Product.NAME_PROPERTY, formerName, this.name );
        }
        else
        {
            throw new IllegalArgumentException( "empty name for products is not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#getDescription()
     */
    @Override
    public String getDescription()
    {
        return description;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#setDescription(java.lang.String)
     */
    @Override
    public void setDescription( String description )
    {
        String formerDescription = this.description;
        this.description = description;
        changes.firePropertyChange( Product.DESCRIPTION_PROPERTY, formerDescription,
                                    this.description );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#getProjects()
     */
    @Override
    public List<IProject> getProjects()
    {
        final List<IProject> projectList = new ArrayList<>( projects );
        return Collections.unmodifiableList( projectList );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#setProjects(java.util.List)
     */
    @Override
    public void setProjects( List<IProject> projects )
        throws IllegalArgumentException
    {
        if ( CollectionUtils.isNotEmpty( projects ) )
        {
            Set<IProject> formerProjects = this.projects;
            this.projects.addAll( projects );
            changes.firePropertyChange( Product.PROJECTS_PROPERTY, formerProjects, this.projects );

        }
        else
        {
            throw new IllegalArgumentException( "empty list of projects is not allowed to add" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProduct#addProject(com.github.jgility.core.project.Project)
     */
    @Override
    public void addProject( IProject newProject )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, newProject ) )
        {
            Set<IProject> formerProjects = this.projects;
            this.projects.add( newProject );
            changes.firePropertyChange( Product.PROJECTS_PROPERTY, formerProjects, this.projects );
        }
        else
        {
            throw new IllegalArgumentException( "null-object as project is not allowed" );
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProduct#removeProject(com.github.jgility.core.project.Project
     * )
     */
    @Override
    public boolean removeProject( IProject removeProject )
    {
        if ( ObjectUtils.notEqual( null, removeProject ) )
        {
            boolean result;
            Set<IProject> formerProjects = this.projects;
            result = this.projects.remove( removeProject );
            changes.firePropertyChange( Product.PROJECTS_PROPERTY, formerProjects, this.projects );
            return result;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#clearProject()
     */
    @Override
    public void clearProject()
    {
        Set<IProject> formerProjects = projects;
        projects.clear();
        changes.firePropertyChange( Product.PROJECTS_PROPERTY, formerProjects, projects );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#getProductOwner()
     */
    @Override
    public IPerson getProductOwner()
    {
        return productOwner;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProduct#setProductOwner(com.github.jgility.core.project.
     * Person)
     */
    @Override
    public void setProductOwner( IPerson productOwner )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, this.productOwner ) )
        {
            throw new IllegalArgumentException( "productowner is already initialize: "
                + this.productBacklog );
        }
        if ( ObjectUtils.notEqual( null, productOwner ) )
        {
            IPerson formerProductOwner = this.productOwner;
            this.productOwner = productOwner;
            changes.firePropertyChange( Product.PRODUCT_OWNER_PROPERTY, formerProductOwner,
                                        this.productOwner );
        }
        else
        {
            throw new IllegalArgumentException( "null person is not allowed as productowner" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#removeProductOwner()
     */
    @Override
    public void removeProductOwner()
    {
        IPerson formerProductOwner = this.productOwner;
        productOwner = null;
        changes.firePropertyChange( Product.PRODUCT_OWNER_PROPERTY, formerProductOwner,
                                    this.productOwner );
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#getProductBacklog()
     */
    @Override
    public IBacklog<IProductRequirement> getProductBacklog()
    {
        return productBacklog;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.jgility.core.project.IProduct#setProductBacklog(com.github.jgility.core.planning
     * .Backlog)
     */
    @Override
    public void setProductBacklog( IBacklog<IProductRequirement> productBacklog )
        throws IllegalArgumentException
    {
        if ( ObjectUtils.notEqual( null, this.productBacklog ) )
        {
            throw new IllegalArgumentException( "productbacklog is already initialize" );
        }
        else if ( ObjectUtils.notEqual( null, productBacklog ) )
        {
            IBacklog<IProductRequirement> formerProductBacklog = this.productBacklog;
            this.productBacklog = productBacklog;
            changes.firePropertyChange( Product.PRODUCT_BACKLOG_PROPERTY, formerProductBacklog,
                                        this.productBacklog );
        }
        else
        {
            throw new IllegalArgumentException( "null backlog is not allowed as productbacklog" );
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.jgility.core.project.IProduct#removeProductBacklog()
     */
    @Override
    public void removeProductBacklog()
    {
        IBacklog<IProductRequirement> formerProductbacklog = this.productBacklog;
        productBacklog = null;
        changes.firePropertyChange( Product.PRODUCT_BACKLOG_PROPERTY, formerProductbacklog,
                                    this.productBacklog );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append( name );
        builder.append( description );
        builder.append( productBacklog );
        builder.append( productOwner );
        builder.append( projects );

        return builder.toHashCode();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( obj instanceof Product )
        {
            Product product = (Product) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append( description, product.description );
            builder.append( name, product.name );
            builder.append( productBacklog, product.productBacklog );
            builder.append( productOwner, product.productOwner );
            builder.append( projects, product.projects );
            return builder.isEquals();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        ToStringBuilder builder = new ToStringBuilder( this, ToStringStyle.SHORT_PREFIX_STYLE );
        builder.append( "name", name );
        builder.append( "description", description );
        builder.append( "projects", projects );
        builder.append( "productOwner", productOwner );
        builder.append( "productBacklog", productBacklog );
        return builder.build();
    }
}
