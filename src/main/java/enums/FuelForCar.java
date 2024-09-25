package enums;

public enum FuelForCar {

    DIESEL("//option[@value='Diesel']"),
    PETROL("//option[@value='Petrol']"),
    HYBRID("//option[@value='Hybrid']"),
    ELECTRIC("//option[@value='Electric']"),
    GAS("//option[@value='Gas']");

    private final String locator;

    FuelForCar(String locator)
    {
        this.locator=locator;
    }

    public String getLocator() {
        return locator;
    }
}









