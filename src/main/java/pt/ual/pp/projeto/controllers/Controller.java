package pt.ual.pp.projeto.controllers;

import pt.ual.pp.projeto.models.CarGenerator;
import pt.ual.pp.projeto.models.Factory;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class Controller {

    private Integer simulationTime;
    private Factory factory;

    public Controller() {
        this.factory = new Factory();
    }

    public void setSimulationTime(String simTime) {
        int time = Integer.parseInt(simTime);
        this.factory.setSimulationTime(time);
    }

    public void startSimulation() {
        this.factory.startSimulation();
    }
}
