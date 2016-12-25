package nodes.leafs;

import interpreter.Visitor;
import nodes.AstNode;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class ConstantNode extends AstNode {
    private int value;

    /**
     * 
     * @param value
     */
    public ConstantNode(int value) {
	this.value = value;
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    @Override
    public String toString() {
	return "<" + value + ">";
    }

    public int getValue() {
	return value;
    }
}
