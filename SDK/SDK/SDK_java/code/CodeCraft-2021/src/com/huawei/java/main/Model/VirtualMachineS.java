package com.huawei.java.main.Model;

public class VirtualMachineS implements IVirtualMachine{

    private static String model;
    private int cpuCores;
    private int ram;
    private int id = -1;
    private Node position = null;

    public VirtualMachineS(String model, int cpuCores, int ram){
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
    }

    @Override
    public void setPosition(IServer server) {
        this.position = server.selectNode();
        server.addVirtualMachine(this);
    }

    @Override
    public boolean ifS() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getModel() {
        return this.model;
    }
}
