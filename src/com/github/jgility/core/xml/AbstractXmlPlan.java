/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.planning.IPlan;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlPlan
    implements IPlan
{

    public static class Adapter
        extends XmlAdapter<AbstractXmlPlan, IPlan>
    {

        @Override
        public AbstractXmlPlan marshal( IPlan v )
            throws Exception
        {
            return (AbstractXmlPlan) v;
        }

        @Override
        public IPlan unmarshal( AbstractXmlPlan v )
            throws Exception
        {
            return v;
        }

    }

}
