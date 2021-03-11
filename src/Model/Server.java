package Model;

public class Server implements IServer{
    private static String model;
    private int cpuCores;
    private int ram;
    private int cost;
    private int dailyCost;
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



}
