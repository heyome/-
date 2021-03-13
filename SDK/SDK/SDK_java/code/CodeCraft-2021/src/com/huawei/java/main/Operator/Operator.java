package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operator implements IOperator{
    List<IServer> servers = new ArrayList<IServer>();
    HashMap<String,IVirtualMachine> vms = new HashMap<String, IVirtualMachine>();
    HashMap<Integer,IServer> boughtServer = new HashMap<Integer,IServer>();
    HashMap<Integer,IVirtualMachine> assignedVM = new HashMap<Integer,IVirtualMachine>();

    @Override
    public void addServer(IServer server) {
        this.servers.add(server);
    }

    @Override
    public void addVirtualMachine(IVirtualMachine vm) {
        this.vms.put(vm.getModel(),vm);
    }

    @Override
    public void buyServer(IServer server, int serverId) {
        this.boughtServer.put(serverId,server);
    }

    @Override
    public void migrateToServer(int serverId, int vmId) {

    }

    @Override
    public void addVMtoServer(int serverId,String vmModel,int vmId) {
        IServer server = this.boughtServer.get(serverId);
        IVirtualMachine vm = this.vms.get(vmModel);
        vm.setId(vmId);
        vm.setPosition(server);
        server.addVirtualMachine(vm);
    }

    @Override
    public void deleteVMFromServer(int serverId, int vmId) {
    }
}
