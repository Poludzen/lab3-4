package com.company;

import java.util.Random;

public class MonteCarlo extends Thread {
    private static int pointsAmount;
    private int result;
    private int beginCord;
    private int endCord;
    private int radius;

    public MonteCarlo(int beginCord, int endCord, int radius) {
        this.beginCord = beginCord;
        this.endCord = endCord;
        this.radius = radius;
    }

    public static void setPointsAmount(int pointsAmount) {
        MonteCarlo.pointsAmount = pointsAmount;
    }

    public int getResult() {
        return result;
    }

    //generate coordinates of two points and check if these points are in the circle
    //if so, increment resul
    public void run() {
        Random rand = new Random();
        for (int i = 0; i <= pointsAmount; i++) {
            int pointX = rand.nextInt(endCord - beginCord) + beginCord;
            int pointY = rand.nextInt(radius - 1) + 1;
            if (Math.sqrt((pointX - radius) * (pointX - radius) + (pointY - radius) * (pointY - radius)) <= radius/2) {
                result++;
            }
        }
    }
}
