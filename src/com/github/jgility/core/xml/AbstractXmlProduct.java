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

import com.github.jgility.core.project.IProduct;

/**
 * Abstrakte Klasse für {@link IProduct}, welche einen {@link XmlAdapter} beinhaltet, der für die
 * XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProduct
    implements IProduct
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlProduct}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter
        extends XmlAdapter<AbstractXmlProduct, IProduct>
    {

        @Override
        public AbstractXmlProduct marshal( IProduct v )
            throws Exception
        {
            return (AbstractXmlProduct) v;
        }

        @Override
        public IProduct unmarshal( AbstractXmlProduct v )
            throws Exception
        {
            return v;
        }

    }
}
