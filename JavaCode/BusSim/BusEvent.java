public class BusEvent implements Event {

    private BusSim currentSim;
    private Bus bus;
    private double time;

    public BusEvent (BusSim currentSim, Bus bus, double currentTime) {
        this.currentSim = currentSim;
        this.bus = bus;
        time = time + currentTime;
    }

    public void run () {
        Statistics.busLoads.add(bus.getNumberOfRiders());
        int unloadedRiders = bus.removeRidersAtStop(time);
        Q1Gen <Rider> line = bus.getCurrentStop().getRidersWaiting();
        Q1Gen <Rider> temp = new Q1Gen<>();
        Rider r = line.remove();
        boolean addedLastPassenger = bus.addRider(r,time);
        int loadedRiders = 0;
        while (addedLastPassenger) {
            if (!bus.isExpressBus() || ((r.getDestinationStop() - bus.getCurrentStop().getStopNumber()) % 4) == 0) { //Passengers won't get on an express bus unless it will stop at their destination stop
                Statistics.waitTimes.add((time - r.getTimeOfArrival()));
                loadedRiders++;
            }
            else {
                temp.add(r); //Temporary queue to store passengers who don't want to get on the bus
            }
            r = line.remove();
            addedLastPassenger = bus.addRider(r,time);
        }
        int length = temp.length();
        while (length != 0) {
            line.add(temp.remove());
            length = temp.length();
        }
        int timeBusWaited = (unloadedRiders * 2) + (loadedRiders * 3); //Passengers take 2 seconds to get off and 3 to get on
        if (timeBusWaited < 15) { //If passengers unload and load quicker than 15 seconds, the bus will stay for at least 15 seconds
            timeBusWaited = 15;
        }

        double timeToChangeStops = bus.moveToNextStop();
        double extraTime = timeBusWaited + timeToChangeStops;

        BusEvent nextBusEvent = new BusEvent(currentSim, bus, extraTime);
        currentSim.addToAgenda(nextBusEvent, extraTime); //Adds another bus event to the agenda
        Statistics.totalPeopleDelivered = Statistics.totalPeopleDelivered + unloadedRiders;
    }
}
