package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class BodyNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

}
