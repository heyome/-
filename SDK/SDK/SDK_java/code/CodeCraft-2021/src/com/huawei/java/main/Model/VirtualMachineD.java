package com.huawei.java.main.Model;

public class VirtualMachineD implements IVirtualMachine{

    private String model;
    private int cpuCores;
    private int ram;
    private int id = -1;
    private Node positionA = null;
    private Node positionB = null;
    private boolean ifS = false;

    public VirtualMachineD(String model, int cpuCores, int ram) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
    }

    @Override
    public void setPosition(IServer server) {
        this.positionA = server.getA();
        this.positionB = server.getB();
    }

    @Override
    public boolean ifS() {
        return false;
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
        this.positionA = null;
        this.positionB = null;
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
        return this.positionA.getId();
    }

    @Override
    public String getPositionName() {
        return "";
    }

    @Override
    public IVirtualMachine clone() {
        return new VirtualMachineD(this.model, this.cpuCores, this.ram);
    }

}
