//Evan Newlund, ID: 5473869, X-500: newlu004
import java.util.Random;

//Main driver program created to simplify code
public class Main {

    public static void main (String[] args) {
        Statistics.start();
        int totalSimulations = 0;
        int load = 60; //Peak Hours
        while (load <= 240) { //Models peak hours, middle hours, and off-peak hours
            for (int count = 0; count < 3; count++) { //Process is repeated 3 times for accuracy
                for (int i = 1; i < 15; i++) {    //These two lines ensure that every bus combination (of 105 total)
                    for (int j = 0; j < 14; j++) { //Is done once for each load value (and then repeated 2 more times, obviously)
                        if (i + j <= 14) {
                            BusSim busSim = new BusSim(load, i, j);
                            busSim.run();
                            Statistics.analyzeStatistics(i , j, count);
                            Statistics.resetIndividualStatistics();
                            totalSimulations++;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            switch (load) {
                case 60:
                    System.out.println("Statistics for rush hour: ");
                    break;
                case 120:
                    System.out.println("Statistics for mid-day: ");
                    break;
                case 240:
                    System.out.println("Statistics for early morning/late night: ");
                    break;
            }
            Statistics.printOverallStatistics();
            Statistics.resetOverallStatistics();
            load = load * 2;
        }
        System.out.println("Total simulations performed: " + totalSimulations);
    }
}
