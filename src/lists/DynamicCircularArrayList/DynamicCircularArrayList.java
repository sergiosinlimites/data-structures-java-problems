package src.lists.DynamicCircularArrayList;

public class DynamicCircularArrayList { // dynamic, circular, not linked list
    private int capacity;
    private int size;
    private int[] arr;

    public DynamicCircularArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.arr = new int[capacity];
    }

    public static void main(String[] args) {
        DynamicCircularArrayList list = new DynamicCircularArrayList(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.printList(); // Output: 1 2 3 4 5
        list.remove(3);
        list.printList(); // Output: 1 2 4 5
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(int data) {
        if (isFull()) {
            capacity *= 2;
            int[] temp = new int[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[size] = data;
        size++;
    }

    public void remove(int data) {
        if (isEmpty()) {
            return;
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == data) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[(i + 1) % capacity];
        }
        size--;
        if (size <= capacity / 4) {
            capacity /= 2;
            int[] temp = new int[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
    }

    public void printList() {
        System.out.println("Printing dynamic, circular, not linked list");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}