package com.company;

public class Timer extends Thread {
    int timer;

    public Timer(int hours, int minutes, int seconds) {
        timer = hours * 3600 + minutes * 60 + seconds;
    }

    @Override
    //every second decreases time count by one
    public void run() {
        while (timer > -1) {
            try {
                printTime();
                sleep(1000);
                timer--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //converts time to timer format
    void printTime(){
        int hours = timer/3600;
        int minutes = (timer%3600)/60;
        int seconds = timer%60;
        System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);
    }
}
