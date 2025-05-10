package com.akimmin.top.car;

/**
 * класс предсвтавляющий сущьность в виде Car.
 */
public class Car {

  private String  manufacturerName ,
    carName,
    volume ,
    yearOfManufacturer,
    color ,
    carType;

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYearOfManufacturer() {
        return yearOfManufacturer;
    }

    public void setYearOfManufacturer(String yearOfManufacturer) {
        this.yearOfManufacturer = yearOfManufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
