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
package com.github.jgility.core.planning;

import com.github.jgility.core.requirement.IIterationRequirement;

/**
 * Interface zur Generalisierung der Iterationsplanung. Stellt ein Backlog zur Verfügung, um
 * Anforderungen zu strukturieren
 * 
 * @author Karsten Schulz (lennylinux.ks@googlemail.com)
 */
public interface IIteration
    extends IPlan
{

    /**
     * Gibt die Referenz zum {@link Backlog} welche {@link IIterationRequirement} beinhaltet
     * 
     * @return Referenz auf das {@link Backlog} um dies zu ändern
     */
    IBacklog<IIterationRequirement> getIterationBacklog();

}