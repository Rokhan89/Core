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
 *
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.ModelObject;
import com.github.jgility.core.project.IProject;

/**
 * Abstrakte Klasse für {@link IProject}, welche einen {@link XmlAdapter} beinhaltet, der für die
 * XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProject
    extends ModelObject
    implements IProject
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlProject}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter
        extends XmlAdapter<AbstractXmlProject, IProject>
    {

        @Override
        public AbstractXmlProject marshal( IProject arg0 )
            throws Exception
        {
            return (AbstractXmlProject) arg0;
        }

        @Override
        public IProject unmarshal( AbstractXmlProject arg0 )
            throws Exception
        {
            return arg0;
        }
    }

}
