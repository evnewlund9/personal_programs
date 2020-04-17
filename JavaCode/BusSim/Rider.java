public class Rider {

    private int destinationStopNumber;
    private double timeOfArrival; //Indicates the time of arrival at the boarding stop, not the time they get off the bus at the destination stop
    private double timeOfPickup;

    public Rider (int destinationStopNumber, double currentTime) {
        this.destinationStopNumber = destinationStopNumber;
        this.timeOfArrival = currentTime;
        Statistics.totalPeopleInLine++;
    }

    public int getDestinationStop () {
        return destinationStopNumber;
    }

    public double getTimeOfArrival () {
        return timeOfArrival;
    }

    public double getTimeOfPickup () {return timeOfPickup;}

    public void setTimeOfPickup (double timeOfPickup) {
        this.timeOfPickup = timeOfPickup;
    }
}
