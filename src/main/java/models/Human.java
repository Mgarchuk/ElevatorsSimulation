package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Human {

    private final double weight;
    private int requiredFloor;
    private boolean dropped;

    public Human(double weight, int requiredFloor) {

        if (weight < 0 || requiredFloor < 1) {
            throw new IllegalArgumentException();
        }

        this.weight = weight;
        this.requiredFloor = requiredFloor;
        this.dropped = false;
    }

    public void setRequiredFloor(int requiredFloor) {

        if (requiredFloor < 1) {
            throw new IllegalArgumentException();
        }
        this.requiredFloor = requiredFloor;
    }

    public void drop() {
        dropped = true;
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }
}
