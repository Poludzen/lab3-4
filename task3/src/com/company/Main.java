package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<MonteCarlo> monteCarlos = new ArrayList<>();
        double result = 0;

        System.out.println("Input a radius");
        int radius = in.nextInt();
        System.out.println("Input amount of threads");
        int amount = in.nextInt();
        System.out.println("Input amount of points");
        int points = in.nextInt();
        MonteCarlo.setPointsAmount(points);

        for (int i = 0; i < amount; i++) { //create threads
            MonteCarlo thread;
            if (i == amount - 1) {
                thread = new MonteCarlo(radius / amount * i + 1, radius, radius);
            }
            else {
                 thread = new MonteCarlo(radius / amount * i + 1, radius / amount * (i + 1), radius);
            }
            monteCarlos.add(thread);
        }
        for (MonteCarlo thread: monteCarlos) { //start threads
            thread.start();
        }
        for (MonteCarlo thread: monteCarlos) {// join threads
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (MonteCarlo thread: monteCarlos) {
            result += thread.getResult();
        }
        result = result*Math.pow(radius, 2)/points;
        System.out.println("Computed result: " + result);
        System.out.println("Actual result: " + Math.PI * Math.pow(radius, 2));
    }
}

