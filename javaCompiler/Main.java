/*
 * Error handling for return if there is no valid return arg
 *
 */

package javaCompiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
      inputVerification(args);
      ArrayList<Character> lex = readFile(args);
      ArrayList<Token> tokens = lexer(lex);
      Node ast = parser(tokens);
      //interpreter() if i feel like it
      //codeGen(ast);
      for (Token token : tokens) {
        if (token.getTokenType() == Token.TokenType.NAME || token.getTokenType() == Token.TokenType.NUMBER) {
          System.out.println(token.getValue());
        } else {
          System.out.println(token.getTokenType());
        }
      }
    }


    public static void inputVerification(String[] args) {
      if (args.length != 1) {
        System.out.println("Please invoke the program with the file to compile.");
        System.exit(1);
      }
    }


    // TOKEN AND LEXER SECTION
    
    public static ArrayList<Token> lexer(ArrayList<Character> lex) {
    ArrayList<Token> tokens = new ArrayList<>();
    boolean end = (lex.size() == 0);

    do {
      if (isBaseToken(lex.get(0).charValue())) {
        tokens.add(new Token(tokeniseChar(lex.get(0).charValue())));
        lex.remove(0);
      } else if (isAlpha(lex.get(0).charValue())) {
        String s = "";
        do {
          s = s + "" + lex.get(0).charValue();
          lex.remove(0);
        } while (isAlpha(lex.get(0).charValue()));
        tokens.add(new Token(Token.TokenType.NAME, s));
      } else if (isAlphaNum(lex.get(0).charValue())) {
        String s = "";
        do {
          s = s + lex.get(0).charValue();
          lex.remove(0);
        } while (isAlpha(lex.get(0).charValue()));
        tokens.add(new Token(Token.TokenType.NUMBER, s));
      } else {
        lex.remove(0);
      }
      end = lex.size() == 0;
    } while (!end);
    
    return tokens;
  }


    public static Token.TokenType tokeniseChar(char ch) {
      if (ch == ';') {
        return Token.TokenType.SEMICOLON;
      } else if (ch == '{') {
        return Token.TokenType.LEFT_BRACKET;
      } else if (ch == '}') {
        return Token.TokenType.RIGHT_BRACKET;
      } else if (ch == '(') {
        return Token.TokenType.LEFT_PARENTHESIS;
      } else if (ch == ')') {
        return Token.TokenType.RIGHT_PARENTHESIS;
      }
      return Token.TokenType.SEMICOLON;
    }

    public static boolean isBaseToken(char ch) {
      return (ch == ';' || ch == '{' || ch == '}' || ch == '(' || ch == ')');
    }

    public static boolean isAlpha(char ch) {
      return (64 < ch && ch < 91 || 96 < ch && ch < 123);
    }

    public static boolean isAlphaNum(char ch) {
      return (47 < ch && ch < 58 || 64 < ch && ch < 91 || 96 < ch && ch < 123);
    }


    // AST

    public static Node parser(ArrayList<Token> tokens) {
      Node head;



      return head;
    }

    // FILE HANDLING SECTION
    public static ArrayList<Character> readFile(String[] args){
      String lex = "";
      ArrayList<Character> lexArrayList = new ArrayList<>();
      try {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
          lex = lex + sc.nextLine();
        }
        sc.close();
      } catch (FileNotFoundException e) {
        System.out.println("Wrong file or command written badly.");
        e.printStackTrace();
      }
      for (char c : lex.toCharArray()) {
        lexArrayList.add(c);
      }
      return lexArrayList;
    }
}

