package examples.IslandVisit;

import java.util.*;

/**
 * Islas Galápagos
 * ## Enunciado del problema
 * 
 * Un grupo de estudiantes de botánica están interesados en innovar. Para eso,
 * deciden hacer un viaje a la isla Wolf (que es una de las Islas Galápagos que
 * solo se puede visitar para quienes tienen fines de investigación o quieren
 * hacer buceo profesional, no para turistas). Este viaje es naturalmente muy
 * costoso y deben sacarle el mayor provecho. Van a ir a investigar sobre una
 * nueva especie de insecto que vive en las copas de los árboles, pero como la
 * isla tiene más de 1.5 kilómetros cuadrados, deben maximizar el proceso de
 * toma de muestras. El proceso de la toma de muestras está organizado en n
 * zonas distribuidas a través de las islas, donde cada zona tiene una cantidad
 * esperada de insectos a recolectar i.
 * 
 * La idea entonces es recibir la lista de las n cantidades de insectos
 * esperados por zona y agregarlos en level order
 * (https://www.geeksforgeeks.org/create-a-tree-in-level-order/). En la
 * contrucción de este árbol encontrará valores en el rango [-1, 25]. El valor
 * -1 es utilizado para representar NULL(es decir, que ese nodo no existe y por
 * ahí no puede hacerse la expedición). Los demás valores diferentes de -1 son
 * valores que sí hacen referencia a la cantidad esperada de insectos.
 * 
 * La expedicción comienza en el cráter del volcán Wolf, que es la primera zona
 * en la lista anterior y representa la raíz del árbol binario creado
 * anteriormente. Como no se tienen los recursos para pasar por cada uno de los
 * nidos de insectos solo se puede pasar por a nodos. Entonces uno de los
 * estudiantes propone 3 maneras de realizar la expedición: Recorrido inorder,
 * preorder y postorder. El estudiante propone visitar los primeros a nodos en
 * cada recorrido, pero no cuenta con un computador en la Isla para realizar los
 * cálculos.
 * 
 * Su misión entonces es ayudar al estudiante a encontrar el mejor recorrido que
 * puede hacer (Inorder, preorder o postorder) y la cantidad esperada de
 * insectos que podrá recolectar en dicho recorrido.
 * 
 * Tenga en cuenta además que si hay dos recorridos que tienen el mismo número
 * de insectos esperados, se tendrá en cuenta el siguiente criterio de
 * preferencia: Preorder - Inorder - Postorder.
 * 
 * Por ejemplo:
 * https://drive.google.com/file/d/18TxsYbe6RYUnZjSADTQU-DKKwiau_mrL/view?usp=share_link
 * 
 * ## Restricciones
 * 
 * El árbol binario de entrada es un árbol binario completo con niveles entre 3
 * y 12.
 * Los valores de cada nodo están entre 0 y 25 con la restricción de que -1
 * representa NULL.
 * ## Entrada
 * 
 * En la primera línea va la lista ordenada de posibles insectos por zona (el
 * primer elemento es la raíz del árbol). Hay suficientes elementos por cada
 * entrada para tener un árbol binario completo en su último nivel. En la
 * segunda línea va el número de nodos a que se deben visitar. |
 * 
 * ## Salida
 * 
 * Inicialmente va la salida dice el mejor recorrido a realizar, con la primera
 * letra en mayúscula ("Preorder", "Inorder", "Postorder") Luego, separado por
 * un espacio, va la cantidad máxima de insectos que se pueden recolectar en la
 * expedición
 * 
 * 
 * ## Ejemplos
 * 
 * 
 * Entrada Ejemplo 1
 * 
 * 4 20 10 -1 16 4 0
 * 4
 * Salida Ejemplo 1
 * 
 * Preorder 50
 */

class Main {

  static class Node {
    int key;
    Node left;
    Node right;
  };

  static Node root = null;

  static Queue<Node> q = new LinkedList<>();

  // Function to create a node
  // with 'value' as the data
  // stored in it.
  // Both the children of this
  // new Node are initially null.
  static Node newNode(int value) {
    Node n = new Node();
    n.key = value;
    n.left = null;
    n.right = null;
    return n;
  }

  static void insertValue(int value) {
    Node node = newNode(value);
    if (root == null)
      root = node;

    // The left child of the
    // current Node is used
    // if it is available.
    else if (q.peek().left == null)
      q.peek().left = node;

    // The right child of the current
    // Node is used if it is available.
    // Since the left child of this
    // node has already been used, the
    // Node is popped from the queue
    // after using its right child.
    else {
      q.peek().right = node;
      q.remove();
    }

    // Whenever a new Node is added
    // to the tree, its address is
    // pushed into the queue. So that
    // its children Nodes can be used
    // later.
    q.add(node);

  }

