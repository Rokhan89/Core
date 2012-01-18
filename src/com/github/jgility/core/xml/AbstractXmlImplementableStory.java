/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.requirement.IImplementableRequirement;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlImplementableStory
    implements IImplementableRequirement
{

    public static class Adapter
        extends XmlAdapter<AbstractXmlImplementableStory, IImplementableRequirement>
    {

        @Override
        public AbstractXmlImplementableStory marshal( IImplementableRequirement v )
            throws Exception
        {
            return (AbstractXmlImplementableStory) v;
        }

        @Override
        public IImplementableRequirement unmarshal( AbstractXmlImplementableStory v )
            throws Exception
        {
            return v;
        }

    }

}
