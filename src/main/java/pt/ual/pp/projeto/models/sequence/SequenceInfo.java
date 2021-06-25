package pt.ual.pp.projeto.models.sequence;

import pt.ual.pp.projeto.models.Zone;

public class SequenceInfo {
    private Zone zone; //Representa qual é o ID da zona que o carro deve passar.
    private Double average; //Representa o tempo medio, já traduzido em tempo de
                            //simulação que um carro fica a ser construido na zona.
    private boolean isDone;

    public SequenceInfo(Zone zone, Double average) {
        this.zone = zone;
        this.average = average;
        this.isDone = false;
    }

    public Zone getZone() {
        return zone;
    }

    public Double getAverage() {
        return average;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
