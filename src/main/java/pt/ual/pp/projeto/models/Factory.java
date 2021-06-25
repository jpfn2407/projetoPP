package pt.ual.pp.projeto.models;

import java.util.HashMap;

public class Factory {
    private Integer simulationTime = 0;
    private HashMap<String, CarGenerator> carGeneratorMap = new HashMap<>();
    private HashMap<String, Thread> carGeneratorThreadMap = new HashMap<>();


    public Factory() {
        this.carGeneratorMap.put("1", new CarGenerator("1"));
    }

    public void setSimulationTime(int time) {
        this.simulationTime = time;
    }

    public void setCarGeneratorSetMinDay(String modelID, int minDay){
        this.carGeneratorMap.get(modelID).setMinDay(minDay);
    }

    public void setCarGeneratorSetMaxDay(String modelID, int maxDay){
        this.carGeneratorMap.get(modelID).setMaxDay(maxDay);
    }

    public void startSimulation() {

        for (CarGenerator carGenerator : this.carGeneratorMap.values()){
            carGenerator.startRunning();
            this.carGeneratorThreadMap.put(carGenerator.getModelID(), new Thread(carGenerator));
            this.carGeneratorThreadMap.get(carGenerator.getModelID()).start();
        }
    }

    public void stopSimulation() {
        for (CarGenerator carGenerator : this.carGeneratorMap.values()){
            carGenerator.stopRunning();
        }
        for(Thread thread : this.carGeneratorThreadMap.values()){
            thread.stop();
        }
    }
}
