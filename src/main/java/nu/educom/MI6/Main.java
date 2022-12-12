package nu.educom.MI6;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  private static String CodeSentence = "For ThE Royal QUEEN";
  private static ArrayList<String> blacklist = new ArrayList<String>();

  public static void main(String[] args) {
    while (true) {
      System.out.println("Enter Servicenumber: ");
      String userInput;
      String userSentence;
      try{
        Scanner scanner = new Scanner(System.in);
        userInput = Integer.toString(scanner.nextInt());
      } catch (Exception e) {
        System.out.println("Only numbers are allowed.");
        continue;
      }

      int lengInput = userInput.length();
      if (lengInput > 3) {
        System.out.println(String.format("Agent number %s is wrong", userInput));
        continue;
      }
      if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 956) {
        System.out.println(String.format("Agent number %s is not in defined range.", userInput));
        continue;
      }

      if (lengInput < 3) {
        userInput =  "0".repeat(3 - lengInput) + userInput;
        //System.out.println(userInput);
      }

      if (blacklist.contains(userInput)) {
        System.out.println(String.format("Agent number %s is blacklisted.", userInput));
        continue;
      }

      System.out.println("What is the secret sentence?");
      Scanner scanner = new Scanner(System.in);
      userSentence = scanner.nextLine();

      if (userSentence.equals(CodeSentence)){
        System.out.println(String.format("Logging agent number %s in", userInput));
      } else {
        System.out.println(String.format("Invalid secret code blacklisting agent number %s", userInput));
        blacklist.add(userInput);
        continue;
      }
    }
  }
}