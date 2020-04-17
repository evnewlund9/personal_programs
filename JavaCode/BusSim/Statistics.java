//Class that utilizes primarily static variables to track statistics
//Rather than create a new Statistics object for each of the 945 simulations, I decided to utilize public static variables that other methods can easily access and modify
//While the security of this class, then, is not as good as the other classes, this isn't an issue since it only tracks statistics
public class Statistics {

    //Reset before each simulation
    public static int numberOfRegularBuses = 0;
    public static int numberOfExpressBuses = 0;
    public static int totalPeopleInLine = 0;
    public static int totalPeopleDelivered = 0;
    public static int totalBusRides = 0;
    public static double totalTimeWaited = 0;
    public static int maxLine = 0;
    public static double maxWaitTime = 0.0;
    public static Q1Gen<Double> waitTimes = new Q1Gen<>();
    public static Q1Gen<Integer> busLoads = new Q1Gen<>();
    public static Q1Gen<Double> rideTimes = new Q1Gen<>();
    public static double averageWaitTime;
    public static int averageBusLoad;
    public static double averageRideTime;

    //Stats that aren't reset but represent statistics for the best and worst simulation for each load
    public static int[] lineStatsForLowest = new int[2];
    public static double maxWaitTimeForHighest = 0;
    private static double lowestWaitTime = 1000000000;
    private static int[] busConfigurationForLowest = new int[3];
    private static int avgBusLoadForLowest = 0;
    private static double avgRideTimeForLowest = 10000000;
    private static double maxWaitTimeForLowest = 0;
    private static int maxLineLengthForLowest = 0;
    private static double highestWaitTime = 0.0;
    private static int[] busConfigurationForHighest = new int[3];
    private static int[] lineStatsForHighest = new int[2];
    private static int avgBusLoadForHighest = 0;
    private static double avgRideTimeForHighest = 0;
    private static int maxLineLengthForHighest = 0;

    //3-D arrays that track the avg wait time and bus load for each of the 945 total simulations
    //Stats used for graphs in the report
    private static double[][][] overallWaitTimes = new double[14][][];
    private static int[][][] overallBusLoads = new int[14][][];

    //One-time method that initialises the arrays used for the report statistics
    public static void start () {
        for (int i = 0; i < 14; i++) {
            overallWaitTimes[i] = new double[14 - i][3];
            overallBusLoads[i] = new int[14 - i][3];
        }
    }

    //Performed after every simulation to create averages and set the maximum values
    public static void analyzeStatistics (int numberOfRegularBuses, int numberOfExpressBuses, int iterations) {
        int length1 = waitTimes.length();
        int originalLength1 = length1;
        while (length1 != 0) {
            double waitTime = waitTimes.remove();
            if (waitTime > maxWaitTime) {
                maxWaitTime = waitTime;
            }
            totalTimeWaited = totalTimeWaited + waitTime;
            length1 = waitTimes.length();
        }
        averageWaitTime = totalTimeWaited / originalLength1;

        int totalBusLoads = 0;
        int length2 = busLoads.length();
        int originalLength2 = length2;
        while (length2 != 0) {
            totalBusLoads = totalBusLoads + busLoads.remove();
            length2 = busLoads.length();
        }
        averageBusLoad = totalBusLoads / originalLength2;

        double totalRideTime = 0;
        int length3 = rideTimes.length();
        int originalLength3 = length3;
        while (length3 != 0) {
            totalRideTime = totalRideTime + rideTimes.remove();
            length3 = rideTimes.length();
        }
        averageRideTime = totalRideTime / originalLength3;

        if (averageWaitTime < lowestWaitTime) { //Some statistics associated with the simulation with the best and worst average wait time is recorded
            lowestWaitTime = averageWaitTime;
            busConfigurationForLowest[0] = numberOfRegularBuses;
            busConfigurationForLowest[1] = numberOfExpressBuses;
            busConfigurationForLowest[2] = totalBusRides;
            lineStatsForLowest[0] = totalPeopleInLine;
            lineStatsForLowest[1] = totalPeopleDelivered;
            avgBusLoadForLowest = averageBusLoad;
            avgRideTimeForLowest = averageRideTime;
            maxWaitTimeForLowest = maxWaitTime;
            maxLineLengthForLowest = maxLine;
        } else if (averageWaitTime > highestWaitTime) {
            highestWaitTime = averageWaitTime;
            lineStatsForHighest[0] = totalPeopleInLine;
            lineStatsForHighest[1] = totalPeopleDelivered;
            busConfigurationForHighest[0] = numberOfRegularBuses;
            busConfigurationForHighest[1] = numberOfExpressBuses;
            busConfigurationForHighest[2] = totalBusRides;
            avgBusLoadForHighest = averageBusLoad;
            avgRideTimeForHighest = averageRideTime;
            maxWaitTimeForHighest = maxWaitTime;
            maxLineLengthForHighest = maxLine;
        }
        overallWaitTimes[numberOfRegularBuses - 1][numberOfExpressBuses][iterations] = averageWaitTime;
        overallBusLoads[numberOfRegularBuses - 1][numberOfExpressBuses][iterations] = averageBusLoad;
    }

