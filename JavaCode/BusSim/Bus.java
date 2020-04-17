public class Bus {

    private final int CAPACITY = 50;
    private boolean isExpressBus;
    private Q1Gen<Rider> riders;
    private Stop currentStop;
    private int numberOfRiders;

    public Bus (Stop currentStop, boolean isExpressBus) {
        this.currentStop = currentStop;
        this.isExpressBus = isExpressBus;
        riders = new Q1Gen<>();
        numberOfRiders = 0;
    }

    public int getNumberOfRiders () {
        return numberOfRiders;
    }

    public Stop getCurrentStop () {
        return currentStop;
    }

    public boolean addRider (Rider r, double currentTime) {
        if (! isFull() && r != null) {
            riders.add(r);
            r.setTimeOfPickup(currentTime); //Used for statistics
            numberOfRiders++;
            return true;
        }
        else {
            return false;
        }
    }

    public int removeRidersAtStop (double currentTime) {
        int unloadedRiders = 0;
        Rider[] stayingRiders = new Rider[CAPACITY]; //Creates an array to store riders that don't want to get off. It's length is the capacity because that's the maximum amount of riders who might want to stay.
        Rider r = riders.remove();
        while (r != null) {
            if (r.getDestinationStop() == currentStop.getStopNumber()) {
                numberOfRiders--;
                unloadedRiders++;
                Statistics.rideTimes.add((currentTime- r.getTimeOfPickup()));
            }
            else {
                int i = 0;
                while (stayingRiders[i] != null) { //Finds the first open spot to store the staying rider in the temporary array
                    i++;
                }
                stayingRiders[i] = r;
            }
            r = riders.remove();
        }
        for (Rider rider : stayingRiders) {
            if (rider != null) {
                riders.add(rider);
            }
            else {
                break; //Exits the loop once all the passengers who didn't exit the bus are added back into the stop queue
            }
        }
        return unloadedRiders;
    }

    public boolean isFull () {
        return (numberOfRiders == CAPACITY);
    }

    //Returns the time the bus takes to move stops
    public double moveToNextStop () {
        currentStop = currentStop.getNextStop();
        int timeTaken = 240;
        if (isExpressBus) { //If the bus is an express bus, it must traverse 3 more stops
            for (int i = 0; i < 3; i++) {
                currentStop = currentStop.getNextStop();
            }
            timeTaken = 960;
        }
        Statistics.totalBusRides++; //Express bus rides, even though they travel 4 stops at once, still only count towards 1 bus ride
        return timeTaken;
    }

    public boolean isExpressBus () {
        return isExpressBus;
    }
}
