package com.company;

public class NegativeImage extends Thread {
    int[] pixels;
    boolean bwPhoto;

    public NegativeImage(int[] pixels, boolean flag) {
        this.pixels = pixels;
        bwPhoto = flag;
    }

    @Override
    public void run(){
        if(bwPhoto){
            convertToBlackAndWhite();
        }
        convertToNegative();
    }

    public int[] getResult() {
        return pixels;
    }

    public void convertToNegative() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = ~pixels[i] & 0xFFFFFF; //invert color
        }
    }

    public void convertToBlackAndWhite() {
        for (int i = 0; i < pixels.length; i++) {
            int intensity = (getRed( pixels[i]) + getGreen(pixels[i]) + getBlue( pixels[i])) / 3; //find average intensity
            pixels[i] = intensity + (intensity << 8) + (intensity << 16); //shift intensity
        }
    }

    //get byte for red color
    public int getRed(int color) {
        return color >> 16;
    }

    //get byte for red color
    public int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }

    //get byte for blue color
    public int getBlue(int color) {
        return color & 0xFF;
    }
}
