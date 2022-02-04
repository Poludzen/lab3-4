package com.company;

import java.util.concurrent.Semaphore;

public class Philosopher_1 extends Thread {
    static int MAX;
    static Semaphore[] fork;
    int number;

    public Philosopher_1 (int nr) {
        number = nr;
    }

    public void run() {
        while(true) {
            // thinking
            System.out.println("I am thinking " + number);
            try {
                Thread.sleep((long)(7000 * Math.random()));
            } catch (InterruptedException e){}
            fork[number].acquireUninterruptibly(); // interception of the left fork
            fork[(number +1)%MAX].acquireUninterruptibly(); // interception of the right fork
            // food
            System.out.println ("Starts to eat " + number);
            try {
                Thread.sleep((long)(5000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println ("Finished eating "+ number) ;
            fork[number].release(); // release left fork
            fork[(number +1)%MAX].release(); // release right fork
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < MAX; i++) {
            fork[i] = new Semaphore(1) ;
        }
        for ( int i =0; i < MAX; i++) {
            new Philosopher_1(i).start();
        }
    }
}
