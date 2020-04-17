import java.util.Random;

public class Passenger {

    private int pickupIsland;
    private int destinationIsland;

    public Passenger (int pickupIsland) {
        this.pickupIsland = pickupIsland;
        Random rand  = new Random();
        int num = rand.nextInt(3);
        while (num == pickupIsland) {
            num = rand.nextInt(3);
        }
        destinationIsland = num;
        FerrySim.totalPeopleInLine++;
    }

    public int getPickupIsland () {
        return pickupIsland;
    }

    public int getDropOffIsland () {
        return destinationIsland;
    }
}
