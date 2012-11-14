/*
 * @(#)Observer.java	1.17 01/12/03
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package de.jwic.util;

import java.io.Serializable;

/**
 * This is a Serializable version of the java.util.Observable class. It is 
 * recommend to use this version instead of the original one as the
 * original one does not support serializsation.
 * 
 * @author  Florian Lippisch (original from Chris Warth)
 * @see     java.util.Observer
 * @see     de.jwic.util.SerObservable
 */
public interface SerObserver extends Serializable {
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the <code>notifyObservers</code>
     *                 method.
     */
    void update(SerObservable o, Object arg);
}
