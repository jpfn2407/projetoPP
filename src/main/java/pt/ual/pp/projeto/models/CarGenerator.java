package pt.ual.pp.projeto.models;

public class CarGenerator implements Runnable {
    private boolean running;
    private String id;
    private double minDay;
    private double maxDay;

    public CarGenerator(String id, double minDay, double maxDay) {
        this.running = false;
        this.id = id;
        this.minDay = minDay;
        this.maxDay = maxDay;
    }

    @Override
    public void run() {

    }

    public void turnOnThread(){
        this.running = true;
    }

    public void turnOffThread(){
        this.running = false;
    }

}
