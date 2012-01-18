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

import com.github.jgility.core.project.ITeam;
import com.github.jgility.core.xml.AbstractXmlImplementableStory;

/**
 * Interface für eine Anforderung, welche Implementiert werden kann
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
@XmlJavaTypeAdapter( AbstractXmlImplementableStory.Adapter.class )
public interface IImplementableRequirement
    extends IRequirement
{

    /**
     * Gibt den Status der Implementierung zurück
     * 
     * @return
     */
    ImplementState getImplementState();

    /**
     * Setzt den Implementierungsstatus
     * 
     * @param implementState als {@link ImplementState}
     * @throws IllegalArgumentException wenn der Implementierungsstatus den Wert <code>null</code>
     *             beinhaltet.
     */
    void setImplementState( ImplementState implementState )
        throws IllegalArgumentException;

    /**
     * Gibt das bearbeitende Team zurück, welche die Anforderung implementieren soll
     * 
     * @return
     */
    ITeam getAssignee();

    /**
     * Setzt das bearbeitende Team
     * 
     * @param assignee
     * @throws IllegalArgumentException wird geworfen, wenn das Team leer ist
     */
    void setAssignee( ITeam assignee )
        throws IllegalArgumentException;
}
