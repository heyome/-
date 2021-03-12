package com.huawei.java.main.Model;

public class Server implements IServer{
    private static String model;
    private int cpuCores;
    private int ram;
    private int cost;
    private int dailyCost;
    private int id = -1;
    private Node a = new Node(true);
    private Node b = new Node(false);

    public Server(String model,int cpuCores,int ram, int cost, int dailyCost) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.cost = cost;
        this.dailyCost = dailyCost;
        this.a.setCpuCores(cpuCores/2);
        this.b.setCpuCores(cpuCores/2);
        this.a.setRam(ram/2);
        this.b.setRam(ram/2);
    }


    //Getters
    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getCpuCores() {
        return cpuCores;
    }

    @Override
    public int getRam() {
        return ram;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getDailyCost() {
        return dailyCost;
    }

    @Override
    public Node getA() {
        return a;
    }

    @Override
    public Node getB() {
        return b;
    }

    @Override
    //Add virtualMachine to Nodes
    public void addVirtualMachine(IVirtualMachine vm) {
        //TODO
    }

    @Override
    public Node selectNode() {
        //TODO
        return null;

    }

    @Override
    public void setId(int id) {
        this.id = id;
        this.a.setID(id);
        this.b.setID(id);
    }
}
