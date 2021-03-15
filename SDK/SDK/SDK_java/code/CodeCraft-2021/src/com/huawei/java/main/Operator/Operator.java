package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Operator implements IOperator{
    private List<IServer> servers = new ArrayList<IServer>();
    private HashMap<String,IVirtualMachine> vms = new HashMap<String, IVirtualMachine>();
    private HashMap<Integer,IServer> boughtServer = new HashMap<Integer,IServer>();
    private HashMap<Integer,IVirtualMachine> assignedVM = new HashMap<Integer,IVirtualMachine>();

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
        server.setId(serverId);
        this.boughtServer.put(serverId,server);
    }

    @Override
    public void migrateToServer(int serverIdFrom, int serverIdTo, int vmId) {
        this.deleteVMFromServer(serverIdFrom,vmId);
        IServer server = this.boughtServer.get(serverIdTo);
        IVirtualMachine vm = this.assignedVM.get(vmId);
        vm.setPosition(server);
        server.addVirtualMachine(vm);
    }

    @Override
    public void addVMtoServer(int serverId,String vmModel,int vmId) {
        IServer server = this.boughtServer.get(serverId);
        IVirtualMachine vm = this.vms.get(vmModel);
        vm.setId(vmId);
        vm.setPosition(server);
        server.addVirtualMachine(vm);
        this.assignedVM.put(vmId,vm);
    }

    @Override
    public void deleteVMFromServer(int serverId, int vmId) {
        IVirtualMachine vm = this.assignedVM.get(vmId);
        IServer server = this.boughtServer.get(serverId);
        server.deleteVM(vmId);
        vm.resetPosition();
    }

    @Override
    public String[] splitString(String s) {
        s = s.substring(1,s.length()-1);
        String[] ss = s.split(",");
        return ss;
    }
}
