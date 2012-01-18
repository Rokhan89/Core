/*
 * = License =
 */
package com.github.jgility.core.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.github.jgility.core.project.IProject;

/**
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public abstract class AbstractXmlProject
    implements IProject
{

    public static class Adapter
        extends XmlAdapter<AbstractXmlProject, IProject>
    {

        @Override
        public AbstractXmlProject marshal( IProject arg0 )
            throws Exception
        {
            return (AbstractXmlProject) arg0;
        }

        @Override
        public IProject unmarshal( AbstractXmlProject arg0 )
            throws Exception
        {
            return arg0;
        }
    }

}
