package Model;

public class Node {

    private boolean A;
    private int id;
    private int cpuCores = 0;
    private int ram = 0;
    private int occupiedCPU = 0;
    private int occupiedRAM = 0;


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

    public void setOccupiedCPU(int occupiedCPU) {
        this.occupiedCPU = occupiedCPU;
    }

    public void setOccupiedRAM(int occupiedRAM) {
        this.occupiedRAM = occupiedRAM;
    }

    public void setID(int id) {
        this.id = id;
    }
}
