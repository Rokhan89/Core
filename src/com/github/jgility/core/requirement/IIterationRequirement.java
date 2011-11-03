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

import java.util.List;

/**
 * Interface welche eine bestimmte {@link IIterationRequirement} definiert
 * 
 * @author Karsten Schulz <lennylinux.ks@googlemail.com>
 */
public interface IIterationRequirement
    extends IImplementableRequirement, IProductRequirement
{

    /**
     * Fügt eine neue Anforderung als abhängige Story hinzu
     * 
     * @param requirement hinzuzufügende {@link IRequirement}
     * @throws IllegalArgumentException wird geworfen, wenn der Parameter <code>null</code> ist
     */
    void addDependency( IRequirement requirement )
        throws IllegalArgumentException;

    /**
     * Entfernt eine Anforderung aus der {@link List}
     * 
     * @param requirement zu entfernende {@link IRequirement}
     * @return <code>true</code> wenn requirement in der {@link List} gefunden und entfernt wurde
     */
    boolean removeDependency( IRequirement requirement );

    /**
     * Gibt eine unveränderliche {@link List} mit {@link IRequirement} zurück, welche die abhängigen
     * Anforderungen beinhaltet
     * 
     * @return unveränderliche {@link List} mit {@link IRequirement}
     */
    List<IRequirement> getDependencies();

    /**
     * Fügt eine neue Aufgabe als {@link IImplementableRequirement} hinzu
     * 
     * @param task {@link IImplementableRequirement} in Form einer {@link RequirementKind#TASK}
     */
    void addTask( IImplementableRequirement task );

    /**
     * Entfernt eine Aufgabe als {@link IImplementableRequirement} aus der {@link List}
     * 
     * @param task {@link IImplementableRequirement} in Form einer {@link RequirementKind#TASK}
     * @return <code>true</code> wenn Element in der {@link List} entfernt wurde
     */
    boolean removeTask( IImplementableRequirement task );

    /**
     * Gibt eine unveränderliche {@link List} mit {@link IImplementableRequirement} zurück, welche
     * alle untergeordneten Aufgaben repräsentiert
     * 
     * @return unveränderliche {@link List} mit {@link IImplementableRequirement}
     */
    List<IImplementableRequirement> getTaskList();
}
