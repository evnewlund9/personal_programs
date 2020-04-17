public class FerryEvent implements Event {

    private int island;
    private int currentTime;
    private Ferry ship = FerrySim.ship;

    public FerryEvent (int island) {
        this.island = island;
    }

    public void run () {
        int unloadedPassengers = ship.removePassengersAtIsland(island);

        int numberOfLoadedPassengers = 0;
        Passenger frontOfLine = (Passenger) FerrySim.islandLines[island].remove();
        boolean addedLastPassenger = ship.addPassenger(frontOfLine);
        while (frontOfLine != null && addedLastPassenger) {
            numberOfLoadedPassengers++;
            frontOfLine = (Passenger) FerrySim.islandLines[island].remove();
            addedLastPassenger = ship.addPassenger(frontOfLine);
        }

        ship.moveToNextIsland();
        FerrySim.agenda.add(new FerryEvent(ship.getIsland()), 60);

        System.out.println();
        System.out.println("Arrived at island " + island +".");
        System.out.println("Unloaded " + unloadedPassengers + " and loaded " + numberOfLoadedPassengers + " for a total occupancy of " + ship.getNumberOfPassengers() + " passengers.");
        System.out.println();
    }
}
