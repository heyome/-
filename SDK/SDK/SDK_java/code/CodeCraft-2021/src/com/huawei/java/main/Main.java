package com.huawei.java.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: read standard input
        // TODO: process
        // TODO: write standard output
        // TODO: System.out.flush()

        //找path
        String path = "path";
        BufferedReader reader = null;
        try {
            String strCurrentLine;
            reader = new BufferedReader(new FileReader(path));
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}