package nodes.leafs;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class RvalNode extends LeafNode {

    /**
     * 
     * @param value
     */
    public RvalNode(String value) {
	super(value);
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }
}
