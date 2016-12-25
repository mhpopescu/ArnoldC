package nodes.leafs;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class LvalNode extends LeafNode {

    /**
     * 
     * @param name
     */
    public LvalNode(String name) {
	super(name);
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }
}
