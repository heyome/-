package Model;

public interface IServer {

    //Getters

    String getModel();

    int getCpuCores();

    int getRam();

    int getCost();

    int getDailyCost();
    Node getA();

    Node getB();

    //Add virtualMachine to Nodes
    void addVirtualMachine(IVirtualMachine vm);

    Node selectNode();

    void setId(int id);
}
