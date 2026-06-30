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
      Node main = new Node(tokens.get(0));
      ArrayList<Node> functions = new ArrayList<>();
      /* ast main() always called first
       * ArrayList<ast> fuctions plugged in the main and themselves
       */
      // function = return name args firstToken lastToken
      // each line is a head and operations, and ill just link them in the function

      for (int i = 0; i < tokens.size(); i++) {
        if (!isKeyword(tokens.get(i)) && tokens.get(i).getTokenType() == Token.TokenType.NAME) {
          String functionName = tokens.get(i).getValue();
          int firstLeftBracket = findFirstLeftBracket(tokens, i);
          int matchingRightBracket = findMatchingRightBracket(tokens, firstLeftBracket);
          Node function = new FunctionNode(tokens.get(i), functionName, firstLeftBracket, matchingRightBracket);
          functions.add(function);
          i = matchingRightBracket + 1;
          System.out.println(i);
          System.out.println(matchingRightBracket);
          System.out.println(isKeyword(tokens.get(i)));
        }
      }

      for (int i = 0; i < functions.size(); i++) {
        if (functions.get(i).getToken().getValue().equals("main")) {
          main = functions.get(i);
        }
      }


      for (int i = 0; i < tokens.size(); i++) { //TODO: for all functions this to get return
        if (tokens.get(i).getValue().equals("return")) {
          main.setNextNode(new Node(tokens.get(i))); // TODO: new "main" for this function
          Node currentNode = main.getNextNode();
          while (currentNode.getToken().getTokenType() != Token.TokenType.SEMICOLON) {
            currentNode.setNextNode(new Node(tokens.get(i+1)));
            currentNode = currentNode.getNextNode();
            i += 1;
          }
          i = tokens.size();
        }
      }

      /*
       * arraylist Nodes divideToLignes(start end)
       * findReturnLigne
       * head.addNextNode return
       * return.addStatement all tokens between return and ;
       */

      printAST(main);

      return main;
    }

    public static void printAST(Node head) {
      System.out.println("AST:");
      while (head.getNextNode() != null) {
        System.out.println(head.getToken().getTokenType());
        head = head.getNextNode();
      }
    }

    public static int findFirstLeftBracket(ArrayList<Token> tokens, int namePosition) {
      for (int i = namePosition + 1; i < tokens.size(); i++) {
        if (tokens.get(i).getTokenType() == Token.TokenType.LEFT_PARENTHESIS) {
          return i;
        }
      }
      return 0;
    }

    public static int findMatchingRightBracket(ArrayList<Token> tokens, int leftBracketPosition) {
    int bracketsOpen = 0;
    int rightBracketPosition = 0;
    for (int i = leftBracketPosition + 1; i < tokens.size(); i++) {
      if (tokens.get(i).getTokenType() == Token.TokenType.RIGHT_BRACKET && bracketsOpen == 0) {
        return i;
      }
      else if (tokens.get(i).getTokenType() == Token.TokenType.LEFT_BRACKET) {
        bracketsOpen++;
      }
      else if (tokens.get(i).getTokenType() == Token.TokenType.RIGHT_BRACKET && bracketsOpen != 0) {
        bracketsOpen--;
      }
    }
    return rightBracketPosition;
  }


    public static boolean isKeyword(Token token) {
      String keyword = token.getValue();
      return keyword.equals("auto") || keyword.equals("break") || keyword.equals("case") ||
        keyword.equals("char") || keyword.equals("const") || keyword.equals("continue") ||
        keyword.equals("default") || keyword.equals("do") || keyword.equals("double") ||
        keyword.equals("else") || keyword.equals("enum") || keyword.equals("extern") ||
        keyword.equals("float") || keyword.equals("for") || keyword.equals("goto") ||
        keyword.equals("if") || keyword.equals("int") || keyword.equals("long") ||
        keyword.equals("register") || keyword.equals("return") || keyword.equals("short") ||
        keyword.equals("signed") || keyword.equals("sizeof") || keyword.equals("static") ||
        keyword.equals("struct") || keyword.equals("switch") || keyword.equals("typedef") ||
        keyword.equals("union") || keyword.equals("unsigned") || keyword.equals("void") ||
        keyword.equals("volatile") || keyword.equals("while");
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

