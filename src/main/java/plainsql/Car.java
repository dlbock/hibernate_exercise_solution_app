package plainsql;

public class Car {
    private String name;
    private String numberPlate;

    public Car(String name, String numberPlate) {
        this.name = name;
        this.numberPlate = numberPlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
}
