package pt.ual.pp.projeto.models;

import pt.ual.pp.projeto.models.sequence.ModelSequence;
import pt.ual.pp.projeto.models.sequence.SequenceInfo;

import java.util.HashMap;

public class Factory {

    private Integer simulationTime = 0;
    private Double dayInSimulationTime = 0.0;
    private Double hourInSimulationTime = 0.0;

    private HashMap<String, Car> carMap = new HashMap<>(); //Todos os carros construidos
    private HashMap<String, Zone> zoneMap = new HashMap<>(); //Mapa com as zonas
    private HashMap<String, Thread> carGeneratorThreadMap = new HashMap<>(); //Threads que correm os geradores de carros
    private HashMap<String, CarGenerator> carGeneratorMap = new HashMap<>(); //Objetos geradores de carros, usados para começarem as threads
    private HashMap<String, ModelSequence> modelSequenceMap = new HashMap<>(); //Objetos que representam a tabela 4 do enunciado

    private Integer debug_numbCarsMade = 0;

    public Factory() {

        //Criação dos carros
        this.carGeneratorMap.put("1", new CarGenerator("1", this));
        this.carGeneratorMap.put("2", new CarGenerator("2", this));
        this.carGeneratorMap.put("3", new CarGenerator("3", this));

        //Criação da tabela de sequencias dos carros
        this.modelSequenceMap.put("1", new ModelSequence("1"));
        this.modelSequenceMap.put("2", new ModelSequence("2"));
        this.modelSequenceMap.put("3", new ModelSequence("3"));

        //Criação das zonas
        this.zoneMap.put("1", new Zone("1", this));
        this.zoneMap.put("2", new Zone("2", this));
        this.zoneMap.put("3", new Zone("3", this));
        this.zoneMap.put("4", new Zone("4", this));
        this.zoneMap.put("5", new Zone("5", this));
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

    public synchronized void buildNewCar(String modelID) {
        Car car = new Car(modelID, this.modelSequenceMap.get(modelID));
        this.carMap.put(modelID, car);
        this.zoneMap.get(car.getNextNotDone().getZone().getZoneID()).inputCar(car);
        notifyAll();
    }

    public void setCarGeneratorSetMinDay(String modelID, int minDay){
        this.carGeneratorMap.get(modelID).setMinDay(minDay * this.dayInSimulationTime);
    }

    public void setCarGeneratorSetMaxDay(String modelID, int maxDay){
        this.carGeneratorMap.get(modelID).setMaxDay(maxDay * this.dayInSimulationTime);
    }

    public void addSequenceInfo(String modelID, int sequenceOrderNumber, String zoneID, double average){
        this.modelSequenceMap.get(modelID).addSequenceInfo(sequenceOrderNumber, this.zoneMap.get(zoneID), average * this.hourInSimulationTime);
    }

    public void setNumberOfLines(String zoneID, int numberOfLines){
        this.zoneMap.get(zoneID).setNumbOfLines(numberOfLines);
    }

    //---------------------------------------------------------------------------------------------------------------
    public void startSimulation() {
        for (CarGenerator carGenerator : this.carGeneratorMap.values()){
            carGenerator.startRunning();
            Thread thread = new Thread(carGenerator);
            this.carGeneratorThreadMap.put(carGenerator.getModelID(), thread);
            //this.carGeneratorThreadMap.get(carGenerator.getModelID()).start();
            thread.start();
        }
    }

    public void stopSimulation() {
        for (CarGenerator carGenerator : this.carGeneratorMap.values()){
            carGenerator.stopRunning();
        }
        for(Thread thread : this.carGeneratorThreadMap.values()){
            thread.stop();
        }
        this.zoneMap.values().stream().forEach(zone -> zone.shutdownLines());
    }

    public void debug_PrintAllModelSequence(){
        for(ModelSequence modelSequence : this.modelSequenceMap.values()){
            modelSequence.debug_PrintAll();
            System.out.println();
        }
    }

    public synchronized void debug_addCarMade(){
        this.debug_numbCarsMade++;
    }

    public void debug_printNumbCarsMade(){
        System.out.println(this.debug_numbCarsMade);
    }

}
