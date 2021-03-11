package Model;

public class Server implements IServer{
    private String model;
    private int cpuCores;
    private int ram;
    private int cost;
    private int dailyCost;
    private int occupiedCPUA = 0;
    private int occupiedCPUB = 0;

    public Server(String model,int cpuCores,int ram, int cost, int dailyCost) {
        this.model = model;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.cost = cost;
        this.dailyCost = dailyCost;
    }


    //Getters
    public String getModel() {
        return model;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public int getRam() {
        return ram;
    }

    public int getCost() {
        return cost;
    }

    public int getDailyCost() {
        return dailyCost;
    }

    //Change occupied CPU volume
    public void setOccupiedCPUA(int occupiedCPUA) {
        this.occupiedCPUA = occupiedCPUA;
    }

    public void setOccupiedCPUB(int occupiedCPUB) {
        this.occupiedCPUB = occupiedCPUB;
    }

    //Change occupied
}
