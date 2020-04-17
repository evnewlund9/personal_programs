public class VehicleSorter {
    public static void main (String[] args) {
        Vehicle[] vehicles = new Vehicle[5];

        vehicles[0] = new Car("Ford Mustang GT", 425.5);
        vehicles[1] = new Car ("Toyota Prius", 235.7);
        vehicles[2] = new Boat ("Speedboat", 1023.4);
        vehicles[3] = new Boat ("Sailboat", 806.2);
        vehicles[4] = new Helicopter ("Apache", 678.3);

        sortVehicles(vehicles);

        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public static void sortVehicles (Vehicle[] list) {
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < list.length; i++) {
                if (list[i - 1].compareTo(list[i]) > 0) {
                    Vehicle temp = list[i];
                    list[i] = list[i - 1];
                    list[i - 1] = temp;
                    swapped = true;
                }
            }
        }
    }
}