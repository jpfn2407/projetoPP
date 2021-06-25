package pt.ual.pp.projeto.models;

import pt.ual.pp.projeto.models.sequence.ModelSequence;
import pt.ual.pp.projeto.models.sequence.SequenceInfo;

public class Car {
    private String modelID;
    private double buildTime = 0.0;
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

    public double getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(double buildTime){
        this.buildTime = buildTime;
    }
}
