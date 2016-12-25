package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class Parser {
    private BufferedReader br;
    private String instruction;
    private String argument;
    private String stringToken;
    private Token token;

    private boolean containsVarriable;
    private boolean containsString;
    private boolean containsConstant;
    private int constant;
    private static final String regex = "\"|@";

    /**
     * 
     * @param inputFile
     * @throws FileNotFoundException
     */
    public Parser(String inputFile) throws FileNotFoundException {
	br = new BufferedReader(new FileReader(inputFile));
    }

    /**
     * 
     * @return Token - the next recognised Token from the file
     */
    public Token getToken() {
	argument = null;

	try {
	    instruction = br.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	parseCommand();

	for (Token recognised : Token.values()) {
	    if (stringToken.startsWith(recognised.keyword())) {
		token = recognised;
		return recognised;
	    }
	}

	return getToken();
    }

    /**
     * 
     * @return Token - the current token
     */
    public Token getLastToken() {
	return token;
    }

    /**
     * 
     * @return the command argument as a String
     */
    public String getArgument() {
	return argument;
    }

    /**
     * 
     * @return true if the argument is a varriable
     */
    public boolean isArgumentVarriable() {
	return containsVarriable;
    }

    /**
     * 
     * @return true if the argument is a constant String
     */
    public boolean isArgumentString() {
	return containsString;
    }

    /**
     * 
     * @return true if the argument is a constant Integer
     */
    public boolean isArgumentConstant() {
	return containsConstant;
    }

    /**
     * 
     * @return the constant integer if any exists in the command
     */
    public int getConstant() {
	return constant;
    }

    private void parseCommand() {
	if (instruction == null) {
	    return;
	}
	containsString = instruction.contains("\"");
	containsConstant = instruction.contains("@");

	String[] args = instruction.split(regex);

	stringToken = args[0];
	stringToken = stringToken.trim().replaceAll(" +", " ");

	if (stringToken.contains(" ")) {
	    argument = stringToken.substring(stringToken.lastIndexOf(" ") + 1);
	    containsVarriable = true;
	}

	if (containsString) {
	    argument = args[1];
	    containsVarriable = false;
	} else if (containsConstant) {
	    constant = args[1].equals(Token.True.keyword()) ? 1 : 0;
	} else if (isInt(argument)) {
	    containsConstant = true;
	    constant = Integer.parseInt(argument);
	}
    }

    static private boolean isInt(String s) {
	try {
	    Integer.parseInt(s);
	    return true;
	} catch (NumberFormatException er) {
	    return false;
	}
    }
}
