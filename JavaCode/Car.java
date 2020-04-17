public class Car extends Vehicle {

    private String model;
    private double horsePower;

    public Car (String model, double horsePower) {
        this.model = model;
        this.horsePower = horsePower;
        numVehicles++;
    }

    public String getModel () {
        return model;
    }

    public double getHorsepower () {
        return horsePower;
    }

    public void movingForward () {
        System.out.println("Car is moving forward.");
    }

    public void movingBackward () {
        System.out.println("Car is moving backward.");
    }
}
