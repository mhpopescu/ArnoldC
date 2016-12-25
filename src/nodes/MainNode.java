package nodes;

import interpreter.Visitor;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class MainNode extends AstNode {

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

}
