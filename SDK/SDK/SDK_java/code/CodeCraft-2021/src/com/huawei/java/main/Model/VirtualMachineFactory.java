package Model;

public class VirtualMachineFactory {


    public IVirtualMachine getVirtualMachine(String model, int cpuCores, int ram, boolean ifS) {
        if (ifS) {
            return new VirtualMachineS(model,cpuCores,ram);
        }
        else {
            return new VirtualMachineD(model,cpuCores,ram);
        }
    }


}
