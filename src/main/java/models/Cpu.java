package models;

import java.util.Objects;

public class Cpu {
    private String manufacturer;
    private String series;
    private String speed;
    private int cores;
    private double price;
    private int id;

    public Cpu(String manufacturer, String series, String speed, int cores, double price) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.speed = speed;
        this.cores = cores;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
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
        Cpu cpu = (Cpu) o;
        return cores == cpu.cores &&
                Double.compare(cpu.price, price) == 0 &&
                id == cpu.id &&
                Objects.equals(manufacturer, cpu.manufacturer) &&
                Objects.equals(series, cpu.series) &&
                Objects.equals(speed, cpu.speed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, series, speed, cores, price, id);
    }
}
