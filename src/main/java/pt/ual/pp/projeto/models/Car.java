package pt.ual.pp.projeto.models;

import pt.ual.pp.projeto.models.sequence.ModelSequence;
import pt.ual.pp.projeto.models.sequence.SequenceInfo;

import java.util.ArrayList;

public class Car {
    private String modelID;
    private ArrayList<Double> buildTime = new ArrayList<>();
    private ArrayList<Double> waitTimesList = new ArrayList<>();
    private ModelSequence modelSequence;

    public Car(String modelID, ModelSequence modelSequence) {
        this.modelID = modelID;
        this.modelSequence = modelSequence;
    }

    public String getModelID() {
        return modelID;
    }

    public boolean isFinished(){
        return this.modelSequence.isFinished();
    }

    public SequenceInfo getNextNotDone(){
        return this.modelSequence.getNextNotDone();
    }

    public double getBuildTime(){
        return this.buildTime.stream().mapToDouble(d -> d).sum();
    }

    public void addBuildTime(double buildTime){
        this.buildTime.add(buildTime);
    }

    public void addWaitTime(Double waitedTime){
        this.waitTimesList.add(waitedTime);
    }

    public Double getWaitedTimeAverage(){
        return this.waitTimesList.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public ModelSequence getModelSequence() {
        return modelSequence;
    }
}
