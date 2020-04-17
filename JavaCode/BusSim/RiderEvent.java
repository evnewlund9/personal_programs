import java.util.Random;

public class RiderEvent implements Event{

    private BusSim currentSim;
    private Stop stop;
    private double time;
    private int averageArrival;

    public RiderEvent (BusSim currentSim, Stop stop, double currentTime) { //Must pass in current simulation so that class can access agenda and current load
        this.currentSim = currentSim;
        this.stop = stop;
        time = time + currentTime;
        averageArrival = currentSim.getLoad();
        if (stop.getStopNumber() == 0 || stop.getStopNumber() == 1 || stop.getStopNumber() == 14 || stop.getStopNumber() == 15 || stop.getStopNumber() == 16 || stop.getStopNumber() == 29) {
            averageArrival = averageArrival / 2; //Downtown stops are twice as popular as others
        }
    }

    public void run () {
        int[] eastStops = {1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15};
        int[] westStops = {16,16,17,18,19,20,21,22,23,24,25,26,27,28,29,29,0,0};
        Random random = new Random();

        int destinationStop;
        if (stop.getStopNumber() < 15) { //Procedure for east-bound riders
            int i = 0;
            destinationStop = eastStops[i];
            while (destinationStop <= stop.getStopNumber()) {
                i++;
                destinationStop = eastStops[i];
            }
            destinationStop = eastStops[(random.nextInt((17 - i) + 1) + i)]; //Makes sure that the rider chooses a stop in between their current stop and the furthest east stop (rather than going backwards)
        }
        else {
            if (stop.getStopNumber() == 29) { //Special case since 29 is the highest stop number
                destinationStop = 0;
            }
            else {
                int i = 0;
                destinationStop = westStops[i];
                while (destinationStop <= stop.getStopNumber()) {
                    i++;
                    destinationStop = westStops[i];
                }
                destinationStop = westStops[(random.nextInt((17 - i) + 1) + i)];
            }
        }
        Rider rider = new Rider(destinationStop, time);
        stop.add(rider);

        double[] arrivalPercents = {0.75,0.75,0.5,0.5,0.5,0.2,0.2,0.2,0.2,0,0,-0.2,-0.2,-0.2,-0.5,-0.5,-0.5,-0.75,-0.75};
        double nextArrival = averageArrival + (averageArrival * (arrivalPercents[random.nextInt(19)])); //Randomizes the arrival of riders at the stop

        RiderEvent nextRiderEvent = new RiderEvent(currentSim, stop, nextArrival);
        currentSim.addToAgenda(nextRiderEvent, nextArrival); //Adds another rider event to the agenda at the random time created above
    }
}
