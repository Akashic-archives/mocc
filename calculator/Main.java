/*
 *
 * A calculator that takes in stdin math and calculates it.
 * 
 * priority of operation
 * try to evaluate if an expression is true or false.
 * multi-line expressions soon possible because of variables and semicolon.
 *
 * PS: i will improve the memory, for now i abuse the fact that i have 16 Gb of ram
 *
 */

package calculator;

import java.util.Scanner;

public class Main{
	public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    Token[] tokens = new Token[1];
    System.out.println("If you want to exit, enter \"exit\".");
    while (true) {
      String line = sc.nextLine();
      if (line.equals("exit")) {
        break;
      }
      tokens = lexer(line);
      //interpreter(tokens);
      Node ast = parser(tokens);
      //interpreterForAst(ast);
    }
  }



  public static Node parser(Token[] tokens) {
    /*
     * TODO: the parser needs to return an ast with priority of operation first.
     * also the parenthesis later.
     *
     * TODO: the interpreter needs to be rewritten to accomodate an ast.
     *
     */
    Node ast = new Node(tokens[1]);
    ast.addLeft(tokens[0]);
    ast.addRight([2]);


    for (int i = 3; i < tokens.length; i++) {
      if () {
        
      }
    }

    /* if higherPriority, put above
     * if lowerPriority, put below
     * i++
     *
     * for the interpreter, top to bottom, so 5+2*4-3 is
     * *
     * |\
     * 4 +
     *  / \
     * 5   2
     *    / \
     *   3   -
     * 
     *
     */


    return ast;
  }


  public static void interpreter(Token[] tokens) {
    float result = tokens[0].getValue();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].getType() == Token.TokenType.PLUS) {
        result = result + tokens[i+1].getValue();
        i++;
      } else if (tokens[i].getType() == Token.TokenType.MINUS) {
        result = result - tokens[i+1].getValue();
        i++;
      } else if (tokens[i].getType() == Token.TokenType.MULTIPLY) {
        result = result * tokens[i+1].getValue();
        i++;
      } else if (tokens[i].getType() == Token.TokenType.DIVIDE) {
        result = result / tokens[i+1].getValue();
        i++;
      }
    }
    System.out.println(result);
  }

  public static int findMachingRightParenthesis(Token[] tokens, int programCounter) {
    int parenthesisOpen = 0;
    for (int i = programCounter + 1; i < tokens.length; i++) {
      if (tokens[i].getType() == Token.TokenType.RIGHT_PARENTHESIS && parenthesisOpen == 0) {
        return i;
      }
      else if (tokens[i].getType() == Token.TokenType.LEFT_PARENTHESIS) {
        parenthesisOpen++;
      }
      else if (tokens[i].getType() == Token.TokenType.RIGHT_PARENTHESIS && parenthesisOpen != 0) { // != 0 for bug
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
      if (tokens[i].getType() == Token.TokenType.LEFT_PARENTHESIS && parenthesisOpen == 0) {
        return i;
      }
      else if (tokens[i].getType() == Token.TokenType.RIGHT_PARENTHESIS) {
        parenthesisOpen++;
      }
      else if (tokens[i].getType() == Token.TokenType.LEFT_PARENTHESIS && parenthesisOpen != 0) {
        parenthesisOpen--;
      }
    }
    System.err.println("Error in the findMachingRightParenthesis programCounter was at " + programCounter);
    System.exit(1);
    return programCounter;
  }



	// TOKEN/LEXER SECTION

  public static Token[] lexer(String lex) {
    // int nbreTokens = countTokens(lex);
    Token[] tokens = new Token[1000]; // [nbreTokens];
    int tokenCounter = 0;
    for (int i = 0; i < lex.length(); i++) {
      // TODO: find token and put it in tokens
      if (lex.charAt(i) == '+') {
        tokens[tokenCounter] = new Token(Token.TokenType.PLUS, 0);
        tokenCounter++;
      } else if (lex.charAt(i) == '-') {
        tokens[tokenCounter] = new Token(Token.TokenType.MINUS, 0);
        tokenCounter++;
      } else if (Character.isDigit(lex.charAt(i))) {
        int longeur = 1;
        while (i + longeur < lex.length() && Character.isDigit(lex.charAt(i+longeur))) {
          longeur++;
        }
        tokens[tokenCounter] = new Token(Token.TokenType.NUMBER, Float.parseFloat("" + lex.substring(i, i + longeur)));
        tokenCounter++;
        i = i + longeur - 1;
      } else if (lex.charAt(i) == '*') {
        tokens[tokenCounter] = new Token(Token.TokenType.MULTIPLY, 0);
        tokenCounter++;
      } else if (lex.charAt(i) == '/') {
        tokens[tokenCounter] = new Token(Token.TokenType.DIVIDE, 0);
        tokenCounter++;
      }
    }
    Token[] tokensLength = new Token[tokenCounter]; // TODO: remove this and make the array dynamic
    for (int i = 0; i < tokenCounter; i++) {
      tokensLength[i] = tokens[i];
    }
    return tokensLength;
  }

  public static Token.TokenType tokeniseChar(char ch) {
    if (ch == '-') {
      return Token.TokenType.MINUS;
    }  
    else {
        System.err.println("Error with the tokens, probably bad sanitization");
        System.exit(1);
      }
      return Token.TokenType.MINUS;
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

