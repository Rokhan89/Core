/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.project.IProduct;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProduct
    implements IProduct
{

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
