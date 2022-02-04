package com.company;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher_3 extends Thread{
    static int MAX;
    static Semaphore[] fork;
    int number;
    Random rand;

    public Philosopher_3(int nr){
        number = nr;
        rand = new Random(number);
    }

    public void run(){
        while(true){
            // thinking
            System.out.println ( "I am thinking  " + number);
            try {
                Thread.sleep((long)(5000 * Math.random()));
            } catch (InterruptedException e) {}
            int side = rand.nextInt(2);
            boolean pickedTwoForks = false;
            do {
                if (side == 0) {
                    fork[number].acquireUninterruptibly();
                    if(!(fork[(number +1)%MAX].tryAcquire())){
                        fork[number].release();
                    } else {
                        pickedTwoForks = true;
                    }
                } else {
                    fork[(number +1)%MAX].acquireUninterruptibly();
                    if (!(fork[number].tryAcquire())){
                        fork[(number +1)%MAX].release();
                    } else {
                        pickedTwoForks = true;
                    }
                }
            } while (!pickedTwoForks);
            System.out.println ( "Starts to eat "+ number);
            try {
                Thread.sleep((long)(3000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println ( "Finished eating "+ number);
            fork[number].release();
            fork[(number +1)%MAX].release();
        }
    }

    public static void main(String [] args){
        for(int i = 0; i < MAX; i++) {
            fork[i] = new Semaphore(1);
        }
        for(int i = 0; i < MAX; i++) {
            new Philosopher_3(i).start();
        }
    }
}