  // This function mainly calls
  // insertValue() for all array
  // elements. All calls use same
  // queue.
  static void createTree(int arr[],
      int n) {
    for (int i = 0; i < n; i++)
      insertValue(arr[i]);
  }

  static Queue<Node> preOrder(Node root) {
    if (root == null)
      return null;

    Stack<Node> stack = new Stack<>();
    Queue<Node> returnStack = new LinkedList<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      Node currentNode = stack.pop();
      returnStack.add(currentNode);
      if (currentNode.right != null)
        stack.push(currentNode.right);
      if (currentNode.left != null)
        stack.push(currentNode.left);
    }

    return returnStack;
  }

  static Queue<Node> inOrder(Node root) {
    if (root == null)
      return null;
    Stack<Node> stack = new Stack<>();
    Queue<Node> returnStack = new LinkedList();
    Node currentNode = root;
    while (currentNode != null || !stack.isEmpty()) {
      while (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.left;
      }
      currentNode = stack.pop();
      returnStack.add(currentNode);
      currentNode = currentNode.right;
    }
    return returnStack;
  }

  static Queue<Node> postOrder(Node root) {
    if (root == null) {
      return null;
    }

    Queue<Node> result = new LinkedList<>();

    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    stack1.push(root);

    while (!stack1.isEmpty()) {
      Node currentNode = stack1.pop();
      stack2.push(currentNode);

      if (currentNode.left != null)
        stack1.push(currentNode.left);
      if (currentNode.right != null)
        stack1.push(currentNode.right);
    }

    while (!stack2.isEmpty()) {
      Node currentNode = stack2.pop();
      result.add(currentNode);
    }
    return result;
  }

  // Driver code
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String posiblePerZone = scanner.nextLine();
    int counter = scanner.nextInt();
    int initialCounter = counter;

    String[] arrString = posiblePerZone.split(" ");
    int[] arr = new int[arrString.length];
    for (int i = 0; i < arrString.length; i++) {
      arr[i] = Integer.valueOf(arrString[i]).intValue();
    }

    int n = arr.length;
    createTree(arr, n);
    Queue<Node> resultPreOrder = preOrder(root);
    Queue<Node> resultInOrder = inOrder(root);
    Queue<Node> resultPostOrder = postOrder(root);
    if (resultInOrder != null) {
      int sumPreOrder = 0;
      int sumInOrder = 0;
      int sumPostOrder = 0;
      counter = initialCounter;
      for (int i = 0; i < counter; i++) {
        Node morePre = !resultPreOrder.isEmpty() ? resultPreOrder.remove() : null;
        if (morePre != null && morePre.key != -1) {
          sumPreOrder += morePre.key;
        } else if (morePre != null && morePre.key == -1) {
          counter++;
        }
      }
      counter = initialCounter;
      for (int i = 0; i < counter; i++) {
        Node moreIn = !resultInOrder.isEmpty() ? resultInOrder.remove() : null;
        if (moreIn != null && moreIn.key != -1) {
          sumInOrder += moreIn.key;
        } else if (moreIn != null && moreIn.key == -1) {
          counter++;
        }
      }
      counter = initialCounter;
      for (int i = 0; i < counter; i++) {
        Node morePost = !resultPostOrder.isEmpty() ? resultPostOrder.remove() : null;
        if (morePost != null && morePost.key != -1) {
          sumPostOrder += morePost.key;
        } else if (morePost != null && morePost.key == -1) {
          counter++;
        }
      }

      if (sumPreOrder > Math.max(sumInOrder, sumPostOrder)) {
        System.out.print("Preorder " + sumPreOrder);
      } else if (sumInOrder > Math.max(sumPreOrder, sumPostOrder)) {
        System.out.print("Inorder " + sumInOrder);
      } else if (sumPostOrder > Math.max(sumPreOrder, sumInOrder)) {
        System.out.print("Postorder " + sumPostOrder);
      } else {
        if (sumPreOrder == sumInOrder) {
          System.out.print("Preorder " + sumPreOrder);
        } else if (sumPreOrder == sumPostOrder) {
          System.out.print("Preorder " + sumPreOrder);
        } else if (sumInOrder == sumPostOrder) {
          System.out.print("Inorder " + sumInOrder);
        }
      }
    }
  }
}
