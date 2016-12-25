package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class IfNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    /**
     * 
     * @return AstNode - the condition node
     */
    public AstNode getCondition() {
	return sons.get(0);
    }

    /**
     * 
     * @return AstNode - the if body node
     */
    public AstNode getIfBodyNode() {
	return sons.get(1);
    }

    /**
     * 
     * @return AstNode - the else body node
     */
    public AstNode getElseBodyNode() {
	return sons.get(2);
    }

    /**
     * 
     * @return true if the IfNode contains an else body node
     */
    public boolean containsElseBody() {
	return sons.size() == 3;
    }
}
