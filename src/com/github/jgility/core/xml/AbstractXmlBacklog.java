/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.planning.IBacklog;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlBacklog<T>
    implements IBacklog<T>
{

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
