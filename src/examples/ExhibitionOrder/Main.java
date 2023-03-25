package src.examples.ExhibitionOrder;
import java.util.Scanner;

/**
 * Exhibition Order
  Problem statement
  A data structures professor needs to organize the presentations of his students on the topics they will cover in the course. He downloads the list of students and assigns the dates in the order they appear on the list, but the student with the last name Cárdenas complained that his classmate with the last name Valencia had the presentation scheduled for the end of the semester, while he had it in two days. To avoid arguments, the professor proposes a different mechanism to assign the presentations: he will take the first one on the list, then the last one, then the second one, then the second to last one, and so on until all students have a presentation topic. This semester, due to overbooking, the number of students n is in the range 2<=n<=10000. Your mission is to obtain the list in the order the students will present, given the original class list. To do this, you will receive an array of last names of the class in alphabetical order and you should store it in a linked list. The process of obtaining the new presentation list should also be done in a linked list.

  Input
  Original list of last names. Each last name is separated by a space.

  Output
  New list of last names. Each last name is separated by a space.

  Examples

  Example 1 Input

  Avila Bermudez Cardenas Ramos Rodriguez Valencia
  Example 1 Output

  Avila Valencia Bermudez Rodriguez Cardenas Ramos

  Example 2 Input

  Bermudez Valencia
  Example 2 Output

  Bermudez Valencia
 */

public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    String nombres = scanner.nextLine();
    new SpecialLinkedList(nombres);
  }

  static class SpecialLinkedList {
    public SpecialLinkedList(String names){
      String[] separatedNames = names.split(" ");
      NodeList initialList = new NodeList();
      for(String name : separatedNames){
        Node initialNode = new Node(name);
        if(initialList.getLast() == null && initialList.getHead() == null){
          initialList.setLast(initialNode);
          initialList.setHead(initialNode);
        } else {
          initialList.pushBack(initialNode);
        }
      }
      initialList.printAll();
      Node lastIn = null;
      StringBuilder stringBuilder = new StringBuilder();
      while(initialList.getHead() != null && initialList.getLast() != null){
        if(initialList.getHead() == initialList.getLast() && initialList.getHead() != lastIn){
          stringBuilder.append(" "+initialList.getHead().getData());
          initialList.setHead(null);
          initialList.setLast(null);
        } else if (initialList.getHead() == initialList.getLast() && initialList.getHead() == lastIn) {
          initialList.setHead(null);
          initialList.setLast(null);
        } else {
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
          if(beforeNode != null && nextNode.getNext() != null){
            beforeNode.setNext(null);
            initialList.setLast(beforeNode);
          }
          lastIn = last;
          stringBuilder.append(" "+ nameLast);
        }
      }
      System.out.print(stringBuilder.toString().trim());
    }
  }

  static class Node {
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


  static class NodeList {

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
      print(this.head);
    }
  
    public void print(Node node){
      Node currentNode = node;
      if(currentNode != null){
        this.print(currentNode.getNext());
      }
    }
  }
  
}

