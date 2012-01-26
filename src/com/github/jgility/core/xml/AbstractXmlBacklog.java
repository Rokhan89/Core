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
import com.github.jgility.core.planning.IBacklog;

/**
 * Abstrakte Klasse für {@link IBacklog}, welche einen {@link XmlAdapter} beinhaltet, der für die
 * XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlBacklog<T>
    extends ModelObject
    implements IBacklog<T>
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlBacklog}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter<T>
        extends XmlAdapter<AbstractXmlBacklog<T>, IBacklog<T>>
    {

        @Override
        public AbstractXmlBacklog<T> marshal( IBacklog<T> v )
            throws Exception
        {
            return (AbstractXmlBacklog<T>) v;
        }

        @Override
        public IBacklog<T> unmarshal( AbstractXmlBacklog<T> v )
            throws Exception
        {
            return v;
        }

    }
}
