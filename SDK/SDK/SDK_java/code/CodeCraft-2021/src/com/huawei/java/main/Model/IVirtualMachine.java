package com.huawei.java.main.Model;

public interface IVirtualMachine {

    //设置虚拟机位置
    void setPosition(IServer server);

    //判断是单节点虚拟机还是双节点虚拟机
    boolean ifS();

    //设定虚拟机ID
    void setId(int id);

    //getter
    String getModel();

    //重置虚拟机节点
    void resetPosition();

    //Getter
    int getCpuCores();

    int getRam();

    int getId();
}
