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
import com.github.jgility.core.project.ITeam;

/**
 * Abstrakte Klasse für {@link ITeam}, welche einen {@link XmlAdapter} beinhaltet, der für die
 * XML-JPA benutzt wird
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlTeam
    extends ModelObject
    implements ITeam
{

    /**
     * Apdapter-Klasse für {@link AbstractXmlTeam}
     * 
     * @author Karsten Schulz (lennylinux.ks@googlemail.com)
     */
    public static class Adapter
        extends XmlAdapter<AbstractXmlTeam, ITeam>
    {

        @Override
        public AbstractXmlTeam marshal( ITeam v )
            throws Exception
        {
            return (AbstractXmlTeam) v;
        }

        @Override
        public ITeam unmarshal( AbstractXmlTeam v )
            throws Exception
        {
            return v;
        }

    }
}
