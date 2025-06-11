/*
 *
 * A calculator that takes in stdin math and calculates it.
 *
 * try to evaluate if an expression is true or false.
 * multi-line expressions soon possible because of variables and semicolon.
 *
 */

package mocc.java;

import java.util.Scanner;

public class Main{
	public static void main(String[] args){
    System.out.println("If you want to exit, enter \"exit\".");
    while (1) {
      String line = sc.nextLine();
      if (line.equals("exit")) {
        break;
      }
      Token[] tokens = lexer(line);
    }
    //ast = parser(lex);
    interpreter(tokens);
    System.out.println();
  }





  public static void interpreter(Token[] tokens) {
  }

  public static int findMachingRightParenthesis(Token[] tokens, int programCounter) {
    int parenthesisOpen = 0;
    for (int i = programCounter + 1; i < tokens.length; i++) {
      if (tokens[i] == TokenType.RIGHT_PARENTHESIS && parenthesisOpen == 0) {
        return i;
      }
      else if (tokens[i] == TokenType.LEFT_PARENTHESIS) {
        parenthesisOpen++;
      }
      else if (tokens[i] == TokenType.RIGHT_PARENTHESIS && parenthesisOpen != 0) { // != 0 for bug
        parenthesisOpen--;
      }
    }
    System.err.println("Error in the findMachingRightParenthesis programCounter was at " + programCounter);
    System.exit(1);
    return programCounter;
  }

  public static int findMachingLeftParenthesis(Token[] tokens, int programCounter) {
    int parenthesisOpen = -1;
    for (int i = programCounter; i < tokens.length - 1; i--) {
      if (tokens[i] == TokenType.LEFT_PARENTHESIS && parenthesisOpen == 0) {
        return i;
      }
      else if (tokens[i] == TokenType.RIGHT_PARENTHESIS) {
        parenthesisOpen++;
      }
      else if (tokens[i] == TokenType.LEFT_PARENTHESIS && parenthesisOpen != 0) {
        parenthesisOpen--;
      }
    }
    System.err.println("Error in the findMachingRightParenthesis programCounter was at " + programCounter);
    System.exit(1);
    return programCounter;
  }



	// TOKEN/LEXER SECTION
	enum TokenType {
    NUMBER,
    PLUS,
    MINUS,
    TIME,
    DIVIDE,
    POINT,
    COMMA,
    RIGHT_PARENTHESIS,
    LEFT_PARENTHESIS,
    EQUALS
	}

  public static Token[] lexer(String lex) {
    // int nbreTokens = countTokens(lex);
    Token[] tokens = new Token[1000]; // [nbreTokens];
    int tokenCounter = 0;
    for (int i = 0; i < lex.length(); i++) {
      // TODO: find token and put it in tokens
      if (lex.charAt(i) == '+') {
        tokens[tokenCounter] = new Token(TokenType.PLUS, 0);
        tokenCounter++;
      } else if (lex.charAt(i) == '-') {
        tokens[tokenCounter] = new Token(TokenType.MINUS, 0);
        tokenCounter++;
      } else if (Character.isDigit(lex.charAt(i))) {
        System.out.println("matched a digit");
      }
    }
    return tokens;
  }

  public static Token tokeniseChar(char ch) {
    if (ch == '-') {
      return TokenType.MINUS;
    }  
    else {
        System.err.println("Error with the tokens, probably bad sanitization");
        System.exit(1);
      }
      return TokenType.MINUS;
  }

  public static int countTokens(String lex) {
    int counter = 0;
    for (int i = 0; i < lex.length(); i++) {
      //if (isValidToken(lex.charAt(i)))
        //counter++;
    }
    System.out.println(counter + " Tokens");
    return counter;
  }

}

