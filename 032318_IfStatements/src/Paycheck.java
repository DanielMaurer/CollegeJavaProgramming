// 03-23-2018
// This calculates a paycheck including taxes for an hourly worker

import java.util.Scanner;

public class Paycheck {
     public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
          System.out.println("Welcome to the paycheck calculator");
          System.out.print("How many hours did you work? ");
          double hours = sc.nextDouble();
          System.out.print("What is hourly pay rate? ");
          double rate = sc.nextDouble();
          double gross = hours * rate;
          // determine the tax rate
          double taxRate;
          if (gross > 2000) {
              taxRate = 0.2;
          } else if (gross > 1000) {
              taxRate = 0.15;
          } else if (gross > 500) {
              taxRate = 0.1;
          } else {
              taxRate = 0;
          }
          double taxes = gross * taxRate;
          double net = gross - taxes;
          // show the paycheck
          System.out.println("Here is your paycheck:");
          System.out.printf("Gross pay:  $%8.2f\n",gross);
          System.out.printf("Taxes:      $%8.2f\n",taxes);
          System.out.printf("Net pay:    $%8.2f\n",net);
          sc.close();
     }
}