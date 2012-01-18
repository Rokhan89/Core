/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.planning.IIteration;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlIteration
    implements IIteration
{

    public static class Adapter
        extends XmlAdapter<AbstractXmlIteration, IIteration>
    {

        @Override
        public AbstractXmlIteration marshal( IIteration v )
            throws Exception
        {
            return (AbstractXmlIteration) v;
        }

        @Override
        public IIteration unmarshal( AbstractXmlIteration v )
            throws Exception
        {
            return v;
        }

    }

}
