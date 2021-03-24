package com.huawei.java.main.Model;

public class Server implements IServer{
    private static String model;
    private int cpuCores;
    private int ram;
    private int cost;
    private int dailyCost;
    private int id = -1;
    private Node a = new Node(true);
    private Node b = new Node(false);

    public Server(String model,int cpuCores,int ram, int cost, int dailyCost) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.cost = cost;
        this.dailyCost = dailyCost;
        this.a.setCpuCores(cpuCores/2);
        this.b.setCpuCores(cpuCores/2);
        this.a.setRam(ram/2);
        this.b.setRam(ram/2);
    }


    //Getters
    @Override
    public String getModel() {
        return model;
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
    public int getCost() {
        return cost;
    }

    @Override
    public int getDailyCost() {
        return dailyCost;
    }

    @Override
    public Node getA() {
        return a;
    }

    @Override
    public Node getB() {
        return b;
    }

    @Override
    //Add virtualMachine to Nodes
    public void addVirtualMachine(IVirtualMachine vm) {
        if (vm.ifS()) {
            if (this.a.CanAdd(vm.getRam(), vm.getCpuCores())) {
                this.a.addVirtualMachine(vm);
            } else {
                this.b.addVirtualMachine(vm);
            }
        }
        else {
            this.a.addVirtualMachine(vm);
            this.b.addVirtualMachine(vm);
        }
    }

    @Override
    public Node selectNode(IVirtualMachine vm) {
        if (vm.getRam() >= vm.getCpuCores()){
            if (this.a.getOccupiedRAM() <= this.b.getOccupiedRAM()) {
                return this.a;
            } else {
                return this.b;
            }
        } else {
            if (this.a.getOccupiedCPU() <= this.b.getOccupiedCPU()) {
                return this.a;
            } else {
                return this.b;
            }
        }

    }

    @Override
    public void setId(int id) {
        this.id = id;
        this.a.setID(id);
        this.b.setID(id);
    }

    @Override
    public void deleteVM(int vmId) {
        if (this.a.getVms().containsKey(vmId)) {
            this.a.deleteVM(vmId);
        }
        if (this.b.getVms().containsKey(vmId)) {
            this.b.deleteVM(vmId);
        }
    }

    @Override
    public IServer clone() {
        return new Server(this.model,this.cpuCores,this.ram, this.cost, this.dailyCost);
    }

    @Override
    public boolean CanAdd(IVirtualMachine vm){
        if (vm.ifS()) {
            return this.a.CanAdd(vm.getRam(),vm.getCpuCores()) || this.b.CanAdd(vm.getRam(),vm.getCpuCores());
        } else {
            return this.a.CanAdd(vm.getRam()/2,vm.getCpuCores()/2) && this.b.CanAdd(vm.getRam()/2,vm.getCpuCores()/2);
        }
    }
}
