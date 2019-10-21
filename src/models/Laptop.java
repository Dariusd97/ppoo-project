package models;

import lombok.*;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class Laptop implements Product, Comparable<Laptop>{
    private String name;
    private double price;
    private int ram;
    private String os;
    private int availability;

    @Override
    public int compareTo(Laptop laptop) {
        int nameDifferent = name.compareToIgnoreCase(laptop.name);
        return nameDifferent != 0 ? nameDifferent : os.compareToIgnoreCase(laptop.os);
    }
}
