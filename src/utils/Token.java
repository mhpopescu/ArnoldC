package utils;

/**
 * 
 * @author Mihai Popescu
 *
 */
public enum Token {
    False("I LIED"), 
    True("NO PROBLEMO"),
    If("BECAUSE I'M GOING TO SAY PLEASE"), 
    Else("BULLSHIT"), 
    Endif("YOU HAVE NO RESPECT FOR LOGIC"), 
    While("STICK AROUND"), 
    EndWhile("CHILL"),
    PlusOperator("GET UP"), 
    MinusOperator("GET DOWN"),
    MultiplicationOperator("YOU'RE FIRED"),
    DivisionOperator("HE HAD TO SPLIT"), 
    ModuloOperator("I LET HIM GO"),
    EqualTo("YOU ARE NOT YOU YOU ARE ME"), 
    GreaterThan("LET OFF SOME STEAM BENNET"),
    Or("CONSIDER THAT A DIVORCE"), 
    And("KNOCK KNOCK"),
    DeclareInt("HEY CHRISTMAS TREE"), 
    SetInitialValue("YOU SET US UP"),
    BeginMain("IT'S SHOWTIME"), 
    EndMain("YOU HAVE BEEN TERMINATED"),
    Print("TALK TO THE HAND"), 
    AssignVariable("GET TO THE CHOPPER"),
    SetValue("HERE IS MY INVITATION"), 
    EndAssignVariable("ENOUGH TALK");

    private final String keyword;

    private Token(String keyword) {
	this.keyword = keyword;
    }

    String keyword() {
	return keyword;
    }
}
