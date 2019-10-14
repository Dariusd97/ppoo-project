package models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laptop {
    private String name;
    private double price;
    private int ram;
    private String os;
    private int availability;
}
