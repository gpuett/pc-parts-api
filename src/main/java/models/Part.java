package models;

import java.util.Objects;

public class Part {
    private String manufacturer;
    private String series;
    private int price;
    private int id;

    public Part(String manufacturer, String series, int price) {
        this.manufacturer = manufacturer;
        this.series = series;
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


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
        Part part = (Part) o;
        return price == part.price &&
                id == part.id &&
                Objects.equals(manufacturer, part.manufacturer) &&
                Objects.equals(series, part.series);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, series, price, id);
    }
}
