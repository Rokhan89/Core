/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.planning.IRelease;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlRelease
    implements IRelease
{

    public static class Adapter
        extends XmlAdapter<AbstractXmlRelease, IRelease>
    {

        @Override
        public AbstractXmlRelease marshal( IRelease v )
            throws Exception
        {
            return (AbstractXmlRelease) v;
        }

        @Override
        public IRelease unmarshal( AbstractXmlRelease v )
            throws Exception
        {
            return v;
        }

    }

}
