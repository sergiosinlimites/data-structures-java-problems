package src.examples.CommonLists;
import java.util.Scanner;

/**
 * Problem statement
  * Given the elements of two different linked lists with the same length n where 2 <= n <= 10000. Both lists must be traversed at the same time: the first list is traversed from left to right and the second from right to left. If at any iteration it is found that two elements of both lists are equal, then that element must be removed from both lists. Finally, the remaining elements in each list must be printed.

  Constraints:

  Length n of each list: 2 <= n <= 10000
  Each element of the lists is between 0 and 9.
  Input:
  Elements of the two linked lists separated by spaces. The elements of the first list are separated from the elements of the second list by a line break.

  Output:
  Resulting elements of the first list (separated by spaces) followed by a line break and then the elements of the second list (separated by spaces).

  Examples:

  Input Example 1:

  6 1 3 9 2 7
  1 2 4 3 1 6

  Output Example 1:

  9 7
  1 4

  Input Example 2:

  1 2 3
  4 5 6

  Output Example 2:

  1 2 3
  4 5 6
 */

public class Main {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] firstList = scanner.nextLine().split(" ");
    String[] secondList = scanner.nextLine().split(" ");
    // String text1 = "6 1 3 10 2 7";
    // String text2 = "1 2 4 3 1 6";
    // String[] firstList = text1.split(" ");
    // String[] secondList = text2.split(" ");
    StringBuilder stringBuilder1 = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();
    NodeList doubleLinkedFirstList = new NodeList(new Node(Integer.valueOf(firstList[0])));
    NodeList doubleLinkedSecondList = new NodeList(new Node(Integer.valueOf(secondList[0])));
    for(int i = 1; i < firstList.length; i++){
      doubleLinkedFirstList.pushBack(new Node(Integer.valueOf(firstList[i])));
      doubleLinkedSecondList.pushBack(new Node(Integer.valueOf(secondList[i])));
    }
    while(doubleLinkedFirstList.getHead() != null && doubleLinkedSecondList.getLast() != null){
      int firstVal = doubleLinkedFirstList.getHead().getData();
      int secondVal = doubleLinkedSecondList.getLast().getData();
      if(firstVal != secondVal){
        stringBuilder1.append(firstVal+" ");
        stringBuilder2.append(secondVal+" ");
      }
      doubleLinkedFirstList.head = doubleLinkedFirstList.head.next;
      doubleLinkedSecondList.last = doubleLinkedSecondList.last.after;
    }

    String first = stringBuilder1.toString().trim();
    String second = stringBuilder2.toString().trim();
    StringBuilder secondReversed = new StringBuilder();
    String[] secondArr = second.split(" ");
    for(int i = secondArr.length - 1; i >= 0; i--){
      secondReversed.append(secondArr[i]+" ");
    }
    System.out.println(first);
    System.out.print(secondReversed.toString().trim());
  }

  static class Node {
    private int data;
    private Node next = null;
    private Node after = null;
    public Node(int data){
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

    public int getData(){
      return this.data;
    }

    public void setData(int data){
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
