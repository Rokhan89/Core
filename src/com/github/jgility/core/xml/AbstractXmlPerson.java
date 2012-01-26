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
import com.github.jgility.core.project.IPerson;

/**
 * Abstrakte Klasse für {@link IPerson}, welche einen {@link XmlAdapter} beinhaltet, der für die
 * XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlPerson
    extends ModelObject
    implements IPerson
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlPerson}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter
        extends XmlAdapter<AbstractXmlPerson, IPerson>
    {

        @Override
        public AbstractXmlPerson marshal( IPerson v )
            throws Exception
        {
            return (AbstractXmlPerson) v;
        }

        @Override
        public IPerson unmarshal( AbstractXmlPerson v )
            throws Exception
        {
            return v;
        }

    }

}
