package models;

import lombok.*;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Wither
public class Laptop implements Product{
    private String name;
    private double price;
    private int ram;
    private String os;
    private int availability;
}
