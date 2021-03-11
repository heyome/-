package Model;

public class VirtualMachineS implements IVirtualMachine{

    private static String model;
    private int cpuCores;
    private int ram;
    private int ID;
    private Node position = null;

    public VirtualMachineS(String model, int cpuCores, int ram, int ID){
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.ID = ID;
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
}
