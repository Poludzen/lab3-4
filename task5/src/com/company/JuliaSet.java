package com.company;

public class JuliaSet extends Thread {
    int begin, end;
    double cRe;
    double cIm;

    public JuliaSet(int begin, int end, double cRe, double cIm){
        this.begin = begin;
        this.end = end;
        this.cRe = cRe;
        this.cIm = cIm;
    }
    //determines shape of Julia set
    public void run() {
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < Main.set.length; j++) {
                double zr = (4.0 * i - 2 * Main.set.length) / Main.set.length;
                double zi = (4.0 * j - 2 * Main.set.length) / Main.set.length;
                int k = 0;

                while (k < 100 && zr * zr + zi * zi < 4.0) {
                    double newr = zr * zr - zi * zi + cRe;
                    double newi = 2 * zr * zi + cIm;
                    zr = newr;
                    zi = newi;
                    k++;
                }
                Main.set[i][j] = k;
            }
        }
    }
}
