package pt.ual.pp.projeto.models;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CarGenerator implements Runnable {
    private boolean running;
    private String modelID;
    private Factory factory;
    private double minDay; //Os valores destes dias já estão
    private double maxDay; //traduzidos para representarem tempo de simulaçao.

    public CarGenerator(String modelID, Factory factory) {
        this.running = false;
        this.factory = factory;
        this.modelID = modelID;
    }

    @Override
    public void run() {

        while (this.running){
            Random random = new Random();
            //double newOrderWaitTime = this.minDay + (this.maxDay - this.minDay) * random.nextDouble();
            double newOrderWaitTime = ThreadLocalRandom.current().nextDouble(this.minDay, this.maxDay);
            try {
                TimeUnit.MICROSECONDS.sleep(Math.round(newOrderWaitTime * 1_000_000));
            } catch (InterruptedException e) {
               // e.printStackTrace();
            }
            this.factory.buildNewCar(this.modelID);
        }

    }

    public String getModelID() {
        return this.modelID;
    }

    public void setMinDay(double minDay) {
        this.minDay = minDay;
    }

    public void setMaxDay(double maxDay) {
        this.maxDay = maxDay;
    }

    public void startRunning(){
        this.running = true;
    }

    public void stopRunning(){
        this.running = false;
    }

}
