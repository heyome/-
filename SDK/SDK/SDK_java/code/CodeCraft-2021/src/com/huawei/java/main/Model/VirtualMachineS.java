package com.huawei.java.main.Model;

public class VirtualMachineS implements IVirtualMachine{

    private String model;
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
        this.position = server.selectNode(this);
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

    @Override
    public void resetPosition() {
        this.position = null;
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
    public int getId() {
        return id;
    }

    @Override
    public int getPositionId() {
        return this.position.getId();
    }

    @Override
    public String getPositionName() {
        return this.position.getName();
    }

    @Override
    public IVirtualMachine clone() {
        return new VirtualMachineS(this.model, this.cpuCores, this.ram);
    }


}
