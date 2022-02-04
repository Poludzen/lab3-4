package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input hours");
        int hours = scanner.nextInt();
        System.out.println("Input minutes");
        int minutes = scanner.nextInt();
        System.out.println("Input seconds");
        int seconds = scanner.nextInt();
        Timer timer = new Timer(hours, minutes, seconds);
        timer.run();
    }
}
