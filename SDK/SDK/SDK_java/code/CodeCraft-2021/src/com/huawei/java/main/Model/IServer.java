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

    //给单节点虚拟机选择节点
    Node selectNode(IVirtualMachine vm);

    //设定服务器ID
    void setId(int id);

    //删除虚拟机
    void deleteVM(int vmId);
}
