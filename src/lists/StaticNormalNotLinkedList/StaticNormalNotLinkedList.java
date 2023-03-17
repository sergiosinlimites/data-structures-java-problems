package src.lists.StaticNormalNotLinkedList;

public class StaticNormalNotLinkedList { // static, not linked and not circular list
  static public int arraySize = 5;
  static Integer[] arr = new Integer[arraySize];
  static public int lastIndex = 0;

  public void print() {
    System.out.println("Printing static, not linked and not circular list");
    String finalString = "";
    for (int i = 0; i < arr.length; i++) {
      finalString = finalString.concat(Integer.toString(arr[i])+" ");
    }
    System.out.println(finalString);
  }

  public boolean empty() {
    System.out.println(arr[0]);
    System.out.println(lastIndex);
    return lastIndex == 0 && arr[lastIndex] == null;
  }

  public void pushFront(int key) {
    for (int i = arraySize - 1; i > 0; i--) {
      arr[i] = arr[i - 1];
    }
    arr[0] = key;
    lastIndex++;
  }

  public void pushBack(int key) {
    if (lastIndex >= arraySize - 1) {
      lastIndex = 0;
      arr[lastIndex] = key;
    } else {
      arr[lastIndex] = key;
    }
  }

  public int topFront() {
    return arr[0];
  }

  public int topBack() {
    return arr[lastIndex];
  }

  public int addBefore(int index, int key) {
    if (index > arraySize - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException("No se puede agregar por fuera de los límites");
    }
    for (int i = arraySize - 1; i > index; i--) {
      arr[i] = arr[i - 1];
    }
    arr[index] = key;
    return arr[index];
  }

  public int addAfter(int index, int key) {
    if (index > arraySize - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException("No se puede agregar por fuera de los límites");
    }
    for (int i = arraySize - 1; i > index + 1; i--) {
      arr[i] = arr[i - 1];
    }
    arr[index + 1] = key;
    return arr[index + 1];
  }

}