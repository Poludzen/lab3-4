package com.company;

import com.sun.imageio.plugins.jpeg.*;
import com.sun.imageio.plugins.png.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<NegativeImage> negativeImages = new ArrayList<>();

        System.out.println("Input amount of threads");
        int amount = in.nextInt();
        System.out.println("Input path to the image");
        String path = in.next();
        System.out.println("Input true if you want d&w negative, input false otherwise");
        Boolean flag = in.nextBoolean();
        System.out.println(flag);

        try {
            ImageReader r = new JPEGImageReader(new JPEGImageReaderSpi());
            r.setInput(new FileImageInputStream(new File(path)));
            BufferedImage img = r.read(0, new ImageReadParam());
            ((FileImageInputStream) r.getInput()).close();
            int height = img.getHeight();
            int width = img.getWidth();
            int[] pixels = new int[height * width];
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    pixels[i * width + j] = img.getRGB(j, i) & 0xFFFFFF; // we record only 3 first bytes
            for (int i = 0; i < amount; i++) {
                NegativeImage thread;
                if (i == amount - 1) {
                    thread = new NegativeImage(Arrays.copyOfRange(pixels, pixels.length/amount * i, pixels.length-1),
                            flag);
                }
                else {
                    thread = new NegativeImage(Arrays.copyOfRange(pixels, pixels.length/amount * i, pixels.length/amount * (i+1)),
                            flag);
                }
                negativeImages.add(thread);
            }
            for (NegativeImage thread: negativeImages) {
                thread.start();
            }
            for (NegativeImage thread: negativeImages) {
                    thread.join();
            }
            ArrayList<Integer> outpixels = new ArrayList<>();
            for (NegativeImage thread: negativeImages) {
                for (int pixel: thread.getResult()) {
                    outpixels.add(pixel);
                }
            }
            outpixels.add(1);
            System.out.println("Input true if you want to save picture as png, input false if you want to save picture as jpg");
            boolean formatFlag = in.nextBoolean();
            ImageWriter writer;
            if(formatFlag) {
                writer = new PNGImageWriter(new PNGImageWriterSpi());
            }else {
                writer = new JPEGImageWriter(new JPEGImageWriterSpi());
            }
            writer.setOutput(new FileImageOutputStream(new File(path.replace(".", "new."))));
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    bi.setRGB(j, i, outpixels.get(i * width + j));
            writer.write(bi);
            ((FileImageOutputStream) writer.getOutput()).close();
        } catch (IOException |  InterruptedException e) {
            e.printStackTrace();
        }
    }
}

