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
      SEMICOLON,
      RETURN
    }

    public static Token[] lexer(String lex) { // TODO: understand how to do it for int with i increment
    int nbreTokens = countTokens(lex);
    Token[] tokens = new Token[nbreTokens];
    int tokenCounter = 0;
    for (int i = 0; i < lex.length(); i++) {
      if (isValidToken(lex.charAt(i))) {
        tokens[tokenCounter] = tokeniseChar(lex.charAt(i));
        tokenCounter++;
      }
    }
    return tokens;
  }


    public static Token tokeniseChar(char ch) { // TODO: overload for "int"
      if (ch == ';') {
        return Token.SEMICOLON;
      }
      return Token.SEMICOLON;
    }

    public static int countTokens(String lex) {
      int counter = 0;
      for (int i = 0; i < lex.length(); i++) {
        if (isValidToken(lex.charAt(i))) // TODO: understand how to handle "int"
          counter++;
      }
      System.out.println(counter + " Tokens");
      return counter;
    }


    public static boolean isValidToken(char ch) {
      return (ch == ';'); // TODO: add the other tokens and understand how to do "int" form char
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

