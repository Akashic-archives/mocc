/*
 * Error handling for return if there is no valid return arg
 *
 * Add value to token
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
		public static void main(String[] args){
      inputVerification(args);
      String lex = readFile(args);
      Token[] tokens = lexer(lex);
      //ast = parser(tokens);
      //codeGen(ast);
      System.out.println(lex);
    }


    public static void inputVerification(String[] args) {
      if (args.length != 1) {
        System.out.println("Please invoke the program with the file to compile.");
        System.exit(1);
      }
    }



    public static void interpreter(Token[] tokens) {
      // TODO: interpret code
    }


    // TOKEN AND LEXER SECTION
    enum Token {
      INT,
      MAIN,
      OPEN_PARENTHESIS,
      CLOSE_PARENTHESIS,
      OPEN_BRACKET,
      RETURN,
      NUMBER, // both needs to have a value
      SEMICOLON,
      CLOSE_BRACKET,
      NAME // variable name or other non basic token
    }

    public static Token[] lexer(String lex) { // TODO: understand how to do it for int with i increment
    int nbreTokens = countTokens(lex);
    Token[] tokens = new Token[nbreTokens];
    int tokenCounter = 0;
    for (int i = 0; i < lex.length(); i++) {
      if (isValidToken(lex.charAt(i))) {
        tokens[tokenCounter] = tokeniseChar(lex.charAt(i));
        tokenCounter++;
      } else if (isAlpha(lex, i)) { // TODO: implement it
        String s = findWord(lex, i); // TODO: i need to do it here so that i can return int and string (for i and s)
        // TODO: i cant forget the fact that hello() is valid and doesnt stop with a space
      } else {
        int number = findNumber(lex, i); // TODO: implement it, can be the same as findWord because of the space
      }
    }
    return tokens;
  }


    public static Token tokeniseChar(char ch) {
      if (ch == ';') {
        return Token.SEMICOLON;
      }
      return Token.SEMICOLON;
    }

    public static Token tokeniseString(String s) {
      if (s.equals("int")) {
        return Token.INT;
      }
      return Token.NAME;
    }

    public static int countTokens(Token[] tokens) { // not used right now
      int counter = tokens.length;
      System.out.println(counter + " Tokens");
      return counter;
    }



    public static boolean isValidToken(char ch) {
      return (ch == ';' || ch == '{' || ch == '}' || ch == '(' || ch == ')');
    }




    // FILE HANDLING SECTION
    public static String readFile(String[] args){
      String lex = "";
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
      return lex;
    }
}

