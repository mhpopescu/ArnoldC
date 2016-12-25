package nodes.leafs;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class ConditionNode extends LeafNode {

    /**
     * 
     * @param condition
     */
    public ConditionNode(String condition) {
	super(condition);
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }
}
