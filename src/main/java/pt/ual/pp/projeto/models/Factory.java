package pt.ual.pp.projeto.models;

import java.util.HashMap;

public class Factory {
    private Integer simulationTime = 0;
    private HashMap<String, CarGenerator> carGeneratorThreadsMap = new HashMap<>();

    public Factory() {

    }

    public void setSimulationTime(int time) {
        this.simulationTime = time;
    }

    public void startSimulation() {
        for (CarGenerator carGenerator : this.carGeneratorThreadsMap.values()){
            carGenerator.turnOnThread();
        }
    }

}
