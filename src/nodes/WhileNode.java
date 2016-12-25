package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class WhileNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    /**
     * 
     * @return AstNode condition child
     */
    public AstNode getCondition() {
	return sons.get(0);
    }

    /**
     * 
     * @return AstNode body child
     */
    public AstNode getBody() {
	return sons.get(1);
    }
}
