package interpreter;

import java.util.HashMap;
import java.util.List;

import nodes.*;
import nodes.leafs.*;
import utils.OutputWriter;

/**
 * ArnoldC interpreter for an ArnoldC Ast
 * 
 * @author Mihai Popescu
 *
 */
public class InterpreterVisitor implements Visitor {
    /*
     * non-constant variables are saved in the hashMap, their values are updated
     * only here
     */
    private HashMap<String, Integer> varriables;
    private OutputWriter out;

    /* leafs visit methods functionality are as setters for these */
    private int currVarriableValue;
    private String currVarriableName;
    private String currStringData;
    private boolean printString = false;
    private boolean condition;

    /**
     * 
     * @param out
     */
    public InterpreterVisitor(OutputWriter out) {
	this.out = out;
	varriables = new HashMap<String, Integer>();
    }

    /**
     * Starts the interpretation
     */
    @Override
    public void visit(MainNode main) {
	visitAllSons(main);
    }

    /**
     * Prints the printNode
     */
    @Override
    public void visit(PrintNode printNode) {
	printString = false;
	visitAllSons(printNode);
	if (printString == true) {
	    out.println(currStringData);
	} else {
	    out.println(varriables.get(currVarriableName).toString());
	}
    }

    /**
     * Updates the current varriable
     */
    @Override
    public void visit(RvalNode rvalNode) {
	currVarriableName = rvalNode.getValue();
	currVarriableValue = varriables.get(currVarriableName);
    }

    /**
     * Visits all the sons of the node
     */
    @Override
    public void visit(BodyNode bodyNode) {
	visitAllSons(bodyNode);
    }

    /**
     * Assign a new varriable in the HashMap
     */
    @Override
    public void visit(DeclareNode declareNode) {
	declareNode.getLeftSide().accept(this);
	String variableName = currVarriableName;
	declareNode.getRightSide().accept(this);
	varriables.put(variableName, currVarriableValue);
    }

    /**
     * Updates the current varriable
     */
    @Override
    public void visit(ConstantNode constantNode) {
	currVarriableValue = constantNode.getValue();

    }

    /**
     * Updates the current varriable
     */
    @Override
    public void visit(LvalNode lvalNode) {
	currVarriableName = lvalNode.getValue();
    }

    /**
     * Updates the string constant to print
     */
    @Override
    public void visit(StringNode stringNode) {
	currStringData = stringNode.getValue();
	printString = true;
    }

    /**
     * Visits all the sons of the node
     */
    @Override
    public void visit(ElseBodyNode elseBodyNode) {
	visitAllSons(elseBodyNode);
    }

    /**
     * Visits all the sons of the node
     */
    @Override
    public void visit(IfBodyNode ifBodyNode) {
	visitAllSons(ifBodyNode);
    }

    /**
     * Updates the condition and set is value with true/false
     */
    @Override
    public void visit(ConditionNode conditionNode) {
	String nodeName = conditionNode.getValue();
	int value = varriables.get(nodeName);
	if (value != 0) {
	    condition = true;
	} else {
	    condition = false;
	}
    }

    /**
     * Interprets the if node's body when condition is true, or else body when
     * exists
     */
    @Override
    public void visit(IfNode ifNode) {
	AstNode conditionNode = ifNode.getCondition();
	conditionNode.accept(this);
	if (condition == true) {
	    ifNode.getIfBodyNode().accept(this);
	} else if (ifNode.containsElseBody()) {
	    ifNode.getElseBodyNode().accept(this);
	}
    }

    /**
     * Loop to interprets while's body as long as condition is true
     */
    @Override
    public void visit(WhileNode whileNode) {
	AstNode conditionNode = whileNode.getCondition();
	conditionNode.accept(this);
	while (condition == true) {
	    whileNode.getBody().accept(this);
	    conditionNode.accept(this); // update condition
	}
    }

    /**
     * Updates the current varriable
     */
    @Override
    public void visit(VariableNode variableNode) {
	currVarriableName = variableNode.getValue();
    }

    /**
     * Interpret the expression, assign to the variable and updates it's value
     * in the HashMap
     */
    @Override
    public void visit(AssignmentNode assignmentNode) {
	assignmentNode.getLeftSide().accept(this);
	String variableName = currVarriableName;
	assignmentNode.getRightSide().accept(this);
	varriables.put(variableName, currVarriableValue);
    }

    /**
     * Executes the operations for an expression
     */
    @Override
    public void visit(OperatorNode operatorNode) {
	int firstOperand;

	operatorNode.getFirstOperand().accept(this);
	firstOperand = currVarriableValue;
	operatorNode.getSecondOperand().accept(this);

	switch (operatorNode.getType()) {
	case And:
	    if (firstOperand != 0 && currVarriableValue != 0) {
		currVarriableValue = 1;
	    } else {
		currVarriableValue = 0;
	    }
	    break;
	case Or:
	    if (firstOperand != 0 || currVarriableValue != 0) {
		currVarriableValue = 1;
	    } else {
		currVarriableValue = 0;
	    }
	    break;
	case EqualTo:
	    if (firstOperand == currVarriableValue) {
		currVarriableValue = 1;
	    } else {
		currVarriableValue = 0;
	    }
	    break;
	case GreaterThan:
	    if (firstOperand > currVarriableValue) {
		currVarriableValue = 1;
	    } else {
		currVarriableValue = 0;
	    }
	    break;
	case DivisionOperator:
	    currVarriableValue = firstOperand / currVarriableValue;
	    break;
	case MinusOperator:
	    currVarriableValue = firstOperand - currVarriableValue;
	    break;
	case ModuloOperator:
	    currVarriableValue = firstOperand % currVarriableValue;
	    break;
	case MultiplicationOperator:
	    currVarriableValue = firstOperand * currVarriableValue;
	    break;
	case PlusOperator:
	    currVarriableValue = firstOperand + currVarriableValue;
	    break;
	default:
	    break;
	}
    }

    private void visitAllSons(AstNode node) {
	List<AstNode> sons = node.getSons();
	for (AstNode child : sons) {
	    child.accept(this);
	}
    }

}
