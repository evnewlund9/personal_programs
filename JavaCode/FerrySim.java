public class FerrySim {

    public static PQ agenda;
    public static int time;
    public static Ferry ship = new Ferry ();
    public static int totalPeopleInLine = 0;
    public static int totalPeopleDelivered = 0;
    public static int totalFerryTrips = 0;
    public static int totalFerryRiders = 0;
    public static Q2[] islandLines;

    public static void main (String[] args) {
        double time = 0.0;

        islandLines = new Q2[] {new Q2(), new Q2(), new Q2()};

        agenda = new PQ();
        agenda.add(new PassengerEvent(0,0), 0);
        agenda.add(new PassengerEvent(1,0), 0);
        agenda.add(new PassengerEvent(2,0), 0);
        agenda.add(new FerryEvent(0), 60);

        while (time < 1000) {
            agenda.remove().run();
            time = agenda.getCurrentTime();
        }
        System.out.println();
        System.out.println("The total people in line for the ferry was " + totalPeopleInLine + ".");
        System.out.println("Of those, " + totalPeopleDelivered + " passengers were successfully delivered.");
        System.out.println("The average number of people on the ferry was " + ((double) totalFerryRiders/totalFerryTrips) + " passengers per trip.");
    }
}
