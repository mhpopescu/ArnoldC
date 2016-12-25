package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class PrintNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    /**
     * 
     * @return AstNode with the PrintNode's value
     */
    public AstNode getChild() {
	return sons.get(0);
    }

    @Override
    public String toString() {
	return getChild().toString();
    }
}
