package pt.ual.pp.projeto.controllers;

import pt.ual.pp.projeto.models.CarGenerator;
import pt.ual.pp.projeto.models.Factory;

import java.util.ArrayList;
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

    public void setCarGeneratorSetMinDay(String modelID, String minDay){
        int minDayInt = Integer.parseInt(minDay);

        this.factory.setCarGeneratorSetMinDay(modelID, minDayInt);
    }

    public void setCarGeneratorSetMaxDay(String modelID, String maxDay){
        int maxDayInt = Integer.parseInt(maxDay);

        this.factory.setCarGeneratorSetMaxDay(modelID, maxDayInt);
    }

    public void addSequenceInfo(String modelID, String sequenceOrderString, String zoneID, String averageString){
        if(!sequenceOrderString.isEmpty() && !zoneID.isEmpty() && !averageString.isEmpty()) {
            int sequenceOrderNumber = Integer.parseInt(sequenceOrderString);
            double average = Double.parseDouble(averageString);
            this.factory.addSequenceInfo(modelID, sequenceOrderNumber, zoneID, average);
        }
    }

    public void setZoneNumberOfLines(String zoneID, String numberOfLinesString){
        int numberOfLines = Integer.parseInt(numberOfLinesString);
        this.factory.setNumberOfLines(zoneID, numberOfLines);
    }

    public void startSimulation() {
        this.factory.startSimulation();

        //this.factory.debug_PrintAllModelSequence();

        try {
            TimeUnit.SECONDS.sleep(this.simulationTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.factory.debug_printNumbCarsMade();

        this.factory.stopSimulation();

    }

    public HashMap<String, Double> getModelAverageBuildTime(){
        return this.factory.getCarBuildAverage();
    }

}
