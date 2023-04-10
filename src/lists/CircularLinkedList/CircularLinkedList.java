package src.lists.CircularLinkedList;

public class CircularLinkedList<T> {
  private Node<T> tail;
  private int size;

  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  // Return the size of the list
  public int size() {
    return size;
  }

  // Check if the list is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Return the first element of the list
  public T getFirst() {
    if (isEmpty()) {
      return null;
    }
    return tail.next.data;
  }

  // Return the last element of the list
  public T getLast() {
    if (isEmpty()) {
      return null;
    }
    return tail.data;
  }

  // Return the element at the specified index
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    if (index == 0) {
      return getFirst();
    }
    if (index == size - 1) {
      return getLast();
    }
    Node<T> current = tail.next;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  // Add an element to the front of the list
  public void addFirst(T data) {
    Node<T> newNode = new Node<>(data);
    if (isEmpty()) {
      tail = newNode;
      tail.next = tail;
    } else {
      newNode.next = tail.next;
      tail.next = newNode;
    }
    size++;
  }

  // Add an element to the end of the list
  public void addLast(T data) {
    addFirst(data);
    tail = tail.next;
  }

  // Add an element at the specified index
  public void add(int index, T data) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    if (index == 0) {
      addFirst(data);
      return;
    }
    if (index == size) {
      addLast(data);
      return;
    }
    Node<T> current = tail.next;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    Node<T> newNode = new Node<>(data);
    newNode.next = current.next;
    current.next = newNode;
    size++;
  }

  // Remove the first element of the list
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }
    Node<T> head = tail.next;
    if (tail == head) {
      tail = null;
    } else {
      tail.next = head.next;
    }
    size--;
    return head.data;
  }

  // Remove the last element of the list
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }
    Node<T> current = tail.next;
    while (current.next != tail) {
      current = current.next;
    }
    Node<T> head = tail.next;
    current.next = head;
    tail = current;
    size--;
    return head.data;
  }

  // Remove the element at the specified index
  public T remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
    if (index == 0) {
      return removeFirst();
    }
    if (index == size - 1) {
      return removeLast();
    }
    Node<T> current = tail.next;
    for (int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    Node<T> removedNode = current.next;
    current.next = removedNode.next;
    size--;
    return removedNode.data;
  }

  // Return a string representation of the list
  public String toString() {
    if (isEmpty()) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    Node<T> current = tail.next;
    while (current != tail) {
      sb.append(current.data).append(", ");
      current = current.next;
    }
    sb.append(tail.data).append("]");
    return sb.toString();
  }
}
