package nodes;

import java.util.HashMap;
import java.util.Map;

import interpreter.Visitor;
import utils.Token;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class OperatorNode extends AstNode {
    private Token type;

    /**
     * 
     * @param token
     */
    public OperatorNode(Token token) {
	this.type = token;
    }

    /**
     * 
     * @return node's Token type
     */
    public Token getType() {
	return type;
    }

    @Override
    public void accept(Visitor v) {
	v.visit(this);
    }

    /**
     * 
     * @return node name of the Operator
     */
    public String getNodeName() {
	return operatorNodeTypes.get(type);
    }

    /**
     * 
     * @return AstNode operand
     */
    public AstNode getFirstOperand() {
	return sons.get(0);
    }

    /**
     * 
     * @return AstNode operand
     */
    public AstNode getSecondOperand() {
	return sons.get(1);
    }

    private final static Map<Token, String> operatorNodeTypes;
    static {
	operatorNodeTypes = new HashMap<Token, String>();
	operatorNodeTypes.put(Token.And, "AndNode");
	operatorNodeTypes.put(Token.EqualTo, "EqualToNode");
	operatorNodeTypes.put(Token.GreaterThan, "GreaterThanNode");
	operatorNodeTypes.put(Token.MinusOperator, "DifferenceNode");
	operatorNodeTypes.put(Token.ModuloOperator, "ModuloNode");
	operatorNodeTypes.put(Token.MultiplicationOperator,
		"MultiplicationNode");
	operatorNodeTypes.put(Token.Or, "OrNode");
	operatorNodeTypes.put(Token.PlusOperator, "SumNode");
	operatorNodeTypes.put(Token.DivisionOperator, "DivisionNode");
    }

    /**
     * 
     * @param token
     * @return true if the token corresponds to an OpperatorNode
     */
    public static boolean isOperator(Token token) {
	return operatorNodeTypes.containsKey(token);
    }
}
