package javaCompiler;

public class Node{
  private Token value;
  Node left, right;

  public void setLeft(Node left) {
    this.left = left;
  }
  public void setRight(Node right) {
    this.right = right;
  }

  public Token getValue() {
    return value;
  }
  public Node getLeft() {
    return left;
  }
  public Node getRight() {
    return right;
  }

  public Node(Token value) {
    this.value = value
  }
}

