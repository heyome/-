package com.huawei.java.main.Operator;

import com.huawei.java.main.Model.IServer;
import com.huawei.java.main.Model.IVirtualMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Operator implements IOperator{

    //输入
    private List<IServer> servers = new ArrayList<IServer>();
    private HashMap<String,IVirtualMachine> vms = new HashMap<String, IVirtualMachine>();
    private HashMap<Integer,String[]> records;

    //读取
    private HashMap<Integer,IServer> boughtServer = new HashMap<Integer,IServer>();

    private HashMap<Integer,IVirtualMachine> assignedVM = new HashMap<Integer,IVirtualMachine>();

    private ArrayList<IVirtualMachine> VMAdds = new ArrayList<IVirtualMachine>();

    private ArrayList<Integer> addsOnDay = new ArrayList<Integer>();

    //最佳方案
    private int optimalScore = 2000000000;
    private ArrayList<IServer> optimalServers;
    private int[] optimalGene;
    private ArrayList<String> log = new ArrayList<String>();


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
                    IVirtualMachine vm = vms.get(vmModel).clone();
                    Integer vmId = Integer.parseInt(vmsToAssign[2]);
                    vm.setId(vmId);
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
    public ArrayList<IServer> fitness(int[] genes) {

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
                    return null;
                } else {
                    serversForGenes.add(suitableServer);
                }
            }
        }

        return  serversForGenes;
    }

    @Override
    public int fitnessScore(ArrayList<IServer> serversForGenes) {
        int total = 0;
        if (serversForGenes == null) {
            return 2000000000;
        }
        for (IServer server: serversForGenes) {
            total += server.getCost();
        }
        return  total;
    }

    @Override
    public int[][] select(int[][] genes, int geneNumberLeft) {
        int vmNumber = VMAdds.size();
        int[][] result = new int[geneNumberLeft][vmNumber];

        HashMap<Integer,Integer> fitnessForGenes = new HashMap<Integer,Integer>();

        for (int i = 0; i < genes.length; i++) {
            ArrayList<IServer> serversForGenes = this.fitness(genes[i]);
            int score = this.fitnessScore(serversForGenes);
            fitnessForGenes.put(i,score);
        }

        int[] smallestIndexes = this.findSmallestIndexes(fitnessForGenes,geneNumberLeft);

        for (int i = 0; i < smallestIndexes.length; i++) {
            for (int j = 0; j < vmNumber; j++) {
                int x = fitnessForGenes.get(smallestIndexes[i]);
                result[i][j] = genes[x][j];
            }
        }

        int optimalScoreInThisGeneration = fitnessForGenes.get(smallestIndexes[0]);
        if (optimalScoreInThisGeneration < this.optimalScore) {
            this.optimalScore = optimalScoreInThisGeneration;
            int smallestIndex = smallestIndexes[0];
            this.optimalGene = genes[smallestIndex].clone();
            this.optimalServers = this.fitness(genes[smallestIndex]);
        }

        return result;
    }

    @Override
    public int[] findSmallestIndexes(HashMap<Integer, Integer> fitnessForGenes, int geneNumberLeft) {
        int[] result = new int[geneNumberLeft];
        for (int i = 0; i < geneNumberLeft ; i++) {
            int smallest = 2000000000;
            int key = fitnessForGenes.size()+1;
            for (int j = 0; j < fitnessForGenes.size(); j++) {
                if (fitnessForGenes.get(j) != null) {
                    if (fitnessForGenes.get(j) < smallest) {
                        smallest = fitnessForGenes.get(j);
                        key = j;
                    }
                }
            }
            result[i] = key;
            fitnessForGenes.remove(key);
        }
        return result;
    }

    @Override
    public int[][] crossOver(int[][] genes, int geneNumber) {
        int vmNumber = VMAdds.size();
        int[][] result = new int[geneNumber][vmNumber];

        Random rand = new Random();
        int geneOriginalNumber = genes.length;
        int geneCrossStart = 0;
        int geneCrossLength = 0;

        int i = 0;
        while (i < geneNumber - 1){
            int[] gene1 = genes[rand.nextInt() % geneOriginalNumber];
            int[] gene2 = genes[rand.nextInt() % geneOriginalNumber];
            geneCrossStart = rand.nextInt() % vmNumber;
            geneCrossLength = rand.nextInt() % (vmNumber - geneCrossStart);

            for (int j = 0; j < vmNumber; j++) {
                if (j >= geneCrossStart && j < geneCrossStart + geneCrossLength) {
                    result[i][j] = gene2[j];
                    result[i+1][j] = gene1[j];
                }
                else {
                    result[i][j] = gene1[j];
                    result[i+1][j] = gene2[j];
                }
            }

            i += 2;
        }

        return result;
    }

    @Override
    public void calculateOptimalBundle() {
        int[][] initialGenes = this.setGenesForAdds(100);
        int iter =0;
        while (iter < 1000) {
            int[][] selectedGenes = this.select(initialGenes,8);
            initialGenes = this.crossOver(selectedGenes,100);
        }
        
        int vmNumber = this.VMAdds.size();
        int serverNumber = 0;
        int day =0;
        ArrayList<IVirtualMachine> virtualMachines = new ArrayList<IVirtualMachine>();
        ArrayList<ArrayList<IServer>> optimalS = new ArrayList<ArrayList<IServer>>();
        ArrayList<IServer> serversOneDay = new ArrayList<IServer>();

        for (int i = 0; i < vmNumber ; i++) {
            if (this.optimalGene[i] == 0) {
                virtualMachines.add(VMAdds.get(i));
            }
            else {
                IServer server = this.optimalServers.get(serverNumber);
                serverNumber++;
                for (IVirtualMachine vm : virtualMachines) {
                    server.addVirtualMachine(vm);
                    vm.setPosition(server);
                }
                serversOneDay.add(server);
                virtualMachines = new ArrayList<IVirtualMachine>();
            }

            if (i == this.addsOnDay.get(day) - 1) {
                optimalS.add(serversOneDay);
                serversOneDay = new ArrayList<IServer>();
            }

        }

        int serverID = 0;
        for (int i = 0; i < optimalS.size(); i++) {
            ArrayList<IServer> sod = optimalS.get(i);
            String s = "";
            Integer q = sod.size();
            s = "(purchase, " + Integer.toString(q) + ")";
            this.log.add(s);
            for (IServer server0 : this.servers) {
                int serverQ = 0;
                for (IServer server1 : sod) {
                    if (server1.getModel() == server0.getModel()) {
                        serverQ++;
                        server1.setId(serverID);
                        this.buyServer(server1,serverID);
                        serverID++;
                    }
                }
                if (serverQ != 0) {
                    s = "(" + server0.getModel() + ", " + Integer.toString(serverQ) + ")";
                    this.log.add(s);
                }
            }


            s = "(migration, 0)";
            this.log.add(s);


            int initialIndex;
            if (i == 0) {
                initialIndex = 0;
            }
            else {
                initialIndex = this.addsOnDay.get(i-1);
            }
            int finalIndex = this.addsOnDay.get(i);
            while (initialIndex < finalIndex) {
                IVirtualMachine vm0 = this.VMAdds.get(initialIndex);
                s = "(" + Integer.toString(vm0.getPositionId()) + ", " + vm0.getPositionName() + ")";
                this.log.add(s);
            }
        }



    }

    @Override
    public void onlyBuyFirstServer() {

        int day = 0;
        int serverId = 0;
        IServer server = servers.get(0).clone();
        ArrayList<IServer> serverList = new ArrayList<IServer>();
        ArrayList<String> vmRecords = new ArrayList<String>();
        server.setId(serverId);
        for (int i = 0; i < VMAdds.size(); i++) {
            IVirtualMachine vm = VMAdds.get(i);

            if (!server.CanAdd(vm)) {
                serverList.add(server);
                serverId++;
                server = servers.get(0).clone();
            }

            if (i == addsOnDay.get(day)-1) {
                String s = "(purchase, " + Integer.toString(serverId+1) + ")";
                this.log.add(s);
                s = "(" + server.getModel() + ", " + Integer.toString(serverId +1) + ")";
                this.log.add(s);
                s = "(migration, 0)";
                this.log.add(s);
                this.log.addAll(vmRecords);
                vmRecords = new ArrayList<String>();
                day++;
            }

            server.addVirtualMachine(vm);
            vm.setPosition(server);
            String string = "(" + vm.getPositionId();
            if (vm.ifS()) {
                string += ", " + vm.getPositionName() + ")";
            } else {
                string += ")";
            }
            vmRecords.add(string);

        }
    }

    @Override
    public ArrayList<String> output() {
        //this.calculateOptimalBundle();
        this.assignedVM();
        this.onlyBuyFirstServer();
        return this.log;
    }
}
