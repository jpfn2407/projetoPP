package pt.ual.pp.projeto.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Zone {
    private String zoneID;
    private Factory factory;
    private boolean useErlang;
    private ExecutorService linePool; //ThreadPool das linhas
    private HashMap<Long, ArrayList<Double>> lineWorkTimes = new HashMap<>(); //HashMap com o ID da thread,
                                                                    //e uma Collection<Double> com os tempos que ela trabalhou

    public Zone(String zoneID, Factory factory){
        this.zoneID = zoneID;
        this.factory = factory;
        this.useErlang = true;
    }

    public void setNumbOfLines(int numbOfLines){
        this.linePool = Executors.newFixedThreadPool(numbOfLines);
    }

    public void setUseErlang(boolean bool){
        this.useErlang = bool;
    }

    //Metodo principal. Para meter carros nas linhas e mandar para outras zonas depois.
    public synchronized void inputCar(Car car){
        long waitingTimeStart = System.nanoTime();

        this.linePool.submit(() -> {

            //double waitTime = car.getNextNotDone().getAverage();

            long workingTimeStart = System.nanoTime();

            double waitTime = 0.0;
            if(useErlang){
                waitTime = erlang(car.getNextNotDone().getAverage());
            } else {
                Random random = new Random();
                waitTime = Math.abs(random.nextGaussian() * car.getNextNotDone().getAverage() + car.getNextNotDone().getAverage());
            }

            if(this.lineWorkTimes.containsKey(Thread.currentThread().getId())){
                this.lineWorkTimes.get(Thread.currentThread().getId()).add(waitTime);
            } else {
                ArrayList<Double> averages = new ArrayList<>();
                averages.add(waitTime);
                this.lineWorkTimes.put(Thread.currentThread().getId(), averages);
            }

            try {
                TimeUnit.MICROSECONDS.sleep(Math.round(waitTime * 1_000_000));
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }

            car.addBuildTime(waitTime);
            car.getNextNotDone().markAsDone();


            long workingTimeEnd = System.nanoTime();
            long waitingTimeEnd = System.nanoTime();

            long workingTime = workingTimeEnd - workingTimeStart;
            long waitingTime = waitingTimeEnd - waitingTimeStart;

            long waitedTime = waitingTime - workingTime;

            car.addWaitTime((double) waitedTime / 1_000_000_000);

            if(car.isFinished()){
                this.factory.debug_addCarMade();
            } else {
                car.getNextNotDone().getZone().inputCar(car);
            }
            return;

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

    public synchronized void getLines(){
        ArrayList<Double> totalWorkTimeList = new ArrayList<>();
        for(Long key : this.lineWorkTimes.keySet()){
            double workTime = 0.0;
            for(Double value : this.lineWorkTimes.get(key)){
                workTime+=value;
            }
            totalWorkTimeList.add((workTime/factory.getSimTime())*100);
        }
        this.factory.setLinesAverages(this.zoneID, totalWorkTimeList);
    }

    //Usado para fechar a thread, só mesmo para assegurar que o programa não continua a correr.
    public synchronized void shutdownLines(){
        try {
            this.linePool.awaitTermination(1, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.linePool.shutdown();
        this.linePool.shutdownNow();
    }


}
