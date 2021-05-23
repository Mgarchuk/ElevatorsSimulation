package models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Human {
    private final double weight;
    private int requiredFloor;

    public Human(double weight, int requiredFloor){
        this.weight = weight;
        this.requiredFloor = requiredFloor;
    }
}
