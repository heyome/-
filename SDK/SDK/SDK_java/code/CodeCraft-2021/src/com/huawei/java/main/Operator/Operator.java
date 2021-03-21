package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Operator implements IOperator{
    
    private List<IServer> servers = new ArrayList<IServer>();
    private HashMap<String,IVirtualMachine> vms = new HashMap<String, IVirtualMachine>();
    
    private HashMap<Integer,IServer> boughtServer = new HashMap<Integer,IServer>();
    private HashMap<Integer,IVirtualMachine> assignedVM = new HashMap<Integer,IVirtualMachine>();
    private ArrayList<IVirtualMachine> VMAdds = new ArrayList<IVirtualMachine>();
    
    private HashMap<Integer,String[]> records;
    private ArrayList<Integer> addsOnDay = new ArrayList<Integer>();

    @Override
    public void addServer(IServer server) {
        this.servers.add(server);
    }

    @Override
    public void addVirtualMachine(IVirtualMachine vm) {
        this.vms.put(vm.getModel(),vm);
    }

    @Override
    public void buyServer(IServer server, int serverId) {
        server.setId(serverId);
        this.boughtServer.put(serverId,server);
    }

    @Override
    public void migrateToServer(int serverIdFrom, int serverIdTo, int vmId) {
        this.deleteVMFromServer(serverIdFrom,vmId);
        IServer server = this.boughtServer.get(serverIdTo);
        IVirtualMachine vm = this.assignedVM.get(vmId);
        vm.setPosition(server);
        server.addVirtualMachine(vm);
    }

    @Override
    public void addVMtoServer(int serverId,int vmId) {
        IServer server = this.boughtServer.get(serverId);
        IVirtualMachine vm = this.assignedVM.get(vmId);
        vm.setPosition(server);
        server.addVirtualMachine(vm);
    }

    @Override
    public void deleteVMFromServer(int serverId, int vmId) {
        IVirtualMachine vm = this.assignedVM.get(vmId);
        IServer server = this.boughtServer.get(serverId);
        server.deleteVM(vmId);
        vm.resetPosition();
    }

    @Override
    public String[] splitString(String s) {
        s = s.substring(1,s.length()-1);
        String[] ss = s.split(", ");
        return ss;
    }

    @Override
    public void setRecords(HashMap<Integer,String[]> records) {
        this.records = records;
    }

    
    @Override
    public void assignedVM() {

        for (Integer key: records.keySet()) {
            String[] vmsToAssign = records.get(key);
            for (int i = 0; i < vmsToAssign.length; i++) {
                if (vmsToAssign[0] == "add") {
                    String vmModel = vmsToAssign[1];
                    IVirtualMachine vm = vms.get(vmModel);
                    Integer vmId = Integer.parseInt(vmsToAssign[2]);
                    this.assignedVM.put(vmId,vm);
                    this.VMAdds.add(vm);
                }
            }
            addsOnDay.add(vmsToAssign.length);
        }
    }

    @Override
    public int[][] setGenesForAdds(int geneNumber) {
        Random rand = new Random(1000);
        int vmNumber = this.VMAdds.size();
        int[][] result = new int[geneNumber][vmNumber];
        int day = 0;

        for (int i = 0; i<geneNumber; i++) {
            for (int j = 0; j<vmNumber;j++) {
                if (vmNumber == addsOnDay.get(day) -1) {
                    result[i][j] = 1;
                }
                else {
                    result[i][j] = rand.nextInt() % 2;
                }
            }
        }
        return result;
    }

    @Override
    public int fitness(int[] genes) {

        ArrayList<IServer> serversForGenes = new ArrayList<IServer>();

        int ramNeededA = 0;
        int cpuNeededA = 0;
        int ramNeededB = 0;
        int cpuNeededB = 0;
        for (int i = 0; i < genes.length; i++) {
            IVirtualMachine vm = this.VMAdds.get(i);
            if (vm.ifS()) {
                if (cpuNeededA <= cpuNeededB){
                    cpuNeededA += vm.getCpuCores();
                    ramNeededA += vm.getRam();
                } else {
                    cpuNeededB += vm.getCpuCores();
                    ramNeededB += vm.getRam();
                }
            }

            if (genes[i] == 1) {

                int cost = 1000000000;
                IServer suitableServer = null;
                for (IServer server : servers) {
                    if (server.getCost() <= cost) {
                        if (server.getCpuCores()/2 >= cpuNeededA && server.getCpuCores()/2 >= cpuNeededB
                                && server.getRam()/2 >= ramNeededA && server.getRam()/2 >= ramNeededB) {
                            suitableServer = server;
                            cost = server.getCost();
                        }
                    }
                }
                if (suitableServer == null) {
                    return 2000000000;
                } else {
                    serversForGenes.add(suitableServer);
                }
            }
        }

        int total = 0;
        for (IServer server: serversForGenes) {
            total += server.getCost();
        }
        return  total;
    }

    @Override
    public void calculateOptimalBundle() {
        for (int i = 0; i < records.keySet().size();i++) {
            
        }
    }
}
