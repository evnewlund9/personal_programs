public class Boat extends Vehicle {

    private String model;
    private double horsePower;

    public Boat (String model, double horsePower) {
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
        System.out.println("Boat is moving forward.");
    }

    public void movingBackward () {
        System.out.println("Boat is moving backward.");
    }

    public void dropAnchor () {
        System.out.println("Dropping anchor.");
    }

    public void hoistAnchor () {
        System.out.println("Hoisting anchor.");
    }
}
