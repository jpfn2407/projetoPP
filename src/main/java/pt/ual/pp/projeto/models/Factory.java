package pt.ual.pp.projeto.models;

import pt.ual.pp.projeto.models.sequence.ModelSequence;

import java.util.HashMap;

public class Factory {

    private Integer simulationTime = 0;
    private Double dayInSimulationTime = 0.0;
    private Double hourInSimulationTime = 0.0;

    private final HashMap<String, Car> carMap = new HashMap<>(); //Todos os carros construidos
    private final HashMap<String, Zone> zoneMap = new HashMap<>(); //Mapa com as zonas
    private final HashMap<String, Thread> carGeneratorThreadMap = new HashMap<>(); //Threads que correm os geradores de carros
    private final HashMap<String, CarGenerator> carGeneratorMap = new HashMap<>(); //Objetos geradores de carros, usados para começarem as threads
    private final HashMap<String, ModelSequence> modelSequenceMap = new HashMap<>(); //Objetos que representam a tabela 4 do enunciado

    public Factory() {

        //Criação dos carros
        this.carGeneratorMap.put("1", new CarGenerator("1", this));
        this.carGeneratorMap.put("2", new CarGenerator("2", this));
        this.carGeneratorMap.put("3", new CarGenerator("3", this));

        //Criação da tabela de sequencias dos carros
        this.modelSequenceMap.put("1", new ModelSequence("1"));
        this.modelSequenceMap.put("2", new ModelSequence("2"));
        this.modelSequenceMap.put("3", new ModelSequence("3"));

        //TODO Criação das zonas - Por default são criadas as zonas com 5 linhas, mas tem de se mudar para um numero escolhido pelo User
        this.zoneMap.put("1", new Zone("1",5));
        this.zoneMap.put("2", new Zone("2",5));
        this.zoneMap.put("3", new Zone("3",5));
        this.zoneMap.put("4", new Zone("4",5));
        this.zoneMap.put("5", new Zone("5",5));



    }

    public void setSimulationTime(int time) {
        this.simulationTime = time;
        this.dayInSimulationTime = time / 365.0;
        this.hourInSimulationTime = time / 8760.0;
    }

    public Integer getSimTime() {
        return simulationTime;
    }

    public Double getSimTimeInDays() {
        return dayInSimulationTime;
    }

    public Double getSimTimeInHours() {
        return hourInSimulationTime;
    }

    public void buildNewCar(String modelID) {
        this.carMap.put(modelID, new Car(modelID, this.modelSequenceMap.get(modelID)));
    }

    public void setCarGeneratorSetMinDay(String modelID, int minDay){
        this.carGeneratorMap.get(modelID).setMinDay(minDay * this.dayInSimulationTime);
    }

    public void setCarGeneratorSetMaxDay(String modelID, int maxDay){
        this.carGeneratorMap.get(modelID).setMaxDay(maxDay * this.dayInSimulationTime);
    }

    public void addSequenceInfo(String modelID, int sequenceOrderNumber, String zoneID, double average){
        this.modelSequenceMap.get(modelID).addSequenceInfo(sequenceOrderNumber, this.zoneMap.get(zoneID), average);
    }



    //---------------------------------------------------------------------------------------------------------------
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
