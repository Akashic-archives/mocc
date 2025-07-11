package calculator;

public class Ast {
  Node head;

  class Node {
    Token token;
    Node right = null
    Node left = null;
    
    Node(Token token) {
      this.token = token;
    }
  }

  public void addRight(Token token) {
    this.right = token;
  }

  public void addLeft(Token token) {
    this.left = token;
  }

  public void changeHead(Token token) {
    Node pastHead = this.head;
    this.head = head;
    this.right = pastHead;
  }

}
