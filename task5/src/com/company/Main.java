package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[][] set;

    public static void main(String[] args) {
        ArrayList<JuliaSet> juliaSets = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Input resolution");
        int resolution = in.nextInt();
        set = new int[resolution][resolution];
        System.out.println("Input amount of threads");
        int amount = in.nextInt();
        System.out.println("Input 1 for red color, 2 for green and 3 for blue");
        int color = in.nextInt();
        System.out.println("Input cRe");
        double cRe = in.nextDouble();
        System.out.println("Input cIm");
        double cIm = in.nextDouble();
        System.out.println("Input path to picture");
        String path = in.next();


        for (int i = 0; i < amount; i++) {
            JuliaSet thread;
            if (i == amount - 1) {
                thread = new JuliaSet(resolution / amount * i, resolution, cRe, cIm);
            } else {
                thread = new JuliaSet(resolution / amount * i, resolution / amount * (i + 1), cRe, cIm);
            }
            juliaSets.add(thread);
        }
        long startTime = System.currentTimeMillis();
        for (JuliaSet thread : juliaSets) {
            thread.start();
        }

        for (JuliaSet thread : juliaSets) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("Calculations completed in %d milliseconds", (endTime - startTime));
        BufferedImage img = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < resolution; i++) {
            for (int j = 0; j < resolution; j++) {
                int k = set[i][j];
                float level;
                if (k < 100) {
                    level = (float) k / 100;
                } else {
                    level = 0;
                }
                float redlevel = 0;
                float greenlevel = 0;
                float bluelevel = 0;
                switch (color) { //change color of picture
                    case 1:
                        redlevel = level;
                        break;
                    case 2:
                        greenlevel = level;
                        break;
                    case 3:
                        bluelevel = level;
                        break;
                }
                Color c = new Color(redlevel, greenlevel, bluelevel);
                img.setRGB(i, j, c.getRGB());
            }
        }
        try {
            ImageIO.write(img, "PNG", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
