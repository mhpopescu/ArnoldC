package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class AssignmentNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    public AstNode getLeftSide() {
	return sons.get(0);
    }

    public AstNode getRightSide() {
	return sons.get(1);
    }
}
