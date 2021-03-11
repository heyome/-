package Model;

public class VirtualMachineFactory {

    public IVirtualMachine getVirtualMachine(String model, int cpuCores, int ram, int ID, boolean ifS) {
        if (ifS) {
            return new VirtualMachineS(model,cpuCores,ram,ID);
        }
        else {
            return new VirtualMachineD(model,cpuCores,ram,ID);
        }
    }


}
