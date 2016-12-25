package nodes.leafs;

import interpreter.Visitor;
import nodes.AstNode;

/**
 * 
 * @author Mihai Popescu
 *
 */
public abstract class LeafNode extends AstNode {
    protected String value;

    /**
     * 
     * @param value
     */
    public LeafNode(String value) {
	this.value = value;
    }

    @Override
    public abstract void accept(Visitor v);

    @Override
    public String toString() {
	return "<" + value + ">";
    }

    public String getValue() {
	return value;
    }
}
