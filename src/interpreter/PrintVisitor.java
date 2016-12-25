package interpreter;

import java.util.List;

import nodes.*;
import nodes.leafs.*;
import utils.OutputWriter;

/**
 * Arnoldc Printer to print the ArnoldC Ast nodes
 * 
 * @author Mihai Popescu
 *
 */
public class PrintVisitor implements Visitor {
    private String identation;
    private OutputWriter out;
    static private final String tab = "\t";

    /**
     * 
     * @param out
     */
    public PrintVisitor(OutputWriter out) {
	this.out = out;
	identation = "";
    }

    /**
     * Print the Ast starting with root
     */
    @Override
    public void visit(MainNode main) {
	out.println(getNodeName(main));
	printAllSons(main);
    }

    /**
     * Print a single PrintNode
     */
    @Override
    public void visit(PrintNode printNode) {
	out.println(identation + getNodeName(printNode));
	printAllSons(printNode);
    }

    /**
     * Print a single LvalNode
     */
    @Override
    public void visit(LvalNode lvalNode) {
	out.println(identation + getNodeName(lvalNode) + " " + lvalNode);
    }

    /**
     * Print a single RvalNode
     */
    @Override
    public void visit(RvalNode rvalNode) {
	out.println(identation + getNodeName(rvalNode) + " " + rvalNode);
    }

    /**
     * Print a single StringNode
     */
    @Override
    public void visit(StringNode stringNode) {
	out.println(identation + getNodeName(stringNode) + " " + stringNode);
    }

    /**
     * Print a single VariableNode
     */
    @Override
    public void visit(VariableNode variableNode) {
	out.println(
		identation + getNodeName(variableNode) + " " + variableNode);
    }

    /**
     * Print a single ConditionNode
     */
    @Override
    public void visit(ConditionNode conditionNode) {
	out.println(
		identation + getNodeName(conditionNode) + " " + conditionNode);
    }

    /**
     * Print a single ConstantNode
     */
    @Override
    public void visit(ConstantNode constantNode) {
	out.println(
		identation + getNodeName(constantNode) + " " + constantNode);
    }

    /**
     * Print a single DeclareNode
     */
    @Override
    public void visit(DeclareNode declareNode) {
	out.println(identation + getNodeName(declareNode));
	printAllSons(declareNode);
    }

    /**
     * Print recursively an OperatorNode
     */
    @Override
    public void visit(OperatorNode operatorNode) {
	out.println(identation + operatorNode.getNodeName());
	printAllSons(operatorNode);
    }

    /**
     * Print an AssignmentNode with the whole expression tree
     */
    @Override
    public void visit(AssignmentNode assignmentNode) {
	out.println(identation + getNodeName(assignmentNode));
	printAllSons(assignmentNode);
    }

    /**
     * Print the entire IfNode
     */
    @Override
    public void visit(IfNode ifNode) {
	out.println(identation + getNodeName(ifNode));
	printAllSons(ifNode);
    }

    /**
     * Print only the if's body
     */
    @Override
    public void visit(IfBodyNode ifBodyNode) {
	out.println(identation + getNodeName(ifBodyNode));
	printAllSons(ifBodyNode);
    }

    /**
     * Print only the if's else body
     */
    @Override
    public void visit(ElseBodyNode elseBodyNode) {
	out.println(identation + getNodeName(elseBodyNode));
	printAllSons(elseBodyNode);
    }

    /**
     * Print the entire WhileNode
     */
    @Override
    public void visit(WhileNode whileNode) {
	out.println(identation + getNodeName(whileNode));
	printAllSons(whileNode);
    }

    /**
     * Print only the while's body
     */
    @Override
    public void visit(BodyNode bodyNode) {
	out.println(identation + getNodeName(bodyNode));
	printAllSons(bodyNode);
    }

    private void printAllSons(AstNode node) {
	List<AstNode> sons = node.getSons();
	String lastIdentation = identation;
	for (AstNode child : sons) {
	    identation += tab;
	    child.accept(this);
	    identation = lastIdentation;
	}
    }
    /* Print with identation all the sons of a Node */

    private String getNodeName(AstNode node) {
	String name = node.getClass().getName();
	name = name.substring(name.lastIndexOf(".") + 1);
	return name;
    }
}
