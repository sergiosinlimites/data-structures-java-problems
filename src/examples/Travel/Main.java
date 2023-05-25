
/**
 * ## Enunciado del problema
 * 
 * Carlos, un estudiante de ingeniería de sistemas, está pensando en viajar en
 * vacaciones para cuando acabe este semestre en julio. Para eso pregunta a su
 * amiga Mariana, que es una reconocida guía turística de la región, y ella le
 * recomienda viajar por diferentes pueblitos de Boyacá. A Carlos le gustó la
 * idea y le pidió que escribiera en una lista de diferentes lugares que debería
 * visitar. Mariana escribió la siguiente lista:
 * 
 * {"Mongui", "Sachica", "Tinjaca", "Combita", "Chiquiza", "Sutamarchan",
 * "Tibasosa", "Toca", "Guican", "Chivata", "Topaga", "Soraca", "Gameza",
 * "Guayata", "Raquira", "Nobsa", "Tenza", "Aquitania"}
 * 
 * Pero Carlos, que se apasiona de aplicar lo que aprende en Estructuras de
 * Datos en su vida cotidiana, decide que va a agregar uno a uno y en orden los
 * lugares que escribió Mariana en la lista anterior en un árbol AVL. Los
 * nombres se agregan por orden lexicográfico.
 * 
 * Una vez que Carlos crea el árbol AVL, se pregunta cuál será el menor número
 * de lugares que debe visitar entre dos lugares organizados en el árbol (se
 * tienen en cuenta los lugares de inicio y fin del recorrido).
 * 
 * Por ejemplo:
 * En la imagen anterior, se creó un árbol AVL con las letras en el rango
 * [a..i]. Para ir del nodo 'c' al nodo 'e' se debe seguir la ruta c->b->d->f->e
 * dando un total de 5 nodos.
 * 
 * # Entrada
 * 
 * Dos puntos de partida y llegada diferentes, separados por espacios.
 * 
 * 
 * Salida
 * 
 * ---
 * 
 * Número mínimo de lugares que debe visitar en el recorrido por el árbol AVL
 * (Recuerde que en esta cuenta se cuentan los puntos de salida y llegada)
 * 
 * 
 * ## Ejemplos
 */

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    AVLTree tree = new AVLTree("Mongui");

    // Scanner scanner = new Scanner(System.in);
    // String lugares = scanner.nextLine();
    // String[] lugaresSeparados = lugares.split(" ");
    // String salida = lugaresSeparados[0];
    // String llegada = lugaresSeparados[1];
    String salida = "Tinjaca";
    String llegada = "Guican";
    System.out.println("Inicia");

    String[] lista = { "Sachica", "Tinjaca", "Combita", "Chiquiza", "Sutamarchan", "Tibasosa", "Toca", "Guican",
        "Chivata", "Topaga", "Soraca", "Gameza", "Guayata", "Raquira", "Nobsa", "Tenza", "Aquitania" };

    Node root = tree.getRoot();

    for (String l : lista) {
      tree.insert(root, l);
    }
    System.out.println("Aqui comienza la impresion");
    tree.printall(root);
    System.out.println("Aqui termina la impresion");
    Node nodoSalida = tree.find(salida);
    tree.getPlacesBetween(nodoSalida, llegada);
  }

  static class Node {
    private String key;
    private int height;
    private Node left, right, parent;

    Node(String k) {
      key = k;
    }
  }

  static class AVLTree {
    private Node root;

    public AVLTree(String key) {
      root = new Node(key);
    }

    public Node getRoot() {
      return root;
    }

    private boolean isEqual(Node node, String key) {
      return node.key.equals(key);
    }

    private boolean isGreater(Node node, String key) {
      int minLength = Math.min(node.key.length(), key.length());
      for (int i = 0; i < minLength; i++) {
        if (node.key.charAt(i) < key.charAt(i)) {
          return true; // El nodo es menor
        } else if (node.key.charAt(i) > key.charAt(i)) {
          return false; // El nodo es mayor
        }
      }
      return key.length() > node.key.length(); // Si todos los caracteres coinciden, el nodo más largo es mayor
    }

    private boolean isLess(Node node, String key) {
      return !isGreater(node, key);
    }

    public void printall(Node node) {
      if (node != null) {
        System.out.println(node.key);
        printall(node.left);
        printall(node.right);
      }
    }

    public void getPlacesBetween(Node n, String key) {
      System.out.println("la key es " + key);
      Node startingNode = find(key);
      Node currentNode = startingNode;
      int totalSteps = 0;
      boolean finished = false;
      while (currentNode.parent != null && !finished) {
        Node lastNode = currentNode;
        currentNode = currentNode.parent;
        totalSteps++;
        System.out.println("incrementa " + currentNode.key + " total: " + totalSteps);
        int moreSteps = 0;
        if (currentNode.left != null && isEqual(currentNode.left, lastNode.key) && currentNode.right != null) {
          moreSteps = findStops(currentNode.right, key, totalSteps);
        } else if (currentNode.right != null && isEqual(currentNode.right, lastNode.key) && currentNode.left != null) {
          moreSteps = findStops(currentNode.left, key, totalSteps);
        }
        if (moreSteps != -1) {
          finished = true;
          System.out.println("El total es " + (moreSteps + totalSteps));
        }
      }
    }

    public Node find(String key) {
      System.out.println("El root es " + root.key);
      Node resultNode = findNode(root, key);
      return resultNode;
    }

    private Node findNode(Node node, String key) {
      if (node == null || isEqual(node, key)) {
        return node;
      } else if (isLess(node, key)) {
        System.out.println("es menor");
        return findNode(node.left, key);
      } else {
        System.out.println("es mayor " + node.key + " < " + key);
        return findNode(node.right, key);
      }
    }

    private int findStops(Node node, String key, int stops) {
      if (node == null)
        return -1;
      if (isEqual(node, key)) {
        return stops;
      } else if (isLess(node, key)) {
        return findStops(node.left, key, stops++);
      } else {
        return findStops(node.right, key, stops++);
      }
    }

    private void updateHeight(Node n) {
      n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
      return n == null ? -1 : n.height;
    }

    private int getBalance(Node n) {
      return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    private Node rotateRight(Node n) {
      Node left = n.left;
      Node right = left.right;
      left.right = n;
      n.left = right;
      updateHeight(n);
      updateHeight(left);
      return left;
    }

    private Node rotateLeft(Node n) {
      System.out.println("El nodo a rotar " + n.key);
      System.out
          .println("Sus hijos " + (n.left != null ? n.left.key : "") + " - " + (n.right != null ? n.right.key : ""));
      Node right = n.right;
      Node left = right.left;
      right.left = n;
      n.right = left;
      updateHeight(n);
      updateHeight(right);
      return right;
    }

    Node rebalance(Node n) {
      updateHeight(n);
      int balance = getBalance(n);
      if (balance > 1) {
        if (height(n.right.right) > height(n.right.left)) {
          n = rotateLeft(n);
        } else {
          n.right = rotateRight(n.right);
          n = rotateLeft(n);
        }
      } else if (balance < -1) {
        if (height(n.left.left) > height(n.left.right))
          n = rotateRight(n);
        else {
          n.left = rotateLeft(n.left);
          n = rotateRight(n);
        }
      }
      return n;
    }

    public Node insert(Node node, String key) {
      if (node == null) {
        return new Node(key);
      } else if (isLess(node, key)) {
        System.out.println(node.key + " > " + key);
        node.left = insert(node.left, key);
        node.left.parent = node;
      } else if (isGreater(node, key)) {
        System.out.println(node.key + " < " + key);
        node.right = insert(node.right, key);
        node.right.parent = node;
      } else {
        throw new RuntimeException("duplicate key!");
      }
      return rebalance(node);
    }

    private Node delete(Node node, String key) {
      if (node == null) {
        return node;
      } else if (isLess(node, key)) {
        node.left = delete(node.left, key);
      } else if (isGreater(node, key)) {
        node.right = delete(node.right, key);
      } else {
        if (node.left == null || node.right == null) {
          node = (node.left == null) ? node.right : node.left;
        } else {
          Node mostLeftChild = mostLeftChild(node.right);
          node.key = mostLeftChild.key;
          node.right = delete(node.right, node.key);
        }
      }
      if (node != null) {
        node = rebalance(node);
      }
      return node;
    }

    private Node mostLeftChild(Node node) {
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }
  }

}
