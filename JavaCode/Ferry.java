public class Ferry {

    private Q2 passengers;
    private int currentIsland;
    private int numberOfPassengers;
    private int Capacity = 60;

    public Ferry () {
        passengers = new Q2();
        numberOfPassengers = 0;
        currentIsland = 0;
    }

    public boolean addPassenger (Passenger p) {
        if (!isFull() && p != null) {
            passengers.add(p);
            numberOfPassengers++;
            FerrySim.totalFerryRiders++;
            return true;
        }
        else {
            return false;
        }
    }

    public int removePassengersAtIsland (int island) {
        int numberOfUnloaded = 0;
        Passenger[] stayingPassengers = new Passenger[60];
        Passenger p = (Passenger) passengers.remove();
        while (p != null) {
            if (p.getDropOffIsland() == currentIsland) {
                FerrySim.totalPeopleDelivered++;
                numberOfPassengers--;
                numberOfUnloaded++;
            }
            else {
                int i = 0;
                while (stayingPassengers[i] != null) {
                    i++;
                }
                stayingPassengers[i] = p;
            }
            p = (Passenger) passengers.remove();
        }
        for (Passenger passenger : stayingPassengers) {
            if (passenger != null) {
                passengers.add(passenger);
            }
            else {
                break;
            }
        }
        return numberOfUnloaded;
    }

    public boolean isFull() {
        return (numberOfPassengers == Capacity);
    }

    public void moveToNextIsland () {
        if (currentIsland != 2) {
            currentIsland++;
        }
        else {
            currentIsland = 0;
        }
        FerrySim.totalFerryTrips++;
    }

    public int getIsland () {
        return currentIsland;
    }

    public int getNumberOfPassengers () {
        return numberOfPassengers;
    }

}
