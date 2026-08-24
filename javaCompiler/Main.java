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
        if (isKeywordToken(s)) {
          tokens.add(new Token(tokeniseKeyword(s)));
        } else {
          tokens.add(new Token(Token.TokenType.NAME, s)); // TODO: revisite
        }
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


  public static Token.TokenType tokeniseKeyword(String s) {
    if (s.equals("auto")) {
      return Token.TokenType.AUTO;
    } else if (s.equals("break")) {
      return Token.TokenType.BREAK;
    } else if (s.equals("case")) {
      return Token.TokenType.CASE;
    } else if (s.equals("char")) {
      return Token.TokenType.CHAR;
    } else if (s.equals("const")) {
      return Token.TokenType.CONST;
    } else if (s.equals("continue")) {
      return Token.TokenType.CONTINUE;
    } else if (s.equals("default")) {
      return Token.TokenType.DEFAULT;
    } else if (s.equals("do")) {
      return Token.TokenType.DO;
    } else if (s.equals("double")) {
      return Token.TokenType.DOUBLE;
    } else if (s.equals("else")) {
      return Token.TokenType.ELSE;
    } else if (s.equals("enum")) {
      return Token.TokenType.ENUM;
    } else if (s.equals("extern")) {
      return Token.TokenType.EXTERN;
    } else if (s.equals("float")) {
      return Token.TokenType.FLOAT;
    } else if (s.equals("for")) {
      return Token.TokenType.FOR;
    } else if (s.equals("goto")) {
      return Token.TokenType.GOTO;
    } else if (s.equals("if")) {
      return Token.TokenType.IF;
    } else if (s.equals("int")) {
      return Token.TokenType.INT;
    } else if (s.equals("long")) {
      return Token.TokenType.LONG;
    } else if (s.equals("register")) {
      return Token.TokenType.REGISTER;
    } else if (s.equals("return")) {
      return Token.TokenType.RETURN;
    } else if (s.equals("short")) {
      return Token.TokenType.SHORT;
    } else if (s.equals("signed")) {
      return Token.TokenType.SIGNED;
    } else if (s.equals("sizeof")) {
      return Token.TokenType.SIZEOF;
    } else if (s.equals("static")) {
      return Token.TokenType.STATIC;
    } else if (s.equals("struct")) {
      return Token.TokenType.STRUCT;
    } else if (s.equals("switch")) {
      return Token.TokenType.SWITCH;
    } else if (s.equals("typedef")) {
      return Token.TokenType.TYPEDEF;
    } else if (s.equals("union")) {
      return Token.TokenType.UNION;
    } else if (s.equals("unsigned")) {
      return Token.TokenType.UNSIGNED;
    } else if (s.equals("void")) {
      return Token.TokenType.VOID;
    } else if (s.equals("volatile")) {
      return Token.TokenType.VOLATILE;
    } else if (s.equals("while")) {
      return Token.TokenType.WHILE;
    } else {
      return Token.TokenType.INT;
    }
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

    public static boolean isKeywordToken(String s) {
      return s.equals("auto") || s.equals("break") || s.equals("case") ||
        s.equals("char") || s.equals("const") || s.equals("continue") ||
        s.equals("default") || s.equals("do") || s.equals("double") ||
        s.equals("else") || s.equals("enum") || s.equals("extern") ||
        s.equals("float") || s.equals("for") || s.equals("goto") ||
        s.equals("if") || s.equals("int") || s.equals("long") ||
        s.equals("register") || s.equals("return") || s.equals("short") ||
        s.equals("signed") || s.equals("sizeof") || s.equals("static") ||
        s.equals("struct") || s.equals("switch") || s.equals("typedef") ||
        s.equals("union") || s.equals("unsigned") || s.equals("void") ||
        s.equals("volatile") || s.equals("while");
    }

    public static boolean isAlpha(char ch) {
      return (64 < ch && ch < 91 || 96 < ch && ch < 123);
    }

    public static boolean isAlphaNum(char ch) {
      return (47 < ch && ch < 58 || 64 < ch && ch < 91 || 96 < ch && ch < 123);
    }


    // AST

    public static Node parser(ArrayList<Token> tokens) {
      ArrayList<FunctionNode> functions = new ArrayList<>();
      FunctionNode main = new FunctionNode(tokens.get(1)); // TODO: search for the main and put it here, for now its manually the second token
      
      for (int i = 0; i < tokens.size(); i++) {
        if (isReturnType(tokens[i]) && !isKeywordToken(tokens[i]) && *FIND PARENTHESIS AND BRACKETS*) { // TODO: implement isReturnType and check isKeyword or isKeywordToken and pseudo-code to code
        functions.add(new FunctionNode(name, return_type, parameters[], central-code)) // l'idee c'est de prendre central-code et de le transformer en lignes avec les ;
        }
      }

      for (int i = 0; i < functions.size(); i++) {
        
      }
      while going through functions
        create an arraylist of lines with head nodes
        link them to the function nodes

      find and replace functions in functions

      return main_func

















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

