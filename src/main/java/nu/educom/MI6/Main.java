package nu.educom.MI6;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Main {

  private static String CodeSentence = "For ThE Royal QUEEN";
  private static ArrayList<String> blacklist = new ArrayList<String>();

  public static void main(String[] args) {
    while (true) {
      System.out.println("Enter Servicenumber: ");
      String userInput;
      String userSentence;
      JFrame frame = new JFrame("Title");
      try{
        userInput = JOptionPane.showInputDialog(frame, "Enter your agent number");
        // cancel -> quit system
        if (userInput == null) {System.exit(0);}
      } catch (Exception e) {
        JOptionPane.showMessageDialog(frame, "Only numbers are allowed.");
        continue;
      }

      int lengInput = userInput.length();
      if (lengInput > 3) {
        JOptionPane.showMessageDialog(frame,String.format("Agent number %s is wrong", userInput));
        continue;
      }
      if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 956) {
        JOptionPane.showMessageDialog(frame, String.format("Agent number %s is not in defined range.", userInput));
        continue;
      }

      if (lengInput < 3) {
        userInput =  "0".repeat(3 - lengInput) + userInput;
        //System.out.println(userInput);
      }

      if (blacklist.contains(userInput)) {
        JOptionPane.showMessageDialog(frame, "ACCESS DENIED");
        continue;
      }

      userSentence = JOptionPane.showInputDialog(frame,"What is the secret sentence?");
      // cancel -> quit system
      if (userSentence == null) {System.exit(0);}

      if (userSentence.equals(CodeSentence)){
        JOptionPane.showMessageDialog(frame,String.format("Logging agent number %s in", userInput));
      } else {
        JOptionPane.showMessageDialog(frame, "ACCESS DENIED");
        blacklist.add(userInput);
        continue;
      }
    }
  }
}