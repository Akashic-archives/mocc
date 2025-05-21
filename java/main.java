import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
		public static void main(String[] args){
      String lex = readFile(args);
      System.out.println(lex);
    }

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

