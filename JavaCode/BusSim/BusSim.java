public class BusSim {

    private PQ agenda;
    private double time;
    private int load;
    private Bus[] buses;

    public BusSim (int load, int numberOfRegularBuses, int numberOfExpressBuses) { //Main class passes in bus configuration and load
        this.load = load;
        Statistics.numberOfRegularBuses = numberOfRegularBuses;
        Statistics.numberOfExpressBuses = numberOfExpressBuses;
        time = 0;

        buses = new Bus[numberOfRegularBuses + numberOfExpressBuses];


        Stop rampB = new Stop(0);
        Stop previousStop = rampB;
        for (int i = 1; i < 30; i++) { //Stops are nodes which point to the stop ahead of them
            Stop nextStop = new Stop(i);
            previousStop.setNextStop(nextStop);
            previousStop = nextStop;
        }
        previousStop.setNextStop(rampB); //This allows for a circular relationship for the stops since the last stop points to the first

        int numberOfBuses = 0;
        int spaceBetweenRegularBuses = 30 / numberOfRegularBuses; //Makes sure that the buses are spaced out when initialized
        while (numberOfBuses < numberOfRegularBuses) {
            int stopNumber = numberOfBuses * spaceBetweenRegularBuses;
            Stop ptr = rampB;
            while (ptr.getStopNumber() != stopNumber) {
                ptr = ptr.getNextStop();
            }
            buses[numberOfBuses] = new Bus(ptr,false); //Each bus has an associated current stop
            numberOfBuses++;
        }

        if (numberOfExpressBuses != 0) {
            int expressBusesCreated = 0;
            int spaceBetweenExpressBuses = 30 / numberOfExpressBuses;
            while (expressBusesCreated < numberOfExpressBuses) {
                int stopNumber = expressBusesCreated * spaceBetweenExpressBuses;
                Stop ptr = rampB;
                while (ptr.getStopNumber() != stopNumber) {
                    ptr = ptr.getNextStop();
                }
                buses[numberOfBuses] = new Bus(ptr, false);
                expressBusesCreated++;
                numberOfBuses++;
            }
        }

        agenda = new PQ();
        Stop ptr = rampB;
        for (int i = 0; i < 30; i++) {
            agenda.add(new RiderEvent(this, ptr, 0.0),0.0); //Creates a rider event for each stop that schedules another rider event
            ptr = ptr.getNextStop();
        }
        for (int i = 0; i < (numberOfRegularBuses + numberOfExpressBuses); i++) {
            agenda.add(new BusEvent(this, buses[i], 0.00), 0.00); //Creates a bus event for every bus
        }
    }

    public void addToAgenda (Event e, double time) { //Allows agenda to be private while still letting other classes add to the overarching agenda
        agenda.add(e,time);
    }

    public int getLoad () {
        return load;
    }

    public void run () {  //Fundamental method of BusSim class, everything else is just set-up
        while (time < 10000) {
            agenda.remove().run();
            time = agenda.getCurrentTime();
        }
    }
}
