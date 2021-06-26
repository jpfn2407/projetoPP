package pt.ual.pp.projeto.models;

import pt.ual.pp.projeto.models.sequence.ModelSequence;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Zone {
    private String zoneID;
    private Factory factory;
    private ExecutorService linePool; //ThreadPool das linhas
    private HashMap<Long, Double> lineWorkTimes = new HashMap<>(); //HashMap com o ID da thread,
                                                                    //e uma Collection<Double> com os tempos que ela trabalhou.

    public Zone(String zoneID, Factory factory){
        this.zoneID = zoneID;
        this.factory = factory;
    }

    public void setNumbOfLines(int numbOfLines){
        this.linePool = Executors.newFixedThreadPool(numbOfLines);
    }

    //Metodo principal. Para meter carros nas linhas e mandar para outras zonas depois.
    public void inputCar(ModelSequence modelSequence){
        this.linePool.submit(() -> {
            if(!modelSequence.isFinished()){
                System.out.println(modelSequence.getCurrentUnfinishedNumber());
                if(modelSequence.getCurrentUnfinishedSequenceInfo() != null){
                    modelSequence.getCurrentUnfinishedSequenceInfo();
                    modelSequence.getCurrentUnfinishedNumber();
                    modelSequence.setAsDone(modelSequence.getCurrentUnfinishedNumber());
                }

            }
        });
    }

    public synchronized double erlang(double average){
        double lambda = 2.0 / average;
        double x = Math.random();
        return (Math.pow(lambda,2.0) * x * Math.exp((-lambda) * x)) / 1.0;
    }

    public String getZoneID() {
        return zoneID;
    }

    //Usado para fechar a thread, só mesmo para assegurar que o programa não continua a correr.
    public void shutdownLines(){
        try {
            this.linePool.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.linePool.shutdown();
        this.linePool.shutdownNow();
    }


}
