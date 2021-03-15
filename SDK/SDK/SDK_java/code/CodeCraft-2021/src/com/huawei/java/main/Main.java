package com.huawei.java.main;

import com.huawei.java.main.Operator.IOperator;
import com.huawei.java.main.Operator.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // TODO: read standard input
        // TODO: process
        // TODO: write standard output
        // TODO: System.out.flush()


        String path = "C:\\Users\\User\\Desktop\\hwrt\\SDK\\SDK\\SDK_java\\code\\CodeCraft-2021\\src\\com\\huawei\\java\\main\\training-data\\training-1.txt";
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