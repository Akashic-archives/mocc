package calculator;

public class Node {
  Token token;
  Node left, right;

  public Node (Token token) {
    this.token = token;
    left = right = null;
  }


  public void addRight(Token token) {
    this.right = new Node(token);
  }

  public void addLeft(Token token) {
    this.left = new Node(token);
  }

  public Node getRight() {
    return right;
  }

  public Node getLeft() {
    return left;
  }

}
