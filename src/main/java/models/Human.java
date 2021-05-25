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
        //ToDo: проверка веса на отриц, надо? тесты испр
        this.weight = weight;
        this.requiredFloor = requiredFloor;
        this.dropped = false;
    }

    public void drop() {
        dropped = true;
    }

    @Override
    public String toString() {
        return Double.toString(weight);
    }
}
