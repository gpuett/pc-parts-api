package models;

import java.util.Objects;

public class Motherboard {
    private String manufacturer;
    private int ramSlots;
    private String maxRam;
    private double price;
    private int id;

    public Motherboard(String manufacturer, int ramSlots, String maxRam, double price) {
        this.manufacturer = manufacturer;
        this.ramSlots = ramSlots;
        this.maxRam = maxRam;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public String getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(String maxRam) {
        this.maxRam = maxRam;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motherboard that = (Motherboard) o;
        return ramSlots == that.ramSlots &&
                Double.compare(that.price, price) == 0 &&
                id == that.id &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(maxRam, that.maxRam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, ramSlots, maxRam, price, id);
    }
}
