/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.project.ITeam;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlTeam
    implements ITeam
{

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
