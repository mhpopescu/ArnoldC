package utils;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public interface Visitable {
    /**
     * 
     * @param v
     */
    public void accept(Visitor v);
}
