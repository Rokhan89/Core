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

/**
 * Interface für Schätzungen
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public interface IEffort
{

    /**
     * Gibt die ursprüngliche Schätzung zurück
     * 
     * @return ursprüngliche Schätzung als float
     */
    float getEstimated();

    /**
     * Gibt die tatsächliche effektiv gearbeiten Wert zurück
     * 
     * @return tatsächlicher Wert
     */
    float getEffective();

    /**
     * Setzt die ursprüngliche Schätzung
     * 
     * @param estimated als float
     * @throws IllegalArgumentException wenn die Schätzung im negativem Bereich liegt
     */
    void setEstimated( float estimated )
        throws IllegalArgumentException;
}
