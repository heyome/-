package com.huawei.java.main.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private boolean A;
    private int id = -1;
    private int cpuCores = 0;
    private int ram = 0;
    private int occupiedCPU = 0;
    private int occupiedRAM = 0;
    private Map<Integer,IVirtualMachine> vms = new HashMap<Integer,IVirtualMachine>();


    //constructor True: 是服务器的A  False:是服务器的B节点
    public Node(boolean A) {
        this.A = A;
    }


    //Set values for node
    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    //getter
    public int getOccupiedCPU() {
        return occupiedCPU;
    }

    public int getOccupiedRAM() {
        return occupiedRAM;
    }

    //设定节点ID，一般为服务器ID
    public void setID(int id) {
        this.id = id;
    }

    //节点上增加虚拟机
    public void addVirtualMachine(IVirtualMachine vm) {
        if (vm.ifS()) {
            if (this.occupiedCPU + vm.getCpuCores() <= this.cpuCores && this.occupiedRAM + vm.getRam() <= this.ram) {
                this.occupiedRAM += vm.getRam();
                this.occupiedCPU += vm.getCpuCores();
            } else {
                throw new IllegalArgumentException("This node is full!");
            }
        } else {
            if (this.occupiedCPU + vm.getCpuCores()/2 <= this.cpuCores && this.occupiedRAM + vm.getRam()/2 <= this.ram) {
                this.occupiedRAM += vm.getRam()/2;
                this.occupiedCPU += vm.getCpuCores()/2;
            } else {
                throw new IllegalArgumentException("This node is full!");
            }
        }
        this.vms.put(vm.getId(),vm);
    }

    //getter
    public Map<Integer, IVirtualMachine> getVms() {
        return vms;
    }

    //节点上删除虚拟机
    public void deleteVM(int vmId) {
        this.vms.get(vmId).resetPosition();
        IVirtualMachine vm = this.vms.get(vmId);
        if (vm.ifS()) {
            this.occupiedRAM -= vm.getRam();
            this.occupiedCPU -= vm.getCpuCores();
        } else {
            this.occupiedRAM -= vm.getRam()/2;
            this.occupiedCPU -= vm.getCpuCores()/2;
        }
        this.vms.remove(vmId);
    }

    public String getName() {
        if (this.A) {
            return "A";
        } else {
            return "B";
        }
    }

    public int getId() {
        return id;
    }

    public boolean CanAdd(int r,int c) {
        return occupiedRAM + r < ram && occupiedCPU + c < cpuCores;
    }
}
