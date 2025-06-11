/*
 *
 * A calculator that takes in stdin math and calculates it.
 *
 * try to evaluate if an expression is true or false.
 * multi-line expressions soon possible because of variables and semicolon.
 *
 */

import java.util.Scanner;

public class Main{
	public static void main(String[] args){
    System.out.println("If you want to exit, enter \"exit\".");
    while (1) {
      String line = sc.nextLine();
      if (line.equals("exit")) {
        break;
      }
    }
	  String lex = readFile(args);
    Token[] tokens = lexer(lex);
    //ast = parser(lex);
    interpreter(tokens);
    System.out.println();
  }





  public static void interpreter(Token[] tokens) {
  }

  public static int findMachingRightParenthesis(Token[] tokens, int programCounter) {
    int bracketsOpen = 0;
    for (int i = programCounter + 1; i < tokens.length; i++) {
      if (tokens[i] == Token.RIGHT_BRACKET && bracketsOpen == 0) {
        return i;
      }
      else if (tokens[i] == Token.LEFT_BRACKET) {
        bracketsOpen++;
      }
      else if (tokens[i] == Token.RIGHT_BRACKET && bracketsOpen != 0) { // != 0 for bug
        bracketsOpen--;
      }
    }
    System.err.println("Error in the findMachingRightBracket programCounter was at " + programCounter);
    System.exit(1);
    return programCounter;
  }

  public static int findMachingLeftParenthesis(Token[] tokens, int programCounter) {
    int bracketsOpen = -1;
    for (int i = programCounter; i < tokens.length - 1; i--) {
      if (tokens[i] == Token.LEFT_BRACKET && bracketsOpen == 0) {
        return i;
      }
      else if (tokens[i] == Token.RIGHT_BRACKET) {
        bracketsOpen++;
      }
      else if (tokens[i] == Token.LEFT_BRACKET && bracketsOpen != 0) {
        bracketsOpen--;
      }
    }
    System.err.println("Error in the findMachingRightBracket programCounter was at " + programCounter);
    System.exit(1);
    return programCounter;
  }



  public static int inputVerification(String[] args) {
    if (args.length != 2) {
      System.out.println("Please invoke the program with the file and 1 for compile or 2 for interpreter.");
      System.exit(1);
    }
    if (args[1].equals("1"))
      return 1;
    return 2;
  }

	// TOKEN/LEXER SECTION
	enum Token {
    NUMBER,
    PLUS,
    MINUS,
    TIME,
    DIVIDE,
    POINT,
    COMMA
	}

  public static Token[] lexer(String lex) {
    int nbreTokens = countTokens(lex);
    Token[] tokens = new Token[nbreTokens];
    int tokenCounter = 0;
    for (int i = 0; i < lex.length(); i++) {
      // TODO: find token and put it in tokens
      if (isValidToken(lex.charAt(i))) {
        tokens[tokenCounter] = tokeniseChar(lex.charAt(i));
        tokenCounter++;
      }
    }
    return tokens;
  }

  public static Token tokeniseChar(char ch) {
    if (ch == '<') {
      return TOKEN.lessThan;
    }  
    else {
        System.err.println("Error with the tokens, probably bad sanitization");
        System.exit(1);
      }
      return Token.MINUS;
  }

  public static int countTokens(String lex) {
    int counter = 0;
    for (int i = 0; i < lex.length(); i++) {
      if (isValidToken(lex.charAt(i)))
        counter++;
    }
    System.out.println(counter + " Tokens");
    return counter;
  }

}

