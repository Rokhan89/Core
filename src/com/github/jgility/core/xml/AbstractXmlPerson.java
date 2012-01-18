/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.project.IPerson;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlPerson
    implements IPerson
{

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
