package nodes.leafs;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class VariableNode extends LeafNode {

    public VariableNode(String name) {
	super(name);
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    public String getName() {
	return value;
    }
}
