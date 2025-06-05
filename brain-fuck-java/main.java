import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
	public static void main(String[] args){
    if (inputVerification(args) == 1) {
      System.out.println("compile");
    } else {
      System.out.println("interpreter");
    } // TODO: for now interpreter mode always
    String lex = readFile(args);
    Token[] tokens = lexer(lex);
    //ast = parser(lex);
    interpreter(tokens);
    //codeGen(ast);
    System.out.println();
  }





  public static void interpreter(Token[] tokens) {
    int index = 5000;
    int programCounter = 0;
    int[] memory = new int[10000]; // TODO: dynamically allocate memory depending on performance profile or memory focused parameter.
    while (programCounter != tokens.length - 1) { // TODO: add method executeInstruction()
      if (tokens[programCounter] == Token.RIGHT_POINTER) {
        index++;
        programCounter++;
      }
      else if (tokens[programCounter] == Token.LEFT_POINTER) {
        index--;
        programCounter++;
      }
      else if (tokens[programCounter] == Token.PLUS) {
        memory[index] = memory[index] + 1;
        programCounter++;
      }
      else if (tokens[programCounter] == Token.MINUS) {
        memory[index] = memory[index] - 1;
        programCounter++;
      }
      else if (tokens[programCounter] == Token.POINT) {
        System.out.print((char) memory[index]);
        programCounter++;
      }
      else if (tokens[programCounter] == Token.COMMA) {
        Scanner sc = new Scanner(System.in);
        memory[index] = (int) sc.nextByte();
        programCounter++;
      }
      else if (tokens[programCounter] == Token.LEFT_BRACKET) {
        if (memory[index] == 0) {
          programCounter = findMachingRightBracket(tokens, programCounter) - 1;
        }
        programCounter++;
      }
      else if (tokens[programCounter] == Token.RIGHT_BRACKET) {
        if (memory[index] != 0) {
          programCounter = findMachingLeftBracket(tokens, programCounter) - 1;
        }
        programCounter++;
      }
    }
  }

  public static int findMachingRightBracket(Token[] tokens, int programCounter) {
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

  public static int findMachingLeftBracket(Token[] tokens, int programCounter) {
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
    RIGHT_POINTER,
    LEFT_POINTER,
    PLUS,
    MINUS,
    POINT,
    COMMA,
    LEFT_BRACKET,
    RIGHT_BRACKET
	}

  public static Token[] lexer(String lex) {
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

  public static Token tokeniseChar(char ch) {
      if (ch == '>')
        return Token.RIGHT_POINTER;
      else if (ch == '<')
        return Token.LEFT_POINTER;
      else if (ch == '+')
        return Token.PLUS;
      else if (ch == '-')
        return Token.MINUS;
      else if (ch == '.')
        return Token.POINT;
      else if (ch == ',')
        return Token.COMMA;
      else if (ch == '[')
        return Token.LEFT_BRACKET;
      else if (ch == ']')
        return Token.RIGHT_BRACKET;
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

  public static boolean isValidToken(char ch) {
    if (ch == '>' || ch == '<' || ch == '+' || ch == '-' || ch == '.' || ch == ',' || ch == '[' || ch == ']')
      return true;
    return false;
  }

	// FILE SECTION
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

