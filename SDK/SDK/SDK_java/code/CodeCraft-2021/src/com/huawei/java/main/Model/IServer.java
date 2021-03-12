package com.huawei.java.main.Model;

public interface IServer {

    //Getters

    String getModel();

    int getCpuCores();

    int getRam();

    int getCost();

    int getDailyCost();

    //get nodes(节点）
    Node getA();

    Node getB();

    //Add virtualMachine to Nodes
    void addVirtualMachine(IVirtualMachine vm);

    Node selectNode();

    void setId(int id);
}
