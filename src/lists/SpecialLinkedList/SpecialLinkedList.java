package src.lists.SpecialLinkedList;

public class SpecialLinkedList {
  public SpecialLinkedList(String names){
    String[] separatedNames = names.split(" ");
    NodeList initialList = new NodeList();
    for(String name : separatedNames){
      System.out.println("el nombre "+ name);
      Node initialNode = new Node(name);
      if(initialList.getLast() == null && initialList.getHead() == null){
        initialList.setLast(initialNode);
        initialList.setHead(initialNode);
      } else {
        initialList.pushBack(initialNode);
      }
    }
    initialList.printAll();

    StringBuilder stringBuilder = new StringBuilder();
    while(initialList.getHead().getNext() != null && initialList.getLast().getAfter() != null){
      // inicio
      Node head = initialList.getHead();
      String nameStart = head.getData();
      Node nextNode = head.getNext();
      if(nextNode != null){
        nextNode.setAfter(null);
        initialList.setHead(nextNode);
      }
      stringBuilder.append(" " + nameStart);
      // final
      Node last = initialList.getLast();
      String nameLast = last.getData();
      Node beforeNode = last.getAfter();
      if(beforeNode != null){
        beforeNode.setNext(null);
        initialList.setLast(beforeNode);
      }
      stringBuilder.append(" "+ nameLast);
    }
    System.out.println("El final: "+stringBuilder.toString());

  }

  class Node {
    private String data = null;
    private Node next = null;
    private Node after = null;
    public Node(String data){
      this.data = data;
      this.next = null;
      this.after = null;
    }
  
    public Node getNext(){
      return this.next;
    }

    public Node getAfter(){
      return this.after;
    }

    public void setNext(Node node){
      this.next = node;
    }

    public void setAfter(Node node){
      this.after = node;
    }

    public String getData(){
      return this.data;
    }

    public void setData(String data){
      this.data = data;
    }

  }


  class NodeList {

    public Node head;
    public Node last;
  
    public NodeList(){
    }

    public NodeList(Node node){
      this.head = node;
      this.last = node;
    }
  
    public Node getHead(){
      return this.head;
    }

    public Node getLast(){
      return this.last;
    }

    public void setHead(Node node){
      this.head = node;
    }

    public void setLast(Node node){
      this.last = node;
    }
  
    public void pushFront(Node node){
      node.setAfter(null);
      node.setNext(this.head);
      last = head;
      head = node;
    }

    public void pushBack(Node node){
      node.setAfter(last);
      node.setNext(null);
      last.setNext(node);
      last = node;
    }

    public Boolean isEmpty(){
      return this.head == null && this.last == null;
    }

    public void printAll(){
      System.out.println("la cabeza" + this.head.getData());
      print(this.head);
    }
  
    public void print(Node node){
      Node currentNode = node;
      if(currentNode != null){
        System.out.println("La data del nodo es: "+currentNode.getData());
        this.print(currentNode.getNext());
      }
    }
  }
}
