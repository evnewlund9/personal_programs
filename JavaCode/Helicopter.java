public class Helicopter extends Vehicle {

    private String model;
    private double horsePower;

    public Helicopter (String model, double horsePower) {
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
        System.out.println("Helicopter is moving forward.");
    }

    public void movingBackward () {
        System.out.println("Helicopter is moving backward.");
    }

    public void moveUp () {
        System.out.println("Helicopter is moving up.");
    }

    public void moveDown () {
        System.out.println("Helicopter is moving down.");
    }
}
