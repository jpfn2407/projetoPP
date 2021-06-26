package pt.ual.pp.projeto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import pt.ual.pp.projeto.models.Car;
import pt.ual.pp.projeto.models.Factory;
import pt.ual.pp.projeto.models.Zone;
import pt.ual.pp.projeto.models.sequence.ModelSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppTest {

    private Car car;
    private ExecutorService linepool = Executors.newFixedThreadPool(2);

    public AppTest() {
        Factory factory = new Factory();
        factory.setSimulationTime(3);
        Zone zone1 = new Zone("1", factory);
        zone1.setNumbOfLines(2);
        Zone zone2 = new Zone("2", factory);
        zone2.setNumbOfLines(2);
        ModelSequence carModelSequence = new ModelSequence("1");
        carModelSequence.addSequenceInfo(1, zone1, 1.1);
        carModelSequence.addSequenceInfo(2, zone2, 0.9);
        this.car = new Car("1", carModelSequence);
    }

    /*@Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }*/

    @Test
    public void carIsNotFinished() {
        assertFalse( this.car.isFinished() );
    }

    @Test
    public void carNextZone() {
        Assert.assertEquals(this.car.getNextNotDone().getZone().getZoneID(), "1");
    }

    @Test
    public void carMarkCurrentAsDone() {
        this.car.getNextNotDone().markAsDone();
        Assert.assertEquals(this.car.getNextNotDone().getZone().getZoneID(), "2");
    }

    @Test
    public void carMarkCurrentAsDoneWithThread() {
        this.linepool.submit(()->{
            double waitTime = erlang(car.getNextNotDone().getAverage());

            try {
                TimeUnit.MICROSECONDS.sleep(Math.round(waitTime * 1_000_000));
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            car.addBuildTime(waitTime);
            car.getNextNotDone().markAsDone();
            if(car.isFinished()){
                Assert.assertTrue(car.isFinished());
            } else {
                car.getNextNotDone().getZone().inputCar(car);
            }
        });

    }

    @Test
    public void carFinishWithThread() {
        this.linepool.submit(()->{
            double waitTime = erlang(car.getNextNotDone().getAverage());

            try {
                TimeUnit.MICROSECONDS.sleep(Math.round(waitTime * 1_000_000));
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            car.addBuildTime(waitTime);
            car.getNextNotDone().markAsDone();
            if(car.isFinished()){
                Assert.assertEquals(this.car.getNextNotDone().getZone().getZoneID(), "2");
            } else {
                Assert.assertEquals(this.car.getNextNotDone().getZone().getZoneID(), "2");
            }
        });

    }

    public synchronized double erlang(double average){
        double lambda = 2.0 / average;
        double x = Math.random();
        return (Math.pow(lambda,2.0) * x * Math.exp((-lambda) * x));
    }

    @Test
    public void carNotFinished() {
        this.car.getNextNotDone().markAsDone();
        assertFalse(this.car.isFinished());
    }

    @Test
    public void carIsFinished() {
        this.car.getNextNotDone().markAsDone();
        this.car.getNextNotDone().markAsDone();
        assertTrue(this.car.isFinished());
    }


}
