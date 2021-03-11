package Model;

public class VirtualMachineD implements IVirtualMachine{

    private static String model;
    private int cpuCores;
    private int ram;
    private int ID;
    private Node PositionA = null;
    private Node PositionB = null;

    public VirtualMachineD(String model, int cpuCores, int ram, int ID) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.ID = ID;
    }

}
