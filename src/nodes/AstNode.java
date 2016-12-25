package nodes;

import java.util.ArrayList;
import java.util.List;

import interpreter.Visitor;
import utils.Visitable;

/**
 * 
 * @author Mihai Popescu
 *
 */
public abstract class AstNode implements Visitable {
    protected List<AstNode> sons = new ArrayList<AstNode>();

    /**
     * 
     * @param child
     */
    public void addChild(AstNode child) {
	sons.add(child);
    }

    /**
     * Insert a child to the first position. All the other childs are shifted
     * with one position to the right
     * 
     * @param child
     */
    public void addChildFirst(AstNode child) {
	sons.add(0, child);
    }

    /**
     * 
     * @return ArrayList of childs
     */
    public List<AstNode> getSons() {
	return sons;
    }

    @Override
    public abstract void accept(Visitor v);
}
