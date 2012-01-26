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
import com.github.jgility.core.requirement.IProductRequirement;

/**
 * Abstrakte Klasse für {@link IProductRequirement}, welche einen {@link XmlAdapter} beinhaltet, der
 * für die XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProductStroy
    extends ModelObject
    implements IProductRequirement
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlProductStroy}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter
        extends XmlAdapter<AbstractXmlProductStroy, IProductRequirement>
    {

        @Override
        public AbstractXmlProductStroy marshal( IProductRequirement v )
            throws Exception
        {
            return (AbstractXmlProductStroy) v;
        }

        @Override
        public IProductRequirement unmarshal( AbstractXmlProductStroy v )
            throws Exception
        {
            return v;
        }

    }

}
