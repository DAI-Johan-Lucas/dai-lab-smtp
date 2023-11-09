package main.java.ch.heig.dai.lab.prankmail;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Controller ctrl = new Controller(args[0], args[1]);
        ctrl.generatePrankGroups(Integer.parseInt(args[2]));


    }
}