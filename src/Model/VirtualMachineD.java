package Model;

public class VirtualMachineD implements IVirtualMachine{

    private static String model;
    private int cpuCores;
    private int ram;
    private int ID;
    private Node positionA = null;
    private Node positionB = null;
    private boolean ifS = false;

    public VirtualMachineD(String model, int cpuCores, int ram, int ID) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.ID = ID;
    }

    @Override
    public void setPosition(IServer server) {
        this.positionA = server.getA();
        this.positionB = server.getB();
        server.addVirtualMachine(this);
    }
}
