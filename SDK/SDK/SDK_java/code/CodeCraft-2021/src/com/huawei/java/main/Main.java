package com.huawei.java.main;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;
import com.huawei.java.main.Model.Server;
import com.huawei.java.main.Model.VirtualMachineFactory;
import com.huawei.java.main.Operator.IOperator;
import com.huawei.java.main.Operator.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // TODO: read standard input
        // TODO: process
        // TODO: write standard output
        // TODO: System.out.flush()


        String path = "C:\\Users\\User\\Desktop\\hwrt\\SDK\\SDK\\SDK_java\\code\\CodeCraft-2021\\src\\com\\huawei\\java\\main\\training-data\\training-1.txt";
        BufferedReader reader = null;
        try {
            //reader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new FileReader(path));
            String serverModels = reader.readLine();
            IOperator operator = new Operator();

            int iterations = 0;
            String s = reader.readLine();
            while (s.contains("(")) {
                String[] ss = operator.splitString(s);
                IServer server = new Server(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]),
                        Integer.parseInt(ss[3]),Integer.parseInt(ss[4]));
                operator.addServer(server);
                s = reader.readLine();
                iterations++;
            }

            s = reader.readLine();
            VirtualMachineFactory vmFac = new VirtualMachineFactory();
            iterations = 0;
            while (s.contains("(")) {
                String[] ss = operator.splitString(s);
                IVirtualMachine vm = vmFac.getVirtualMachine(ss[0],Integer.parseInt(ss[1]),
                        Integer.parseInt(ss[2]), Integer.parseInt(ss[3]) == 0);
                operator.addVirtualMachine(vm);

                iterations++;
                s = reader.readLine();
            }


            HashMap<Integer,String[]> records = new HashMap<Integer,String[]>();
            iterations = 0;
            s = reader.readLine();
            while (s != null) {
                String[] record = new String[Integer.parseInt(s)];
                s = reader.readLine();
                int iter = 0;
                while (s.contains("(")) {
                    record[iter] = s;
                    iter++;
                    s = reader.readLine();
                    if (s == null) {
                        break;
                    }
                }
                records.put(iterations,record);
                iterations++;
            }

            operator.setRecords(records);

            ArrayList<String> output = operator.output();
            for (String str : output) {
                System.out.println(str);
            }

            //System.out.flush();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}