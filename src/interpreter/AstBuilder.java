package interpreter;

import java.io.FileNotFoundException;

import nodes.*;
import nodes.leafs.*;
import utils.Parser;
import utils.SyntaxException;
import utils.Token;

/**
 * ArnoldC Abstract Syntax Tree builder
 * 
 * @author Mihai Popescu
 *
 */
public class AstBuilder {
    private AstNode main;
    private Parser parser;

    /**
     * 
     * @param fileName
     * @throws FileNotFoundException
     */
    public AstBuilder(String fileName) throws FileNotFoundException {
	parser = new Parser(fileName);
    }

    /**
     * Builds the Ast
     * 
     * @return root of the Ast
     */
    public AstNode buildAst() {
	Token beginMain = parser.getToken();

	if (!beginMain.equals(beginMain)) {
	    throw new SyntaxException();
	}

	makeMainNode();

	return main;
    }

    private void makeMainNode() {
	main = new MainNode();
	Token token = parser.getToken();

	while (!token.equals(Token.EndMain)) {
	    AstNode node = getNode(token);
	    main.addChild(node);
	    token = parser.getToken();
	}
    }

    private AstNode makeDeclareNode() {
	AstNode node = new DeclareNode();
	AstNode lvalNode = new LvalNode(parser.getArgument());

	Token token = parser.getToken();

	if (!token.equals(Token.SetInitialValue)) {
	    throw new SyntaxException();
	}

	AstNode constantNode = new ConstantNode(parser.getConstant());

	node.addChild(lvalNode);
	node.addChild(constantNode);

	return node;
    }

    private AstNode makePrintNode() {
	AstNode node = new PrintNode();
	String argument = parser.getArgument();

	AstNode child = null;
	if (parser.isArgumentVarriable()) {
	    child = new VariableNode(argument);
	} else {
	    child = new StringNode(argument);
	}
	node.addChild(child);

	return node;
    }

    private AstNode makeIfNode() {
	AstNode node = new IfNode();
	String argument = parser.getArgument();

	AstNode ConditionNode = new ConditionNode(argument);
	node.addChild(ConditionNode);

	AstNode bodyNode = makeIfBodyNode();
	node.addChild(bodyNode);

	if (parser.getLastToken().equals(Token.Else)) {
	    AstNode elseNode = makeElseBodyNode();
	    node.addChild(elseNode);
	}

	return node;
    }

    private AstNode makeIfBodyNode() {
	AstNode ifBodyNode = new IfBodyNode();
	Token token = parser.getToken();

	while (!token.equals(Token.Else) && !token.equals(Token.Endif)) {
	    AstNode node = getNode(token);
	    ifBodyNode.addChild(node);
	    token = parser.getToken();
	}

	return ifBodyNode;
    }

    private AstNode makeElseBodyNode() {
	AstNode elseBodyNode = new ElseBodyNode();
	Token token = parser.getToken();

	while (!token.equals(Token.Endif)) {
	    AstNode node = getNode(token);
	    elseBodyNode.addChild(node);
	    token = parser.getToken();
	}

	return elseBodyNode;
    }

    private AstNode makeAssignmentNode() {
	AstNode assignmentNode = new AssignmentNode();
	String argument = parser.getArgument();

	LvalNode lvalNode = new LvalNode(argument);
	assignmentNode.addChild(lvalNode);

	AstNode expresionNode = makeExpressionNode();
	assignmentNode.addChild(expresionNode);

	return assignmentNode;
    }

    private AstNode makeExpressionNode() {
	Token token = parser.getToken();

	AstNode node = getNodeByType();

	token = parser.getToken();
	while (!token.equals(Token.EndAssignVariable)) {
	    AstNode newNode = getNodeByType();
	    if (OperatorNode.isOperator(token)) {
		newNode.addChildFirst(node);
	    }
	    node = newNode;
	    token = parser.getToken();
	}

	return node;
    }

    private AstNode getNodeByType() {
	AstNode node = null;

	if (parser.isArgumentConstant()) {
	    node = new ConstantNode(parser.getConstant());
	} else if (parser.isArgumentVarriable()) {
	    node = new RvalNode(parser.getArgument());
	}

	if (OperatorNode.isOperator(parser.getLastToken())) {
	    AstNode operatorNode = new OperatorNode(parser.getLastToken());
	    operatorNode.addChild(node);
	    return operatorNode;
	}

	return node;
    }
    /* for types as ConstantNode or RvalNode or OperatorNode */

    private AstNode makeWhileNode() {
	AstNode whileNode = new WhileNode();
	String argument = parser.getArgument();

	AstNode conditionNode = new ConditionNode(argument);
	whileNode.addChild(conditionNode);

	AstNode bodyNode = new BodyNode();
	Token token = parser.getToken();

	while (!token.equals(Token.EndWhile)) {
	    AstNode node = getNode(token);
	    bodyNode.addChild(node);
	    token = parser.getToken();
	}

	whileNode.addChild(bodyNode);

	return whileNode;
    }

    private AstNode getNode(Token token) {
	switch (token) {
	case DeclareInt:
	    return makeDeclareNode();
	case Print:
	    return makePrintNode();
	case If:
	    return makeIfNode();
	case AssignVariable:
	    return makeAssignmentNode();
	case While:
	    return makeWhileNode();
	default:
	    throw new SyntaxException();// these are the only accepted commands
	}
    }
}
