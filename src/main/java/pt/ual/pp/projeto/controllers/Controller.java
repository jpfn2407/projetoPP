package pt.ual.pp.projeto.controllers;

import pt.ual.pp.projeto.models.CarGenerator;
import pt.ual.pp.projeto.models.Factory;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private Integer simulationTime;
    private Factory factory;

    public Controller() {
        this.factory = new Factory();

    }

    public void setSimulationTime(String simTime) {
        int time = Integer.parseInt(simTime);
        this.simulationTime = time;
        this.factory.setSimulationTime(time);
    }

    public void setCarGeneratorSetMinDay(String modelID, int minDay){
        this.factory.setCarGeneratorSetMinDay(modelID, minDay);
    }

    public void setCarGeneratorSetMaxDay(String modelID, int maxDay){
        this.factory.setCarGeneratorSetMaxDay(modelID, maxDay);
    }

    public void startSimulation() {
        this.factory.startSimulation();
        try {
            TimeUnit.SECONDS.sleep(this.simulationTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.factory.stopSimulation();
    }
}
