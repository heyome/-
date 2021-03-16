package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.ArrayList;
import java.util.List;

public interface IOperator{
    //添加可购买服务器到服务器列表
    void addServer(IServer server);
    //添加可用虚拟机到虚拟机额列表
    void addVirtualMachine(IVirtualMachine vm);

    //购买服务器
    void buyServer(IServer server, int serverId);
    //迁移虚拟机到另一个服务器
    void migrateToServer(int serverIdFrom, int serverIdTo, int vmId);
    //添加虚拟机到服务器上
    void addVMtoServer(int serverId,String vmModel,int vmId);
    //从服务器删除虚拟机
    void deleteVMFromServer(int serverId, int vmId);


    //输入处理
    String[] splitString(String s);

    void setRecords(ArrayList<String>[] records);
}
