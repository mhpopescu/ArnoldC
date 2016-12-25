package interpreter;

import nodes.*;
import nodes.leafs.*;

/**
 * 
 * @author Mihai Popescu
 *
 */
public interface Visitor {
    /**
     * 
     * @param rvalNode
     */
    void visit(RvalNode rvalNode);

    /**
     * 
     * @param printNode
     */
    void visit(PrintNode printNode);

    /**
     * 
     * @param main
     */
    void visit(MainNode main);

    /**
     * 
     * @param conditionNode
     */
    void visit(ConditionNode conditionNode);

    /**
     * 
     * @param operatorNode
     */
    void visit(OperatorNode operatorNode);

    /**
     * 
     * @param assignmentNode
     */
    void visit(AssignmentNode assignmentNode);

    /**
     * 
     * @param bodyNode
     */
    void visit(BodyNode bodyNode);

    /**
     * 
     * @param declareNode
     */
    void visit(DeclareNode declareNode);

    /**
     * 
     * @param constantNode
     */
    void visit(ConstantNode constantNode);

    /**
     * 
     * @param lvalNode
     */
    void visit(LvalNode lvalNode);

    /**
     * 
     * @param stringNode
     */
    void visit(StringNode stringNode);

    /**
     * 
     * @param elseBodyNode
     */
    void visit(ElseBodyNode elseBodyNode);

    /**
     * 
     * @param ifBodyNode
     */
    void visit(IfBodyNode ifBodyNode);

    /**
     * 
     * @param ifNode
     */
    void visit(IfNode ifNode);

    /**
     * 
     * @param whileNode
     */
    void visit(WhileNode whileNode);

    /**
     * 
     * @param variableNode
     */
    void visit(VariableNode variableNode);

}