    //Also performed after every simulation (separated from the first for clarity)
    //Since all variables are static, they need to be manually reset rather than create a new Statistics object
    public static void resetIndividualStatistics () {
        totalPeopleInLine = 0;
        totalPeopleDelivered = 0;
        totalBusRides = 0;
        totalTimeWaited = 0.0;
        maxLine = 0;
        maxWaitTime = 0.0;
        waitTimes = new Q1Gen<>();
        busLoads = new Q1Gen<>();
        averageWaitTime = 0;
        averageBusLoad = 0;
    }

    //Resets the "overall" statistics that represent the maximums/minimums across an entire set of simulations instead of a single one
    public static void resetOverallStatistics () {
        lowestWaitTime = 1000000000;
        busConfigurationForLowest = new int[3];
        lineStatsForLowest = new int[2];
        avgBusLoadForLowest = 0;
        maxWaitTimeForLowest = 0;
        maxLineLengthForLowest = 0;
        highestWaitTime = 0.0;
        busConfigurationForHighest = new int[3];
        lineStatsForHighest = new int[2];
        avgBusLoadForHighest = 0;
        maxWaitTimeForHighest = 0;
        maxLineLengthForHighest = 0;
    }

    //Prints information about a set of simulations for a given load
    //Contains some commented-out code that was used to get data for the report (included for clarity)
    public static void printOverallStatistics () {
//        for (int i = 0; i < 14; i++) {
//            System.out.println(i);
//            for (double[] waitTimes : overallWaitTimes[i]) {
//                System.out.println(((waitTimes[0] + waitTimes[1] + waitTimes[2])/3));
//            }
//        }
//
//        for (int i = 0; i < 14; i++) {
//            for (int[] busLoads : overallBusLoads[i]) {
//                System.out.println(((100 * (double) busLoads[0] / 50) + (100 * (double) busLoads[1] / 50) + (100 * (double) busLoads[2] / 50)) / 3);
//            }
//        }

        System.out.println("The simulation with the best average wait time, " + lowestWaitTime + " seconds (" + (lowestWaitTime / 60) + " minutes), had a maximum wait time of " + maxWaitTimeForLowest + " seconds (" + (maxWaitTimeForLowest / 60) + " minutes) and a maximum line length of " + maxLineLengthForLowest + " people. ");
        System.out.println("Of the " + busConfigurationForLowest[0] + " regular buses and " + busConfigurationForLowest[1] + " express buses, the average bus load was " + avgBusLoadForLowest + " riders across " + busConfigurationForLowest[2] + " total bus rides.");
        System.out.println("Of the total " + lineStatsForLowest[0] + " potential bus riders (throughout all 30 of the stops), this simulation delivered " + lineStatsForLowest[1] + " of them with an average ride time of " + avgRideTimeForLowest + " seconds.");
        System.out.println("The simulation with the worst average wait time, " + highestWaitTime + " seconds (" + (highestWaitTime / 60) + " minutes), had a maximum wait time of  " + maxWaitTimeForHighest + " seconds (" + (maxWaitTimeForHighest / 60) + " minutes) and a maximum line length of " + maxLineLengthForHighest + " people.");
        System.out.println("Of the " + busConfigurationForHighest[0] + " regular buses and " + busConfigurationForHighest[1] + " express buses, the average bus load was " + avgBusLoadForHighest + " riders across " + busConfigurationForHighest[2] + " total bus rides.");
        System.out.println("Of the total " + lineStatsForHighest[0] + " potential bus riders (throughout all 30 of the stops), this simulation delivered " + lineStatsForHighest[1] + " of them with an average ride time of " + avgRideTimeForHighest + " seconds.");
        System.out.println();
    }
}
