package com.company;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    private static String variant;

    public static void main(String[] args) {
        menu();
    }

    static public void menu() {

        System.out.println("Choose variant from 1 to 4");
        System.out.println("1. Standard implementation.");
        System.out.println("2. Asymmetric forks.");
        System.out.println("3. Coin toss.");
        System.out.println("4. Exit");

        Scanner scan = new Scanner(System.in);
        variant = scan.next();
        while(checkVariant(variant)) {
            System.out.println("Error! Choose variant from 1 to 4");
            variant = scan.next();
        }

        switch(variant){
            case "1":
                System.out.println("Number 1 Start!");
                Philosopher_1.MAX = setPhilosophersAmount();
                Philosopher_1.fork = new Semaphore[Philosopher_1.MAX];
                for(int i = 0; i < Philosopher_1.MAX; i++) {
                    Philosopher_1.fork[i] = new Semaphore(1);
                }
                for (int i = 0; i < Philosopher_1.MAX; i++) {
                    new Philosopher_1(i).start();
                }
                break;
            case "2":
                System.out.println("Number 2 Start!");
                Philosopher_2.MAX = setPhilosophersAmount();
                Philosopher_2.fork = new Semaphore[Philosopher_2.MAX];
                for(int i = 0; i < Philosopher_2.MAX; i++) {
                    Philosopher_2.fork[i] = new Semaphore(1);
                }
                for (int i = 0; i < Philosopher_2.MAX; i++) {
                    new Philosopher_2(i).start();
                }
                break;
            case "3":
                System.out.println("Number 3 Start!");
                Philosopher_3.MAX = setPhilosophersAmount();
                Philosopher_3.fork = new Semaphore[Philosopher_3.MAX];
                for(int i = 0; i < Philosopher_3.MAX; i++) {
                    Philosopher_3.fork[i] = new Semaphore(1);
                }
                for (int i = 0; i < Philosopher_3.MAX; i++) {
                    new Philosopher_3(i).start();
                }
                break;
            case "4":
                System.out.println("Exit");
                break;
            default:
                System.out.println("Error!");
        }
    }

    private static int setPhilosophersAmount() {
        int n;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of philosophers from 2 to 1000:");
        n = scan.nextInt();
        while(!(n > 1 && n < 1001)) {
            System.out.println("Error! Enter the number of philosophers from 2 to 1000:");
            n = scan.nextInt();
        }
        scan.close();
        return n;
    }

    private static boolean checkVariant(String variant) {
        String [] vars = {"1", "2", "3", "4"};
        for(String s: vars) {
            if(variant.equals(s)) {
                return false;
            }
        }
        return true;
    }
}
