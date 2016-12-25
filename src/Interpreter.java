import java.io.FileNotFoundException;
import java.io.IOException;

import interpreter.AstBuilder;
import interpreter.InterpreterVisitor;
import interpreter.PrintVisitor;
import nodes.AstNode;
import utils.OutputWriter;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class Interpreter {
    private AstNode root;

    /**
     * 
     * @param inputFileName
     */
    public Interpreter(String inputFileName) {
	AstBuilder builder;
	try {
	    builder = new AstBuilder(inputFileName);
	    root = builder.buildAst();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 
     * @param outputFileName
     */
    public void printAst(String outputFileName) {
	OutputWriter out;
	try {
	    out = new OutputWriter(outputFileName);
	    PrintVisitor printer = new PrintVisitor(out);
	    root.accept(printer);
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 
     * @param outputFileName
     */
    public void interpretAst(String outputFileName) {
	OutputWriter out;
	try {
	    out = new OutputWriter(outputFileName);
	    InterpreterVisitor interpreter = new InterpreterVisitor(out);
	    root.accept(interpreter);
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 
     * @param args
     *            args[0] - file with commands; args[1] output file name to
     *            print Ast; args[2] output file name to print the result of the
     *            interpreted Ast
     */
    public static void main(String[] args) {
	Interpreter interpreter = new Interpreter(args[0]);
	interpreter.printAst(args[1]);
	interpreter.interpretAst(args[2]);
    }
}
