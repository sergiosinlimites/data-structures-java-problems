package src.examples.RobotsAttack;
import java.util.Scanner;

/**
 * Problem Statement
  The mechatronics engineering students had to make a project for a subject, which consisted of building a sumo robot. They worked on this throughout the semester and now it's time to test the robots.

  Each robot has a power that can be a number -10^4 <= n <= 10^4 and power != 0. The sign of the power indicates the direction in which the robot is moving: if it is positive, it moves to the right and if it is negative, it moves to the left.

  Each robot moves at the same speed.

  So, the course professor decides to divide the classroom into two groups. Each group will be located at one end of the engineering courtyard basketball court. The robots that come out on the left side of the court will have a positive power (they move to the right), and those that come out on the right side will have a negative power (they move to the left), as seen in the following image: https://drive.google.com/file/d/1DNs3ionTYrjMHAikv7wsHk4OqCCorncx/view?usp=share_link

  The game is simple: Every 5 seconds, each team will send a robot to collide with the robot from the opposite team. At the moment they collide, the robot with more power will destroy the weaker robot and continue on its way. If two robots have the same power, they both destroy each other.

  The list of robot powers will have a size n with 2 <= n <= 10^6. The task is to find and print the state of the sumo robots after all collisions.

  Input
  This section is intended to describe the input of the problem, for example: it is said to read a number n such that 0 <= n <= 2000000000.

  Output
  This section is intended to describe the format of the output or result of the problem. For example, it can be said: "Your program should print a number n on the screen (using cout) as indicated in the statement.".

  Examples

  Input Example 1
  2 7 -3

  Output Example 1
  2 7

  Input Example 2
  9 5 -10

  Output Example 2
  -10

  Input Example 3
  1 -1

  Output Example 3
  No quedaron robots! (No robots left!)
 */

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] poderes = scanner.nextLine().split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    //String text = "14 15 16 17 -17 -16 -15 -14";
    //String[] poderes = text.split(" ");
    double primerPositivo;
    if(poderes.length % 2 == 0){
      primerPositivo = poderes.length / 2;
    } else {
      primerPositivo = Math.floor(poderes.length / 2) + 1;
    }
    double primerNegativo = primerPositivo + 1;
    Stack stackPositivos = new Stack((int) primerPositivo);
    Stack stackNegativos = new Stack((poderes.length - (int) primerNegativo) + 1);
    for(double i = poderes.length - 1; i >= primerNegativo - 1; i--){
      stackNegativos.push(Double.valueOf(poderes[(int) i]));
    } 
    for(double i = 0; i < primerPositivo; i++){
      stackPositivos.push(Double.valueOf(poderes[(int) i]));
    }
    String ganador = "";
    while(!stackPositivos.empty() && !stackNegativos.empty()){
      double positivo = stackPositivos.peek();
      double negativo = stackNegativos.peek();
      if(positivo + negativo == 0){
        stackNegativos.pop();
        stackPositivos.pop();
      } else if(positivo + negativo > 0){
        stackNegativos.pop();
      } else if(positivo + negativo < 0) {
        stackPositivos.pop();
      }
      if(stackNegativos.empty() && stackPositivos.empty()){
        ganador = "ninguno";
      } else if(stackNegativos.empty()) {
        ganador = "positivos";
      } else if(stackPositivos.empty()) {
        ganador = "negativos";
      }
    }
    if(ganador == "ninguno"){
      stringBuilder.append("No quedaron robots!");
    } else if(ganador == "positivos"){
      while(!stackPositivos.empty()){
        stringBuilder.insert(0, (int) stackPositivos.pop() + " ");
      }
    } else if(ganador == "negativos") {
      while(!stackNegativos.empty()){
        stringBuilder.append((int) stackNegativos.pop() + " ");
      }
    }
    System.out.print(stringBuilder.toString().trim());
  }

  static class Stack {
    private double[] data;
    private int top;

    public Stack(){}

    public Stack(int myInt) {
        data = new double[myInt];
        top = -1;
    }

    public void push(double value) {
        top++;
        data[top] = value;
    }

    public double pop() {
        double value = data[top];
        top--;
        return value;
    }

    public double peek() {
        return data[top];
    }

    public boolean empty() {
        return top == -1;
    }
  }
}
