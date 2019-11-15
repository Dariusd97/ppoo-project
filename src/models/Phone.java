package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class Phone implements Product, Comparable<Phone>{
    private String name;
    private double price;
    private int storage;
    private String brand;
    private int availability;

    @Override
    public int compareTo(Phone phone) {
        int nameDifferent = name.compareToIgnoreCase(phone.name);
        return nameDifferent != 0 ? nameDifferent : brand.compareToIgnoreCase(phone.getBrand());
    }
}
