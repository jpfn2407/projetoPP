package pt.ual.pp.projeto.models;

public class CarGenerator implements Runnable {
    private boolean running;
    private String modelID;
    private double minDay;
    private double maxDay;

    public CarGenerator(String modelID) {
        this.running = false;
        this.modelID = modelID;
    }

    @Override
    public void run() {

        while (this.running){
            System.out.println(this.modelID + " está a correr");
        }

    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public void setMinDay(double minDay) {
        this.minDay = minDay;
    }

    public void setMaxDay(double maxDay) {
        this.maxDay = maxDay;
    }

    public String getModelID() {
        return this.modelID;
    }

    public void startRunning(){
        this.running = true;
    }

    public void stopRunning(){
        this.running = false;
    }

}
