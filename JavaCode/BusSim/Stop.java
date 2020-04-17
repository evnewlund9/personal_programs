public class Stop {

    private Q1Gen <Rider> ridersWaiting;
    private Stop next;
    private int stopNumber;

    //Stops are created in a linked-list style that allows for a circular data structure for the buses to peruse
    public Stop (int stopNumber) {
        this.stopNumber = stopNumber;
        next = null;
        ridersWaiting = new Q1Gen<>();
    }

    public void add (Rider r) {
        ridersWaiting.add(r);
        if (ridersWaiting.length() > Statistics.maxLine) {
            Statistics.maxLine = ridersWaiting.length();
        }
    }

    public int getStopNumber () {
        return stopNumber;
    }

    public Q1Gen <Rider> getRidersWaiting () {
        return ridersWaiting;
    }

    public Stop getNextStop () {
        return next;
    }

    public void setNextStop (Stop next) {
        this.next = next;
    }
}
