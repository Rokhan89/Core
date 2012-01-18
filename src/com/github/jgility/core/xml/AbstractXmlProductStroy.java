/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.requirement.IProductRequirement;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProductStroy
    implements IProductRequirement
{

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
