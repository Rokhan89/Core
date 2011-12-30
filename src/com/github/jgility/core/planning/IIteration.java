/*
 * = License =
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
    Backlog<IIterationRequirement> getIterationBacklog();

}