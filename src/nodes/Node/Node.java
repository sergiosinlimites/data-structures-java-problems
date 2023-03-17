package src.nodes.Node;
public class Node {
  private Integer data = null;
  private Node next = null;
  public Node(int data){
    this.data = data;
    this.next = null;
  }

  public Node getNext(){
    return this.next;
  }

  public void setNext(Node node){
    this.next = node;
  }

  public Integer getData(){
    return this.data;
  }

  public void setData(int data){
    this.data = data;
  }
  
}