/*
 * 
 * Copyright (c) 2011 by Jgility Development Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Karsten Schulz
 *
 */
package com.github.jgility.core.requirement;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.jgility.core.xml.AbstractXmlProductStroy;

/**
 * Marker-Interface für {@link IProductRequirement}
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlJavaTypeAdapter( AbstractXmlProductStroy.Adapter.class )
public interface IProductRequirement
    extends IRequirement
{

}
