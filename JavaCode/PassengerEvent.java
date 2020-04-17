import java.lang.Math;

public class PassengerEvent implements Event {

    private int island;
    private int currentTime;

    public PassengerEvent (int island, int currentTime) {
        this.island = island;
        this.currentTime = currentTime;
    }

    public void run () {
        int waitingPeriod = (int) (Math.random() * ((10 - 5) + 1)) + 5;
        Passenger p = new Passenger(island);
        FerrySim.islandLines[island].add(p);
        FerrySim.agenda.add(new PassengerEvent(island, currentTime + waitingPeriod), waitingPeriod);
        System.out.println("Passenger Event Island: " + island + ", Time is: " + currentTime + ", Next Passenger in: " + waitingPeriod + " seconds.");
    }
}
