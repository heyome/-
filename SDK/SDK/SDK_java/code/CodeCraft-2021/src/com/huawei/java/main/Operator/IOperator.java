package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.List;

public interface IOperator{
    void addServer(IServer server);
    void addVirtualMachine(IVirtualMachine vm);

    void buyServer(IServer server, int serverId);
    void migrateToServer(int serverId, int vmId);
    void addVMtoServer(int serverId,String vmModel,int vmId);
    void deleteVMFromServer(int serverId, int vmId);

}
