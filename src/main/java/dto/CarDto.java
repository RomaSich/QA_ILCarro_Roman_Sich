package dto;

import enums.FuelForCar;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class CarDto {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private FuelForCar fuel;
    private int seats;
    private String carClass;
    private double pricePerDay;
    private String about;
    private String city; //location


}
