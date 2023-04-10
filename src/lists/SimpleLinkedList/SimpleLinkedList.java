package src.lists.SimpleLinkedList;

public class SimpleLinkedList<T> {
  private Node<T> head;

  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }
  }

  public void pushFront(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.next = head;
    head = newNode;
  }

  public void pushBack(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  public T popFront() {
    if (head == null) {
      return null;
    }
    T removed = head.data;
    head = head.next;
    return removed;
  }

  public T popBack() {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      T removed = head.data;
      head = null;
      return removed;
    }
    Node<T> current = head;
    while (current.next.next != null) {
      current = current.next;
    }
    T removed = current.next.data;
    current.next = null;
    return removed;
  }
}