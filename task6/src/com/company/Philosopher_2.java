package com.company;

import java.util.concurrent.Semaphore;


public class Philosopher_2 extends Thread {
    static int MAX;
    static Semaphore[] fork;
    int number;

    public Philosopher_2(int nr) {
        number = nr;
    }

    public void run(){
        while(true) {
            // thinking
            System.out.println ( "I am thinking  " + number) ;
            try {
                Thread.sleep ((long)(5000 * Math.random())) ;
            } catch (InterruptedException e) {}
            if (number == 0) {
                fork[(number +1)%MAX].acquireUninterruptibly();
                fork[number].acquireUninterruptibly();
            } else {
                fork[number].acquireUninterruptibly();
                fork[(number +1)%MAX].acquireUninterruptibly();
            }
            // food
            System.out.println ( "Starts to eat "+ number);
            try {
                Thread.sleep ((long)(3000 * Math.random()));
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
            System.out.println ( "Finished eating "+ number) ;
            fork[number].release();
            fork[(number +1)%MAX].release();
        }
    }

    public static void main ( String [] args ) {
        for(int i = 0; i < MAX; i++) {
            fork[i] = new Semaphore(1);
        }
        for(int i =0; i < MAX; i++) {
            new Philosopher_2(i).start();
        }
    }
}
