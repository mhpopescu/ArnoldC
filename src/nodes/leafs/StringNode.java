package nodes.leafs;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class StringNode extends LeafNode {

    /**
     * 
     * @param data
     */
    public StringNode(String data) {
	super(data);
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }
}
